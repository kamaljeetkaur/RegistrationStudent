package com.rd.dataservice;

import com.rd.entity.Student;
import com.rd.utilityClasses.HelperUtility;

public class TestStudentDao extends BaseDBUnit {
	
	StudentDao studentDao;

	public TestStudentDao(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studentDao = (StudentDaoImpl) context.getBean("studentDao");
	}
	
	
	public void testSaveStudent(){
		Student student = HelperUtility.createStudent1();
		studentDao.saveStudent(student);
		
	}
	/*public void testSaveStudent(){
		
	}*/
	
}
