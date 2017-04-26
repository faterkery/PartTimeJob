package com.clv.parttimejobs.activity.minelayout.resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.fragment.my_fragment.resume.Fragment_mycredit_grfc;
import com.clv.parttimejobs.fragment.my_fragment.resume.Fragment_mycredit_jbxx;
import com.clv.parttimejobs.fragment.my_fragment.resume.Fragment_mycredit_jntc;
import com.clv.parttimejobs.fragment.my_fragment.resume.Fragment_mycredit_jyjl;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.view.ui.customview.LazyScrollView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class Wdjl_activity extends FragmentActivity   implements OnPageChangeListener{

	private Context context;
	private ImageView mycredit_imagebutton; 
	private ImageButton mycreadit_return_button;
	private ImageButton myimageview01;
	private ImageButton myimageview02;
	private ImageButton myimageview03;
	private ImageButton myimageview04;
	private ViewPager vpContainer;
	private FragmentPagerAdapter adapter;
	private LazyScrollView scrollview;
	
	private float mLastX;
	private DisplayImageOptions options;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wdjl_activity_main);
		context=this;
		init();
		
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.myjjxx_pic_nrtx_nor) // 设置图片下载期间显示的图�?
		.showImageForEmptyUri(R.drawable.myjjxx_pic_nrtx_nor) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.myjjxx_pic_nrtx_nor) // 设置图片加载或解码过程中发生错误显示的图�?
		.cacheInMemory(true) // 设置下载的图片是否缓存在内存�?
		.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图�?
		.build(); // 创建配置过得DisplayImageOption对象
		
		new Thread(new MyThread1()).start();
	}

	public void init(){
		mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
		myimageview01 =(ImageButton) findViewById(R.id.myimageview01);
		myimageview02 =(ImageButton) findViewById(R.id.myimageview02);
		myimageview03 =(ImageButton) findViewById(R.id.myimageview03);
		myimageview04 =(ImageButton) findViewById(R.id.myimageview04);
		mycredit_imagebutton=(ImageView) findViewById(R.id.mycredit_imagebutton);
		InnerOnclickLisener i =new InnerOnclickLisener();
		mycreadit_return_button.setOnClickListener(i);
		myimageview01.setOnClickListener(i);
		myimageview02.setOnClickListener(i);
		myimageview03.setOnClickListener(i);
		myimageview04.setOnClickListener(i);
		scrollview =(LazyScrollView) findViewById(R.id.mycredit_scrollview);
		scrollview.setVerticalScrollBarEnabled(false);

		vpContainer = (ViewPager) findViewById(R.id.mycredit_vp_container);
		adapter = new InnerFragmentPagerAdapter(getSupportFragmentManager());
		vpContainer.setAdapter(adapter);
		vpContainer.setOnPageChangeListener(this);
		scrollview.setEnabled(true);
		
		
		vpContainer.setOnTouchListener(new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        int action = event.getAction();

		        if(action == MotionEvent.ACTION_DOWN) {
		            // 记录点击到ViewPager时�?，手指的X坐标
		            mLastX = event.getX();
		        }
		        if(action == MotionEvent.ACTION_MOVE) {
		            // 超过阈�?
		            if(Math.abs(event.getX() - mLastX) > 60f) {
		                vpContainer.requestDisallowInterceptTouchEvent(true);
		            }
		        }
		        if(action == MotionEvent.ACTION_UP) {
		            // 用户抬起手指，恢复父布局状�?
		        	if(Math.abs(event.getX() - mLastX) > 60f) {
		                vpContainer.requestDisallowInterceptTouchEvent(true);
		            }
		        }
		        return false;
		    }
		});
	}
	
	private class InnerFragmentPagerAdapter extends FragmentPagerAdapter {

		public InnerFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new Fragment_mycredit_jbxx();
				break;

			case 1:
				fragment = new Fragment_mycredit_jyjl();
				break;

			case 2:
				fragment = new Fragment_mycredit_jntc();
				break;

			case 3:
				fragment = new Fragment_mycredit_grfc();
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 4;
		}

	}
	private class InnerOnclickLisener implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			hideFragment();
			switch(view.getId()){
			case R.id.mycreadit_return_button:{
				Wdjl_activity.this.finish();
				break;
			}
			case R.id.myimageview01:{
				myimageview01.setImageResource(R.drawable.myjlxx_pic_jbxx_long);
				vpContainer.setCurrentItem(0);
				break;
			}
			case R.id.myimageview02:{
				myimageview02.setImageResource(R.drawable.myjlxx_pic_jyjl_long);
				vpContainer.setCurrentItem(1);
				break;
			}
			case R.id.myimageview03:{
				myimageview03.setImageResource(R.drawable.myjlxx_pic_jntc_long);
				vpContainer.setCurrentItem(2);
				break;
			}
			case R.id.myimageview04:{
				myimageview04.setImageResource(R.drawable.myjlxx_pic_grfc_long);
				vpContainer.setCurrentItem(3);
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

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		hideFragment();
		switch(position){
		case 0:{
			myimageview01.setImageResource(R.drawable.myjlxx_pic_jbxx_long);
			break;
		}
		case 1:{
			myimageview02.setImageResource(R.drawable.myjlxx_pic_jyjl_long);
			break;
		}
		case 2:{
			myimageview03.setImageResource(R.drawable.myjlxx_pic_jntc_long);
			break;
		}
		case 3:{
			myimageview04.setImageResource(R.drawable.myjlxx_pic_grfc_long);
			break;
		}
		}
	}

	public void hideFragment(){
		myimageview01.setImageResource(R.drawable.myjlxx_pic_jcxx_short);
		myimageview02.setImageResource(R.drawable.myjlxx_pic_jyxl_short);
		myimageview03.setImageResource(R.drawable.myjlxx_pic_jntc_short);
		myimageview04.setImageResource(R.drawable.myjlxx_pic_grfc_short);
	}
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}

	private class MyThread1 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String line = "";
		StringBuilder response;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				URL url = new URL(
						new UriFactory().getHeadPortraitUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String msg = JSONAnalysis(response.toString().trim());
				System.out.println("=======================" + msg);
				UserDao user = new UserDao(context);
				String imgutl = user.detailImgUrl();
				String imageurl = msg + imgutl;
				System.out.println(imageurl);
				Message message = new Message();
				Bundle data = new Bundle();
				data.putString("imgurl", imageurl);
				message.what = 1;
				message.setData(data);
				handler1.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (connection != null) {
					connection.disconnect();
				}
			}
		}

	}

	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String imageurl = data.getString("imgurl");
				
				ImageLoader.getInstance().displayImage(imageurl, mycredit_imagebutton, options);
				break;
			}
			}
			super.handleMessage(msg);
		}
	};
	protected String JSONAnalysis(String string) {
		JSONObject jsonObject;
		String msg = "";
		try {
			jsonObject = new JSONObject(string);
			msg = jsonObject.getString("msg");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	protected String JSONAnalysisMessage(String string) {
		String imgurl="";
		try {
		JSONObject jsonObj = new JSONObject(string); 
		JSONArray resultJsonArray = jsonObj.getJSONArray("value");
        JSONObject resultJsonObject= resultJsonArray.getJSONObject(0); 
		imgurl=resultJsonObject.getString("photoName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return imgurl;
	}
}
