package com.example.wifilocator_rebuild.module;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable,Component{
	/**
	 *  Class contain place name and  associated signals.
	 *  it implements component to had tree structure.
	 *  The component variable is list of its children location, the parent is its parent location.
	 *  it implements Serializable to be able to save and load to internal storage. 
	 */
	private static final long serialVersionUID = 1L; //default id for serialization.
	//----------variables---------.
	private String placeName;	
	private ArrayList<Signal> associatedSignals;	
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
	
	//--------------Set Functions-----------.
	
	public void setPlaceName (String _locationDetail) {
		placeName= _locationDetail;
	}
	
	public void associatedSignal(ArrayList<Signal> _associatedSignals) {
		associatedSignals = _associatedSignals;
	}
	
	
	public String getPlaceName() {
		return placeName;
	}
	
	public int compareWithThisLocation(ArrayList<Signal> currentListSignals) {
		/*
		 * this function compare associated Signals of this Location with Signals of current location.
		 *  To measure the relief of this location.
		 *  using isTrueForLoacation function of class Signals to know number of the same signals.
		 *  the more the same signals, the more rank of relief.
		 *  return the rank of relief.
		 */
		int rankForLocationDeterminant=0;
		for(int i=0; i<currentListSignals.size(); i++){
			for(int j=0; j<associatedSignals.size();j++) {
				if(currentListSignals.get(i).isTrueForLocation(associatedSignals.get(j))) rankForLocationDeterminant++;
			}
		}
		return rankForLocationDeterminant;
	}
	
	// -------------convert functions--------------.
	
	public ArrayList<Location> getAll() {
		/*
		 * this function to convert an ArrayList<Component> from getAllOfsping function to an ArrayList<Location>.
		 */
		ArrayList<Location> listAllLocation= new ArrayList<Location>();
		ArrayList<Component> listAllcomponent= getAllOfSping();
		
		for(int i =0; i< listAllcomponent.size(); i++){
			listAllLocation.add((Location)listAllcomponent.get(i));
		}
		
		return listAllLocation;
	}
	public ArrayList<Location> getAllLocationChildren() {
		/*
		 * this function to convert an ArrayList<Component> from getAllchildren function to an ArrayList<Location>.
		 */

		ArrayList<Location> allOfchildren= new ArrayList<Location>();
		for(int i=0; i<component.size(); i++) {
			allOfchildren.add((Location)component.get(i));
		}
		return allOfchildren;
	}
	
	
	//------------------ Override functions of Component Interface-------------------.
	
	
	public void add(Component c) {
		/*
		 * add a children.
		 */
		component.add(c);
		((Location)c).parent= this;
	}
	public void remove(int index) {
		/*
		 * delete a children by index.
		 */
		component.remove(index);
	}
	public boolean isLeaf() {
		return (component.size()==0);
	}
	
	public Component getRoot() {
		/*
		 * get the "root"
		 * the ancestor of all components.
		 */
		if(parent==null) return this;
		return parent.getRoot();
	}
	public int getSize() {
		/*
		 * get number of all components.
		 */
		if (isLeaf()) return 0;
		int size = 0;
		for (int i = 0; i < component.size(); i++) 
			size += component.get(i).getSize();
		return size+ component.size();
	}
	public Component getChild (int index) {
		/*
		 * return a child by index.
		 */
		return component.get(index);
	}
	public ArrayList<Component> getAllChildren() {
		/*
		 * return all children component.
		 */
		return component;
	}
	public ArrayList<Component> getAllOfSping() {
		/*
		 * return all component.
		 * using preOder function to get them.
		 */
		ArrayList<Component> allOfSping= new ArrayList<Component>();
		preOrder(allOfSping,this);
		return allOfSping;
	}
	public void preOrder(ArrayList<Component> allOfSping,Component root) {
		/*
		 * search wikipedia.com to more detail.
		 */
		allOfSping.add(root);
		for(int i=0; i< root.getAllChildren().size(); i++) {
			preOrder(allOfSping,root.getChild(i));
		}
	}
	
	public Component getParent() {
		/*
		 * get parent of this.
		 */
		return parent;
	}
	public String printInfo() {
		/*
		 * return a string of this name and all ancestor name.
		 */
		if(parent==null) {
			return (this.toString());
		}
		String allDetail = ((Location)parent).printInfo()+this.toString();
		return allDetail;
	}
	
	public String print(int space) {
		/*
		 * return a String of all component name by tree structure.
		 */
		String tmp = "";
		if(isLeaf()) {
			for (int i=0; i<space; i++) 
				tmp = tmp + " ";
			tmp = tmp + this.toString() + System.getProperty("line.separator");
			return tmp;
		}
		
		for (int i = 0; i < component.size(); i++) {
			tmp += component.get(i).print(space+2);
		}
		return tmp;
	}
	public String toString() {
		return " "+placeName+".";
	}
}
