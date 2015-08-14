package com.rd.utility;

import java.util.ArrayList;
import java.util.List;

import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.vo.LoginUserVo;
import com.rd.vo.StudentVo;

public class Util {
	
	public static List<String> getDeptNames(List<Department> deptList) {
		List<String> deptNameList = new ArrayList<String>();
		for (Department dept : deptList) {
			deptNameList.add(dept.getName());
		}
		return deptNameList;
	}
	
	public static Student createStudentFromStudentVo(StudentVo studentVo,
			Department department) {
		Student student = new Student();
		student.setUserName(studentVo.getUserName());
		student.setPassword(studentVo.getPassword());
		student.setFirstName(studentVo.getFirstName());
		student.setLastName(studentVo.getLastName());
		student.setRollNo(Integer.valueOf(studentVo.getRollNo()));
		student.setCity(studentVo.getCity());
		student.setDepartment(department);
		student.setAddress(studentVo.getAddress());
		student.setAge(Integer.valueOf(studentVo.getAge()));
		student.setId(studentVo.getId());
		return student;
	}
	
	public static void createStudentVo(StudentVo studentVo, Student student) {
		studentVo.setUserName(student.getUserName());
		studentVo.setPassword(student.getPassword());
		studentVo.setFirstName(student.getFirstName());
		studentVo.setLastName(student.getLastName());
		studentVo.setAddress(student.getAddress());
		studentVo.setCity(student.getCity());
		studentVo.setAge(Integer.toString(student.getAge()));
		studentVo.setRollNo(Integer.toString(student.getRollNo()));
		studentVo.setDepartment(student.getDepartment().getName());
		studentVo.setId(student.getId());
	}
	
	public static Student createStudentFromLoginUserVo(LoginUserVo loginUserVo) {
		Student student = new Student();
		student.setUserName(loginUserVo.getUserName());
		student.setPassword(loginUserVo.getPassword());
		return student;
	}
	
	



}
