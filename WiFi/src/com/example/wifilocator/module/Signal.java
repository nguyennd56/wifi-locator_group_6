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
	public Signal(int strength, String SSID, String BSSID,String name){
		super();
		this.strength=strength;
		this.place=name;
		this.SSID=SSID;
		this.BSSID=BSSID;
	}
	
	//-----------functions------------
	
	//.........set functions.........
	public void setStrength(int strength){
		this.strength=strength;
	}
	public void setPlace( Signal signal) {
		this.place= signal.place;
	}
	public void setPlace(String name){
		this.place = name;}
	public void setSSID(String SSID){this.SSID = SSID;
	}
	public void setBSSID(String BSSID){
		this.BSSID = BSSID;
	}
	public void setRate(String rate){
		this.rate= rate;
	}
	
	public int getStrength(){
		return this.strength;
	}
	public String getPlace(){
		return this.place;}
	public String getSSID(){
		return this.SSID;
	}
	public String getBSSID(){
		return this.BSSID;
		}
	public String getRate(){
		return this.rate;
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
	
	@Override
	public String toString(){
		return String.format(this.SSID+"\t\t Strength:"+ this.strength + "t["+this.place+"]");
	}
	
}
