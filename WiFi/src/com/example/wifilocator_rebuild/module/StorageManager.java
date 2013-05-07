
package com.example.wifilocator_rebuild.module;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import android.os.Environment;

public class StorageManager {
	/**
	* This class is used to save and load objects.
	* The Object will be stored at the folder /SDCARD/MyFiles.
	* This class contain many static functions.
	*/
	
	public final static String FOLDER_NAME	 =	"/MyFiles";
	public final static String LOCATION_FILE_NAME = "locations.data";
	public final static String SIGNAL_FILE_NAME	 = "signals.data";
	//variables.
	private Serializable storableObject;
	
	//functions.
	
	//---------Constructor----------
	public StorageManager(Serializable aObject) {
		storableObject = aObject;
	}
	
	public void saveTo(String fileName) {
		/*
		 * Save object to an file.
		 */
		if((fileName!= SIGNAL_FILE_NAME)||(fileName!= LOCATION_FILE_NAME)) { // Check for validation of fileName.
			try{
				//---SD Card Storage---
				
				File sdCard = Environment.getExternalStorageDirectory();
	            File directory = new File (sdCard.getAbsolutePath() +
	                FOLDER_NAME);
	            directory.mkdirs();
	            File file = new File(directory, fileName);            
	            FileOutputStream fOut = new FileOutputStream(file);	            
	            ObjectOutputStream out= new ObjectOutputStream(fOut);
	                        
				out.writeObject(storableObject);
				out.flush();
				out.close();
			}catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
	
	public static void saveLocation( Location root) {
		/*
		 * This function for saving location objects only.
		 */
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                FOLDER_NAME);
            directory.mkdirs();
            File file = new File(directory, LOCATION_FILE_NAME);            
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
	
	public static Location loadLocation() {
		/*
		 * This function for loading location only.
		 */
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                FOLDER_NAME);
            directory.mkdirs();
            
            if(!(new File(sdCard.getAbsolutePath() +
                    FOLDER_NAME+"/"+LOCATION_FILE_NAME).exists())){
            	File _file = new File(directory, LOCATION_FILE_NAME);			// check whether file exist.
	            
	            FileOutputStream _fOut = new FileOutputStream(_file,true);
	            
	            ObjectOutputStream _out= new ObjectOutputStream(_fOut);
	            _out.writeObject(new Location("All location", new ArrayList<Signal>())); // if not create and write the default object to file. 
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
                FOLDER_NAME);
            File file = new File(directory, LOCATION_FILE_NAME);
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
	
	public static void saveSignal(Signal aSignal) {
		/*
		 * This function for saving signal objects only.
		 */
		try{
			//---SD Card Storage---
			
			File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() +
                FOLDER_NAME);
            directory.mkdirs();
            
            if(!(new File(sdCard.getAbsolutePath() +
                    FOLDER_NAME +"/" + SIGNAL_FILE_NAME).exists())){
            	File _file = new File(directory, SIGNAL_FILE_NAME);
	            
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
                FOLDER_NAME);
            directory.mkdirs();
            
	            File file = new File(directory, SIGNAL_FILE_NAME);
	            
	            FileOutputStream fOut = new FileOutputStream(file,true);
	            
	            ObjectOutputStream out= new AppendingObjectOutputStream(fOut);
	            
	            
				out.writeObject(aSignal);
				out.close();
			
		}catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
         
		
	}
	
	public static ArrayList<Signal> loadSignals () {
		/*
		 *  This function for loading signals object only.
		 *  return a ArrayList of Signals.
		 */
		ArrayList<Signal> signalsList= new ArrayList<Signal>();
    	try
		{
	    	File sdCard = Environment.getExternalStorageDirectory();
	        File directory = new File (sdCard.getAbsolutePath() + 
	            FOLDER_NAME);
	        File file = new File(directory, SIGNAL_FILE_NAME);
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


/**
 * copied from http://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream/1195078#1195078
 * using to get capacity of appending for objectOutPutStream.
 * write many object at many times.
 */
class AppendingObjectOutputStream extends ObjectOutputStream {

  public AppendingObjectOutputStream(OutputStream out) throws IOException {
    super(out);
  }

  @Override
  protected void writeStreamHeader() throws IOException {
    // do not write a header
	  reset();
  }

}