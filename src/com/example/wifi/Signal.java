package com.example.wifi;

import java.io.Serializable;


public class Signal implements Serializable{
	private int strength;
	private String SSID;
	private String name;
	private String rate;
	
	public Signal(){super();}
	public Signal(int strength, String SSID, String name){super();this.strength=strength;this.name=name;this.SSID=SSID;}
	public void setStrength(int strength){this.strength=strength;}
	public void setName(String _name){this.name = _name;}
	public void setSSID(String _SSID){this.SSID = _SSID;}
	public void setRate(String _rate){this.rate=_rate;}
	public int getStrength(){return this.strength;}
	public String getName(){return this.name;}
	public String getSSID(){return this.SSID;}
	public String getRate(){return this.rate;}
	
	public boolean equalTo(Signal _sig) {
		return (this.SSID.equals(_sig.SSID));
	}
	
	public void getPlace( Signal _sig) {
		this.name= _sig.name;
	}
	
	public String toString(){
		return String.format(this.SSID+"\t\t Streng:"+ this.strength + " \t\t["+this.name+"]");
	}
}
