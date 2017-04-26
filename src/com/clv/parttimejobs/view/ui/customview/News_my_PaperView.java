package com.clv.parttimejobs.view.ui.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;

public class News_my_PaperView extends ViewPager {

	private static final String TAG = "dzt_pager";
	private static final int MOVE_LIMITATION = 1;// 触发移动的像素距离
	private float mLastMotionX; // 手指触碰屏幕的最后一次x坐标
	private int mCurScreen;

	private Scroller mScroller; // 滑动控件

	public News_my_PaperView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public News_my_PaperView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		mScroller = new Scroller(context);
		mCurScreen = 0;// 默认设置显示第一个VIEW
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		final int action = event.getAction();
		final float x = event.getX();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "[BTViewPager->]onTouchEvent ACTION_DOWN");
			mLastMotionX = x;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "[BTViewPager->]onTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "Item = " + getCurrentItem() + " count = "
					+ getChildCount());

			if (Math.abs(x - mLastMotionX) < MOVE_LIMITATION) {
				// snapToDestination(); // 跳到指定页
				snapToScreen(getCurrentItem());
				return true;
			}
			break;
		default:
			break;
		}
		Log.d(TAG, "[BTViewPager->]onTouchEvent--end");
		return super.onTouchEvent(event);
	}

	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		Log.d(TAG, "[BTViewPager->]computeScroll");
		super.computeScroll();

		if (mScroller.computeScrollOffset()) {
			Log.d(TAG,
					"[BTViewPager->]computeScroll x = " + mScroller.getCurrX());
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			invalidate();
		}

	}

	/**
	 * 根据滑动的距离判断移动到第几个视图
	 */
	public void snapToDestination() {
		final int screenWidth = getWidth();
		final int destScreen = (getScrollX() + screenWidth / 2) / screenWidth;
		Log.d(TAG, "[BTViewPager->]snapToDestination screenWidth = "
				+ screenWidth + " destScreen = " + destScreen);
		snapToScreen(destScreen);
	}

	/**
	 * 滚动到制定的视图
	 * 
	 * @param whichScreen
	 *            视图下标
	 */
	public void snapToScreen(int whichScreen) {
		// whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() -
		// 1));
		if (getScrollX() != (whichScreen * getWidth())) {

			final int delta = whichScreen * getWidth() - getScrollX();
			Log.d(TAG, "[BTViewPager->]snapToScreen-whichScreen = "
					+ whichScreen + " delta = " + delta + " scrollX = "
					+ getScrollX());
			mScroller.startScroll(getScrollX(), 0, delta, 0,
					Math.abs(delta) * 2);
			mCurScreen = whichScreen;
			invalidate();
		}
	}

	/**
	 * 用于拦截手势事件的，每个手势事件都会先调用这个方法。Layout里的onInterceptTouchEvent默认返回值是false,
	 * 这样touch事件会传递到childview控件 ，如果返回false子控件可以响应，否则了控件不响应，这里主要是拦截子控件的响应，
	 * 对ViewGroup不管返回值是什么都会执行onTouchEvent
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "[BTViewPager->]onInterceptTouchEvent");
		final int action = arg0.getAction();
		final float x = arg0.getX();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "onInterceptTouchEvent---ACTION_DOWN ");
			mLastMotionX = x;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "onInterceptTouchEvent---ACTION_MOVE ");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, "onInterceptTouchEvent---ACTION_UP ");
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(arg0);
	}
}
