/**
 * 
 * @author Abhishek Verma
 * @web www.androidlobby.com
 * 
 * Description: ListView adapter class containing ViewPager
 */
package com.androidlobby.swipeablelistview.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidlobby.swipeablelistview.R;
import com.androidlobby.swipeablelistview.callbacks.IHorizontalOrVerticalScroll;
import com.androidlobby.swipeablelistview.custom.CustomViewPager;
import com.androidlobby.swipeablelistview.data.SwipeRowData;
import com.androidlobby.swipeablelistview.utils.ConstantFields;

public class AdapterListView extends BaseAdapter implements ConstantFields{

	private Context context = null;
	private ArrayList<SwipeRowData> arrayListSwipeData = null;
	
	private IHorizontalOrVerticalScroll horizontalOrVerticalScroll;
	
	public AdapterListView(Context context, ArrayList<SwipeRowData> arrayListSwipeData, IHorizontalOrVerticalScroll horizontalOrVerticalScroll) {
		this.context = context;
		this.arrayListSwipeData = arrayListSwipeData;
		this.horizontalOrVerticalScroll = horizontalOrVerticalScroll;
	}
	
	@Override
	public int getCount() {
		return arrayListSwipeData.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayListSwipeData.get(position).getSwipeRowValue();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		 Holder holder = null;
		    if (convertView == null) {
		    	convertView = View.inflate(context, R.layout.adapter_list_row, null);
		        holder = new Holder();
		        holder.viewPagerRow = (CustomViewPager) convertView.findViewById(R.id.view_pager_row);
		        holder.viewPagerRow.setCallBack(horizontalOrVerticalScroll);
		        convertView.setTag(holder);
		    } else {
		        holder = (Holder) convertView.getTag();
		    }

		    AdapterViewPager adapterViewPager = new AdapterViewPager(context, arrayListSwipeData.get(position).getSwipeRowValue());
		    holder.viewPagerRow.setAdapter(adapterViewPager);
		    holder.viewPagerRow.setCurrentItem(VIEW_PAGER_MAIN_PAGE);

		    return convertView;
	}

	class Holder {
	   public CustomViewPager viewPagerRow;
	}
}
