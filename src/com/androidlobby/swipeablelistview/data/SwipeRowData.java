/**
 * 
 * @author Abhishek Verma
 * @web www.androidlobby.com
 * 
 * Description: Getter class containing ViewPager row information. Can be updated with more information.
 */
package com.androidlobby.swipeablelistview.data;

public class SwipeRowData {

	private String swipeRowValue;

	public SwipeRowData(String swipeRowValue) {
		this.swipeRowValue = swipeRowValue;
	}
	
	/**
	 * @return the swipeRowValue
	 */
	public String getSwipeRowValue() {
		return swipeRowValue;
	}
}
