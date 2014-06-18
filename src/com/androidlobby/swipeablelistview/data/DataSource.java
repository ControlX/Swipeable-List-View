/**
 * 
 * @author Abhishek Verma
 * @web www.androidlobby.com
 * 
 * Description: Singleton class managing data retrieval/addition
 */
package com.androidlobby.swipeablelistview.data;

import java.util.ArrayList;

import android.content.Context;

import com.androidlobby.swipeablelistview.R;

public class DataSource {

	private String [] mDataArray;
	private ArrayList<SwipeRowData> mArraySwipeData = null;
	private static DataSource dataSource = null;
	 
	public void init(Context context){
	    mDataArray = context.getResources().getStringArray(R.array.array_data);
	    mArraySwipeData = new ArrayList<SwipeRowData>();
	    
	    for(int i=0; i<mDataArray.length; i++)
		{
			mArraySwipeData.add(i, new SwipeRowData(mDataArray[i]));
		}
	 }
	
	private DataSource() {
	}
	
	public static DataSource getInstance()
	{
		if(dataSource == null)
		{
			dataSource = new DataSource();
		}
		return dataSource;
	}
	
	public ArrayList<SwipeRowData> getValues()
	{
		return mArraySwipeData;
	}
	
	public void setValues(int arrayPosition)
	{
		mArraySwipeData.add(arrayPosition, new SwipeRowData(mDataArray[arrayPosition]));
	}
}


