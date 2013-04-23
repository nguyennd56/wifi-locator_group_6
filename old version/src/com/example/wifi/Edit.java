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
	Signal s;
	EditText textBox;
	TextView mainText;
	Button save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		s= new Signal(123,"notthing","in your eyes");
		s= (Signal)getIntent().getSerializableExtra("clicked");
		
		setContentView(R.layout.edit_info_layout);
		textBox= (EditText)findViewById(R.id.editText1);
		mainText= (TextView)findViewById(R.id.textView2);
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
                    s.getSSID(), Toast.LENGTH_SHORT).show();
            }
            is.close();
            br.close();
          
        } catch (Exception e) {
            e.printStackTrace();
        }
       

	}
	
	public void onClickSave(View view) {
		String _name = textBox.getText().toString();
		textBox.setText("ok");
		
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            
	            File file = new File(directory, "signal.data");
	            
	            FileOutputStream fOut = new FileOutputStream(file,true);
	            
	            ObjectOutputStream out= new AppendingObjectOutputStream(fOut);
	            
	            
	            s.setName(_name);
				out.writeObject(s);
				out.close();
			
			//---display file saved message---
			Toast.makeText(getBaseContext(),
					"Signal saved successfully!",
					Toast.LENGTH_SHORT).show();

			//---clears the EditText---
			textBox.setText("");
            
		}catch (IOException ioe)
		{
			textBox.setText("exception");
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
	            mainText.setText(_thesignal.toString());
	            
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
		for(int i = 0; i < _wifiList.size(); i++){
            sb.append(Integer.valueOf(i+1).toString() + ".");
            sb.append((_wifiList.get(i)).getSSID()+ " "+ _wifiList.get(i).getName());
            sb.append(System.getProperty ("line.separator"));
        }
       mainText.setText(sb);
	}
	
}
