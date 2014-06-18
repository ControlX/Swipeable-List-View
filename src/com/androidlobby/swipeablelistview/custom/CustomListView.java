/**
 * 
 * @author Abhishek Verma
 * @web www.androidlobby.com
 * 
 * Description: CustomListView class detecting gestures
 */
package com.androidlobby.swipeablelistview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;

import com.androidlobby.swipeablelistview.R;
import com.androidlobby.swipeablelistview.callbacks.IHorizontalOrVerticalScroll;

public class CustomListView extends ListView{

	private boolean isLock = false;
	private Context context = null;
	private GestureDetector gestureDetector = null;
	private IHorizontalOrVerticalScroll horizontalOrVerticalScroll;

	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.context = context;
		gestureDetector = new GestureDetector(context, new DetectScroll());
	}

	/**
	 * Listening to touch events and locking ListView to use
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

		private float distanceYNew = 0;
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

			if(distanceYNew == 0)
				distanceYNew = distanceY;

			if (horizontalOrVerticalScroll != null)
			{
				if(distanceY <= distanceYNew)
					horizontalOrVerticalScroll.receiveNotification(context.getResources().getString(R.string.you_are_swiping_listview_upwards));
				else
					horizontalOrVerticalScroll.receiveNotification(context.getResources().getString(R.string.you_are_swiping_listview_downwards));
			}
			distanceYNew = distanceY;
			return true;
		}
	}

	public void setCallBack(IHorizontalOrVerticalScroll horizontalOrVerticalScroll)
	{
		this.horizontalOrVerticalScroll = horizontalOrVerticalScroll;
	}

}
