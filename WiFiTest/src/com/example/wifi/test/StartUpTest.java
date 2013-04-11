package com.example.wifi.test;

import com.example.wifi.StartUp;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StartUpTest extends ActivityInstrumentationTestCase2<StartUp>{
	
	private StartUp mActivity;
	private TextView mView;
	private ProgressBar mProgressBar;
	private String loadText= "Loading . . .";
	public StartUpTest() {
		super("test", StartUp.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		mActivity = this.getActivity();
		mView = (TextView)mActivity.findViewById(com.example.wifi.R.id.txt_load);
		mProgressBar = (ProgressBar)mActivity.findViewById(com.example.wifi.R.id.progressBar1);
	}
	
	public void testPrecondition(){
		assertNotNull(mView);
		assertNotNull(mProgressBar);
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) public void testProgressBarUI(){
		mActivity.runOnUiThread(
			new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mProgressBar.requestFocus();
					mProgressBar.isActivated();
					
				}
				
			}	
		);
	}
	public void testTextViewUI(){
		assertEquals(loadText,(String)mView.getText());
	}
	
}
