package com.rd.businessservice;

import java.util.List;

import com.rd.entity.Department;
import com.rd.entity.Student;

public interface StudentBusinessService {

	void saveStudent(Student student);

	public List<Student> fetchAllStudents();

	public void updateStudent(Student student);

	public void deleteStudent(Student student);

	public Student fetchStudentWithId(Integer id);

	public void updateDepartmentInStudent(Integer id);

	public List<Student> getUpdatedListAfterDeletion(Integer id);

	public Boolean userNameExists(String userName);

	public Boolean rollNumberExists(Integer rollNumber);

}
