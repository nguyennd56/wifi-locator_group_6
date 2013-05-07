
package com.example.wifilocator.module;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.os.Environment;


/**
* This class is used to save and load objects.
* The Object will be stored at the folder /SDCARD/MyFiles.
* This class contain many static functions.
*/

public class StorageManager {

	
	public final static String FOLDER_NAME	 =	"/MyFiles";
	public final static String LOCATION_FILE_NAME = "locations.data";
	public final static String SIGNAL_FILE_NAME	 = "signals.data";
	
	
	
	
	
	/*
	 * This function for saving location objects only.
	 * just need save the root, all referent location will be saved too.
	 */
	public static void saveLocation( Location location) {
		try{
			//---SD Card Storage---
			
			
			
            File file = new File(loadFileOnSDCard(), LOCATION_FILE_NAME);            
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            
            
			objectOutputStream.writeObject(location);
			objectOutputStream.flush();
			objectOutputStream.close();
		}catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
	}
	
	/*
	 * This function for loading location only.
	 * loading the root can get all saved location.
	 */
	public static Location loadLocation() {
		
		activeFileLocation();
		
		Location root=new Location(null,null);
		try{
			
	        File file = new File(loadFileOnSDCard(), LOCATION_FILE_NAME);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            
            while (true) {
            	root=(Location)objectInputStream.readObject();
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
	
	/*
	 * This function for saving signal objects only.
	 * must use AppendingOjectOutputStream to get capacity of saving object many times.
	 */
	public static void saveSignal(Signal aSignal) {
	
		activeFileSignal();
		
		try{
			//---SD Card Storage---
			
	        
	            File file = new File(loadFileOnSDCard(), SIGNAL_FILE_NAME);
	            
	            FileOutputStream fOut = new FileOutputStream(file,true);
	            
	            ObjectOutputStream out= new AppendingObjectOutputStream(fOut);
	            
	            
				out.writeObject(aSignal);
				out.close();
			
		}catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
         
		
	}
	
	/*
	 *  This function for loading signals object only.
	 *  return a ArrayList of Signals.
	 */
	public static ArrayList<Signal> loadSignals () {
		ArrayList<Signal> signalsList= new ArrayList<Signal>();
    	try
		{
	    	
	        File file = new File(loadFileOnSDCard(), SIGNAL_FILE_NAME);
	        FileInputStream fileInputStream = new FileInputStream(file);
	        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	        while (true) {
	            signalsList.add( (Signal) objectInputStream.readObject());
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
	
	private static File loadFileOnSDCard(){
		File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() +
            FOLDER_NAME);
        directory.mkdirs();
        return directory;
	}
	
	
	
	
	private static void activeFileLocation(){
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            if(!(new File(sdCard.getAbsolutePath() +
                    FOLDER_NAME+"/"+LOCATION_FILE_NAME).exists())){
            	File file = new File(loadFileOnSDCard(), LOCATION_FILE_NAME);			// check whether file exist.
	            
	            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
	            
	            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
	            objectOutputStream.writeObject(new Location("All location", new ArrayList<Signal>())); // if not create and write the default object to file. 
	            objectOutputStream.flush();
	            objectOutputStream.close();
            }
        }catch (IOException ioe)
    	{
			ioe.printStackTrace();
    	}
	}
	
	private static void activeFileSignal(){
		try{
			//---SD Card Storage---
			File sdCard = Environment.getExternalStorageDirectory();
            if(!(new File(sdCard.getAbsolutePath() +
                    FOLDER_NAME +"/" + SIGNAL_FILE_NAME).exists())){
            	File file = new File(loadFileOnSDCard(), SIGNAL_FILE_NAME);
	            
	            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
	            
	            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
	            objectOutputStream.writeObject(new Signal(000,"UNKNOW","UNKNOW","UNKNOW"));
	            objectOutputStream.close();
            }
        }catch (IOException ioe)
    	{
			ioe.printStackTrace();
    	}
		
	}
}
