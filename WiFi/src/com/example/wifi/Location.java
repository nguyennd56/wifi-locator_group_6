package com.example.wifi;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//variable
	private String country;
	private String city;
	private String distric;
	private String place;
	private String locationDetail;
	
	private ArrayList<Signal> associatedSignals;
	
	public Location(String _country,String _city,String _distric, String _place, String _locationDetail,ArrayList<Signal> _associatedSignals) {
		country=_country;
		city=_city;
		distric=_distric;
		place=_place;
		locationDetail=_locationDetail;
		associatedSignals=_associatedSignals;
	}
	
	//function
	//Set Funtions
	public void setCountry(String _country) {
		country= _country;
	}
	public void setCity(String _city) {
		city=_city;
	}
	public void setSDistric(String _distric) {
		distric=_distric;
	}
	public void setPlace (String _place) {
		place=_place;
	}
	public void setLocationDetail (String _locationDetail) {
		locationDetail= _locationDetail;
	}
	public void associatedSignal(ArrayList<Signal> _associatedSignals) {
		associatedSignals = _associatedSignals;
	}
	
	public int compareWithThisLocation(ArrayList<Signal> currentListSignals) {
		int rankForLocationDeterminant=0;
		for(int i=0; i<currentListSignals.size(); i++){
			for(int j=0; j<associatedSignals.size();j++) {
				if(currentListSignals.get(i).isTrueForLocation(associatedSignals.get(j))) rankForLocationDeterminant++;
			}
		}
		return rankForLocationDeterminant;
	}
	
	
	
	//Get functions and toString
	public String getCountry(){
		return country;
	}
	public String getcity() {
		return city;
	}
	public String getPlace() {
		return place;
	}
	public String getLocationDetail() {
		return locationDetail;
	}
	
	
	public String toString() {
		return String.format("you are at: " +locationDetail);
	}
}
