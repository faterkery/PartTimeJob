package com.clv.parttimejobs.activity.minelayout.contactus;

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

public class AboutSoftware extends Activity{
	private ImageButton returnButton;
	private ScrollView about_soft_scrollview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_software_layout);
		returnButton=(ImageButton) findViewById(R.id.about_soft_return_button);
		InnerOnClickListener i =new InnerOnClickListener();
		returnButton.setOnClickListener(i);
		
		about_soft_scrollview =(ScrollView)findViewById(R.id.about_soft_scrollview);
		SildingFinishLayout mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);  
        mSildingFinishLayout  
                .setOnSildingFinishListener(new OnSildingFinishListener() {  
  
                    @Override  
                    public void onSildingFinish() {  
                    	AboutSoftware.this.finish();  
                    }  
                });  
        mSildingFinishLayout.setTouchView(about_soft_scrollview);  
        about_soft_scrollview.setVerticalScrollBarEnabled(false);
	}

	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.about_soft_return_button:{
				System.out.println("µã»÷");
				AboutSoftware.this.finish();
				break;
			}
			}
		}
		
	}
}