package com.example.wifilocator.module;

import java.io.Serializable;

/**
 * new class of  signals.
 * has variable of basic component of a WIFI signals.
 * implements Serializable to save and load to internal storage.
 */

public class Signal  implements Serializable{

	private static final long serialVersionUID = 1L;  //default id for serialization.
	private static final int MAX_ACCEPTABLE_STRENGTH_DIFFIRENTCE=10;
	//-----------variables----------
	private int strength;
	private String SSID;
	private String BSSID;
	private String place;
	private String rate;
	
	
	//-----------constructor---------
	public Signal(){
		super();
		}
	
	//constructor with parameters
	public Signal(int strength, String SSID, String _BSSID,String name){
		super();
		this.strength=strength;
		this.place=name;this.
		SSID=SSID; this.
		BSSID=_BSSID;
	}
	
	//-----------functions------------
	
	//.........set functions.........
	public void setStrength(int strength){
		this.strength=strength;
<<<<<<< HEAD:WiFi/src/com/example/wifilocator/module/Signal.java
		}
	public void setPlace(String name){
		this.place = name;}
	public void setSSID(String SSID){this.SSID = SSID;
	}
	public void setBSSID(String BSSID){
		this.BSSID = BSSID;
		}
	public void setRate(String rate){
		this.rate=rate;}
=======
	}
	public void setPlace(String _name){
		this.place = _name;}
	public void setSSID(String _SSID){this.SSID = _SSID;
	}
	public void setBSSID(String _BSSID){
		this.BSSID = _BSSID;
	}
	
	public void setRate(String _rate){
		this.rate= _rate;
	}
>>>>>>> 8dbcceac2fad6930b1a9616d7b61890fb28f4a76:WiFi/src/com/example/wifilocator_rebuild/module/Signal.java
	public int getStrength(){return this.strength;
	}
	
	//........get functions...........
	public String getPlace(){
		return this.place;}
	public String getSSID(){
		return this.SSID;
	}
	public String getBSSID(){
		return this.BSSID;
		}
	public String getRate(){return this.rate;
	}
	public void getPlace( Signal signal) {
		this.place= signal.place;
	}
	
	
	/*
	 * this function compare this signal with a an other signal.
	 * using SSID and strength of signals to determine if they are the same.
	 * this function is used for determining  location. 
	 */
	
	@Override
	public boolean equals (Object signal) {
		Signal otherSignal=(Signal) signal;
		return (this.BSSID.equals(otherSignal.BSSID)
				&&( Math.abs(this.strength - otherSignal.strength) < MAX_ACCEPTABLE_STRENGTH_DIFFIRENTCE) );
	} 
	
	
	public String toString(){
		return String.format(this.SSID+"\t\t Strength:"+ this.strength + "t["+this.place+"]");
	}
	
}
