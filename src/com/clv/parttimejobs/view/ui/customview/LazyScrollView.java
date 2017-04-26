package com.clv.parttimejobs.view.ui.customview;

   
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;
     
public class LazyScrollView extends ScrollView{    

	GestureDetector gestureDetector;

	    public LazyScrollView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        gestureDetector = new GestureDetector(new Yscroll());
	        setFadingEdgeLength(0);
	    }

	    public boolean onInterceptTouchEvent(MotionEvent ev) {
	        //return super.onInterceptTouchEvent(ev) && gestureDetector.onTouchEvent(ev);
	    	int    mDownX = 0,mDownY = 0;
	    	switch (ev.getAction()) {  
	           case MotionEvent.ACTION_DOWN:  
	               mDownX  = (int) ev.getRawX();  
	               mDownY = (int) ev.getRawY();  
	               break;  
	           case MotionEvent.ACTION_MOVE:  
	               int moveX = (int) ev.getRawX();  
	               // 满足此条件屏蔽SildingFinishLayout里面子类的touch事件  
	               if (Math.abs(moveX - mDownX) > 6  
	                       && Math.abs((int) ev.getRawY() - mDownY) <6) {
	                   return false;  
	               }  
	               break;  
	           }  
	     
	           return super.onInterceptTouchEvent(ev);
	    }

	    class Yscroll extends GestureDetector.SimpleOnGestureListener {

	        @Override
	        public boolean onScroll(MotionEvent e1, MotionEvent e2,
	                                float distanceX, float distanceY) {
	            //控制手指滑动的距�?
	            if (Math.abs(distanceY) >= Math.abs(distanceX)) {
	                return true;
	            }
	            return false;
	        }
	    }
	    
} 