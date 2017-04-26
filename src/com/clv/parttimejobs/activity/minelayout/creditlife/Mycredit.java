package com.clv.parttimejobs.activity.minelayout.creditlife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.clv.homework.R;
import com.clv.parttimejobs.view.ui.customview.SildingFinishLayout;
import com.clv.parttimejobs.view.ui.customview.SildingFinishLayout.OnSildingFinishListener;

public class Mycredit extends Activity {
	private ImageButton returnButton;
	private ScrollView school_scrollview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycreditlayout);
			returnButton=(ImageButton) findViewById(R.id.mycreadit_return_button);
			school_scrollview=(ScrollView)findViewById(R.id.school_scrollview);
			InnerOnClickListener i =new InnerOnClickListener();
			returnButton.setOnClickListener(i);
			
			SildingFinishLayout mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);  
	        mSildingFinishLayout  
	                .setOnSildingFinishListener(new OnSildingFinishListener() {  
	  
	                    @Override  
	                    public void onSildingFinish() {  
	                    	Mycredit.this.finish();  
	                    }  
	                });  
	        mSildingFinishLayout.setTouchView(school_scrollview);  
	        school_scrollview.setVerticalScrollBarEnabled(false);
		}

		private class InnerOnClickListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId()){
				case R.id.mycreadit_return_button:{
					Mycredit.this.finish();
					break;
				}
				}
			}
			
		}
	}