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
import com.rd.utilityClasses.HelperUtility;
import com.rd.vo.DepartmentVo;

public class TestDepartmentController extends TestAbstractController<DepartmentController>{

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
	protected DepartmentController createController() {
		return new DepartmentController();
	}
	
	@Test
	public void testDisplayUpdateDepartment(){
		Department dept = HelperUtility.createDepartment();
		DepartmentVo deptVo = new DepartmentVo();
		deptVo.setId(1);
		List<Department> deptList = HelperUtility.createDepartmentList();
		
		expect(mockDepartmentBusinessService.fetchDepartmentById(EasyMock.isA(Integer.class))).andReturn(dept);
		expect(mockDepartmentBusinessService.fetchAllDepartments()).andReturn(deptList);
		
		assertNotSame(dept.getName(), deptVo.getName());
		
		EasyMock.replay(mockDepartmentBusinessService);
		ModelAndView deptView = controller.displayUpdateDepartment(mockRequest, mockResponse, deptVo);
		EasyMock.verify(mockDepartmentBusinessService);
		
		assertEquals(dept.getName(), deptVo.getName());
		assertFalse(deptVo.isCreate());
		assertEquals("department", deptView.getViewName());
		
	}
	
	@Test
	public void testCreateDepartment_deptNameEmpty(){
		String message = "Department name must not be empty";
		DepartmentVo deptVo = new DepartmentVo();
		deptVo.setId(1);
		deptVo.setName("");
		List<Department> deptList = HelperUtility.createDepartmentList();
		expect(mockDepartmentBusinessService.fetchAllDepartments()).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentBusinessService);
		ModelAndView deptView = controller.createDepartment(mockRequest, mockResponse, deptVo);
		EasyMock.verify(mockDepartmentBusinessService);
		
		assertTrue(deptVo.isCreate());
		assertEquals("department", deptView.getViewName());
		assertEquals(message, deptView.getModelMap().get("errorDept"));
		
	}
	
	@Test
	public void testCreateDepartment_deptNameNotEmpty(){
		String message = "Department successfully created";
		DepartmentVo deptVo = HelperUtility.createDepartmentVo();
		Department dept = HelperUtility.createDepartment();
		List<Department> deptList = HelperUtility.createDepartmentList();
		mockDepartmentBusinessService.createDepartment(EasyMock.isA(Department.class));
		expect(mockDepartmentBusinessService.fetchAllDepartments()).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentBusinessService);
		ModelAndView deptView = controller.createDepartment(mockRequest, mockResponse, deptVo);
		EasyMock.verify(mockDepartmentBusinessService);
		
		assertEquals("department", deptView.getViewName());
		assertEquals(dept.getName(), deptVo.getName());
		assertEquals(message, deptView.getModelMap().get("message"));
		
	}
	
	@Test
	public void testLogOut(){
		ModelAndView loginView = controller.logOut(mockRequest, mockResponse);
		assertEquals("login", loginView.getViewName());
	}
	
	@Test
	public void testUpdateDepartment(){
		String message = "Department successfully updated";
		DepartmentVo deptVo = HelperUtility.createDepartmentVo();
		Department dept = HelperUtility.createDepartment();
		List<Department> deptList = HelperUtility.createDepartmentList();
		
		mockDepartmentBusinessService.updateDepartment(EasyMock.isA(Department.class));
		expect(mockDepartmentBusinessService.fetchAllDepartments()).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentBusinessService);
		ModelAndView deptView = controller.updateDepartment(mockRequest, mockResponse,deptVo);
		EasyMock.verify(mockDepartmentBusinessService);
		
		assertEquals("department", deptView.getViewName());
		assertEquals(dept.getName(), deptVo.getName());
		assertEquals(message, deptView.getModelMap().get("message"));
		
	}
	
	@Test
	public void testDeleteDepartment(){
		String message = "Department deleted successfully";
		DepartmentVo deptVo = HelperUtility.createDepartmentVo();
		List<Department> deptList = HelperUtility.createDepartmentList();
		
		expect(mockDepartmentBusinessService.getUpdatedListAfterDepartmentDeletion(EasyMock.isA(Integer.class))).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentBusinessService);
		ModelAndView deptView = controller.deleteDepartment(mockRequest, mockResponse,deptVo);
		EasyMock.verify(mockDepartmentBusinessService);
		
		assertEquals("department", deptView.getViewName());
		assertEquals(message, deptView.getModelMap().get("message"));
		
	}
	
	@Test
	public void testDeptNameExists() throws IOException{
		String message = "Department already exists.";
		mockRequest.setParameter("name", "java");
		
		expect(mockDepartmentBusinessService.deptNameExists(EasyMock.isA(String.class))).andReturn(true);
		
		EasyMock.replay(mockDepartmentBusinessService);
		controller.deptNameExists(mockRequest, mockResponse);
		EasyMock.verify(mockDepartmentBusinessService);
		assertEquals(message, mockResponse.getContentAsString());
		
	}
	
}
