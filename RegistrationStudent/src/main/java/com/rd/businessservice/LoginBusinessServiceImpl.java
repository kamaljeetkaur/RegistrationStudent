package com.rd.businessservice;

import com.rd.dataservice.StudentDao;
import com.rd.entity.Student;

public class LoginBusinessServiceImpl implements LoginBusinessService{
	private StudentDao studentDao;
	
	public boolean studentExist(Student student) {
		return studentDao.studentExist(student);
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

}
