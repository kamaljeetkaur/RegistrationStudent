package com.rd.utilityClasses;

import java.util.ArrayList;
import java.util.List;

import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.vo.DepartmentVo;
import com.rd.vo.LoginUserVo;
import com.rd.vo.StudentVo;

public class HelperUtility {
	
	public static Student createStudent(int id, int rollno, int age,String user, String fname, String lname){
		Student student = new Student();
		student.setId(id);
		student.setRollNo(rollno);
		student.setAge(age);
		student.setDepartment(createDepartment(222,"java"));
		student.setFirstName(fname);
		student.setLastName(lname);
		student.setUserName(user);
		student.setPassword("12345");
		student.setAddress("noida");;
		student.setCity("del");
		return student;
	}
	
	public static List<Student> createStudentList(){
		Student s1 = createStudent(1,11,33,"kamal","lotus","kaur");
		Student s2 = createStudent(2,22,29,"arv","arvind","gupta");
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(s1);
		studentList.add(s2);
		return studentList;
	}
	
	public static Department createDepartment(int id, String name){
		Department dept = new Department();
		dept.setId(id);
		dept.setName(name);
		return dept;
	}
	
	public static List<Department> createDepartmentList(){
		Department d1 = createDepartment(2,"java");
		Department d2 = createDepartment(3,"IT");
		List<Department> deptList = new ArrayList<Department>();
		deptList.add(d1);
		deptList.add(d2);
		return deptList;
 	}
	
	public static Department createDepartment(){
		Department dept = new Department();
		dept.setId(1);
		dept.setName("java");
		return dept;
	}
	
	public static Student createStudent(){
		Student student = new Student();
		student.setId(1);
		student.setRollNo(33);
		student.setAge(29);
		student.setDepartment(createDepartment(222,"java"));
		student.setFirstName("kamal");
		student.setLastName("kaur");
		student.setUserName("lotus");
		student.setPassword("12345");
		student.setAddress("noida");;
		student.setCity("del");
		return student;
	}
	
	public static Student createStudent1(){
		Student student = new Student();
		student.setId(1);
		student.setRollNo(33);
		student.setAge(29);
		student.setDepartment(null);
		student.setFirstName("kamal");
		student.setLastName("kaur");
		student.setUserName("lotus");
		student.setPassword("12345");
		student.setAddress("noida");;
		student.setCity("del");
		return student;
	}
	
	public static DepartmentVo createDepartmentVo(int id, String name){
		DepartmentVo deptVo = new DepartmentVo();
		deptVo.setId(id);
		deptVo.setName(name);
		return deptVo;
	}

	public static DepartmentVo createDepartmentVo(){
		DepartmentVo deptVo = new DepartmentVo();
		deptVo.setId(1);
		deptVo.setName("java");
		return deptVo;
	}
	
	public static List<DepartmentVo> createDepartmentVoList(){
		DepartmentVo d1 = createDepartmentVo(2,"java");
		DepartmentVo d2 = createDepartmentVo(3,"IT");
		List<DepartmentVo> deptList = new ArrayList<DepartmentVo>();
		deptList.add(d1);
		deptList.add(d2);
		return deptList;
 	}
	
	
	public static StudentVo createStudentVo(){
		StudentVo studentVo = new StudentVo();
		studentVo.setId(1);
		studentVo.setRollNo("33");
		studentVo.setAge("29");
		studentVo.setDepartment("java");
		studentVo.setFirstName("kamal");
		studentVo.setLastName("kaur");
		studentVo.setUserName("lotus");
		studentVo.setPassword("12345");
		studentVo.setAddress("noida");;
		studentVo.setCity("del");
		return studentVo;
	}
	
	public static StudentVo createStudentVo(String name){
		StudentVo studentVo = new StudentVo();
		studentVo.setId(1);
		studentVo.setRollNo("33");
		studentVo.setAge("29");
		studentVo.setDepartment(name);
		studentVo.setFirstName("kamal");
		studentVo.setLastName("kaur");
		studentVo.setUserName("lotus");
		studentVo.setPassword("12345");
		studentVo.setAddress("noida");;
		studentVo.setCity("del");
		return studentVo;
	}
	
	public static StudentVo createStudentVo(int id, String rollno, String age,String user, String fname, String lname){
		StudentVo studentVo = new StudentVo();
		studentVo.setId(id);
		studentVo.setRollNo(rollno);
		studentVo.setAge(age);
		studentVo.setDepartment("java");
		studentVo.setFirstName(fname);
		studentVo.setLastName(lname);
		studentVo.setUserName(user);
		studentVo.setPassword("12345");
		studentVo.setAddress("noida");;
		studentVo.setCity("del");
		return studentVo;
	}
	
	public static List<StudentVo> createStudentVoList(){
		StudentVo s1 = createStudentVo(1,"11","33","kamal","lotus","kaur");
		StudentVo s2 = createStudentVo(2,"22","29","arv","arvind","gupta");
		List<StudentVo> studentList = new ArrayList<StudentVo>();
		studentList.add(s1);
		studentList.add(s2);
		return studentList;
	}
	
	public static LoginUserVo createLoginUserVo(){
		LoginUserVo userVo = new LoginUserVo();
		userVo.setUserName("kamal");
		userVo.setPassword("12345");
		return userVo;
	}
}
