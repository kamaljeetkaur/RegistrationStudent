package com.rd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.rd.businessservice.DepartmentBusinessService;
import com.rd.businessservice.StudentBusinessService;
import com.rd.entity.Department;
import com.rd.vo.DepartmentVo;
import com.rd.vo.LoginUserVo;

public class DepartmentController extends MultiActionController{
	
	private StudentBusinessService studentBusinessService;
	private DepartmentBusinessService departmentBusinessService;
	
	public ModelAndView displayUpdateDepartment(HttpServletRequest request,
			HttpServletResponse response, DepartmentVo departmentVo) {
		Department dept = fetchDepartmentById(departmentVo.getId());
		departmentVo.setName(dept.getName());
		List<Department> deptList = departmentBusinessService
				.fetchAllDepartments();
		departmentVo.setCreate(false);
		ModelAndView deptView = new ModelAndView("department", "departmentVo",
				departmentVo);
		deptView.addObject("deptList", deptList);
		return deptView;
	}
	
	private Department fetchDepartmentById(Integer id) {
		return departmentBusinessService.fetchDepartmentById(id);
	}
	
	public ModelAndView createDepartment(HttpServletRequest request,
			HttpServletResponse response, DepartmentVo departmentVo) {
		ModelAndView deptView = new ModelAndView("department", "departmentVo",
				departmentVo);
		if (departmentVo.getName() == "") {
			List<Department> deptList = departmentBusinessService
					.fetchAllDepartments();
			deptView.addObject("errorDept", "Department name must not be empty");
			departmentVo.setCreate(true);
			deptView.addObject("deptList", deptList);

		} else {
			Department dept = new Department();
			dept.setName(departmentVo.getName());
			departmentBusinessService.createDepartment(dept);
			List<Department> deptList = departmentBusinessService
					.fetchAllDepartments();
			deptView.addObject("deptList", deptList);
			deptView.addObject("message", "Department successfully created");
		}
		return deptView;
	}
	
	public ModelAndView updateDepartment(HttpServletRequest request,
			HttpServletResponse response, DepartmentVo departmentVo) {
		ModelAndView deptView = new ModelAndView("department", "departmentVo",
				departmentVo);
		Department dept = new Department();
		dept.setId(departmentVo.getId());
		dept.setName(departmentVo.getName());
		departmentBusinessService.updateDepartment(dept);
		List<Department> deptList = departmentBusinessService
				.fetchAllDepartments();
		deptView.addObject("deptList", deptList);
		deptView.addObject("message", "Department successfully updated");
		return deptView;
	}
	
	public ModelAndView logOut(HttpServletRequest request,
			HttpServletResponse response) {
		LoginUserVo loginUserVo = new LoginUserVo();
		ModelAndView loginView = new ModelAndView("login", "loginUserVo",
				loginUserVo);
		return loginView;
	}
	
	public ModelAndView deleteDepartment(HttpServletRequest request,
			HttpServletResponse response,DepartmentVo departmentVo) {
		
		Department dept = new Department();
		dept.setId(departmentVo.getId());
		
		List<Department> deptList = departmentBusinessService
				.getUpdatedListAfterDepartmentDeletion(departmentVo.getId());
		ModelAndView deptView = new ModelAndView("department",
				"departmentVo", departmentVo);
		deptView.addObject("deptList", deptList);
		deptView.addObject("message", "Department deleted successfully");
		return deptView;
	}
	
	public void deptNameExists(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String deptName = request.getParameter("name");
		String message="";
		if(departmentBusinessService.deptNameExists(deptName)){
			message = "Department already exists.";
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
