package com.example.wifilocator_rebuild.module;

import java.util.ArrayList;


public interface Component {
	/**
	 * Component pattern;
	 * interface of tree structure.
	 * search google of wikipedia to more detail. 
	 */
	
	public void add(Component c);
	public void remove(int index);
	public int getSize();
	public Component getChild(int index);
	public ArrayList<Component> getAllChildren();
	public boolean isLeaf();
	public Component getRoot();
	public Component getParent();
	public String printInfo();
	public String print(int space);
	public ArrayList<Component> getAllOfSping();
}
