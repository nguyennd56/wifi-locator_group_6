package com.example.wifi;

/*
 * Object Function with simple infomation : name, icon
 * 
 * */
public class Functions {
	
	//Variables
	private String name ;	//name of function
	private int icon;		//icon of function
	
	//Methods
	////Constructor
	public Functions(){super();}
	public Functions(String name, int icon){
		super();this.name=name;this.icon=icon;
	}
	////get
	public String getName() {
		return name;
	}
	public int getImageNumber() {
		return icon;
	}
	////set
	public void setName(String name) {
		this.name = name;
	}
	
	public void setImageNumber(int icon) {
		this.icon = icon;
	}
	
}
