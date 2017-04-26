package com.clv.parttimejobs.view.ui.customview;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyPaperView extends ViewPager {

	private int currentItem;
	private int countItem;

	public MyPaperView(Context context) {
		super(context);
	}

	public MyPaperView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
		if (v != this && v instanceof ViewPager) {
			currentItem = ((ViewPager) v).getCurrentItem();
			countItem = ((ViewPager) v).getAdapter().getCount();
			if ((currentItem == (countItem - 1) && dx < 0)
					|| (currentItem == 0 && dx > 0)) {
				return false;
			}
			return true;
		}
		return super.canScroll(v, checkV, dx, x, y);
	}

}