package com.example.wifi.test;

import com.example.wifi.Functions;

import junit.framework.TestCase;

public class FunctionTest extends TestCase {

	public void testFunctionConstructor(){
		Functions function = new Functions();
		Functions function2 = new Functions("Function",2);
		assertNotNull(function);
		assertNotNull(function2);
	}
	public void testSetGetFunction(){
		Functions function1 = new Functions();
		function1.setName("name");
		function1.setImageNumber(0);
		assertEquals("name", function1.getName());
		assertEquals(0,function1.getImageNumber());
	}
}
