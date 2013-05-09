package com.example.wifilocator.activity;

/**
 * this activity for add place name for a wifi signals.
 */

import com.example.wifilocator.module.Signal;
import com.example.wifilocator.module.StorageManager;
import com.example.wifilocator_rebuild.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*
 * 
 * 
 * 
 * 
 * DON'T CARE 
 * This function  we don't use in this app
 * but we don't delete it because we want to repair in later
 * 
 * SORRY for this inconvenience
 * 
 * But don't care it, again
 * 
 * THANKs, guy.
 * 
 * 
 *
 * 
 * 
 */
public class EditSignalPlaceActivity extends Activity{
	private EditText editTextBox;
	private TextView viewTextBox;
	private Signal clickedSignal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_signal_place);
		editTextBox= (EditText)findViewById(R.id.ed_edit_place);
		viewTextBox= (TextView)findViewById(R.id.tv_SSID);
		clickedSignal=(Signal)getIntent().getSerializableExtra("clicked");
		viewTextBox.setText(clickedSignal.getSSID());
	}
	
	//-- add place to signal object and save ---
	public void onClickAdd(View v) {
		String placeName = editTextBox.getText().toString();
		clickedSignal.setPlace(placeName);
		StorageManager.saveSignal(clickedSignal);
		editTextBox.setText("");
	}
	
	//-- return to MainTabs --
	public void onClickBack(View v) {
		try{
			Class<?> Menuclass = Class.forName("com.example.wifilocator_rebuild.MainTabActivity");
			Intent backToMenuActivity = new Intent(EditSignalPlaceActivity.this,Menuclass);
			startActivity(backToMenuActivity);
		}
		catch(ClassNotFoundException e){
				e.printStackTrace();
		}
	}
}
