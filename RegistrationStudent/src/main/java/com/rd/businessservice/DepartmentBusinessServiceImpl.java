package com.rd.businessservice;

import java.util.List;

import com.rd.dataservice.DepartmentDao;
import com.rd.entity.Department;
import com.rd.entity.Student;

public class DepartmentBusinessServiceImpl implements DepartmentBusinessService {

	private DepartmentDao departmentDao;
	private StudentBusinessService studentBusinessService;
	
	public void createDepartment(Department dept) {
		departmentDao.createDepartment(dept);
	}

	public Department fetchDepartment(String deptName) {
		return departmentDao.fetchDepartment(deptName);
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public List<Department> fetchAllDepartments() {
		return departmentDao.fetchAllDepartments();
	}
	
	public Department fetchDepartmentById(Integer id) {
		return departmentDao.fetchDepartmentById(id);
	}

	public void updateDepartment(Department dept) {
		departmentDao.updateDepartment(dept);
	}
	
	public void deleteDepartment(Department dept){
		departmentDao.deleteDepartment(dept);
	}
	
	public List<Department> getUpdatedListAfterDepartmentDeletion(Integer id) {
		studentBusinessService.updateDepartmentInStudent(id); 
		Department dept = departmentDao.fetchDepartmentById(id);
		departmentDao.deleteDepartment(dept);
		return departmentDao.fetchAllDepartments();
	}

	public StudentBusinessService getStudentBusinessService() {
		return studentBusinessService;
	}

	public void setStudentBusinessService(
			StudentBusinessService studentBusinessService) {
		this.studentBusinessService = studentBusinessService;
	}

	public List<Student> getUpdatedListAfterDeletion(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
