package com.example.wifi;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Label extends Activity{
	
	Location current_Location;
	EditText country;
	EditText city;
	EditText distric;
	EditText placeName;
	EditText locationDetail;
	ArrayList<Signal> currentListSignals;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.label_layout);
		country = (EditText)findViewById(R.id.place_country_edit);
		city	= (EditText)findViewById(R.id.place_city_edit);
		distric = (EditText)findViewById(R.id.place_distric_edit);
		placeName=(EditText)findViewById(R.id.place_edit);
		locationDetail= (EditText)findViewById(R.id.place_name_edit);
		
		currentListSignals=(ArrayList<Signal>) getIntent().getSerializableExtra("currentSignals");
		
		Toast.makeText(getBaseContext(),
				Integer.toString(currentListSignals.size()),
				Toast.LENGTH_SHORT).show();
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            
            if(!(new File(sdCard.getAbsolutePath() +
                    "/MyFiles/location.data").exists())){
            	File _file = new File(directory, "location.data");
	            
	            FileOutputStream _fOut = new FileOutputStream(_file,true);
	            
	            ObjectOutputStream _out= new ObjectOutputStream(_fOut);
	            _out.writeObject( new Location("unknow","unknow","unknow","unknow","unknow",new ArrayList<Signal>()));
	            _out.close();
            }
        }catch (IOException ioe)
    	{
			ioe.printStackTrace();
    	}
	}
	
	public void onClickSave(View view){
		String Country = country.getText().toString();
		String City	   = city.getText().toString();
		String Distric = distric.getText().toString();
		String PlaceName = placeName.getText().toString();
		String Detail=locationDetail.getText().toString();
		
		current_Location= new Location(Country,City,Distric,PlaceName,Detail,currentListSignals);
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            File file = new File(directory, "location.data");
            
            FileOutputStream fOut = new FileOutputStream(file,true);
            
            ObjectOutputStream out= new AppendingObjectOutputStream(fOut);
            
            
			out.writeObject(current_Location);
			out.close();
			
			//---display file saved message---
			Toast.makeText(getBaseContext(),
					"Signal saved successfully!",
					Toast.LENGTH_SHORT).show();

			//---clears the EditText---
			country.setText("");
			city.setText("");
			distric.setText("");
			placeName.setText("");
			locationDetail.setText("");
		}catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public void onClickCancel(View view) {
		ArrayList<Location> locationList= new ArrayList<Location>();
		try
		{
			//---SD Storage---
			
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() + 
                "/MyFiles");
            File file = new File(directory, "location.data");
            FileInputStream fIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fIn);
            
            while (true) {
            	Location theLocation=(Location)in.readObject();
            	
	            locationList.add(theLocation);
	            
	            Toast.makeText(getBaseContext(),
						"File loaded successfully!",
						Toast.LENGTH_SHORT).show();
            }
		}
		catch (EOFException e) {
			e.printStackTrace();
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < locationList.size(); i++){
            sb.append(Integer.valueOf(i+1).toString() + ".");
            sb.append((locationList.get(i)).toString());
            sb.append(System.getProperty ("line.separator"));
        }
		Toast.makeText(getBaseContext(),
				sb,
				Toast.LENGTH_SHORT).show();
	}
	
	
}
