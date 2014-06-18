/**
 * 
 * @author Abhishek Verma
 * @web www.androidlobby.com
 * 
 * Description: Main/launcher activity class integrating ListView
 */
package com.androidlobby.swipeablelistview;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.androidlobby.swipeablelistview.adapter.AdapterListView;
import com.androidlobby.swipeablelistview.callbacks.IHorizontalOrVerticalScroll;
import com.androidlobby.swipeablelistview.custom.CustomListView;
import com.androidlobby.swipeablelistview.data.DataSource;
import com.androidlobby.swipeablelistview.utils.ConstantFields;

public class ListViewActivity extends Activity implements OnItemClickListener, IHorizontalOrVerticalScroll, ConstantFields{

	private Context mContext = null;
	private CustomListView mListView = null;
	private AdapterListView mAdapterListView = null;
	private DataSource mDataSource = null;
	private ActionBar actionBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		init();
		setListeners();
		initListing();
	}
	
	private void init()
	{
		mContext = this;

		actionBar = getActionBar();

		mDataSource = DataSource.getInstance();
		mDataSource.init(mContext);
		
		mListView = (CustomListView) findViewById(R.id.activity_list_view_listview);
	}
	
	private void setListeners()
	{
		mListView.setCallBack(this);
		mListView.setOnItemClickListener(this);
	}

	private void initListing()
	{
		mAdapterListView = new AdapterListView(mContext, mDataSource.getValues(), this);
		mListView.setAdapter(mAdapterListView);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public void receiveNotification(String text) {
		actionBar.setTitle(text);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				actionBar.setTitle(getResources().getString(R.string.app_name));
			}
		}, TIME_OUT);
	}
}
