package com.clv.parttimejobs.view.ui.customview;

import android.content.Context;  
import android.util.AttributeSet;  
import android.view.MotionEvent;  
import android.widget.GridView;  
  
public class MyGridView extends GridView{  
  
    public MyGridView(Context context) {  
        super(context);  
    }  
    public MyGridView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
    }  
  
    @Override    
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {    
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,          
                MeasureSpec.AT_MOST);    
        super.onMeasure(widthMeasureSpec, expandSpec);    
    }   
    @Override  
    public boolean dispatchTouchEvent(MotionEvent ev) {  
        if(ev.getAction() == MotionEvent.ACTION_MOVE){       
            return true;     
        }    
        return super.dispatchTouchEvent(ev);  
    }  
  
}  