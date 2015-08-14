package com.rd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.rd.businessservice.DepartmentBusinessService;
import com.rd.businessservice.LoginBusinessService;
import com.rd.businessservice.StudentBusinessService;
import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.utility.Util;
import com.rd.vo.DepartmentVo;
import com.rd.vo.LoginUserVo;
import com.rd.vo.StudentVo;

public class LoginController extends MultiActionController{
	
	private Validator studentValidator;
	private BindingResult errors;
	private StudentBusinessService studentBusinessService;
	private LoginBusinessService loginBusinessService;
	private DepartmentBusinessService departmentBusinessService;
	
	public ModelAndView getLoginPage(HttpServletRequest request,
			HttpServletResponse response, LoginUserVo loginUserVo) {
		ModelAndView loginView = new ModelAndView("login", "loginUserVo",
				loginUserVo);
		return loginView;
	}
	
	public ModelAndView doLogin(HttpServletRequest request,
			HttpServletResponse response, LoginUserVo loginUserVo) {

		Student student = Util.createStudentFromLoginUserVo(loginUserVo);
		studentValidator.validate(loginUserVo, errors);
		List<Student> studentList = null;

		if (errors.hasErrors()) {
			ModelAndView loginView = new ModelAndView("login", "loginUserVo",
					loginUserVo);
			loginView.addObject("Error", "Username/Password must not be empty");
			return loginView;
		}
		if (loginBusinessService.studentExist(student)) {
			studentList = fetchAllStudents();
			ModelAndView studentreportView = new ModelAndView("studentreport");
			studentreportView.addObject("studentList", studentList);
			return studentreportView;
		} else {
			ModelAndView loginView = new ModelAndView("login", "loginUserVo",
					loginUserVo);
			loginView.addObject("Error", "Username/Password does not exist");
			return loginView;
		}
	}
	
	public ModelAndView displaySignUp(HttpServletRequest request,
			HttpServletResponse response, StudentVo studentVo) {
		List<Department> deptList = departmentBusinessService
				.fetchAllDepartments();
		List<String> deptNameList = Util.getDeptNames(deptList);
		ModelAndView signupView = new ModelAndView("signup", "studentVo",
				studentVo);
		signupView.addObject("deptNameList", deptNameList);
		return signupView;
	}
	
	public ModelAndView displayDepartment(HttpServletRequest request,
			HttpServletResponse response, DepartmentVo departmentVo) {
		List<Department> deptList = departmentBusinessService
				.fetchAllDepartments();
		departmentVo.setCreate(true);
		ModelAndView deptView = new ModelAndView("department", "departmentVo",
				departmentVo);
		deptView.addObject("deptList", deptList);
		return deptView;
	}
	
	private List<Student> fetchAllStudents() {
		return studentBusinessService.fetchAllStudents();
	}

	public Validator getStudentValidator() {
		return studentValidator;
	}

	public void setStudentValidator(Validator studentValidator) {
		this.studentValidator = studentValidator;
	}

	public BindingResult getErrors() {
		return errors;
	}

	public void setErrors(BindingResult errors) {
		this.errors = errors;
	}

	public StudentBusinessService getStudentBusinessService() {
		return studentBusinessService;
	}

	public void setStudentBusinessService(
			StudentBusinessService studentBusinessService) {
		this.studentBusinessService = studentBusinessService;
	}
	
	public LoginBusinessService getLoginBusinessService() {
		return loginBusinessService;
	}

	public void setLoginBusinessService(LoginBusinessService loginBusinessService) {
		this.loginBusinessService = loginBusinessService;
	}
	
	
	public DepartmentBusinessService getDepartmentBusinessService() {
		return departmentBusinessService;
	}

	public void setDepartmentBusinessService(
			DepartmentBusinessService departmentBusinessService) {
		this.departmentBusinessService = departmentBusinessService;
	}

	@Override
	protected void bind(HttpServletRequest request, Object command)
			throws Exception {
		ServletRequestDataBinder binder = null;
		binder = createBinder(request, command);
		binder.bind(request);
		errors = binder.getBindingResult();
	}
	

}
