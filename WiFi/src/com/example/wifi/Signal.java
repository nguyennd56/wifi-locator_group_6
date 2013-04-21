package com.example.wifi;

import java.io.Serializable;


public class Signal implements Serializable{
	/**
	 * 
	 */
	
	final int POOR_IMAGE_1 = 0;
	final int POOR_IMAGE_2 = 1;
	final int POOR_IMAGE_3 = 2;
	final int FAIR_IMAGE = 3;
	final int GOOD_IMAGE = 4;
	final int EXCELLENT_IMAGE = 5;
	final int DEFAULT = 0;
	
	final int POOR_LEVEL_1 = -95;
	final int POOR_LEVEL_2 = -75;
	final int FAIR_LEVEL = -65;
	final int GOOD_LEVEL = -55;
	final int EXCELLENT_LEVEL = -45;
	
	private static final long serialVersionUID = 1L;
	
	private String SSID;//ssid name
	private String rate;//relate to strength
	private String BSSID;
	private String capabilities;
	private int frequency;
	private int level;
	private long timestamp;
	private int image;
	
	public Signal(){super();}
	public Signal(String SSID, String BSSID, String capabilities,int frequency,int level, long timestamp){
		super();
		this.SSID = SSID;
		this.BSSID = BSSID;
		this.rate = "default";
		this.image = DEFAULT;
		this.capabilities = capabilities;
		this.frequency = frequency;
		this.level = level;
		this.timestamp = timestamp;
	}
	public void setRate(){
		if(this.level>=EXCELLENT_LEVEL){
			this.rate="excellent";
			this.image = EXCELLENT_IMAGE;
		}
		else if(this.level>=GOOD_LEVEL){
			this.rate = "good";
			this.image = GOOD_IMAGE;
		}
		else if(this.level>=FAIR_LEVEL){
			this.rate = "fair";
			this.image = FAIR_IMAGE;
		}
		else if(this.level>=POOR_LEVEL_2){
			this.rate = "poor";
			this.image = POOR_IMAGE_3;
		}
		else if(this.level>=POOR_LEVEL_1){
			this.rate = "poor";
			this.image = POOR_IMAGE_2;
		}
		else {
			this.rate ="poor";
			this.image = POOR_IMAGE_1;
		}
	}
	
	public String getSSID(){return this.SSID;}
	
	public String getBSSID(){return this.BSSID;}
	
	public String getRate(){return this.rate;}
	
	public int getImage(){return this.image;}
	public String getCapability(){
		return this.capabilities;
	}
	
	public int getFrequency(){
		return this.frequency;
	}
	
	public int getLevel(){
		return this.level;
	}
	public long getTimestamp(){
		return this.timestamp;
	}
	
	public boolean equalTo(Signal _sig) {
		return (this.SSID.equals(_sig.SSID));
	}
	
	public String toString(){
		return String.format(this.SSID+"\t\t Streng:"+ this.level + " \t\t["+this.SSID+"]");
	}
	
	public boolean isTrueForLocation (Signal otherSignal) {
		return (this.SSID.equals(otherSignal.SSID)&&(Math.abs(this.level-otherSignal.level)<10));
	}
}
