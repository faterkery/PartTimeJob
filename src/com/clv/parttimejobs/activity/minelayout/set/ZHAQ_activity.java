package com.clv.parttimejobs.activity.minelayout.set;

import com.clv.homework.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ZHAQ_activity extends Activity {

	private ImageButton sz_return_button;
	private Button zhaq_button_1;
	private Button zhaq_button_2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhaq_activity_layout);
		init();
	}

	public void init(){
		sz_return_button=(ImageButton) findViewById(R.id.sz_return_button);
		zhaq_button_1=(Button) findViewById(R.id.zhaq_button_1);
		zhaq_button_2=(Button) findViewById(R.id.zhaq_button_2);
		InnerOnCLickListener i =new InnerOnCLickListener();
		sz_return_button.setOnClickListener(i);
		zhaq_button_1.setOnClickListener(i);
		zhaq_button_2.setOnClickListener(i);
	}
	private class InnerOnCLickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.sz_return_button:{
				ZHAQ_activity.this.finish();
				break;
			}
			case R.id.zhaq_button_1:{
				Intent intent  =new Intent(ZHAQ_activity.this,GHBD_activity.class);
				startActivity(intent);
				break;
			}
			case R.id.zhaq_button_2:{
				Intent intent  =new Intent(ZHAQ_activity.this,GHYX_activity.class);
				startActivity(intent);
				break;
			}
			}
		}
		
	}

}
