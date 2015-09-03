package com.rd.businessservice;

import static org.easymock.EasyMock.expect;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import com.rd.dataservice.StudentDao;
import com.rd.entity.Student;
import com.rd.utilityClasses.HelperUtility;

public class TestStudentBusinessService extends TestAbstractBusinessService<StudentBusinessServiceImpl>{
	
	private StudentDao mockStudentDao;
	
	protected void setDependencies() {
		mockStudentDao = EasyMock.createMock(StudentDao.class);
		businessService.setStudentDao(mockStudentDao);
	}

	protected StudentBusinessServiceImpl createBusinessService() {
		return new StudentBusinessServiceImpl();
	}
	
	@Test
	public void testSaveStudent(){
		Student student = HelperUtility.createStudent();
		mockStudentDao.saveStudent(student);
		
		EasyMock.replay(mockStudentDao);
		businessService.saveStudent(student);
		EasyMock.verify(mockStudentDao);
	}
	
	@Test
	public void testFetchStudentWithId(){
		Integer id = 1;
		Student student = HelperUtility.createStudent();
		expect(mockStudentDao.fetchStudentWithId(id)).andReturn(student);
		
		EasyMock.replay(mockStudentDao);
		Student actualStudent = businessService.fetchStudentWithId(id);
		EasyMock.verify(mockStudentDao);
		
		/*assertTrue(actualStudent.hashCode() == student.hashCode());
		assertTrue(actualStudent.equals(student));
		
		assertEquals(actualStudent.getId(), student.getId());
		assertEquals(actualStudent.getRollNo(), student.getRollNo());
		assertEquals(actualStudent.getUserName(), student.getUserName());
		assertEquals(actualStudent.getFirstName(), student.getFirstName());
		assertEquals(actualStudent.getLastName(), student.getLastName());
		assertEquals(actualStudent.getAge(), student.getAge());
		assertEquals(actualStudent.getCity(),student.getCity());
		assertEquals(actualStudent.getAddress(), student.getAddress());
		assertEquals(actualStudent.getDepartment().getId(),student.getDepartment().getId());
		assertEquals(actualStudent.getDepartment().getName(), student.getDepartment().getName());*/
		
	}

	@Test
	public void testFetchAllStudents(){
		List<Student> studentList = HelperUtility.createStudentList();
		expect(mockStudentDao.fetchAllStudents()).andReturn(studentList);
		
		EasyMock.replay(mockStudentDao);
		businessService.fetchAllStudents();
		EasyMock.verify(mockStudentDao);
		
	}
	
	@Test
	public void testUpdateStudent(){
		Student student = HelperUtility.createStudent();
		mockStudentDao.updateStudent(student);
		
		EasyMock.replay(mockStudentDao);
		businessService.updateStudent(student);
		EasyMock.verify(mockStudentDao);
	}
	
	@Test
	public void testDeleteStudent() {
		Student student = HelperUtility.createStudent();
		mockStudentDao.deleteStudent(student);
		
		EasyMock.replay(mockStudentDao);
		businessService.deleteStudent(student);
		EasyMock.verify(mockStudentDao);
	
	}
	
	@Test
	public void testUpdateDepartmentInStudent() {
		Integer id = 1;
		mockStudentDao.updateDepartmentInStudent(id);
		
		EasyMock.replay(mockStudentDao);
		businessService.updateDepartmentInStudent(id);
		EasyMock.verify(mockStudentDao);
	}
	
	@Test
	public void testUserNameExists() {
		String userName = "kamal"; 
		expect(mockStudentDao.userNameExists(userName)).andReturn(true);
		
		EasyMock.replay(mockStudentDao);
		businessService.userNameExists(userName);
		EasyMock.verify(mockStudentDao);
		
	}

	
	@Test
	public void testRollNumberExists() {
		Integer id = 1;
		expect(mockStudentDao.rollNumberExists(id)).andReturn(true);
		
		EasyMock.replay(mockStudentDao);
		businessService.rollNumberExists(id);
		EasyMock.verify(mockStudentDao);
		
	}
	
	@Test
	public void testGetUpdatedListAfterDeletion() {
		Integer id = 1;
		Student student = HelperUtility.createStudent();
		expect(mockStudentDao.fetchStudentWithId(id)).andReturn(student);
		mockStudentDao.deleteStudent(student);
		List<Student> studentList = HelperUtility.createStudentList();
		expect(mockStudentDao.fetchAllStudents()).andReturn(studentList);
		
		EasyMock.replay(mockStudentDao);
		businessService.getUpdatedListAfterDeletion(id);
		EasyMock.verify(mockStudentDao);
	}

}

