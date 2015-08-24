package com.rd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.rd.businessservice.DepartmentBusinessService;
import com.rd.businessservice.StudentBusinessService;
import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.utility.Util;
import com.rd.vo.LoginUserVo;
import com.rd.vo.StudentVo;

public class StudentController extends MultiActionController {

	private StudentBusinessService studentBusinessService;
	private DepartmentBusinessService departmentBusinessService;

	public ModelAndView doSignUp(HttpServletRequest request,
			HttpServletResponse response, StudentVo studentVo) {

		Student student = Util.createStudentFromStudentVoForSignUp(studentVo);
		if (studentVo.getDepartment() != "NONE") {
			Department dept = fetchDepartment(studentVo);
			student.setDepartment(dept);
		}

		studentBusinessService.saveStudent(student);

		LoginUserVo loginUserVo = new LoginUserVo();
		ModelAndView loginView = new ModelAndView("login", "loginUserVo",
				loginUserVo);
		return loginView;
	}

	

	public ModelAndView displayUpdateStudent(HttpServletRequest request,
			HttpServletResponse response, StudentVo studentVo) {
		List<Department> deptList = departmentBusinessService
				.fetchAllDepartments();
		List<String> deptNameList = Util.getDeptNames(deptList);
		Student student = studentBusinessService.fetchStudentWithId(studentVo
				.getId());

		Util.createStudentVo(studentVo, student);

		ModelAndView updatestudentView = new ModelAndView("updatestudent",
				"studentVo", studentVo);
		updatestudentView.addObject("deptNameList", deptNameList);
		updatestudentView.addObject("student", student);
		return updatestudentView;
	}

	public ModelAndView updateStudent(HttpServletRequest request,
			HttpServletResponse response, StudentVo studentVo) {

		Department department = fetchDepartment(studentVo);
		Student student = Util
				.createStudentFromStudentVo(studentVo, department);
		studentBusinessService.updateStudent(student);

		ModelAndView updatestudentView = new ModelAndView("updatestudent",
				"studentVo", studentVo);
		updatestudentView.addObject("message", "Student successfully updated");
		return updatestudentView;
	}

	public ModelAndView deleteStudent(HttpServletRequest request,
			HttpServletResponse response, StudentVo studentVo) {
		List<Student> studentList = studentBusinessService
				.getUpdatedListAfterDeletion(studentVo.getId());
		ModelAndView studentreportView = new ModelAndView("studentreport",
				"studentVo", studentVo);
		studentreportView.addObject("studentList", studentList);
		studentreportView.addObject("message", "Student deleted successfully");
		return studentreportView;
	}

	private Department fetchDepartment(StudentVo studentVo) {
		return departmentBusinessService.fetchDepartment(studentVo
				.getDepartment());
	}
	
	public void userNameExists(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userName = request.getParameter("userName");
		String message="";
		if(studentBusinessService.userNameExists(userName)){
			message = "Username already exists.";
		}
		PrintWriter out = response.getWriter();
		out.write(message);
	}

	public void rollNumberExists(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String rollNumber = request.getParameter("rollNo");
		String message="";
		if(studentBusinessService.rollNumberExists(Integer.valueOf(rollNumber))){
			message = "Roll number already exists.";
		}
		PrintWriter out = response.getWriter();
		out.write(message);
	}

	public StudentBusinessService getStudentBusinessService() {
		return studentBusinessService;
	}

	public void setStudentBusinessService(
			StudentBusinessService studentBusinessService) {
		this.studentBusinessService = studentBusinessService;
	}

	public DepartmentBusinessService getDepartmentBusinessService() {
		return departmentBusinessService;
	}

	public void setDepartmentBusinessService(
			DepartmentBusinessService departmentBusinessService) {
		this.departmentBusinessService = departmentBusinessService;
	}
}
