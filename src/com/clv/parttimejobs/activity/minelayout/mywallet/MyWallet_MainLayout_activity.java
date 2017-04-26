package com.clv.parttimejobs.activity.minelayout.mywallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.clv.homework.R;

public class MyWallet_MainLayout_activity extends Activity{
	private ImageButton return_button;
	private TextView moneycount_textview;
	private Button chongzhi_button;
	private Button tixian_button;
	private Button mybd_button;
	private Button progress_button;
	private Button zzdetail_button;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywallet_mainlayout_activity);
		init();
	}
	private void init(){
		return_button=(ImageButton)findViewById(R.id.mymywallet_mainlayout_return_button);
		moneycount_textview =(TextView)findViewById(R.id.mymywallet_mainlayout_text_moneycount);
		chongzhi_button =(Button)findViewById(R.id.mywallet_chongzhi_button);
		tixian_button=(Button)findViewById(R.id.mywallet_tixian_button);
		mybd_button=(Button)findViewById(R.id.mymywallet_mainlayout_rey04_button_1);
		progress_button=(Button)findViewById(R.id.mymywallet_mainlayout_rey05_button_1);
		zzdetail_button=(Button)findViewById(R.id.mymywallet_mainlayout_rey06_button_1);
		InnerOnClickListener i =new InnerOnClickListener();
		return_button.setOnClickListener(i);
		chongzhi_button.setOnClickListener(i);
		tixian_button.setOnClickListener(i);
		mybd_button.setOnClickListener(i);
		progress_button.setOnClickListener(i);
		zzdetail_button.setOnClickListener(i);
	}
	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.mymywallet_mainlayout_return_button:{
				MyWallet_MainLayout_activity.this.finish();
;				break;
			}
			case R.id.mywallet_chongzhi_button:{
				break;
			}
			case R.id.mywallet_tixian_button:{
				Intent intent =new Intent(MyWallet_MainLayout_activity.this,MyWallet_tx_activity.class);
				startActivity(intent);
				break;
			}
			case R.id.mymywallet_mainlayout_rey04_button_1:{
				Intent intent =new Intent(MyWallet_MainLayout_activity.this,MyWallet_mybd_activity.class);
				startActivity(intent);
				break;
			}
			case R.id.mymywallet_mainlayout_rey05_button_1:{
				Intent intent =new Intent(MyWallet_MainLayout_activity.this,MyWallet_txjd_activity.class);
				startActivity(intent);
				break;
			}
			case R.id.mymywallet_mainlayout_rey06_button_1:{
				Intent intent =new Intent(MyWallet_MainLayout_activity.this,MyWallet_zdmx_activity.class);
				startActivity(intent);
				break;
			}
			}
		}
		
	}
}
