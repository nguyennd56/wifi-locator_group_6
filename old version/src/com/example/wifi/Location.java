package com.example.wifi;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable,Component{
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
	
	private ArrayList<Component> component;
	private Component parent;
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
	
	public void add(Component c) {
		component.add(c);
		((Location)c).parent= this;
	}
	public void remove(int index) {
		component.remove(index);
	}
	public boolean isLeaf() {
		return (component.size()==0);
	}
	public int getSize() {
		if (isLeaf()) return 0;
		int size = 0;
		for (int i = 0; i < component.size(); i++) 
			size += component.get(i).getSize();
		return size+ component.size();
	}
	public Component getChild (int index) {
		return component.get(index);
	}
	public ArrayList<Component> getAllChildren() {
		return component;
	}
	public Component getParent() {
		return parent;
	}
	public String printInfo() {
		if(parent==null) {
			return (this.toString()+".");
		}
		return (((Location)parent).toString()+this.toString()+".");
	}
	
	public String print(int space) {
		String s = "";
		if(isLeaf()) {
			for (int i=0; i<space; i++) 
				s = s + " ";
			s = s + this.toString() + System.getProperty("line.separator");
			return s;
		}
		
		for (int i = 0; i < component.size(); i++) {
			s += component.get(i).print(space+2);
		}
		return s;
	}
	public String toString() {
		return locationDetail;
	}
}
