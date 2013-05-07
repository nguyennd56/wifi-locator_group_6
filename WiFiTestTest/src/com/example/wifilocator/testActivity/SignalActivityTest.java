package com.example.wifilocator.testActivity;

import com.example.wifilocator.activity.SignalsActivity;
import com.example.wifilocator_rebuild.R;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class SignalActivityTest extends ActivityInstrumentationTestCase2<SignalsActivity>{

	private SignalsActivity signalsActivity;
	private TextView location;
	
	@SuppressWarnings("deprecation")
	public SignalActivityTest(String pkg, Class<SignalsActivity> activityClass) {
		super(pkg, activityClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		signalsActivity = getActivity();
		location = (TextView)signalsActivity.findViewById(R.id.location_tv);
		location.setText("SignalActivityTest");
	
	}
	
	public void testNotNull(){
		assertNotNull(signalsActivity);
		assertNotNull(location);
	}
	
	public void testSetView(){
		assertEquals((String)location.getText(), "SignalActivityTest");
	}

	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	

	
	
}
