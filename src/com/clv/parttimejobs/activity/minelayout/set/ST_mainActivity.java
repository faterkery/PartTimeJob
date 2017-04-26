package com.clv.parttimejobs.activity.minelayout.set;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.minelayout.login.Login;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.fragment.my_fragment.FragmentMy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class ST_mainActivity extends Activity {

	private ImageButton sz_button_return;
	private ImageButton sz_rey04_imagebutton01;
	private ImageButton sz_rey04_imagebutton02;
	private ImageButton sz_rey04_imagebutton03;
	private Button st_button_1;
	private Button st_button_3;
	private Button st_button_4;
	private Button st_button_5;
	private CheckBox sz_rey04_checkbox;
	private TextView sz_rey04_textview;
	private PopupWindow popupWindow;
	private Context context;
	
	private int button_select=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.st_activity_main);
		context=this;
		init();
	}

	public void init(){
		sz_button_return=(ImageButton) findViewById(R.id.sz_return_button);
		st_button_1=(Button) findViewById(R.id.st_button_1);
		st_button_3=(Button) findViewById(R.id.st_button_3);
		st_button_4=(Button) findViewById(R.id.st_button_4);
		sz_rey04_checkbox=(CheckBox) findViewById(R.id.sz_rey04_checkbox);
		st_button_5=(Button) findViewById(R.id.st_button_5);
		sz_rey04_imagebutton01 =(ImageButton) findViewById(R.id.sz_rey04_imagebutton01);
		sz_rey04_imagebutton02 =(ImageButton) findViewById(R.id.sz_rey04_imagebutton02);
		sz_rey04_imagebutton03 =(ImageButton) findViewById(R.id.sz_rey04_imagebutton03);
		sz_rey04_textview=(TextView) findViewById(R.id.sz_rey04_textview);
		InnerOnCLickListener i =new InnerOnCLickListener();
		sz_button_return.setOnClickListener(i);
		st_button_1.setOnClickListener(i);
		st_button_3.setOnClickListener(i);
		st_button_4.setOnClickListener(i);
		sz_rey04_checkbox.setOnClickListener(i);
		st_button_5.setOnClickListener(i);
		sz_rey04_imagebutton01.setOnClickListener(i);
		sz_rey04_imagebutton02.setOnClickListener(i);
		sz_rey04_imagebutton03.setOnClickListener(i);
	}
	private class InnerOnCLickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.sz_return_button:{
				ST_mainActivity.this.finish();
				break;
			}
			case R.id.st_button_1:{
				if(FragmentMy.isLogin){
				Intent intent =new Intent(ST_mainActivity.this,XGMM_activity.class);
				startActivity(intent);}
				else{
					createView(view);
					
				}
				break;
			}
			case R.id.st_button_3:{
				button_select=1;
				showPopwindow();
				break;
			}
			case R.id.st_button_4:{
				Intent intent =new Intent(ST_mainActivity.this,ZHAQ_activity.class);
				startActivity(intent);
				break;
			}
			case R.id.sz_rey04_checkbox:{
				break;
			}
			case R.id.st_button_5:{
				button_select=2;
				showPopwindow();
				break;
			}
			case R.id.sz_rey04_imagebutton01:{
				if(FragmentMy.isLogin){
					Intent intent =new Intent(ST_mainActivity.this,XGMM_activity.class);
					startActivity(intent);}
					else{
						createView(view);
						
					}
				break;
			}
			case R.id.sz_rey04_imagebutton02:{
				button_select=1;
				showPopwindow();
				break;
			}
			case R.id.sz_rey04_imagebutton03:{
				Intent intent =new Intent(ST_mainActivity.this,ZHAQ_activity.class);
				startActivity(intent);
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

	private void showPopwindow() {
		// 利用layoutInflater获得View
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.popwindow_under_select_exit, null);

		// 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

		final PopupWindow window = new PopupWindow(view,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);

		// 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
		window.setFocusable(true);


		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		window.setBackgroundDrawable(dw);

		
		// 设置popWindow的显示和消失动画
		window.setAnimationStyle(R.style.mypopwindow_anim_style);
		// 在底部显�?
		window.showAtLocation(ST_mainActivity.this.findViewById(R.id.st_button_5),
				Gravity.BOTTOM, 0, 0);

		// 这里�?验popWindow里的button是否可以点击
		Button qd = (Button) view.findViewById(R.id.qd);
		Button esc = (Button) view.findViewById(R.id.esc);
		qd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(button_select==1){
					window.dismiss();
					sz_rey04_textview.setText("0MB");
				}else if(button_select==2){
					UserDao userdao =new UserDao(context);
					userdao.exitLogin();
					window.dismiss();
					setResult(101);
					ST_mainActivity.this.finish();
				}
			}
		});
		esc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				window.dismiss();
			}
		});
		//popWindow消失监听方法
		window.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				System.out.println("popWindow消失");
			}
		});

	}
	
	private void createView(View v) {
		// 获取自定义布�?文件activity_popupwindow_left.xml的视�?
		View popupWindow_view = this.getLayoutInflater()
				.inflate(R.layout.requset_login, null, false);

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setFocusable(true);
		backgroundAlpha(0.7f);
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay()
				.getMetrics(dm);

		int screenWidth = dm.widthPixels;

		int screenHeigh = dm.heightPixels;
		// 设置动画效果
		// popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方�?,在屏幕的左侧
		popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, screenWidth / 16 * 1,
				screenHeigh / 6 * 2);

		Button button_exit = (Button) popupWindow_view.findViewById(R.id.exit);
		Button button_goto = (Button) popupWindow_view
				.findViewById(R.id.gotoLogin);
		button_exit.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				return true;
			}
		});

		button_goto.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				Intent intent = new Intent(context, Login.class);
				startActivityForResult(intent, 1);
				setResult(100);
				ST_mainActivity.this.finish();
				return true;
			}
		});

	}
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = this.getWindow()
				.getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		this.getWindow().setAttributes(lp);
	}
}
