package com.example.wifilocator_rebuild.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.os.Environment;

public class SignalSaver {

	public static void saveTheSignal(Signal signal2Save) {
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            
            if(!(new File(sdCard.getAbsolutePath() +
                    "/MyFiles/signals.data").exists())){
            	File _file = new File(directory, "signals.data");
	            
	            FileOutputStream _fOut = new FileOutputStream(_file,true);
	            
	            ObjectOutputStream _out= new ObjectOutputStream(_fOut);
	            _out.writeObject(new Signal(000,"UNKNOW","UNKNOW","UNKNOW"));
	            _out.close();
            }
        }catch (IOException ioe)
    	{
			ioe.printStackTrace();
    	}
		
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            
	            File file = new File(directory, "signals.data");
	            
	            FileOutputStream fOut = new FileOutputStream(file,true);
	            
	            ObjectOutputStream out= new AppendingObjectOutputStream(fOut);
	            
	            
				out.writeObject(signal2Save);
				out.close();
			
		}catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
         
	}
	
}
