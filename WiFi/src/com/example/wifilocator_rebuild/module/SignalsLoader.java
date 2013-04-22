package com.example.wifilocator_rebuild.module;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.os.Environment;


public class SignalsLoader {
	
	public static ArrayList<Signal> getSignalSaved() {  // to get all saved wifi signals.
    	ArrayList<Signal> signalsList= new ArrayList<Signal>();
    	try
		{
	    	File sdCard = Environment.getExternalStorageDirectory();
	        File directory = new File (sdCard.getAbsolutePath() + 
	            "/MyFiles");
	        File file = new File(directory, "signals.data");
	        FileInputStream fIn = new FileInputStream(file);
	        ObjectInputStream in = new ObjectInputStream(fIn);
	        while (true) {
	            signalsList.add( (Signal) in.readObject());
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

	    return signalsList;
    }
}
