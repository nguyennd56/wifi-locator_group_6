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
	//----------variables---------.
	private String placeName;	
	private ArrayList<Signal> associatedSignals;
	//--------- variable for Component interface--------
	private ArrayList<Component> component;
	private Component parent;

	
	
	//----------Constructor---------.
	public Location(String _placeName,ArrayList<Signal> _associatedSignals) {
		placeName=_placeName;
		associatedSignals=_associatedSignals;
		component= new ArrayList<Component>();
		parent= null;
	}
	
	//--------Functions--------.
	
	/**
	 * @param _locationDetail
	 */
	public void setPlaceName (String _locationDetail) {
		placeName= _locationDetail;
	}
	
	public void associatedSignal(ArrayList<Signal> _associatedSignals) {
		associatedSignals = _associatedSignals;
	}
	
	
	public String getPlaceName() {
		return placeName;
	}
	
	
	/**
	 * this function compare associated Signals of this Location with Signals of current location.
	 *  To measure the relief of this location.
	 *  using isTrueForLoacation function of class Signals to know number of the same signals.
	 *  the more the same signals, the more rank of relief.
	 *  
	 * @param listSignals : 
	 * @return the rank of relief.
	 */
	public int compareToOtherListSignals(ArrayList<Signal> listSignals) {
		int rankForLocationDeterminant=0;
		for(int i=0; i<listSignals.size(); i++){
			for(int j=0; j<associatedSignals.size();j++) {
				if(listSignals.get(i).equals(associatedSignals.get(j))) rankForLocationDeterminant++;
			}
		}
		return rankForLocationDeterminant;
	}
	
	
	// -------------convert functions--------------.

	/**
	 * this function to convert an ArrayList<Component> from getAllOfsping function to an ArrayList<Location>.
	 */

	public ArrayList<Location> getAll() {
		ArrayList<Location> listAllLocation= new ArrayList<Location>();
		ArrayList<Component> listAllcomponent= getAllOfspings();
		
		for(int i =0; i< listAllcomponent.size(); i++){
			listAllLocation.add((Location)listAllcomponent.get(i));
		}
		
		return listAllLocation;
	}
	
	/**
	 * this function to convert an ArrayList<Component> from getAllchildren function to an ArrayList<Location>.
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
	 * add a children.
	 */
	public void add(Component c) {
		component.add(c);
		((Location)c).parent= this;
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
		if(parent==null) return this;
		return parent.getRoot();
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
		return parent;
	}
	/*
	 * return a string of this name and all ancestor name.
	 */
	public String printInfo() {
		if(parent==null) {
			return (this.toString());
		}
		String allDetail = ((Location)parent).printInfo()+this.toString();
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
		return " "+placeName+".";
	}

}
