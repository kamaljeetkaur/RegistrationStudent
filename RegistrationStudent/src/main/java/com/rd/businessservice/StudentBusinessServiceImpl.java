package com.rd.businessservice;

import java.util.List;

import com.rd.dataservice.StudentDao;
import com.rd.entity.Department;
import com.rd.entity.Student;

public class StudentBusinessServiceImpl implements StudentBusinessService{
	
	private StudentDao studentDao;

	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public List<Student> fetchAllStudents() {
		return studentDao.fetchAllStudents();
	}

	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
	}

	public void deleteStudent(Student student) {
		studentDao.deleteStudent(student);
	}
	
	public Student fetchStudentWithId(Integer id){
		return studentDao.fetchStudentWithId(id);
	}
	
	public List<Student> getUpdatedListAfterDeletion(Integer id) {
		Student student = studentDao.fetchStudentWithId(id);
		student.getDepartment().getId();
		studentDao.deleteStudent(student);
		return studentDao.fetchAllStudents();
	}
	
	public void updateDepartmentInStudent(Integer id) {
	studentDao.updateDepartmentInStudent(id); 
	}



}
