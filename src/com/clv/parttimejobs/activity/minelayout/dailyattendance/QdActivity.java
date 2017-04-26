package com.clv.parttimejobs.activity.minelayout.dailyattendance;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.dao.user.UserJntcDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.entity.my.dailyattendance.SignEntity;
import com.clv.parttimejobs.entity.my.resume.Jntc_basis;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonParse;
import com.clv.parttimejobs.util.ecutetime.CalculateGiftDate;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.view.adapter.my.dailyattendance.SignAdapter;
import com.clv.parttimejobs.view.ui.customview.CircleProgressView;
import com.clv.parttimejobs.view.ui.customview.SignView;
import com.clv.parttimejobs.view.ui.customview.SildingFinishLayout;
import com.clv.parttimejobs.view.ui.customview.SignView.DayType;
import com.clv.parttimejobs.view.ui.customview.SildingFinishLayout.OnSildingFinishListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class QdActivity extends Activity {

	private final static int GIFTDAY = 7;
	private Context context;
	private TextView tvSignDay;
	private TextView tvScore;
	private TextView tvYear;
	private TextView tvMonth;
	private SignView signView;
	private List<SignEntity> data;
	private ImageButton button_last;
	private ImageButton button_next;
	private int month;
	private int year;
	private TextView timetext;
	private CircleProgressView progressview;
	private Button qd_button;
	private ImageButton qd_return_button;
	private ImageView qd_button_finish;
	private ImageButton qd_imagebutton;
	private ScrollView qd_scrollview;
	private TextView textview4;
	private TextView textview_text2;
	Calendar calendar;
	SignAdapter signAdapter;

	private boolean isqd_start = true;
	private List<String> gift_istake =new  ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_everydayqiandao);
		context = this;
		calendar = Calendar.getInstance();
		// tvSignDay = (TextView) findViewById(R.id.activity_main_tv_main_day);
		// tvScore = (TextView) findViewById(R.id.activity_main_tv_score);
		// tvYear = (TextView) findViewById(R.id.activity_main_tv_year);
		// tvMonth = (TextView) findViewById(R.id.activity_main_tv_month);
		// signView = (SignView) findViewById(R.id.activity_main_cv);
		// initView();
		// onReady();
		signView = (SignView) findViewById(R.id.activity_main_cv);
		button_last = (ImageButton) findViewById(R.id.qd_imageview_1);
		button_next = (ImageButton) findViewById(R.id.qd_imageview_2);
		timetext = (TextView) findViewById(R.id.qd_textview_1);
		progressview = (CircleProgressView) findViewById(R.id.circleProgressbar);
		progressview.setProgress(0);
		qd_button = (Button) findViewById(R.id.qd_button);
		qd_return_button = (ImageButton) findViewById(R.id.qd_return_button);
		qd_imagebutton = (ImageButton) findViewById(R.id.qd_imagebutton);
		qd_button_finish = (ImageView) findViewById(R.id.qd_button_finish);
		qd_scrollview = (ScrollView) findViewById(R.id.qd_scrollview);
		textview4=(TextView) findViewById(R.id.textview4);
		textview_text2=(TextView) findViewById(R.id.textview_text2);
		qd_scrollview.setVerticalScrollBarEnabled(false);
		InnerOnClickListener in = new InnerOnClickListener();
		button_last.setOnClickListener(in);
		button_next.setOnClickListener(in);
		qd_imagebutton.setOnClickListener(in);
		qd_return_button.setOnClickListener(in);
		data = new ArrayList<SignEntity>();
		signAdapter = new SignAdapter(data);
		signView.setAdapter(signAdapter);

		SildingFinishLayout mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);
		mSildingFinishLayout
				.setOnSildingFinishListener(new OnSildingFinishListener() {

					@Override
					public void onSildingFinish() {
						QdActivity.this.finish();
					}
				});
		mSildingFinishLayout.setTouchView(qd_scrollview);
		mSildingFinishLayout.setTouchView(signView);

		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		month++;
		String text = year + "年" + month + "月";
		timetext.setText(text);

		int daycount = countDays(year, month);

		for (int i = 1; i <= 31; i++) {
			SignEntity signEntity = new SignEntity();
			signEntity.setDayType(1);
			data.add(signEntity);
		}
		new Thread(new MyThread4(year + "", month + "")).start();
		new Thread(new MyThread2(year + "", month + "")).start();
		
	}

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.qd_imageview_1: {
				calendar.add(Calendar.MONTH, -1);
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH);
				month++;
				String text = year + "年" + month + "月";
				timetext.setText(text);
				signView.setDate(calendar);
				toOriginal(year,month);
				new Thread(new MyThread4(year + "", month + "")).start();
				new Thread(new MyThread2(year + "", month + "")).start();
				
				signView.notifyDataSetChanged();
				break;
			}
			case R.id.qd_imageview_2: {
				calendar.add(Calendar.MONTH, 1);
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH);
				month++;
				String text = year + "年" + month + "月";
				timetext.setText(text);
				signView.setDate(calendar);
				toOriginal(year,month);
				new Thread(new MyThread4(year + "", month + "")).start();
				new Thread(new MyThread2(year + "", month + "")).start();
				
				signView.notifyDataSetChanged();
				break;
			}
			case R.id.qd_imagebutton: {
				if (isqd_start) {
					new Thread(new MyThread1()).start();
				}
				break;
			}
			case R.id.qd_return_button: {
				QdActivity.this.finish();
				break;
			}
			}
		}

	}

	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			int a = msg.what;
			progressview.setProgress(a + 1);
			if (a == 99) {
				Calendar calendar1 = Calendar.getInstance();
				int daycurrent =calendar1.get(Calendar.DAY_OF_MONTH);
				new Thread(new MyThread3(daycurrent+"")).start();
				qd_button.setText("已签到");
				qd_button_finish
						.setImageResource(R.drawable.defult_icon_check_nor);
			}
			super.handleMessage(msg);
		}
	};

	private class MyThread1 implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			isqd_start = false;
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(5L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message message = new Message();
				message.what = i;
				handler1.sendMessage(message);
			}

		}

	}

	public int onReady(int year, int month, List<String> dayArray) {
		
		toOriginal(year,  month);
		for (String dayS : dayArray) {
			int day = Integer.parseInt(dayS);
			data.get(day - 1).setDayType(0);
		}

		int length1 =new CalculateGiftDate().toCalculateDate(dayArray);
		int length =length1;
		Calendar calendar1 = Calendar.getInstance();
		int daycurrent =calendar1.get(Calendar.DAY_OF_MONTH);
		if(length>GIFTDAY){
			length=length%GIFTDAY;
		}
		length=GIFTDAY-length;
		int day = daycurrent+length;
		data.get(day-1).setDayType(4);
		
		return length1;
	}

	//初始化
	public void toOriginal(int year, int month){
		int daycount = countDays(year, month);
		for (int i = 0; i < daycount; i++) {
			// DayType.DISABLED礼物
			// DayType.SIGNED已签到
			data.get(i).setDayType(1);
		}
	}
	public void drawHistroyGift(int year, int month, List<String> dayArray){
		for (String dayS : dayArray) {
			int day = Integer.parseInt(dayS);
			data.get(day - 1).setDayType(4);
		}
	}
	private int countDays(int year, int month) {
		if (year <= 0)
			return 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if ((year % 4 != 0) || ((year % 100 == 0) && (year % 400 != 0)))
				return 28;
			else
				return 29;
		default:
			return 0;
		}
	}

	//
	
	// 查询用户签到次数
	private class MyThread2 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String year = "";
		String month = "";
		String line = "";
		StringBuilder response;

		public MyThread2(String year, String month) {
			this.year = year;
			this.month = month;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				URL url = new URL(
						new UriFactory().getselectSignInUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String a = "enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim()
						+ "&year=" + URLEncoder.encode(year, "UTF-8").trim()
						+ "&month=" + URLEncoder.encode(month, "UTF-8").trim();
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				JsonParse json =new JsonParse();
				String msg=json.JSONAnalysis(response.toString().trim());
				Message message = new Message();
				message.what = 1;
				int yeari = Integer.parseInt(year);
				int monthi = Integer.parseInt(month);
				if("success".equals(msg)){
					List<String> dayArray = JSONAnalysisMessageqd(response
							.toString().trim());
					if(dayArray.size()>0){
					int length=0;
					length=onReady(yeari, monthi, dayArray);
					Bundle bundle=new Bundle();  
					bundle.putString("length", length+"");  
	                message.setData(bundle);//bundle传值，耗时，效率低  
					String y =dayArray.get(dayArray.size()-1);
					Calendar calendar1 = Calendar.getInstance();
					int mcurrent = calendar1.get(Calendar.MONTH);
					int ycurrent = calendar1.get(Calendar.YEAR);
					int daycurrent =calendar1.get(Calendar.DAY_OF_MONTH);
					if((yeari==ycurrent)&&(monthi==(mcurrent+1))&&(""+daycurrent).equals(y)){
						System.out.println("今天已签到");
						message.what = 2;
					}
					boolean istake =false;
					for(String day :gift_istake){
						if(day.equals(daycurrent+"")){
							istake=true;
						}
					}
					if(!istake){
					if(length!=0&&length%GIFTDAY==0){
						Intent intent =new Intent(QdActivity.this,GiftActivity.class);
						startActivityForResult(intent, 1);
					}}
					}else{
						toOriginal(yeari,  monthi);
					}
				}else if("fail".equals(msg)){
					String codeS=json.JSONAnalysisWrong(response.toString().trim());
					int code = Integer.parseInt(codeS);
					switch(code){
					case 101:{
						Log.i("teg","无查询月份的签到记录");
						break;
					}
					default:{
						Log.i("teg",code+"身份验证相关的错误");
					}
					}
				}
				user.destory();
				handler2.sendMessage(message);

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

	final Handler handler2 = new Handler() {
		public void handleMessage(Message msg) { // handle message
			if(msg.what==2){
		    isqd_start = false;
			qd_button.setText("已签到");
			progressview.setProgress(100);
			qd_button_finish
					.setImageResource(R.drawable.defult_icon_check_nor);
			}
			String l =msg.getData().getString("length");
			if(l!=null){
			int length=Integer.parseInt(l);
			textview_text2.setText(length+"");
			if(length>=GIFTDAY){
				length=length%GIFTDAY;
			}
			length=GIFTDAY-length;
			textview4.setText(length+"天");}
			signView.notifyDataSetChanged();
			super.handleMessage(msg);
		}
	};

	// 用户签到
	private class MyThread3 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String day = "";
		String line = "";
		StringBuilder response;

		public MyThread3(String day) {
			this.day = day;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				URL url = new URL(
						new UriFactory().getRetroactiveUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String a = "enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim()
						+ "&date=" +URLEncoder.encode(day, "UTF-8").trim();
				System.out.println(a);
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				new Thread(new MyThread2(year + "", month + "")).start(); 
				user.destory();

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
	//查询礼包
	private class MyThread4 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String year = "";
		String month ="";
		String line = "";
		StringBuilder response;

		public MyThread4(String year,String month) {
			this.year=year;
			this.month = month;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				URL url = new URL(
						new UriFactory().getSelectSignInGiftUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String a = "enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim()
						+ "&year=" +URLEncoder.encode(year, "UTF-8").trim()
						+ "&month=" +URLEncoder.encode(month, "UTF-8").trim();
				System.out.println(a);
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				JsonParse json =new JsonParse();
				String msg=json.JSONAnalysis(response.toString().trim());
				Message message = new Message();
				message.what = 1;
				if("success".equals(msg)){
					List<String> dayArray = JSONAnalysisMessageqd(response
							.toString().trim());
					gift_istake=dayArray;
					int yeari = Integer.parseInt(year);
					int monthi = Integer.parseInt(month);
					drawHistroyGift(yeari, monthi, dayArray);
				}
				user.destory();
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
	
	
	protected List<String> JSONAnalysisMessageqd(String string) {
		List<String> array = new ArrayList<String>();
		String content = "";
		String id = "";
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			int length = resultJsonArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject resultJsonObject = resultJsonArray.getJSONObject(i);
				content = resultJsonObject.getString("day");
				array.add(content);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == 100) {
				
			}
		}
	}
}
