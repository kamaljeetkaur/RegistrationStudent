package com.rd.controller;

import static org.easymock.EasyMock.expect;

import java.io.IOException;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.rd.businessservice.DepartmentBusinessService;
import com.rd.businessservice.StudentBusinessService;
import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.utilityClasses.HelperUtility;
import com.rd.vo.StudentVo;

public class TestStudentController extends TestAbstractController<StudentController>{
	private StudentBusinessService mockStudentBusinessService;
	private DepartmentBusinessService mockDepartmentBusinessService;
	

	@Override
	protected void setDependencies() {
		mockStudentBusinessService = EasyMock.createMock(StudentBusinessService.class);
		mockDepartmentBusinessService = EasyMock.createMock(DepartmentBusinessService.class);
		controller.setDepartmentBusinessService(mockDepartmentBusinessService);
		controller.setStudentBusinessService(mockStudentBusinessService);
	}

	@Override
	protected StudentController createController() {
		return new StudentController();
	}
	
	@Test
	public void testUserNameExists() throws IOException{
			String message = "Username already exists.";
			mockRequest.setParameter("userName", "kamal");
			expect(mockStudentBusinessService.userNameExists(EasyMock.isA(String.class))).andReturn(true);
			EasyMock.replay(mockStudentBusinessService);
			controller.userNameExists(mockRequest, mockResponse);
			EasyMock.verify(mockStudentBusinessService);
			assertEquals(message, mockResponse.getContentAsString());
			
		}
	
	@Test
	public void testRollNumberExists() throws IOException{
			String message = "Roll number already exists.";
			mockRequest.setParameter("rollNo", "33");
			expect(mockStudentBusinessService.rollNumberExists(EasyMock.isA(Integer.class))).andReturn(true);
			EasyMock.replay(mockStudentBusinessService);
			controller.rollNumberExists(mockRequest, mockResponse);
			EasyMock.verify(mockStudentBusinessService);
			assertEquals(message, mockResponse.getContentAsString());
			
		}
	
	@Test
	public void testDoSignUp_deptNone(){
		StudentVo studentVo = HelperUtility.createStudentVo("NONE");
		Department dept = HelperUtility.createDepartment();
		mockStudentBusinessService.saveStudent(EasyMock.isA(Student.class));
		
		EasyMock.replay(mockDepartmentBusinessService,mockStudentBusinessService);
		ModelAndView loginView = controller.doSignUp(mockRequest, mockResponse, studentVo);
		EasyMock.verify(mockDepartmentBusinessService,mockStudentBusinessService);
		
		assertEquals("login", loginView.getViewName());
		
	}
	
	
	@Test
	public void testDoSignUp_deptNotNone(){
		StudentVo studentVo = HelperUtility.createStudentVo();
		Department dept = HelperUtility.createDepartment();
		expect(mockDepartmentBusinessService.fetchDepartment(EasyMock.isA(String.class))).andReturn(dept);
		mockStudentBusinessService.saveStudent(EasyMock.isA(Student.class));
		
		EasyMock.replay(mockDepartmentBusinessService,mockStudentBusinessService);
		ModelAndView loginView = controller.doSignUp(mockRequest, mockResponse, studentVo);
		EasyMock.verify(mockDepartmentBusinessService,mockStudentBusinessService);
		
		assertEquals("login", loginView.getViewName());
	}
	
	@Test
	public void testDisplayUpdateStudent(){
		StudentVo studentVo = HelperUtility.createStudentVo();
		studentVo.setId(1);
		Student student = HelperUtility.createStudent();
	//	Department dept = HelperUtility.createDepartment();
		List<Department> deptList = HelperUtility.createDepartmentList();
		
		expect(mockDepartmentBusinessService.fetchAllDepartments()).andReturn(deptList);
		expect(mockStudentBusinessService.fetchStudentWithId(EasyMock.isA(Integer.class))).andReturn(student);
		
		EasyMock.replay(mockDepartmentBusinessService,mockStudentBusinessService);
		ModelAndView updatestudentView = controller.displayUpdateStudent(mockRequest, mockResponse, studentVo);
		EasyMock.verify(mockDepartmentBusinessService,mockStudentBusinessService);
		
		assertEquals("updatestudent", updatestudentView.getViewName());
		assertEquals(studentVo.getUserName(), student.getUserName());
		assertEquals(studentVo.getFirstName(), student.getFirstName());
		assertEquals(studentVo.getLastName(), student.getLastName());
		assertEquals(studentVo.getCity(),student.getCity());
		assertEquals(studentVo.getAddress(), student.getAddress());
		assertEquals(studentVo.getDepartment(), student.getDepartment().getName());
		assertEquals(Integer.valueOf(studentVo.getAge()), Integer.valueOf(student.getAge()));
		assertEquals(Integer.valueOf(studentVo.getRollNo()), Integer.valueOf(student.getRollNo()));
		assertEquals(studentVo.getId(), Integer.valueOf(student.getId()));
	}
	
	@Test
	public void testUpdateStudent(){
		String message = "Student successfully updated";
		StudentVo studentVo = HelperUtility.createStudentVo();
		Department dept = HelperUtility.createDepartment();
		
		expect(mockDepartmentBusinessService.fetchDepartment(EasyMock.isA(String.class))).andReturn(dept);
		mockStudentBusinessService.updateStudent(EasyMock.isA(Student.class));
				
		EasyMock.replay(mockDepartmentBusinessService,mockStudentBusinessService);
		ModelAndView updatestudentView = controller.updateStudent(mockRequest, mockResponse, studentVo);
		EasyMock.verify(mockDepartmentBusinessService,mockStudentBusinessService);
		
		assertEquals("updatestudent", updatestudentView.getViewName());
		assertEquals(message, updatestudentView.getModelMap().get("message"));
		
	}
	
	@Test
	public void testDeleteStudent(){
		String message = "Student deleted successfully";
		StudentVo studentVo = new StudentVo();
		studentVo.setId(1);
		List<Student> studentList = HelperUtility.createStudentList();
		expect(mockStudentBusinessService.getUpdatedListAfterDeletion(EasyMock.isA(Integer.class))).andReturn(studentList);
				
		EasyMock.replay(mockStudentBusinessService);
		ModelAndView studentreportView = controller.deleteStudent(mockRequest, mockResponse, studentVo);
		EasyMock.verify(mockStudentBusinessService);
		
		assertEquals("studentreport", studentreportView.getViewName());
		assertEquals(message, studentreportView.getModelMap().get("message"));
		
	}
}
