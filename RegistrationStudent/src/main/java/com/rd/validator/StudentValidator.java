package com.rd.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rd.entity.Student;

public class StudentValidator implements Validator{
	
	public StudentValidator(){
		super();
	}

	public boolean supports(Class obj) {
		return Student.class.equals(obj);
	}

	public void validate(Object arg0, Errors errors) {
		System.out.println("reached validator");
		ValidationUtils.rejectIfEmpty(errors, "userName", "", "User Name is required");
		ValidationUtils.rejectIfEmpty(errors, "password", "", "password is required");
	}

}
