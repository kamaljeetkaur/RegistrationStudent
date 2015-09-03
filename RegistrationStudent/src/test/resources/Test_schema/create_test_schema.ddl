
# Create the database
drop Database if exists amstestdb;
create database amstest DEFAULT CHARACTER SET = utf8;

# Start using the database
use amstest;

create table department(
	dept_id int(10) NOT NULL AUTO_INCREMENT,
	dept_name varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY(dept_id)
) ENGINE=InnoDB;


create table student (
	stud_id int(10) NOT NULL AUTO_INCREMENT,
	username varchar(255) NOT NULL UNIQUE,
	password varchar(255) NOT NULL UNIQUE,
	rollno int(10) NOT NULL UNIQUE,
	firstname varchar(255),
	lastname varchar(255),
	address varchar(255),
	city varchar(255),
	age int(10),
	dept_id int(10),
	PRIMARY KEY (stud_id),
	FOREIGN KEY(dept_id) REFERENCES department(dept_id)
	) ENGINE=InnoDB;


