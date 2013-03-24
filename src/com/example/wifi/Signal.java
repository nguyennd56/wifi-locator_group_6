package com.example.wifi;


public class Signal {
	private int strength;
	private String SSID;
	private String name;
	private String rate;
	
	public Signal(){super();}
	public Signal(int strength, String SSID, String name){super();this.strength=strength;this.name=name;this.SSID=SSID;}
	public void setStrength(int strength){this.strength=strength;}
	public void setName(String name){this.name = name;}
	public void setSSID(String SSID){this.SSID = SSID;}
	public void setRate(String rate){this.rate=rate;}
	public int getStrength(){return this.strength;}
	public String getName(){return this.name;}
	public String getSSID(){return this.SSID;}
	public String getRate(){return this.rate;}
}
