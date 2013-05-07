package com.example.wifilocator.testActivity;

import com.example.wifilocator.activity.Tab;
import android.test.ActivityInstrumentationTestCase2;

public class TabTest extends ActivityInstrumentationTestCase2<Tab>{

	private Tab tab;
	
	
	@SuppressWarnings("deprecation")
	public TabTest(String pkg, Class<Tab> activityClass) {
		super(pkg, activityClass);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		tab = getActivity();
	}

	public void testNotNull(){
		assertNotNull(tab);
	}
	

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	
	
}
