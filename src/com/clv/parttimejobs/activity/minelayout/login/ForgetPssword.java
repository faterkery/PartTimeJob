package com.clv.parttimejobs.activity.minelayout.login;

import com.clv.homework.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ForgetPssword extends Activity {
	private EditText password;// 密码
	private EditText repassword;// 二次密码
	private ImageView show_password;
	private ImageView show_repassword;
	private Button show_pa_button;
	private Button show_re_button;
	private Button return_my_button;
	private Resources resources;
	private boolean isShow = false;
	private boolean isShowre = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		resources = this.getResources();
		password =(EditText) findViewById(R.id.forget_editview_04);
		repassword=(EditText) findViewById(R.id.forget_editview_05);
		show_password = (ImageView) findViewById(R.id.forget_imageview_showpassword);
		show_repassword = (ImageView) findViewById(R.id.forget_imageview_showrepassword);
		show_pa_button = (Button) findViewById(R.id.forget_button_showPassword);
		show_re_button = (Button) findViewById(R.id.forget_button_showrePassword);
		return_my_button =(Button) findViewById(R.id.forget_return_button);
		InnerOnClickListener i =new InnerOnClickListener();
		show_pa_button.setOnClickListener(i);
		show_re_button.setOnClickListener(i);
		return_my_button.setOnClickListener(i);
	}
	private class InnerOnClickListener implements View.OnClickListener {

		Drawable imageDrawable = resources
				.getDrawable(R.drawable.mylogin_icon_key_no); // 图片在drawable目录下
		Drawable imageDrawable_show = resources
				.getDrawable(R.drawable.mylogin_icon_key_ok);
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.forget_button_showPassword:{
				System.out.println("1111");
				if (isShow) {
					show_password
							.setImageDrawable(imageDrawable);
					password.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					isShow = false;
				} else {
					show_password
							.setImageDrawable(imageDrawable_show);
					password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					isShow = true;
				}
				break;
			}
			case R.id.forget_button_showrePassword:{
				if (isShowre) {
					show_repassword
							.setImageDrawable(imageDrawable);
					repassword.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					isShowre = false;
				} else {
					show_repassword
							.setImageDrawable(imageDrawable_show);
					repassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					isShowre = true;
				}
				break;
			}
			case R.id.forget_return_button:{
				ForgetPssword.this.finish();
				break;
			}
			}
		}
	}
}
