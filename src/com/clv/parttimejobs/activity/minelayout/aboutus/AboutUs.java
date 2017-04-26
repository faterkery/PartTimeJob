package com.clv.parttimejobs.activity.minelayout.aboutus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.minelayout.dailyattendance.QdActivity;
import com.clv.parttimejobs.view.ui.customview.SildingFinishLayout;
import com.clv.parttimejobs.view.ui.customview.SildingFinishLayout.OnSildingFinishListener;

public class AboutUs extends Activity {

	private ImageButton returnButton;
	private ScrollView about_us_scrollview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us_layout);
		returnButton=(ImageButton) findViewById(R.id.about_us_return_button);
		about_us_scrollview =(ScrollView)findViewById(R.id.about_us_scrollview);
		InnerOnClickListener i =new InnerOnClickListener();
		returnButton.setOnClickListener(i);
		
		SildingFinishLayout mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);  
        mSildingFinishLayout  
                .setOnSildingFinishListener(new OnSildingFinishListener() {  
  
                    @Override  
                    public void onSildingFinish() {  
                    	AboutUs.this.finish();  
                    }  
                });  
        mSildingFinishLayout.setTouchView(about_us_scrollview);
        mSildingFinishLayout.setVerticalScrollBarEnabled(false);
	}

	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.about_us_return_button:{
				AboutUs.this.finish();
				break;
			}
			}
		}
		
	}
}
