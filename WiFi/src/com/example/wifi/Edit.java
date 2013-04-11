package com.example.wifi;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends Activity{
	Signal signal;
	EditText editText;
	TextView textView;
	Button save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		signal= new Signal(123,"notthing","in your eyes");
		signal= (Signal)getIntent().getSerializableExtra("clicked");
		
		setContentView(R.layout.activity_edit);
		editText= (EditText)findViewById(R.id.editText1);
		textView= (TextView)findViewById(R.id.textView2);
		save = (Button) findViewById(R.id.btSave);
		
		InputStream is = this.getResources().openRawResource(R.raw.signal);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            
            if(!(new File(sdCard.getAbsolutePath() +
                    "/MyFiles/signal.data").exists())){
            	File _file = new File(directory, "signal.data");
	            
	            FileOutputStream _fOut = new FileOutputStream(_file,true);
	            
	            ObjectOutputStream _out= new ObjectOutputStream(_fOut);
	            _out.writeObject(new Signal(000,"UNKNOW","UNKNOW"));
	            _out.close();
            }
        }catch (IOException ioe)
    	{
			ioe.printStackTrace();
    	}
    	
        
        try {
        	while ((br.readLine()) != null) {
                Toast.makeText(getBaseContext(), 
                    signal.getSSID(), Toast.LENGTH_SHORT).show();
            }
            is.close();
            br.close();
          
        } catch (Exception e) {
            e.printStackTrace();
        }
       

	}
	
	public void onClickSave(View view) {
		String _name = editText.getText().toString();
		editText.setText("ok");
		
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            
	            File file = new File(directory, "signal.data");
	            
	            FileOutputStream fOut = new FileOutputStream(file,true);
	            
	            ObjectOutputStream out= new AppendingObjectOutputStream(fOut);
	            
	            
	            signal.setName(_name);
				out.writeObject(signal);
				out.close();
			
			//---display file saved message---
			Toast.makeText(getBaseContext(),
					"Signal saved successfully!",
					Toast.LENGTH_SHORT).show();

			//---clears the EditText---
			editText.setText("");
            
		}catch (IOException ioe)
		{
			editText.setText("exception");
			ioe.printStackTrace();
		}
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	
	
	public void onClickLoad(View view) {
		ArrayList<Signal> _wifiList= new ArrayList<Signal>();
		try
		{
			//---SD Storage---
			
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() + 
                "/MyFiles");
            File file = new File(directory, "signal.data");
            FileInputStream fIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fIn);
            
            while (true) {
            	Signal _thesignal=(Signal)in.readObject();
            	
	            _wifiList.add(_thesignal);
	            textView.setText(_thesignal.toString());
	            
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
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < _wifiList.size(); i++){
            stringBuilder.append(Integer.valueOf(i+1).toString() + ".");
            stringBuilder.append((_wifiList.get(i)).getSSID()+ " "+ _wifiList.get(i).getName());
            stringBuilder.append(System.getProperty ("line.separator"));
        }
       textView.setText(stringBuilder);
	}
	
}
