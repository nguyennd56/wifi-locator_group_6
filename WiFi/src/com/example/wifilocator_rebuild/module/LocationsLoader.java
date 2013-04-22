package com.example.wifilocator_rebuild.module;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.os.Environment;


public class LocationsLoader {
	public static Location getSavedLocation(){
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            
            if(!(new File(sdCard.getAbsolutePath() +
                    "/MyFiles/locations.data").exists())){
            	File _file = new File(directory, "locations.data");
	            
	            FileOutputStream _fOut = new FileOutputStream(_file,true);
	            
	            ObjectOutputStream _out= new ObjectOutputStream(_fOut);
	            _out.writeObject(new Location("All location", new ArrayList<Signal>()));
	            _out.flush();
	            _out.close();
            }
        }catch (IOException ioe)
    	{
			ioe.printStackTrace();
    	}
		Location root=new Location(null,null);
		try{
			
			//---SD Storage---
			
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() + 
                "/MyFiles");
            File file = new File(directory, "locations.data");
            FileInputStream fIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fIn);
            
            while (true) {
            	root=(Location)in.readObject();
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
		return root;
	}
}
