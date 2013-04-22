package com.example.wifilocator_rebuild.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.os.Environment;


public class LocationSaver {
	public static void saveAllLocation(Location root){
		
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                "/MyFiles");
            directory.mkdirs();
            File file = new File(directory, "locations.data");
            
            FileOutputStream fOut = new FileOutputStream(file);
            
            ObjectOutputStream out= new ObjectOutputStream(fOut);
            
            
			out.writeObject(root);
			out.flush();
			out.close();
		}catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

}
