package com.example.wifilocator.testActivity;

import com.example.wifilocator.activity.StartUp;
import com.example.wifilocator_rebuild.R;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ProgressBar;

public class StartUpTest extends ActivityInstrumentationTestCase2<StartUp>{

	private StartUp startUp;
	private ProgressBar progressBar;
	
	
	
	@SuppressWarnings("deprecation")
	public StartUpTest(String pkg, Class<StartUp> activityClass) {
		super(pkg, activityClass);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		startUp = getActivity();
		progressBar = (ProgressBar)startUp.findViewById(R.id.progressBar1);
	}

	
	public void testNotNull(){
		assertNotNull(startUp);
		assertNotNull(progressBar);
	}
	

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
}
