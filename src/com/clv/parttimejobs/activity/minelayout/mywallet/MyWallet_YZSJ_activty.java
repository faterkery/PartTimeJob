package com.clv.parttimejobs.activity.minelayout.mywallet;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.clv.homework.R;

public class MyWallet_YZSJ_activty extends Activity{

	private boolean isSend =false;//发送位
	private int currentTime=60;
	
	private Button returnButton;
	private EditText edittext;
	private ImageButton sendButton;
	private TextView waitText;
	private Button bdButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywallet_yzsj_layout);
		init();
		initView();
	}
	private void init(){
		returnButton =(Button) findViewById(R.id.mymywallet_yzsj_return_button);
		edittext=(EditText) findViewById(R.id.mymywallet_yzsj_edittext);
		sendButton=(ImageButton) findViewById(R.id.mymywallet_yzsj_imagebutton_send);
		waitText=(TextView) findViewById(R.id.mymywallet_yzsj_textview05);
		bdButton=(Button) findViewById(R.id.mymywallet_yzsj_imagebutton);
	}
	public void initView(){
		edittext.addTextChangedListener(textWatcher); 
		InnerOnClickListener i =new InnerOnClickListener();
		returnButton.setOnClickListener(i);
		sendButton.setOnClickListener(i);
		bdButton.setOnClickListener(i);
	}
	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.mymywallet_yzsj_return_button:{
				MyWallet_YZSJ_activty.this.finish();
				break;
			}
			case R.id.mymywallet_yzsj_imagebutton_send:{
				sendButton.setVisibility(8);
				waitText.setVisibility(1);
				isSend=true;
				new Thread(new MyThread1()).start();
				break;
			}
			case R.id.mymywallet_yzsj_imagebutton:{
				break;
			}
			}
		}
		
	}
	private TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(!(("").equals(edittext.getText().toString()))){
				bdButton.setBackgroundResource(R.drawable.select_mywallet_yzsj_button);
				bdButton.setClickable(true);
			}else{
				bdButton.setBackgroundResource(R.drawable.mywalt_bar_bangdin_nor);
				bdButton.setClickable(false);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			//
		}

		@Override
		public void afterTextChanged(Editable s) {
			//
		}
	};

	public class MyThread1 implements Runnable { // thread
		@Override
		public void run() {
			if (isSend) {
				while (true) {
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
	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				if (currentTime > 1) {
					currentTime--;
					waitText.setText("获得验证码\n(" + currentTime + ")");
				} else {
					currentTime = 60;
					sendButton.setVisibility(1);
					waitText.setVisibility(8);
					isSend = false;
				}
			}
			default: {

			}
			}

			super.handleMessage(msg);
		}
	};
}
