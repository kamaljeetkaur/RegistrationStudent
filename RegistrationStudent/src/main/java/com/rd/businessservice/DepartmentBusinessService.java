package com.rd.businessservice;

import java.util.List;

import com.rd.entity.Department;
import com.rd.entity.Student;

public interface DepartmentBusinessService {
	void createDepartment(Department dept);
	public Department fetchDepartment(String deptName);
	public Department fetchDepartmentById(Integer id);
	public void updateDepartment(Department dept);
	public void deleteDepartment(Department dept);
	public List<Department> getUpdatedListAfterDepartmentDeletion(Integer id);
	public List<Department> fetchAllDepartments();

}
