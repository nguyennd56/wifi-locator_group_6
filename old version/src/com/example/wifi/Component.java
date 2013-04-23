package com.example.wifi;

import java.util.ArrayList;


public interface Component {
	public void add(Component c);
	public void remove(int index);
	public int getSize();
	public Component getChild(int index);
	public ArrayList<Component> getAllChildren();
	public boolean isLeaf();
	public Component getParent();
	public String printInfo();
	public String print(int space);
}
