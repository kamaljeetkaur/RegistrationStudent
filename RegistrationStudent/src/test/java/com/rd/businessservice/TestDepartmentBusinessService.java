package com.rd.businessservice;

import static org.easymock.EasyMock.expect;

import java.util.List;

import org.easymock.EasyMock;

import com.rd.dataservice.DepartmentDao;
import com.rd.entity.Department;
import com.rd.utilityClasses.HelperUtility;

public class TestDepartmentBusinessService extends TestAbstractBusinessService<DepartmentBusinessServiceImpl>{

	private DepartmentDao mockDepartmentDao;
	private StudentBusinessService mockStudentBusinessService;
	
	@Override
	protected void setDependencies() {
		mockDepartmentDao = EasyMock.createMock(DepartmentDao.class);
		mockStudentBusinessService = EasyMock.createMock(StudentBusinessService.class);
		businessService.setDepartmentDao(mockDepartmentDao);
		businessService.setStudentBusinessService(mockStudentBusinessService);
	}

	@Override
	protected DepartmentBusinessServiceImpl createBusinessService() {
		return new DepartmentBusinessServiceImpl();
	}
	
	public void testCreateDepartment() {
		Department dept = HelperUtility.createDepartment();
		mockDepartmentDao.createDepartment(dept);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.createDepartment(dept);
		EasyMock.verify(mockDepartmentDao);
	}
	
	public void testUpdateDepartment() {
		Department dept = HelperUtility.createDepartment();
		mockDepartmentDao.updateDepartment(dept);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.updateDepartment(dept);
		EasyMock.verify(mockDepartmentDao);
	}
	
	public void testDeleteDepartment() {
		Department dept = HelperUtility.createDepartment();
		mockDepartmentDao.deleteDepartment(dept);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.deleteDepartment(dept);
		EasyMock.verify(mockDepartmentDao);
	}
	
	public void testFetchDepartment() {
		String name = "java";
		Department dept = HelperUtility.createDepartment();
		expect(mockDepartmentDao.fetchDepartment(name)).andReturn(dept);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.fetchDepartment(name);
		EasyMock.verify(mockDepartmentDao);
	}
	
	public void testFetchAllDepartments() {
		List<Department> deptList = HelperUtility.createDepartmentList();
		expect(mockDepartmentDao.fetchAllDepartments()).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.fetchAllDepartments();
		EasyMock.verify(mockDepartmentDao);
	}
	
	public void testFetchDepartmentById() {
		Integer id = 1;
		Department dept = HelperUtility.createDepartment();
		expect(mockDepartmentDao.fetchDepartmentById(id)).andReturn(dept);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.fetchDepartmentById(id);
		EasyMock.verify(mockDepartmentDao);
	}
	
	public void testDeptNameExists() {
		String name = "java";
		expect(mockDepartmentDao.deptNameExists(name)).andReturn(true);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.deptNameExists(name);
		EasyMock.verify(mockDepartmentDao);
	}
	
	public void testGetUpdatedListAfterDepartmentDeletion() {
		List<Department> deptList = HelperUtility.createDepartmentList();
		Department dept = HelperUtility.createDepartment();
		Integer id = 1;
		mockStudentBusinessService.updateDepartmentInStudent(id);
		expect(mockDepartmentDao.fetchDepartmentById(id)).andReturn(dept);
		mockDepartmentDao.deleteDepartment(dept);
		expect(mockDepartmentDao.fetchAllDepartments()).andReturn(deptList);
		
		EasyMock.replay(mockDepartmentDao);
		businessService.getUpdatedListAfterDepartmentDeletion(id);
		EasyMock.verify(mockDepartmentDao);
	}

}
