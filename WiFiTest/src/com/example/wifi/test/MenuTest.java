package com.example.wifi.test;

import junit.framework.TestCase;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;

import com.example.wifi.Menu;

public class MenuTest extends  ActivityInstrumentationTestCase2<Menu>{

	@SuppressWarnings("deprecation")
	public MenuTest() {
		super("Menu Test", Menu.class);
		// TODO Auto-generated constructor stub
	}
	private Menu mActivity;
	private Button mButton;
	private ListView mListView;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		mActivity = this.getActivity();
		mButton = (Button)mActivity.findViewById(com.example.wifi.R.id.quit_menu);
		mListView =(ListView)mActivity.findViewById(com.example.wifi.R.id.menu_list_function);
	}
	public void testPreconditions(){
		assertNotNull(mButton);
		assertNotNull(mListView);
	}
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) public void testUIButton(){
		mActivity.runOnUiThread(
			new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mButton.requestFocus();
					mButton.callOnClick();
					
				}
			}
		);
	}

}
