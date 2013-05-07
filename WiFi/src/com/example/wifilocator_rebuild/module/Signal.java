package com.example.wifilocator_rebuild.module;

import java.io.Serializable;

/**
 * new class of wifi signals.
 * has variable of basic component of a wifi signals.
 * implements Serializable to save and load to internal storage.
 */

public class Signal  implements Serializable{

	private static final long serialVersionUID = 1L;  //default id for serialization.
	private static final int MAX_ACCEPTABLE_STRENGTH_DIFFIRENTCE=10;
	//-----------variable----------
	private int strength;
	private String SSID;
	private String BSSID;
	private String place;
	private String rate;
	
	
	//-----------constructor---------
	public Signal(){
		super();
		}
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
	public int getStrength(){return this.strength;
	}
	
	//........get functions...........
	public String getPlace(){
		return this.place;}
	public String getSSID(){return this.SSID;
	}
	public String getBSSID(){
		return this.BSSID;}
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
				&&(Math.abs(this.strength-otherSignal.strength)<MAX_ACCEPTABLE_STRENGTH_DIFFIRENTCE));
	}
	
	
	public String toString(){
		return String.format(this.SSID+"\t\t Streng:"+ this.strength + "t["+this.place+"]");
	}
	
}
