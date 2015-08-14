package com.rd.utility;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibUtil extends HibernateDaoSupport{
	
	public void closeSession(Session session) {
		//getSession().close();
	}
	
	public Session getNewSession() {
		return getSession();
	}
	
	// Hibernate Callback
	// @SuppressWarnings("unchecked")
		// public Student fetchStudentWithId(final Integer id) {
		// List<Student> studentList = new ArrayList<Student>();
		// final StringBuilder str = new
		// StringBuilder("select student from Student student where id=:id");
		// studentList = (List<Student>)getHibernateTemplate().execute(new
		// HibernateCallback(){
		// public Object doInHibernate(org.hibernate.Session session)throws
		// HibernateException, SQLException {
		// Query query = session.createQuery(str.toString());
		// query.setInteger("id", id);
		// return query.list();
		// }
		// });
		// return studentList.get(0);
		// }

}
