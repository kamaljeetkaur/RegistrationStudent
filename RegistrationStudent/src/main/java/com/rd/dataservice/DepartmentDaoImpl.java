package com.rd.dataservice;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.rd.entity.Department;
import com.rd.utility.HibUtil;

public class DepartmentDaoImpl extends HibUtil implements DepartmentDao{
	
	public void createDepartment(Department dept) {
		getHibernateTemplate().save(dept);
	}

	public Department fetchDepartment(String deptName) {
		StringBuilder str = new StringBuilder(
				"select d from Department d where d.name = :deptName");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		query.setParameter("deptName", deptName);
		List<Department> deptList = query.list();
		closeSession(session);
		return deptList.get(0);
	}

	public List<Department> fetchAllDepartments() {
		StringBuilder str = new StringBuilder(
				"select department from Department department");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		List<Department> deptList = query.list();
		closeSession(session);
		return deptList;
	}
	
	public Department fetchDepartmentById(Integer id) {
		StringBuilder str = new StringBuilder(
				"select department from Department department where id=:id");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		query.setInteger("id", id);
		Department dept = (Department) query.list().get(0);
		closeSession(session);
		return dept;
	}
	
	public void updateDepartment(Department dept) {
		getHibernateTemplate().update(dept);

	}

	public void deleteDepartment(Department dept) {
		getHibernateTemplate().delete(dept);
	}

}
