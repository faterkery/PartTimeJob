package com.clv.parttimejobs.view.ui.customview;

import java.util.HashMap;
import java.util.Map;

import com.clv.parttimejobs.model.interfacebackage.mainlayout.mywallet.ScrollViewListener;
import com.clv.parttimejobs.view.adapter.my.mywallet.HorizontalScrollViewAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MyHorizontalScrollView extends HorizontalScrollView implements
		View.OnClickListener {
	private OnItemClickListener mOnClickListener;
	private LinearLayout mContainer;

	private ScrollViewListener scrollViewListener;
	/**
	 * 子元素的宽度
	 */
	private int mChildWidth;
	/**
	 * 子元素的高度
	 */
	private int mChildHeight;
	/**
	 * 当前最后一张图片的index
	 */
	private int mCurrentIndex;
	/**
	 * 当前第一张图片的下标
	 */
	private int mFristIndex;
	private int index = 0;
	/**
	 * 当前第一个View
	 */
	private View mFirstView;
	/**
	 * 数据适配器
	 */
	// private HorizontalScrollViewAdapter mAdapter;
	/**
	 * 每屏幕最多显示的个数
	 */
	private int mCountOneScreen;
	/**
	 * 屏幕的宽度
	 */
	private int mScreenWitdh;

	/**
	 * 保存View与位置的键值对
	 */
	private HorizontalScrollViewAdapter mAdapter;
	private Map<View, Integer> mViewPos = new HashMap<View, Integer>();

	public interface OnItemClickListener {
		void onClick(View view, int pos);
	}

	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWitdh = outMetrics.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mContainer = (LinearLayout) getChildAt(0);
	}

	public void initView(HorizontalScrollViewAdapter mAdapter) {
		this.mAdapter = mAdapter;
		mContainer = (LinearLayout) getChildAt(0);
		View v = mAdapter.getView(0, null, mContainer);
		;
		mContainer.addView(v);// why add
		if (mChildWidth == 0 && mChildHeight == 0) {
			int w = View.MeasureSpec.makeMeasureSpec(0,
					View.MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0,
					View.MeasureSpec.UNSPECIFIED);
			v.measure(w, h);
			mChildHeight = v.getMeasuredHeight();
			mChildWidth = v.getMeasuredWidth();
			
			mCountOneScreen = mScreenWitdh / mChildWidth + 2;
		}
		initFirstScreenView(mCountOneScreen);
	}

	private void initFirstScreenView(int mCountOneScreen2) {
		mContainer = (LinearLayout) getChildAt(0);
		mContainer.removeAllViews();
		mViewPos.clear();
		for (int i = 0; i < mCountOneScreen; i++) {
			View v = mAdapter.getView(i, null, mContainer);
			v.setOnClickListener(this);
			mContainer.addView(v);
			mViewPos.put(v, i);
			mCurrentIndex = i;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_MOVE:
			int scrollX = getScrollX();
			System.out.println(scrollX);
			if (scrollX >= mChildWidth) {
				loadNext();
			}
			if (scrollX == 0) {
				loadPre();
			}
			if(scrollX >= 360){
				index = mFristIndex;index++;
				System.out.println(index);
				System.out.println("增加");
				if(scrollViewListener!=null){
					notifyCurrentImgChanged();
				}
			}
//			if (scrollX == 0){
//				System.out.println("减少");
//				index = mFristIndex;index--;
//				if(scrollViewListener!=null){
//					notifyCurrentImgChanged();
//				}
//			}
			break;

		}
		return super.onTouchEvent(ev);
	}

	private void loadNext() {
		View v;
		scrollTo(0, 0);
		mViewPos.remove(mContainer.getChildAt(0));
		mContainer.removeViewAt(0);
		if (mCurrentIndex == mAdapter.getCount() - 1) {
			v = mAdapter.getView(0, null, mContainer);
			mCurrentIndex = 0;
		} else {
			v = mAdapter.getView(++mCurrentIndex, null, mContainer);
		}
		v.setOnClickListener(this);
		mContainer.addView(v);
		mViewPos.put(v, mCurrentIndex);
		mFristIndex++;
		
	}

	private void loadPre() {
		int oldViewPos = mContainer.getChildCount() - 1;

		if (mFristIndex == 0) {
			mViewPos.remove(mContainer.getChildAt(oldViewPos));
			mContainer.removeViewAt(oldViewPos);
			View view = mAdapter.getView(mAdapter.getCount() - 1, null,
					mContainer);
			mViewPos.put(view, mAdapter.getCount() - 1);
			mContainer.addView(view, 0);
			view.setOnClickListener(this);
			// 水平滚动位置向左移动view的宽度个像素
			scrollTo(mChildWidth, 0);

			mFristIndex = mAdapter.getCount() - 1;
		} else {
			int index = mCurrentIndex - mCountOneScreen;
			if (index >= 0) {
				mViewPos.remove(mContainer.getChildAt(oldViewPos));
				mContainer.removeViewAt(oldViewPos);
				View view = mAdapter.getView(index, null, mContainer);
				mViewPos.put(view, index);
				mContainer.addView(view, 0);
				view.setOnClickListener(this);
				// 水平滚动位置向左移动view的宽度个像素
				scrollTo(mChildWidth, 0);
				mFristIndex--;
			} else {
				mViewPos.remove(mContainer.getChildAt(oldViewPos));
				mContainer.removeViewAt(oldViewPos);
				View view = mAdapter.getView(index + mAdapter.getCount(), null,
						mContainer);
				mViewPos.put(view, index + mAdapter.getCount());
				mContainer.addView(view, 0);
				view.setOnClickListener(this);
				// 水平滚动位置向左移动view的宽度个像素
				scrollTo(mChildWidth, 0);
				// 当前位置--，当前第一个显示的下标--
				mFristIndex--;
			}
		}
		mCurrentIndex = mViewPos
				.get(mContainer.getChildAt(mCountOneScreen - 1));
		
		
	}

	public void notifyCurrentImgChanged(){
		scrollViewListener.scrollviewChange(index);
	}
	@Override
	public void onClick(View v) {
		if (mOnClickListener != null) {

			mOnClickListener.onClick(v, mViewPos.get(v));
		}
	}

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {  
        this.scrollViewListener = scrollViewListener;  
    }  
}