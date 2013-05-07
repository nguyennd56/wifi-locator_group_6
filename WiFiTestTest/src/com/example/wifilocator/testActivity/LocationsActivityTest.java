package com.example.wifilocator.testActivity;

import com.example.wifilocator.activity.LocationsActivity;
import com.example.wifilocator_rebuild.R;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class LocationsActivityTest extends ActivityInstrumentationTestCase2<LocationsActivity>{

	private LocationsActivity activity;
	private TextView location;
	private Button button;
	
	
	@SuppressWarnings("deprecation")
	public LocationsActivityTest(String pkg,
			Class<LocationsActivity> activityClass) {
		super(pkg, activityClass);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		activity = getActivity();
		location = (TextView)activity.findViewById(R.id.location_name);
		button = (Button)activity.findViewById(R.id.edit_location);
		location.setText("Locate");
	}


	public void testNotNull(){
		assertNotNull(activity);
		assertNotNull(location);
		assertNotNull(button);
	}
	public void testSetVew(){
		assertEquals(location.getText(), "Locate");
	}
	public void testClickable(){
		assertTrue(button.isClickable());
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	
	
}
