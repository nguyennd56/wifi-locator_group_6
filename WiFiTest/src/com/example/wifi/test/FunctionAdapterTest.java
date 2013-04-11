package com.example.wifi.test;


import junit.framework.TestCase;


import android.test.ActivityInstrumentationTestCase2;

import com.example.wifi.FunctionAdapter;
public class FunctionAdapterTest extends ActivityInstrumentationTestCase2 {

	public FunctionAdapterTest() {
		super("test", FunctionAdapter.class);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
	}

	
	FunctionAdapter functionAdapter;


	public void testFunctionAdapterNotNull(){
		FunctionAdapter functionAdapter = new FunctionAdapter();
		assertNotNull(functionAdapter);
	}
	
}
