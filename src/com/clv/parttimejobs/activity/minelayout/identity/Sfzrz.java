package com.clv.parttimejobs.activity.minelayout.identity;

import com.clv.homework.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class Sfzrz  extends Activity{

	private ScrollView scrollview;
	private ImageButton mysfrz_image03;
	private ImageButton mycreadit_return_button;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sfrz_layout);
		scrollview =(ScrollView) findViewById(R.id.sfrz_scrollview);
		scrollview.setVerticalScrollBarEnabled(false);
		mysfrz_image03=(ImageButton) findViewById(R.id.mysfrz_image03);
		mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
		
		InnerOnclickListener i =new InnerOnclickListener();
		mysfrz_image03.setOnClickListener(i);
		mycreadit_return_button.setOnClickListener(i);
	}
	private class InnerOnclickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.mysfrz_image03:{
				startActivity(new Intent(Sfzrz.this,Sfz_activity.class));
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
				break;
			}
			case R.id.mycreadit_return_button:{
				Sfzrz.this.finish();
				break;
			}
			}
		}
		
	}
}
