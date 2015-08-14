package com.rd.dataservice;

import java.util.List;

import com.rd.entity.Department;

public interface DepartmentDao {

	public void createDepartment(Department dept);

	public Department fetchDepartment(String deptName);

	public List<Department> fetchAllDepartments();

	public Department fetchDepartmentById(Integer id);

	public void updateDepartment(Department dept);

	public void deleteDepartment(Department dept);

}
