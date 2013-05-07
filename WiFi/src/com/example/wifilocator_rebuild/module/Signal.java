package com.example.wifilocator_rebuild.module;
import java.io.Serializable;


/**
 * @author Nguyen D. Ngo
 *
 */
public class Signal  implements Serializable{
	/**
	 * 
	 * @category Object Signal (of wifi)
	 * 
	 * has variable of basic component of a wifi signals.
	 * implements Serializable to save and load to internal storage.
	 */
	private static final long serialVersionUID = 1L;  //default id for serialization.
	public final static int MAX_ACCEPTABLE_STRENGTH_DIFFIRENCE=10;
	
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
	/*
	 * 
	 */
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
	public void setPlace(String _name){
		this.place = _name;}
	public void setSSID(String _SSID){this.SSID = _SSID;
	}
	public void setBSSID(String _BSSID){
		this.BSSID = _BSSID;
		}
	public void setRate(String _rate){
		this.rate=_rate;}
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
	
	//................
	@Override
	public boolean equals(Object theSignal) {/*
		 * this function compare this signal with a an other signal.
		 * using SSID and strength of signals to determine if they are the same.
		 * this function is used for determining  location. 
		 */
		Signal signal = (Signal) theSignal;
		return (this.SSID.equals(signal.SSID)&&
				(Math.abs(this.strength-signal.strength)<MAX_ACCEPTABLE_STRENGTH_DIFFIRENCE));
	}
	
	
	@Override
	public String toString(){
		return String.format(this.SSID+"\t\t Streng:"+ this.strength + "t["+this.place+"]");
	}
	
}
