package com.clv.parttimejobs.activity.minelayout.set;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.encrypt.MarkKey;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GHBD_activity extends Activity {

	private Context context;
	private ImageButton sz_return_button;
	private TextView register_huoqu_textview;
	private boolean isSend=true;
	private int currentTime=30;
	
	private EditText ghbd_edittext01;
	private EditText ghbd_edittext02;
	private EditText ghbd_edittext03;
	private Button ghbd_huoqu_button;
	private Button ghbd_submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ghbd_activity_layout);
		init();
		context=this;
	}

	public void init(){
		sz_return_button =(ImageButton) findViewById(R.id.sz_return_button);
		ghbd_submit=(Button) findViewById(R.id.ghbd_submit);
		ghbd_huoqu_button=(Button) findViewById(R.id.ghbd_huoqu_button);
		register_huoqu_textview=(TextView) findViewById(R.id.register_huoqu_textview);
		ghbd_edittext01=(EditText)findViewById(R.id.ghbd_edittext01);
		ghbd_edittext02=(EditText)findViewById(R.id.ghbd_edittext02);
		ghbd_edittext03=(EditText)findViewById(R.id.ghbd_edittext03);
		InnerOnCLickListener i =new InnerOnCLickListener();
		sz_return_button.setOnClickListener(i);
		ghbd_submit.setOnClickListener(i);
		ghbd_huoqu_button.setOnClickListener(i);
	}
	private class InnerOnCLickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.sz_return_button:{
				GHBD_activity.this.finish();
				break;
			}
			case R.id.ghbd_submit:{
				String oldphone=ghbd_edittext01.getText().toString().trim();
				String yzm =ghbd_edittext02.getText().toString().trim();
				String newphone=ghbd_edittext03.getText().toString().trim();
				UserDao user =new UserDao(context);
				String phone =user.detailPhoneNo();
				if(oldphone.length()!=13){
					Toast.makeText(GHBD_activity.this, "请输入正确原来手机号", Toast.LENGTH_SHORT).show();
				}else if(newphone.length()!=13){
					Toast.makeText(GHBD_activity.this, "请输入正确的新手机号", Toast.LENGTH_SHORT).show();
				}else if(oldphone.equals(newphone)){
					Toast.makeText(GHBD_activity.this, "手机号不能相�?", Toast.LENGTH_SHORT).show();
				}else if(phone.equals(newphone)){
					Toast.makeText(GHBD_activity.this, "手机号不能相�?", Toast.LENGTH_SHORT).show();
				}else{
				new Thread(new MyThread4(oldphone,yzm,newphone)).start();
				GHBD_activity.this.finish();}
				break;
			}
			case R.id.ghbd_huoqu_button:{
				new Thread(new MyThread1()).start();
				ghbd_huoqu_button.setVisibility(8);
				register_huoqu_textview.setVisibility(1);
				String oldphone = ghbd_edittext01.getText().toString();
				if(oldphone.length()==13){
				new Thread(new MyThread2(oldphone)).start();}
				else{
					Toast.makeText(GHBD_activity.this, "请输入正确的手机�?", Toast.LENGTH_SHORT).show();
				}
				break;
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
					register_huoqu_textview.setText("已发�?( " + currentTime + " )");
				} else {
					currentTime = 60;
					ghbd_huoqu_button.setVisibility(1);
					register_huoqu_textview.setVisibility(8);
					isSend = false;
				}
			}
			default: {

			}
			}

			super.handleMessage(msg);
		}
	};

	public class MyThread1 implements Runnable { // thread
		@Override
		public void run() {
			
				while (true) {
					if (isSend) {
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
	public class MyThread2 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String line = "";
		StringBuilder response;

		public MyThread2(String phone) {
			this.phone = phone;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				// 请求手机是否能用
				try {
					URL url = new URL(
							new UriFactory().getIsUserPhoneNoUrl());
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setDoOutput(true);
					connection.setDoInput(true);
					DataOutputStream out = new DataOutputStream(
							connection.getOutputStream());
					String a = "isphoneNo=" + URLEncoder.encode(phone, "UTF-8");
					out.writeBytes(a);
					out.flush();
					InputStream in = connection.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					response = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
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
				Message message = new Message();
				Bundle data = new Bundle();
				String msg=JSONAnalysis(response.toString().trim());
				String wrong="";
				if ("success".equals(msg)) {
					new Thread(new MyThread3(phone)).start();
					data.putString("value", msg);
				}else if("fail".equals(msg)){
					wrong=JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
				}
				message.what = 1;
				message.setData(data);
				handler2.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	final Handler handler2 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String val = data.getString("value");
				
				if (val.equals("101")) {
					// 1，手机号已经注册
					Toast.makeText(GHBD_activity.this, "手机号已经被注册",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("success")) {
					// 0，手机号未注�?
					Toast.makeText(GHBD_activity.this, "可以申请", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(GHBD_activity.this, "",
							Toast.LENGTH_SHORT).show();
				}

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
				jsonObject=new JSONObject(string);
				msg= jsonObject.getString("msg");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return msg;
	    }  
	  protected String JSONAnalysisWrong(String string) { 
		  JSONObject jsonObject;
		  String context = "";
	        try {
				jsonObject=new JSONObject(string);
				context= jsonObject.getString("code");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return context;
	    }  
	
	public class MyThread3 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String password = "";
		String line = "";
		StringBuilder response;

		public MyThread3(String phone) {
			super();
			this.phone = phone;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				URL url = new URL(new UriFactory().getCodeUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String a = "phoneGetCode=" + URLEncoder.encode(phone, "UTF-8");
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				Message message = new Message();
				Bundle data = new Bundle();
				data.putString("value", response.toString().trim());
				String msg=JSONAnalysis(response.toString().trim());
				String wrong="";
				if ("success".equals(msg)) {
					data.putString("value", msg);
				}else if("fail".equals(msg)){
					wrong=JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
				}
				message.what = 1;
				message.setData(data);
				handler3.sendMessage(message);
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

	final Handler handler3 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String val = data.getString("value");
				if (val.equals("success")) {
					Toast.makeText(GHBD_activity.this, "发�?�短信成�?",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("101")){
					Toast.makeText(GHBD_activity.this, "发�?�失�?",
							Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(GHBD_activity.this, "",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
			}
			super.handleMessage(msg);
		}
	};

	public class MyThread4 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String code = "";
		String newphone="";
		String line = "";
		StringBuilder response;

		public MyThread4(String phone, String code,String newphone) {
			super();
			this.phone = phone;
			this.code = code;
			this.newphone=newphone;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(isSend){
			try {
				isSend =false;
				URL url = new URL(new UriFactory().getCheckCode());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String sendContext = "checkPhone="
						+ URLEncoder.encode(phone, "UTF-8") + "&checkCode="
						+ URLEncoder.encode(code, "UTF-8");
				out.writeBytes(sendContext);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				Message message = new Message();
				Bundle data = new Bundle();
				String msg=JSONAnalysis(response.toString().trim());
				String wrong="";
				if ("success".equals(msg)) {
					data.putString("value", msg);
					new Thread(new MyThread5(newphone)).start();
				}else if("fail".equals(msg)){
					wrong=JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
				}
				message.what = 1;
				message.setData(data);
				handler4.sendMessage(message);
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
				isSend=true;

			}
			}
		}

	}

	final Handler handler4 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String val = data.getString("value");
				/*
				 * "0"表示该手机号没有相对的验证码 "-2"表示该用户的验证码失效，过时了，不管对错 "1"表示验证码正�?
				 * "-1"表示验证码错�?
				 */
				if (val.equals("103")) {
					Toast.makeText(GHBD_activity.this, "请先获取验证�?",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("102")) {
					Toast.makeText(GHBD_activity.this, "请重新获取验�?",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("success")) {
					Toast.makeText(GHBD_activity.this, "验证码正�?", Toast.LENGTH_SHORT)
							.show();

				} else if (val.equals("101")) {
					Toast.makeText(GHBD_activity.this, "验证码错�?,请重新输�?",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(GHBD_activity.this, "",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
			}
			super.handleMessage(msg);
		}
	};

	public class MyThread5 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String newphone="";
		String line = "";
		StringBuilder response;

		public MyThread5(String newphone) {
			super();
			this.newphone = newphone;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(isSend){
			try {
				UserDao user =new UserDao(context);
				User u=user.detailMessage();
				long id = u.getUser_id();
				String phone =u.getUser_phoneNo();
				String security_key =u.getSecurity_key();
				MarkKey re = new MarkKey();
				
				isSend =false;
				URL url = new URL(new UriFactory().getModifyUserPhoneUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String timekey = re.builderTimeKey();
				String message_key =re.builderMessageKey(phone,security_key);
				String newpassword_encry = re.Encrypt(re.getMD5(newphone), message_key);
				String id_en=re.builderId(id+"",security_key);
				String id_enery =re.Encrypt(id_en, timekey);
				
				String sendContext = "enPhone="
						+ URLEncoder.encode(newpassword_encry, "UTF-8") + "&enId="
						+ URLEncoder.encode(id_enery, "UTF-8");
				out.writeBytes(sendContext);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				Message message = new Message();
				Bundle data = new Bundle();
				String msg=JSONAnalysis(response.toString().trim());
				String wrong="";
				if ("success".equals(msg)) {
					data.putString("value", msg);
					List<User> l=JSONAnalysisMessage(response.toString().trim());
					for(User u1 :l){
						UserDao user1 =new UserDao(context);
						user1.insert(u1);
					}
				}else if("fail".equals(msg)){
					wrong=JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
				}
				message.what = 1;
				message.setData(data);
				handler5.sendMessage(message);
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
				isSend=true;

			}
			}
		}

	}
	final Handler handler5 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String val = data.getString("value");
				/*
				 * "0"表示该手机号没有相对的验证码 "-2"表示该用户的验证码失效，过时了，不管对错 "1"表示验证码正�?
				 * "-1"表示验证码错�?
				 */
				if (val.equals("401")) {
					Toast.makeText(GHBD_activity.this, "信息解析错误，需要重新获取用户信�?",
							Toast.LENGTH_SHORT).show();
				} else if(("success").equals(val)){
					
					Toast.makeText(GHBD_activity.this, "更换保定成功",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
			}
			super.handleMessage(msg);
		}
	};
	protected List<User> JSONAnalysisMessage(String string) {
		List<User> user =  new ArrayList<User>();  
		try {
		JSONObject jsonObj = new JSONObject(string); 
		JSONArray resultJsonArray = jsonObj.getJSONArray("value");
        System.out.println(resultJsonArray.length());
        JSONObject resultJsonObject= resultJsonArray.getJSONObject(0); 
		User u1 =new User();
		u1.setHeadPortraitPath(resultJsonObject.getString("headPortraitPath"));
		u1.setSecurity_key(resultJsonObject.getString("security_key"));
		System.out.println(resultJsonObject.getString("security_key"));
		u1.setUser_id(resultJsonObject.getInt("user_id"));
		u1.setUser_name(resultJsonObject.getString("user_name"));
		u1.setUser_phoneNo(resultJsonObject.getString("user_phoneNo"));
		user.add(u1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return user;
	}
}
