package com.rd.businessservice;

import org.easymock.EasyMock;
import org.junit.Test;
import static org.easymock.EasyMock.expect;

import com.rd.dataservice.StudentDao;
import com.rd.entity.Student;
import com.rd.utilityClasses.HelperUtility;


public class TestLoginBusinessService extends TestAbstractBusinessService<LoginBusinessServiceImpl> {
	
	private StudentDao mockStudentDao;

	@Override
	protected void setDependencies() {
		mockStudentDao = EasyMock.createMock(StudentDao.class);
		businessService.setStudentDao(mockStudentDao);
	}

	@Override
	protected LoginBusinessServiceImpl createBusinessService() {
		return new LoginBusinessServiceImpl();
	}
	
	@Test
	public void testStudentExist() {
		Student student = HelperUtility.createStudent();
		expect(mockStudentDao.studentExist(student)).andReturn(true);
		
		EasyMock.replay();
		businessService.studentExist(student);
		EasyMock.verify();
	}
	
}
