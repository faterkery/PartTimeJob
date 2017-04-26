package com.clv.parttimejobs.activity.minelayout.identity;

import com.clv.homework.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class Sfz_activity extends Activity {

	private ImageButton mysfrz_imagebutton_1;
	private ImageButton mycreadit_return_button;
	private ScrollView scrollview;
	private Button gotoRz_sf;
	private Button gotoRz_student;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sfz);
		mysfrz_imagebutton_1 =(ImageButton) findViewById(R.id.mysfrz_imagebutton_1);
		scrollview=(ScrollView) findViewById(R.id.mysfrz_scrollview);
		scrollview.setVerticalScrollBarEnabled(false);
		gotoRz_sf=(Button) findViewById(R.id.mysfrz_button_01);
		gotoRz_student=(Button) findViewById(R.id.mysfrz_button_02);
		mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
		
		InnerOnClickListener i =new InnerOnClickListener();
		mysfrz_imagebutton_1.setOnClickListener(i);
		gotoRz_sf.setOnClickListener(i);
		gotoRz_student.setOnClickListener(i);
		mycreadit_return_button.setOnClickListener(i);
	}

	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.mysfrz_imagebutton_1:{
				startActivity(new Intent(Sfz_activity.this,Fbzj.class));
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
				break;
			}
			case R.id.mysfrz_button_01:{
				startActivity(new Intent(Sfz_activity.this,Sfzrz_sh.class));
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
				break;
			}
			case R.id.mysfrz_button_02:{
				startActivity(new Intent(Sfz_activity.this,Xsrz_sh.class));
				overridePendingTransition(R.anim.leftin, R.anim.leftout);
				break;
			}
			case R.id.mycreadit_return_button:{
				Sfz_activity.this.finish();
				break;
			}
			}
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
