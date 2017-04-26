package com.clv.parttimejobs.activity.minelayout.set;


import com.clv.homework.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GHYX_activity extends Activity {

	private ImageButton sz_return_button;
	private Button ghbd_submit;
	private Button register_huoqu_button;
	private TextView register_huoqu_textview;
	private boolean isSend=true;
	private int currentTime=30;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ghyx_activity_layout);
		init();
	}

	public void init(){
		sz_return_button =(ImageButton) findViewById(R.id.sz_return_button);
		ghbd_submit=(Button) findViewById(R.id.ghbd_submit);
		register_huoqu_button=(Button) findViewById(R.id.register_huoqu_button);
		register_huoqu_textview=(TextView) findViewById(R.id.register_huoqu_textview);
		InnerOnCLickListener i =new InnerOnCLickListener();
		sz_return_button.setOnClickListener(i);
		ghbd_submit.setOnClickListener(i);
		register_huoqu_button.setOnClickListener(i);
	}
	private class InnerOnCLickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.sz_return_button:{
				GHYX_activity.this.finish();
				break;
			}
			case R.id.ghbd_submit:{
				GHYX_activity.this.finish();
				break;
			}
			case R.id.register_huoqu_button:{
				new Thread(new MyThread1()).start();
				register_huoqu_button.setVisibility(8);
				register_huoqu_textview.setVisibility(1);
				break;
			}
			}
		}
		
	}
	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				if (currentTime > 1) {
					currentTime--;
					register_huoqu_textview.setText("已发�?( " + currentTime + " )");
				} else {
					currentTime = 60;
					register_huoqu_button.setVisibility(1);
					register_huoqu_textview.setVisibility(8);
					isSend = false;
				}
			}
			default: {

			}
			}

			super.handleMessage(msg);
		}
	};

	public class MyThread1 implements Runnable { // thread
		@Override
		public void run() {
			
				while (true) {
					if (isSend) {
					try {
						Thread.sleep(1000); // sleep 1000ms
						Message message = new Message();
						message.what = 1;
						handler1.sendMessage(message);
					} catch (Exception e) {
					}
				}
			}
		}
	}

}
