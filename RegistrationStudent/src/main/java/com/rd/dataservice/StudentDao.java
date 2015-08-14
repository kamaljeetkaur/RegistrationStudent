package com.rd.dataservice;

import java.util.List;

import com.rd.entity.Department;
import com.rd.entity.Student;

public interface StudentDao {

	public void saveStudent(Student student);

	public boolean studentExist(Student student);

	public List<Student> fetchAllStudents();

	public void updateStudent(Student student);

	public void deleteStudent(Student student);

	public Student fetchStudentWithId(Integer id);

	public List<Student> fetchStudentsOfDept(Integer deptId);

	public void updateDepartmentInStudent(Integer deptId);

}
