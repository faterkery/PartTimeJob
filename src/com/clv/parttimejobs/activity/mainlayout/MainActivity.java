package com.clv.parttimejobs.activity.mainlayout;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.map.MainMapLocation;
import com.clv.parttimejobs.fragment.consult_fragment.FragmentNews;
import com.clv.parttimejobs.fragment.message_fragment.FragmentMessage;
import com.clv.parttimejobs.fragment.my_fragment.FragmentMy;
import com.clv.parttimejobs.fragment.task_fragment.FragmentOrderForm;
import com.clv.parttimejobs.thirdparty.zxing.activity.CaptureActivity;
import com.clv.parttimejobs.thirdparty.zxing.activity.WebViewAcitvity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {
	public static int Screen_WIDTH;
	public static int Screen_HEIGHT;
	public static float phone_px;
	public static int phone_DPI;

	// 布局管理器
	private FragmentManager fManager;

	RadioButton radiobutton1;
	RadioButton radiobutton2;
	RadioButton radiobutton3;
	RadioButton radiobutton4;
	RadioGroup radiogroup;

	private TextView text_Location;
	private Button head_text_location_button_button;
	private ImageButton imagebutton;
	private Button imagebutton_map;
	private ImageView imageview_add;
	private PopupWindow popupWindow;
	private RelativeLayout main_head;
	private FragmentManager fragmentManager;
	private Fragment[] mFragments;

	// head
	@SuppressLint({ "Recycle", "CutPasteId" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		LinearLayout parent = (LinearLayout) inflater.inflate(
				R.layout.activity_main, null);
		parent.removeView(parent);
		setContentView(parent);
		imagebutton_map = (Button) findViewById(R.id.head_text_location_button);
		imagebutton = (ImageButton) findViewById(R.id.ImageButton_sousuo);
		main_head = (RelativeLayout) findViewById(R.id.main_head);
		text_Location = (TextView) findViewById(R.id.head_text_location);
		head_text_location_button_button = (Button) findViewById(R.id.head_text_location_button);
		
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		Screen_WIDTH = metric.widthPixels; // 宽度（PX）
		Screen_HEIGHT = metric.heightPixels; // 高度（PX）

		phone_px = metric.density; // 密度（0.75 / 1.0 / 1.5）
		phone_DPI = metric.densityDpi; // 密度DPI（120 / 160 / 240）
		// 初始化组件
		initViews();
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction trans = fragmentManager.beginTransaction();
		mFragments = new Fragment[4];

		main_head.setVisibility(0);
		trans.show(mFragments[0]);

		InnerOnCheckedListener i = new InnerOnCheckedListener();
		radiogroup.setOnCheckedChangeListener(i);
		// 初始化搜索框
		// 创建一个ArrayAdapter封装数组
		//
		// 初始化popwindow
		imageview_add = (ImageView) findViewById(R.id.head_add_imageview);
		InnerOnCLickListener innerlistener = new InnerOnCLickListener();
		imageview_add.setOnClickListener(innerlistener);
		imagebutton.setOnClickListener(innerlistener);
		imagebutton_map.setOnClickListener(innerlistener);
		//
		// news 倒计时
		setSelect(0);

		// 获取location
		head_text_location_button_button.setOnClickListener(innerlistener);

		initImageLoader(this);
	}

	public static void initImageLoader(Context context) {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

	private void setSelect(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		// 把图片设置为亮的
		// 设置内容区域
		switch (i) {
		case 0:
			if (mFragments[0] == null) {
				mFragments[0] = new FragmentNews();
				transaction.add(R.id.mainfragment, mFragments[0]);
			} else {
				transaction.show(mFragments[0]);
			}
			break;
		case 1:
			if (mFragments[1] == null) {
				mFragments[1] = new FragmentOrderForm();
				transaction.add(R.id.mainfragment, mFragments[1]);
			} else {
				transaction.show(mFragments[1]);

			}
			break;
		case 2:
			if (mFragments[2] == null) {
				mFragments[2] = new FragmentMessage();
				transaction.add(R.id.mainfragment, mFragments[2]);
			} else {
				transaction.show(mFragments[2]);
			}
			break;
		case 3:
			if (mFragments[3] == null) {
				mFragments[3] = new FragmentMy();
				transaction.add(R.id.mainfragment, mFragments[3]);
			} else {
				transaction.show(mFragments[3]);
			}
			break;

		default:
			break;
		}

		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (mFragments[0] != null) {
			transaction.hide(mFragments[0]);
		}
		if (mFragments[1] != null)
			transaction.hide(mFragments[1]);
		if (mFragments[2] != null)
			transaction.hide(mFragments[2]);
		if (mFragments[3] != null)
			transaction.hide(mFragments[3]);
	}

	private void initViews() {
		// 布局管理器
		fManager = getSupportFragmentManager();

		radiogroup = (RadioGroup) findViewById(R.id.group1);
		radiobutton1 = (RadioButton) findViewById(R.id.radio1);
		radiobutton2 = (RadioButton) findViewById(R.id.radio2);
		radiobutton3 = (RadioButton) findViewById(R.id.radio3);
		radiobutton4 = (RadioButton) findViewById(R.id.radio4);
	}

	private class InnerOnCheckedListener implements
			RadioGroup.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int groupid) {
			FragmentTransaction trans = fManager.beginTransaction();
			// TODO Auto-generated method stub
			switch (groupid) {
			case R.id.radio1: {
				setSelect(0);
				main_head.setVisibility(0);
				radiobutton1.setChecked(true);
				// head_text_context.setText("资讯");
				break;
			}
			case R.id.radio2: {
				setSelect(1);
				;
				main_head.setVisibility(8);
				radiobutton2.setChecked(true);
				// head_text_context.setText("订单");
				break;
			}
			case R.id.radio3: {
				setSelect(2);
				radiobutton3.setChecked(true);
				main_head.setVisibility(8);
				// head_text_context.setText("我的");
				break;
			}
			case R.id.radio4: {
				setSelect(3);
				radiobutton4.setChecked(true);
				main_head.setVisibility(8);
				break;
			}
			}
			trans.commit();
		}

	}

	// 设置点击事件
	private class InnerOnCLickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.head_add_imageview: {

				createView(v);
				break;
			}
			case R.id.ImageButton_sousuo: {
				Intent intent = new Intent(MainActivity.this,
						Search_activity.class);
				startActivityForResult(intent, 6);
				break;
			}
			case R.id.head_text_location_button: {
				Log.w("teg", "点击");
				Intent intent = new Intent(MainActivity.this,
						MainMapLocation.class);
				startActivityForResult(intent, 5);
				break;
			}
			}
		}

	}

	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getWindow().setAttributes(lp);
	}

	private void createView(View v) {
		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = getLayoutInflater().inflate(
				R.layout.activity_popupwindow_left, null, false);

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		// (int)(100*phone_DPI/160),(int)(150*phone_DPI/160)
		popupWindow.setFocusable(true);
		int WidthSize = imageview_add.getLeft();
		int HeightSize = imageview_add.getBottom();

		// 设置动画效果
		// popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方式,在屏幕的左侧
		popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, WidthSize
				- (86 * phone_DPI / 160), HeightSize + (25 * phone_DPI / 160));
		backgroundAlpha(0.7f);
		// 点击其他地方消失
		popupWindow_view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				return true;
			}
		});
		ImageButton button_saoyisao =(ImageButton) popupWindow_view.findViewById(R.id.imageButton2);
		button_saoyisao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				Intent openCameraIntent = new Intent(MainActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 7);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/**
		 * 判断requestCode, resultCode 来确定要执行的代码
		 */
		if (requestCode == 1) {
			// 在这设置选中你要显示的fragment
			setSelect(0);
		}
		if (requestCode == 2) {
			// 在这设置选中你要显示的fragment
			setSelect(1);
		}
		if (requestCode == 3) {
			// 在这设置选中你要显示的fragment
			setSelect(2);
		}
		if (requestCode == 4) {
			// 在这设置选中你要显示的fragment
			setSelect(3);
		}
		if (requestCode == 5) {
			if (resultCode == 101) {
				String result = data.getStringExtra("province");
				text_Location.setText(result);
			}
		}
		if(requestCode == 6){
//			((FragmentNews)mFragments[0]).smoothToTop();
		}
		if(requestCode == 7){
			if(data!=null){
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			System.out.println("------"+scanResult);
		    
			Intent intent =new Intent(MainActivity.this,WebViewAcitvity.class);
			if(scanResult.startsWith("http")){
				intent.putExtra("value", "1");
				intent.putExtra("address",scanResult );
			}else{
				intent.putExtra("value", "2");
				intent.putExtra("text",scanResult );
			}
			startActivity(intent);
			}
		}

		
	}

}
