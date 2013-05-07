package com.example.wifilocator_rebuild.module;

import java.util.ArrayList;


/**
 * composite design pattern;
 * interface of tree structure.
 * add some function to get more convenient.
 * search google or wikipedia to more detail. 
 */
public interface Component {
	
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
	public ArrayList<Component> getAllOfspings();
}
