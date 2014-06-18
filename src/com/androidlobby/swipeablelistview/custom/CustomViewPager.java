/**
 * 
 * @author Abhishek Verma
 * @web www.androidlobby.com
 * 
 * Description: CustomViewPager class listening to gestures
 */
package com.androidlobby.swipeablelistview.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import com.androidlobby.swipeablelistview.R;
import com.androidlobby.swipeablelistview.callbacks.IHorizontalOrVerticalScroll;

public class CustomViewPager extends ViewPager{

	private boolean isLock = false;
	private Context context = null;
	private GestureDetector gestureDetector = null;
	private IHorizontalOrVerticalScroll horizontalOrVerticalScroll;

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.context = context;
		gestureDetector = new GestureDetector(context, new DetectScroll());
	}

	/**
	 * Listening to touch events and locking ViewPager to use
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!isLock)
			isLock = gestureDetector.onTouchEvent(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP: {
			isLock = false;
			break;
		}
		}
		getParent().requestDisallowInterceptTouchEvent(isLock);
		return super.onTouchEvent(event);
	}

	/**
	 * Calculating swipe direction
	 * NOTE: Buggy implementation and shows wrong information sometimes
	 */
	private class DetectScroll extends SimpleOnGestureListener {
		private float distanceXNew = 0;

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if(distanceXNew == 0)
				distanceXNew = distanceX;

			if (horizontalOrVerticalScroll != null)
			{
				if(distanceX <= distanceXNew)
					horizontalOrVerticalScroll.receiveNotification(context.getResources().getString(R.string.you_are_swiping_row_to_left));
				else
					horizontalOrVerticalScroll.receiveNotification(context.getResources().getString(R.string.you_are_swiping_row_to_right));
			}
			distanceXNew = distanceX;
			return true;
		}
	}

	public void setCallBack(IHorizontalOrVerticalScroll horizontalOrVerticalScroll)
	{
		this.horizontalOrVerticalScroll = horizontalOrVerticalScroll;
	}
}
