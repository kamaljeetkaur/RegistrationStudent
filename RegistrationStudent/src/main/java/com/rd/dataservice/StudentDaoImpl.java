package com.rd.dataservice;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.utility.HibUtil;

public class StudentDaoImpl extends HibUtil implements StudentDao {

	public void saveStudent(Student student) {
		getHibernateTemplate().save(student);
	}

	public boolean studentExist(Student student) {
		StringBuilder str = new StringBuilder(
				"select st from Student st where st.userName=:userName and st.password=:password");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		query.setString("userName", student.getUserName());
		query.setString("password", student.getPassword());
		List<Student> listStudent = query.list();
		closeSession(session);
		if (listStudent.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public List<Student> fetchAllStudents() {
		StringBuilder str = new StringBuilder(
				"select student from Student student");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		List<Student> studentList = query.list();
		closeSession(session);
		return studentList;
	}

	public void updateStudent(Student student) {
		getHibernateTemplate().update(student);
	}

	public void deleteStudent(Student student) {
		getHibernateTemplate().delete(student);
	}

	public Student fetchStudentWithId(Integer id) {
		StringBuilder str = new StringBuilder(
				"select student from Student student where id=:id");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		query.setInteger("id", id);
		List<Student> studentlist = query.list();
		closeSession(session);
		return studentlist.get(0);
	}

	public List<Student> fetchStudentsOfDept(Integer deptId) {
		StringBuilder str = new StringBuilder(
				"select student from Student student where id =:id");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		query.setInteger("id", deptId);
		List<Student> studentlist = query.list();
		closeSession(session);
		return studentlist;
	}

	public void updateDepartmentInStudent(Integer deptId) {
		StringBuilder str = new StringBuilder(
				"update Student st set st.department=null where st.department.id =:id");
		Session session = getNewSession();
		Query query = session.createQuery(str.toString());
		query.setInteger("id", deptId);
		int i = query.executeUpdate();
		closeSession(session);
	}

}
