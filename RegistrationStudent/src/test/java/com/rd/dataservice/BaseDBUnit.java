package com.rd.dataservice;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Statement;
import java.util.Properties;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseDBUnit extends DBTestCase {
	
	protected IDatabaseTester databaseTester;
	protected IDataSet dataSet;
	ApplicationContext context;

	public BaseDBUnit(String name) {
		super(name);
	}

	protected IDatabaseConnection getNewConnection() throws Exception {
		return databaseTester.getConnection();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/student.xml"));
	}

	protected void setUp() throws Exception {
		
		setUpDatabaseConnection();
		
	   

		
		IDatabaseConnection connection =databaseTester.getConnection();
		
		DatabaseConfig config = connection.getConfig();
		//dbCfg.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, Boolean.FALSE);
		//config.setProperty(DatabaseConfig.PROPERTY_PRIMARY_KEY_FILTER, new MyPrimaryKeyFilter("username"));
		databaseTester.setDataSet(dataSet);
		databaseTester.getDataSet().getTable("student");
		context = new ClassPathXmlApplicationContext("classpath:TestApplicationContext.xml");
		try {
			//connection = databaseTester.getConnection();
			DatabaseOperation.REFRESH.execute(connection, dataSet);
			//DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} catch (Exception e) {
			System.err.println("error");
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	protected void tearDown() throws Exception {
		IDatabaseConnection connection = null;
		try {
			connection = databaseTester.getConnection();
			Statement s = connection.getConnection().createStatement();
			s.execute("set foreign_key_checks = 0");
			DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
			s.execute("set foreign_key_checks = 1");
		} catch (Exception e) {
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	void setUpDatabaseConnection() throws Exception {
		Properties prop = new Properties();
		InputStream input=null;
		String filename = "./testdb.properties";
		input=getClass().getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
			return;
		}
		prop.load(input);
		dataSet = getDataSet();
		databaseTester = new JdbcDatabaseTester(
				prop.getProperty("driverClass"),
				"jdbc:mysql://"+prop.getProperty("host")+":3306/"+prop.getProperty("dbname"),
				prop.getProperty("username"),
				prop.getProperty("password"));
		databaseTester.setDataSet(dataSet);
		
		 System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("driverClass")); 
	        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://"+prop.getProperty("host")+":3306/"+prop.getProperty("dbname")); 
	        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("username")); 
	        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("password"));
	        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "XXX"); 
	        
		input.close();
		
	}

}
