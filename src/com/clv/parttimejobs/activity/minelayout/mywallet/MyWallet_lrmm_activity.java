package com.clv.parttimejobs.activity.minelayout.mywallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.clv.homework.R;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.mywallet.MyWalletLrmmListener;
import com.clv.parttimejobs.view.ui.customview.PassWordEditTextView;

public class MyWallet_lrmm_activity extends Activity implements MyWalletLrmmListener{

	private boolean is_edittext1_finish =false;
	private boolean is_edittext2_finish =false;
	private ImageButton button;
	private ImageButton returnButton;
	private PassWordEditTextView edittextview01;
	private PassWordEditTextView edittextview02;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywallet_lrmm_layout);
		init();
	}
	private void init(){
		button=(ImageButton)findViewById(R.id.mymywallet_lrmm_imagebutton);
		returnButton =(ImageButton)findViewById(R.id.mycreadit_return_button);
		edittextview01 =(PassWordEditTextView) findViewById(R.id.mymywallet_lrmm_edittext_input_1);
		edittextview02 =(PassWordEditTextView) findViewById(R.id.mymywallet_lrmm_edittext_input_2);
		edittextview01.setCursorVisible(false);
		edittextview02.setCursorVisible(false);
		edittextview01.setListener(this, 1);
		edittextview02.setListener(this, 2);
		InnerOnClickListener i =new InnerOnClickListener();
		button.setOnClickListener(i);
		returnButton.setOnClickListener(i);
	}
	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.mymywallet_lrmm_imagebutton:{
				String password = edittextview01.getText().toString().trim();
				String repassword = edittextview02.getText().toString().trim();
				if(("").equals(password)){
				}else if(("").equals(repassword)){
				}else if(password.equals(repassword)){
					Intent intent =new Intent(MyWallet_lrmm_activity.this,MyWallet_MainLayout_activity.class);
					startActivityForResult(intent, 100);
				}else{
					Toast.makeText(MyWallet_lrmm_activity.this, "确保两次密码相同", Toast.LENGTH_SHORT).show();
				}
				break;
			}
			case R.id.mycreadit_return_button:{
				MyWallet_lrmm_activity.this.finish();
				break;
			}
			}
		}
		
	}
	private void fresh(){
		if(is_edittext1_finish&&is_edittext2_finish){
			button.setImageResource(R.drawable.seclect_mywallet_lrmm_button);
		}else{
			button.setImageResource(R.drawable.myawlt_bar_jinru_set);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100){
			MyWallet_lrmm_activity.this.finish();
		}
	}
	@Override
	public void finish(int position) {
		// TODO Auto-generated method stub
		if(position==1){
			is_edittext1_finish = true;
		}else if(position==2){
			is_edittext2_finish = true;
		}
		fresh();
	}
	@Override
	public void dontfinish(int position) {
		// TODO Auto-generated method stub
		if(position==1){
			is_edittext1_finish = false;
		}else if(position==2){
			is_edittext2_finish = false;
		}
		fresh();
	}
}
