package com.rd.businessservice;

import static org.easymock.EasyMock.createControl;
import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;

import com.rd.dataservice.StudentDao;

public abstract class TestAbstractBusinessService<BUSINESSSERVICE> extends TestCase{
	
	protected BUSINESSSERVICE businessService;
	protected IMocksControl mocks;
	
	@Before
	public void setUp() throws Exception {
		businessService = createBusinessService();
		mocks= createControl();
		setDependencies();
	}
	
	@After
	public void tearDown() {
		businessService = null;
		mocks.reset();
	}
	
	protected abstract void setDependencies();
	protected abstract BUSINESSSERVICE createBusinessService();

}
