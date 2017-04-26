package com.clv.parttimejobs.activity.message.personal;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.minelayout.login.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

public class Message_person_set_activity extends Activity {

	private Context context;
	private PopupWindow popupWindow;
	private ImageButton message_person_exit_button;
	private ImageButton messet_back_btn;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_person_set_layout);
		init();
	}
    private void init(){
    	context=this;	
    	message_person_exit_button =(ImageButton)findViewById(R.id.message_person_exit_button);
    	messet_back_btn=(ImageButton)findViewById(R.id.messet_back_btn);
    	InnerOnClickListener i =new InnerOnClickListener();
    	message_person_exit_button.setOnClickListener(i);
    	messet_back_btn.setOnClickListener(i);
    }
    private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.message_person_exit_button:{
				createView(v);
				break;
			}
			case R.id.messet_back_btn:{
				Message_person_set_activity.this.finish();
				break;
			}
			}
		}
    	
    }
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = this.getWindow()
				.getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		this.getWindow().setAttributes(lp);
	}
	private void createView(View v) {
		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = this.getLayoutInflater()
				.inflate(R.layout.chatset_layout, null, false);

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setFocusable(true);
		backgroundAlpha(0.7f);
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		// 设置动画效果
		// popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方式,在屏幕的左侧
		popupWindow.showAtLocation(v, Gravity.BOTTOM,0,
				0);


	}
}
