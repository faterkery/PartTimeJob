package com.clv.parttimejobs.activity.minelayout.mywallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.clv.homework.R;

public class MyWalletActivity extends Activity{

	private ImageButton startButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywallet_activity_main);
		init();
	}
	private void init(){
		startButton=(ImageButton)findViewById(R.id.mywallet_startlayout_button);
		
		InnerOnClickListener i =new InnerOnClickListener();
		startButton.setOnClickListener(i);
	}
	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.mywallet_startlayout_button:{
				
				Intent intent =new Intent(MyWalletActivity.this,MyWallet_lrmm_activity.class);
				startActivityForResult(intent, 100);
				break;
			}
			}
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100){
			MyWalletActivity.this.finish();
		}
	}
}
