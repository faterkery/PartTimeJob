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
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.entity.my.dailyattendance.Music;
import com.clv.parttimejobs.model.interfacebackage.PressureListener;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonParse;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.view.ui.customview.PowerImageView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

public class GiftActivity extends Activity implements PressureListener {
	private Context context;
	PowerImageView powerimageview;
	private int moneycont = 0;
	private boolean isadd = false;
	private boolean isfinish =false;
	private Button gift_activity_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gift_activity);
		context = this;
		powerimageview = (PowerImageView) findViewById(R.id.image_view);
		gift_activity_button=(Button)findViewById(R.id.gift_activity_button);
		powerimageview.setListener(this);
		
		InnerOnclickListener i =new InnerOnclickListener();
		gift_activity_button.setOnClickListener(i);
	}

	private class InnerOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.gift_activity_button:{
				if(isfinish){
					dismiss();
					
				}else{
					powerimageview.startPlay();
				}
				
				break;
			}
			}
		}
		
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub\
		GiftActivity.this.finish();
	}

	@Override
	public void getCoin() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		int daycurrent =calendar.get(Calendar.DAY_OF_MONTH);
		new Thread(new MyThread5(daycurrent+"")).start();
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		super.onDestroy();
	}

	@Override
	public void playMusic() {
		// TODO Auto-generated method stub
		new Music().playMusic(this);
	}

	//用户获取礼物
		private class MyThread5 implements Runnable {
			HttpURLConnection connection = null;
			BufferedReader reader = null;
			String day = "";
			String line = "";
			StringBuilder response;

			public MyThread5(String day) {
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
							new UriFactory().getSignInGiftUrl());
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
					Message message = new Message();
					Bundle bundle=new Bundle();
					String msg=new JsonParse().JSONAnalysis(response.toString().trim());
					if("success".equals(msg)){
						message.what = 2;
					List<String> dayArray = JSONAnalysisMessageqd(response
							.toString().trim());
					String gitmoney = dayArray.get(0);
                    bundle.putString("gift", gitmoney);  
                    moneycont += Integer.parseInt(gitmoney.trim());}
					else if("fail".equals(msg)){
						message.what = 3;
						String code=new JsonParse().JSONAnalysisWrong(response.toString().trim());
						bundle.putString("code", code);  
					}
            		isadd = true;
                    message.setData(bundle);//bundle传值，耗时，效率低  
					handler1.sendMessage(message);
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
		final Handler handler1 = new Handler() {
			public void handleMessage(Message msg) { // handle message
				if(msg.what==2){
					String gif =msg.getData().getString("gift");
					if (isadd) {
						Toast.makeText(GiftActivity.this, "获得" + gif + "金币",
								Toast.LENGTH_SHORT).show();
					}
				}else if(msg.what==3){
					String code =msg.getData().getString("code");
					if("101".equals(code)){
						Toast.makeText(GiftActivity.this, "不符合领取条件",
								Toast.LENGTH_SHORT).show();
					}else if("102".equals(code)){
						Toast.makeText(GiftActivity.this, "无签到记录",
								Toast.LENGTH_SHORT).show();
					}else if("103".equals(code)){
						Toast.makeText(GiftActivity.this, "查询日期不规范",
								Toast.LENGTH_SHORT).show();
					}
				}
				super.handleMessage(msg);
			}
		};
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
					content = resultJsonObject.getString("gift");
					array.add(content);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return array;
		}

		@Override
		public void changeIsfinish() {
			// TODO Auto-generated method stub
			isfinish=true;
		}
}
