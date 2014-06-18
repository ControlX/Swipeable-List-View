/**
 * 
 * @author Abhishek Verma
 * @web www.androidlobby.com
 * 
 * Description: ViewPager adapter class containing Button as a row child
 */
package com.androidlobby.swipeablelistview.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

import com.androidlobby.swipeablelistview.R;
import com.androidlobby.swipeablelistview.custom.CustomViewPager;
import com.androidlobby.swipeablelistview.utils.ConstantFields;

public class AdapterViewPager extends PagerAdapter implements OnClickListener, ConstantFields{
	
	private Context context = null;
	private String stringButtonText = null;
	
	public AdapterViewPager(Context context, String stringButtonText) {
		this.context = context;
		this.stringButtonText = stringButtonText;
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		Button button = new Button(context);
		button.setId(VIEW_PAGER_MAIN_BUTTON_ID);
		button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		button.setGravity(Gravity.CENTER);
		button.setTextColor(context.getResources().getColor(android.R.color.white));
		button.setTextSize(context.getResources().getInteger(R.integer.text_size_14));
		button.setOnClickListener(this);
		
		if(position == VIEW_PAGER_PREVIOUS_PAGE)  
			button.setBackgroundColor(context.getResources().getColor(android.R.color.holo_purple));
		else if(position == VIEW_PAGER_MAIN_PAGE)
			button.setBackgroundColor(context.getResources().getColor(android.R.color.black));
		else if(position == VIEW_PAGER_NEXT_PAGE)
			button.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
		
		  if(position == VIEW_PAGER_MAIN_PAGE)
			  button.setText(stringButtonText);
		  else
			  button.setText("");
		  
		  ((CustomViewPager) container).addView(button, 0);
		  return button;
	}
	
	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		 return arg0 == ((View) arg1);
	}

	 @Override
	 public void destroyItem(View arg0, int arg1, Object arg2) {
	  ((CustomViewPager) arg0).removeView((View) arg2);
	 }

     @Override
    public int getItemPosition(Object object) {
    	 return	((ViewPager) object).getCurrentItem();
    }
	 
	@Override
	public void onClick(View v) {
		 if(getItemPosition(v.getParent()) == VIEW_PAGER_PREVIOUS_PAGE)
		 {
			 Toast.makeText(context, R.string.you_clicked_on_purple_colour, Toast.LENGTH_SHORT).show();
		 }
		 else if(getItemPosition(v.getParent()) == VIEW_PAGER_MAIN_PAGE)
		 {
			 Toast.makeText(context, R.string.you_clicked_on_the_month_+stringButtonText, Toast.LENGTH_SHORT).show();
		 }
		 else if(getItemPosition(v.getParent()) == VIEW_PAGER_NEXT_PAGE)
		 {
			 Toast.makeText(context, R.string.you_clicked_on_green_colour, Toast.LENGTH_SHORT).show();
		 }
	}
}
