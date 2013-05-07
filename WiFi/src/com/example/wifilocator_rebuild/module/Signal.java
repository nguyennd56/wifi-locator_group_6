package com.example.wifilocator_rebuild.module;
import java.io.Serializable;


public class Signal  implements Serializable{
	/**
	 * @category Object Signal (of wifi)
	 * 
	 * has variable of basic component of a wifi signals.
	 * implements Serializable to save and load to internal storage.
	 */
	private static final long serialVersionUID = 1L;  //default id for serialization.
	
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
	public void getPlace( Signal _sig) {
		this.place= _sig.place;
	}
	
	//................
	public boolean equalTo(Signal _sig) {
		return (this.SSID.equals(_sig.SSID));
	}
	
	public boolean isTrueForLocation (Signal otherSignal) {
		/*
		 * this function compare this signal with a an other signal.
		 * using SSID and strength of signals to determine if they are the same.
		 * this function is used for determining  location. 
		 */
		return (this.SSID.equals(otherSignal.SSID)&&(Math.abs(this.strength-otherSignal.strength)<30));
	}
	
	
	public String toString(){
		return String.format(this.SSID+"\t\t Streng:"+ this.strength + "t["+this.place+"]");
	}
	
}
