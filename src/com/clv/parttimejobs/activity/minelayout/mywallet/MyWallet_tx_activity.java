package com.clv.parttimejobs.activity.minelayout.mywallet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.minelayout.login.Login;
import com.clv.parttimejobs.fragment.my_fragment.mywallet.YingLianFragment;
import com.clv.parttimejobs.fragment.my_fragment.mywallet.ZhifuBaoFragment;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.mywallet.MyWalleTxPopWindowListener;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.mywallet.ScrollViewListener;
import com.clv.parttimejobs.view.adapter.my.mywallet.HorizontalScrollViewAdapter;
import com.clv.parttimejobs.view.ui.customview.MyHorizontalScrollView;
import com.clv.parttimejobs.view.ui.customview.PassWordEditRectTextView;

public class MyWallet_tx_activity extends Activity implements
		ScrollViewListener,MyWalleTxPopWindowListener {

	private MyHorizontalScrollView mHorizontalScrollView;
	private HorizontalScrollViewAdapter mAdapter;
	private YingLianFragment mYinLian;
	private ZhifuBaoFragment mZhiFuBao;
	private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
			R.drawable.mywallt_bar_yinglian_nor,
			R.drawable.mywallt_bar_zhifubao_nor,
			R.drawable.mywallt_bar_yinglian_nor,
			R.drawable.mywallt_bar_zhifubao_nor));

	private PopupWindow popupWindow;
	private ImageButton tx_button;
	private EditText edittext;
    private ImageButton returnButton;
	
    private String password="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mywallet_tx_layout);

		init();
		mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.scrollPicker);
		mAdapter = new HorizontalScrollViewAdapter(this, mDatas);
		// 添加滚动回调
		mHorizontalScrollView.setScrollViewListener(this);
		// 添加点击回调
		// 设置适配器
		mHorizontalScrollView.initView(mAdapter);
		setDefaultFragment();
	}

	private void init() {
		returnButton = (ImageButton) findViewById(R.id.mymywallet_tx_return_button);
		tx_button = (ImageButton) findViewById(R.id.mymywallet_tx_imagebutton);
		tx_button.setClickable(false);
		edittext = (EditText) findViewById(R.id.mymywallet_tx_edittext);
		edittext.addTextChangedListener(textWatcher);  
		MyOnChickListener i = new MyOnChickListener();
		tx_button.setOnClickListener(i);
		returnButton.setOnClickListener(i);
	}

	private class MyOnChickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.mymywallet_tx_imagebutton: {
				if(!(("").equals(edittext.getText().toString()))){
					createView(v);
				}
				break;
			}
			case R.id.mymywallet_tx_return_button:{
				MyWallet_tx_activity.this.finish();
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
				tx_button.setImageResource(R.drawable.select_mywallet_tx_button);
				tx_button.setClickable(true);
			}else{
				tx_button.setImageResource(R.drawable.mywalt_bar_tixian_set);
				tx_button.setClickable(false);
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

	//显示输入密码
	
	private void setDefaultFragment() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mYinLian = new YingLianFragment();
		transaction.replace(R.id.fragment, mYinLian);
		transaction.commit();
	}

	private void createView(View v) {
		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = this.getLayoutInflater()
				.inflate(R.layout.popwindow_mywallet_tx_layout, null, false);

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
		// 这里是位置显示方式,在屏幕的左侧
		popupWindow.showAtLocation(v, Gravity.CENTER, 0,
				0);

		PassWordEditRectTextView edittextview =(PassWordEditRectTextView)popupWindow_view.findViewById(R.id.popwindow_mywallet_tx_PassWordEditRectTextView);
		edittextview.setListener(this);
		edittextview.setCursorVisible(false);
	}
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = this.getWindow()
				.getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		this.getWindow().setAttributes(lp);
	}
	
	@Override
	public void scrollviewChange(int position) {
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
		// 开启Fragment事务
		FragmentTransaction transaction = fm.beginTransaction();
		if (position % 2 == 0) {
			if (mYinLian == null) {
				mYinLian = new YingLianFragment();
				transaction.replace(R.id.fragment, mYinLian);
			} else {
				transaction.replace(R.id.fragment, mYinLian);
			}
		} else {
			if (mZhiFuBao == null) {
				mZhiFuBao = new ZhifuBaoFragment();
				transaction.replace(R.id.fragment, mZhiFuBao);
			} else {
				transaction.replace(R.id.fragment, mZhiFuBao);
			}
		}
		transaction.commit();
	}

	@Override
	public void finish(String text) {
		// TODO Auto-generated method stub
		password=text;
		popupWindow.dismiss();
		popupWindow = null;
		backgroundAlpha(1f);
	}

}
