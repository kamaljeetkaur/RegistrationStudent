package com.rd.dataservice;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rd.entity.Department;
import com.rd.entity.Student;
import com.rd.utility.HibUtil;

public class StudentDaoImpl extends HibUtil implements StudentDao {

	public void saveStudent(Student student) {
		getHibernateTemplate().save(student);
	}

	@SuppressWarnings("unchecked")
	public boolean studentExist(Student student) {
		String userName = student.getUserName();
		String password = student.getPassword();
		String parameters[] = { userName, password };
		StringBuilder str = new StringBuilder(
				"select st from Student st where st.userName=? and st.password=?");
		List<Student> listStudent = getHibernateTemplate().find(str.toString(),
				parameters);
		getHibernateTemplate().initialize(listStudent.get(0).getDepartment());

		if (listStudent.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Student> fetchAllStudents() {
		StringBuilder str = new StringBuilder(
				"select student from Student student join fetch   student.department");
		List<Student> listStudent = getHibernateTemplate().find(str.toString());
		return listStudent;
	}

	public void updateStudent(Student student) {
		getHibernateTemplate().update(student);
	}

	public void deleteStudent(Student student) {
		getHibernateTemplate().delete(student);
	}

	public Student fetchStudentWithId(Integer id) {
		StringBuilder str = new StringBuilder(
				"select student from Student student where id=?");
		return (Student) getHibernateTemplate().find(str.toString(), id).get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Student> fetchStudentsOfDept(Integer deptId) {
		StringBuilder str = new StringBuilder(
				"select student from Student student where id =?");
		return getHibernateTemplate().find(str.toString(), deptId);
	}

	public void updateDepartmentInStudent(final Integer deptId) {
		final StringBuilder str = new StringBuilder(
				"update Student st set st.department=null where st.department.id =?");
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(str.toString());
				query.setInteger("id", deptId);
				int i = query.executeUpdate();
				return null;
			}
		});

	}

	@SuppressWarnings("unchecked")
	public Boolean userNameExists(String userName) {
		StringBuilder str = new StringBuilder(
				"select st from Student st where st.userName=?");
		List<Student> listStudent = (List<Student>) getHibernateTemplate()
				.find(str.toString(), userName);
		if (! listStudent.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean rollNumberExists(Integer rollNumber) {
		StringBuilder str = new StringBuilder(
				"select st from Student st where st.rollNo=?");
		@SuppressWarnings("unchecked")
		List<Student> listStudent = (List<Student>) getHibernateTemplate()
				.find(str.toString(), rollNumber);
		if (! listStudent.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
