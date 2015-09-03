package com.rd.controller;

import junit.framework.TestCase;
import static org.easymock.EasyMock.createControl;

import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public abstract class TestAbstractController<CONTROLLER>  extends TestCase{
	protected CONTROLLER controller;
	protected IMocksControl mocks;
	protected MockHttpServletRequest mockRequest;
	protected MockHttpServletResponse mockResponse;
	
	protected abstract void setDependencies();
	protected abstract CONTROLLER createController();
	
	@Before
	public void setUp() throws Exception{
		controller = createController();
		mocks = createControl();
		mockRequest = new MockHttpServletRequest();
		mockResponse = new MockHttpServletResponse();
		setDependencies();
	}
	
	@After
	public void tearDown() {
		controller = null;
		mocks.reset();
	}
	
}
