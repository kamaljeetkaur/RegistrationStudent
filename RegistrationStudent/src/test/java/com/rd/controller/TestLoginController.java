package com.rd.controller;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;

import java.io.IOException;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.rd.businessservice.DepartmentBusinessService;
import com.rd.businessservice.LoginBusinessService;
import com.rd.businessservice.StudentBusinessService;
import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.utilityClasses.HelperUtility;
import com.rd.validator.StudentValidator;
import com.rd.vo.DepartmentVo;
import com.rd.vo.LoginUserVo;
import com.rd.vo.StudentVo;

public class TestLoginController extends TestAbstractController<LoginController> {
	
	private Validator mockStudentValidator;
	private BindingResult errors;
	private StudentBusinessService mockStudentBusinessService;
	private LoginBusinessService mockLoginBusinessService;
	private DepartmentBusinessService mockDepartmentBusinessService;

	@Override
	protected void setDependencies() {
		mockStudentBusinessService = EasyMock.createMock(StudentBusinessService.class);
		mockLoginBusinessService = EasyMock.createMock(LoginBusinessService.class);
		mockDepartmentBusinessService = EasyMock.createMock(DepartmentBusinessService.class);
		mockStudentValidator = EasyMock.createMock(StudentValidator.class);
		controller.setDepartmentBusinessService(mockDepartmentBusinessService);
		controller.setStudentBusinessService(mockStudentBusinessService);
		controller.setLoginBusinessService(mockLoginBusinessService);
		controller.setStudentValidator(mockStudentValidator);
	}

	@Override
	protected LoginController createController() {
		return new LoginController();
	}
	
	@Test
	public void testgetLoginPage() throws IOException{
		LoginUserVo loginVo = new LoginUserVo();
		ModelAndView loginView = controller.getLoginPage(mockRequest, mockResponse,loginVo);
		assertEquals("login", loginView.getViewName());
	}
	
	@Test
	public void testDisplayDepartment() throws IOException{
		DepartmentVo deptVo = new DepartmentVo();
		List<Department> deptList = HelperUtility.createDepartmentList();
		expect(mockDepartmentBusinessService.fetchAllDepartments()).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentBusinessService);
		ModelAndView deptView = controller.displayDepartment(mockRequest, mockResponse,deptVo);
		EasyMock.verify(mockDepartmentBusinessService);
		
		assertEquals("department", deptView.getViewName());
		assertTrue(deptVo.isCreate());
	}
	
	@Test
	public void testDisplaySignUp() throws IOException{
		StudentVo studentVo = new StudentVo();
		List<Department> deptList = HelperUtility.createDepartmentList();
		expect(mockDepartmentBusinessService.fetchAllDepartments()).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentBusinessService);
		ModelAndView signupView = controller.displaySignUp(mockRequest, mockResponse,studentVo);
		EasyMock.verify(mockDepartmentBusinessService);
		
		assertEquals("signup", signupView.getViewName());
	}
	
	@Test
	public void testDoLogin_hasErrors() throws IOException{
		LoginUserVo loginVo = HelperUtility.createLoginUserVo();
		
		errors = createBindingErrors();
		errors.addError(new ObjectError("error", "error"));
		controller.setErrors(errors);
		mockStudentValidator.validate(loginVo, errors);
		EasyMock.replay(mockStudentValidator,mockLoginBusinessService);
		ModelAndView loginView = controller.doLogin(mockRequest, mockResponse,loginVo);
		EasyMock.verify(mockStudentValidator,mockLoginBusinessService);
		assertEquals("login", loginView.getViewName());
	}
	
	@Test
	public void testDoLogin_hasNoErrorsStudentExist() throws IOException{
		LoginUserVo loginVo = HelperUtility.createLoginUserVo();
		List<Student> studList = HelperUtility.createStudentList();
		errors = createBindingErrors();
		controller.setErrors(errors);
		mockStudentValidator.validate(isA(Object.class), isA(BindingResult.class));
		expect(mockLoginBusinessService.studentExist(EasyMock.isA(Student.class))).andReturn(true);
		expect(mockStudentBusinessService.fetchAllStudents()).andReturn(studList);
		
		EasyMock.replay(mockStudentValidator,mockLoginBusinessService,mockStudentBusinessService);
		ModelAndView studentreportView = controller.doLogin(mockRequest, mockResponse,loginVo);
		EasyMock.verify(mockStudentValidator,mockLoginBusinessService,mockStudentBusinessService);
	
		assertEquals("studentreport", studentreportView.getViewName());
	}
	
	@Test
	public void testDoLogin_hasNoErrorsStudentDoesNotExist() throws IOException{
		String message ="Username/Password does not exist";
		LoginUserVo loginVo = HelperUtility.createLoginUserVo();
		errors = createBindingErrors();
		controller.setErrors(errors);
		mockStudentValidator.validate(loginVo, errors);
		
		expect(mockLoginBusinessService.studentExist(isA(Student.class))).andReturn(false);
		
		ModelAndView loginView = controller.doLogin(mockRequest, mockResponse,loginVo);
		assertEquals("login", loginView.getViewName());
		assertEquals(message, loginView.getModelMap().get("Error"));
	}
	
	private BindingResult createBindingErrors() {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(mockRequest);
		binder.bind(mockRequest);
		errors = binder.getBindingResult();
		return errors;
	}

}

