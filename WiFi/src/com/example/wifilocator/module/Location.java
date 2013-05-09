package com.example.wifilocator.module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Class contain place name and  associated signals.
 *  it implements component to had tree structure.
 *  The component variable is list of its children location, the parent is its parent location.
 *  it implements Serializable to be able to save and load to internal storage. 
 */

public class Location implements Serializable,Component{

	private static final long serialVersionUID = 1L; //default id for serialization.
	private final static int BASE_SCORE = 0;
	
	//----------variables---------.
	private String location;	
	private ArrayList<Signal> basedSignal;
	
	//--------- variable for Component interface--------
	private ArrayList<Component> component;
	private Component parentLocation;

	//----------Constructor---------.
	public Location(String _placeName,ArrayList<Signal> _associatedSignals) {
		location=_placeName;
		basedSignal=_associatedSignals;
		component= new ArrayList<Component>();
		parentLocation= null;
	}
	
	//--------Functions--------.
	
	/**
	 * @param locationName
	 */
	public void setLocationName (String locationName) {
		this.location= locationName;
	}
	
	public void setBasedSignal(ArrayList<Signal> basedSignal) {
		this.basedSignal = basedSignal;
	}
	
	
	public String getLocationName() {
		return this.location;
	}
	
	
	/**
	 * @describe : compare the list signal which scan with the baseListSignal of location
	 * 			   + criteria: base on the each signal on list,
	 * 				           each signal is equal then score will increase by 1 point
	 * 			   + best location's score will be shown. 
	 * @param listSignals : 
	 * @return score
	 */
	public int ratingBasedSignal(ArrayList<Signal> listSignals) {
		int ratingScore = BASE_SCORE;
		//compare each signal on list with all base signal
		for(int i=0; i<listSignals.size(); i++){
			for(int j=0; j<basedSignal.size();j++) {
				
				if(listSignals.get(i).equals(basedSignal.get(j))){
					ratingScore = increaseScore(ratingScore);
				}
			}
		}
		
		return ratingScore;
	}
	
	private int increaseScore(int score){
		return score++;
	}
	
	
	// -------------convert functions--------------.

	/**
	 * this function to convert an ArrayList<Component> from getAllOfsping function to an ArrayList<Location>.
	 */

	public ArrayList<Location> getLocationList() {
		
		ArrayList<Location> locations= new ArrayList<Location>();
		ArrayList<Component> components= getAllOfspings();
		
		//each component contains class of locations - has same level
		for(int i =0; i< components.size(); i++){
			locations.add((Location)components.get(i));
		}
		
		return locations;
	}
	
	/**
	 * @describe: return list of locations which has same "parent-location" 
	 */
	public ArrayList<Location> getLeafLocation() {

		ArrayList<Location> leafLocation= new ArrayList<Location>();
		for(int i=0; i<component.size(); i++) {
			leafLocation.add((Location)component.get(i));
		}
		return leafLocation;
	}
		
	//------------------ Override functions of Component Interface-------------------.

	/*
	 * add a "children-location"
	 */
	public void add(Component c) {
		component.add(c);
		((Location)c).parentLocation= this;
	}
	
	/*
	 * delete a children by index.
	 */
	public void remove(int index) {
		component.remove(index);
	}
	public boolean isLeaf() {
		return (component.size()==0);
	}
	
	/*
	 * get the "root"
	 * the ancestor of all components.
	 */
	public Component getRoot() {
		if(parentLocation==null) return this;
		return parentLocation.getRoot();
	}
	
	
	/*
	 * get number of all components.
	 */
	public int getSize() {
		if (isLeaf()) return 0;
		int size = 0;
		for (int i = 0; i < component.size(); i++) 
			size += component.get(i).getSize();
		return size+ component.size();
	}
	/*
	 * return a child by index.
	 */
	public Component getChild (int index) {
		return component.get(index);
	}
	/*
	 * return all children component.
	 */
	public ArrayList<Component> getAllChildren() {
		return component;
	}
	/*
	 * return all component.
	 * using preOder function to get them.
	 */
	public ArrayList<Component> getAllOfspings() {
		
		ArrayList<Component> allOfSping= new ArrayList<Component>();
		preOrder(allOfSping,this);
		return allOfSping;
	}
	/*
	 * search wikipedia.com to more detail.
	 */
	public void preOrder(ArrayList<Component> allOfSping,Component root) {
		
		allOfSping.add(root);
		for(int i=0; i< root.getAllChildren().size(); i++) {
			preOrder(allOfSping,root.getChild(i));
		}
	}
	
	/*
	 * get parent of this.
	 */
	public Component getParent() {
		return parentLocation;
	}
	/*
	 * return a string of this name and all ancestor name.
	 */
	public String printInfo() {
		if(parentLocation==null) {
			return (this.toString());
		}
		String allDetail = ((Location)parentLocation).printInfo()+this.toString();
		return allDetail;
	}
	
	/*
	 * return a String of all component name by tree structure.
	 */
	public String print(int space) {
		String string = "";
		if(isLeaf()) {
			for (int i=0; i<space; i++) 
				string = string + " ";
			string = string + this.toString() + System.getProperty("line.separator");
			return string;
		}
		
		for (int i = 0; i < component.size(); i++) {
			string += component.get(i).print(space+2);
		}
		return string;
	}
	public String toString() {
		return " "+location+".";
	}

}
