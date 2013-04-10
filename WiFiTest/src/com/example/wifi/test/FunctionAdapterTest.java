package com.example.wifi.test;


import junit.framework.TestCase;


import com.example.wifi.FunctionAdapter;
public class FunctionAdapterTest extends TestCase {

	FunctionAdapter functionAdapter;


	public void testFunctionAdapterNotNull(){
		FunctionAdapter functionAdapter = new FunctionAdapter();
		assertNotNull(functionAdapter);
	}
	
}
