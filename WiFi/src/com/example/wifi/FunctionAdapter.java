package com.example.wifi;


import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * Set a normal view of each function on layout
 * 
 * */

public class FunctionAdapter extends BaseAdapter {
	
	final static int ICON = com.example.wifi.R.drawable.sc_func;
	
	//variables
	private static ArrayList<Functions> functionDetailList;
	//List of icon
	private Integer[] iconId = {
			ICON
			};
	
	private LayoutInflater layoutInflater;

	//Methods
	////constructor
	public FunctionAdapter(){super();}
	public FunctionAdapter(Context context, ArrayList<Functions> results) {
		super();
		functionDetailList = results;
		layoutInflater = LayoutInflater.from(context);
	}
	//<-- implement abstract methods 
	public int getCount() {return functionDetailList.size();}
	public Object getItem(int position) {return functionDetailList.get(position);}
	public long getItemId(int position) {return position;}
	//-->
	//make all functions in listView
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;//hold all components of functions in layout
		
		if (convertView == null) {//set for first run
			convertView = layoutInflater.inflate(R.layout.adapter_function, null);//set in layout
			viewHolder = new ViewHolder();
			viewHolder.nameFunction = (TextView) convertView.findViewById(R.id.function_name);//set_Name
			viewHolder.iconFunction = (ImageView) convertView.findViewById(R.id.function_icon);//set_icon
			convertView.setTag(viewHolder);											//set_tag
		} else {//set for other run
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.nameFunction.setText(functionDetailList.get(position).getName());
		viewHolder.iconFunction.setImageResource(iconId[functionDetailList.get(position).getImageNumber() - 1]);
		return convertView;
	}

	static class ViewHolder {
		TextView nameFunction;//name of function
		ImageView iconFunction;  // icon
	}
}
