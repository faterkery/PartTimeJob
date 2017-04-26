package com.clv.parttimejobs.activity.minelayout.login.register;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.clv.homework.R;
import com.clv.homework.R.layout;
import com.clv.homework.R.menu;
import com.clv.parttimejobs.fragment.consult_fragment.FragmentNews.MyThread;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.util.encrypt.RegisterEncryption;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Registered extends Activity {

	// 注册信息加密关键匙，不可更改
	private final String addUserKey = "18410528a3f29f4d";

	// 登录信息加密关键匙，不可更改
	private final String signInKey = "7bb42c9d58378826";
	private Button return_button;// 返回按钮
	private EditText phone;// 手机号
	private Button getCode;
	private TextView getCodeText;// 倒计时
	private EditText code;// 验证码
	private EditText password;// 密码
	private EditText repassword;// 二次密码
	private ImageButton login_button;// 注册按钮
	private ImageView show_password;
	private ImageView show_repassword;
	private Button show_pa_button;
	private Button show_re_button;
	private boolean isShow = false;
	private boolean isShowre = false;
	private String name;
	private String phoneno = "";
	private String code_userinput;
	private String password_userinput;
	private String repassword_userinput;
	private Resources resources;
	Boolean isSend = false;
	Boolean isstartLogin =true;
	int currentTime = 60;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registered_xml);
		isstartLogin=true;
		resources = this.getResources();
		return_button = (Button) findViewById(R.id.login_return_button);
		phone = (EditText) findViewById(R.id.login_editview_02);
		getCode = (Button) findViewById(R.id.register_huoqu_button);
		code = (EditText) findViewById(R.id.login_editview_03);
		password = (EditText) findViewById(R.id.login_editview_04);
		repassword = (EditText) findViewById(R.id.login_editview_05);
		login_button = (ImageButton) findViewById(R.id.login_ImageButton_03);
		getCodeText = (TextView) findViewById(R.id.register_huoqu_textview);
		show_password = (ImageView) findViewById(R.id.registered_imageview_showpassword);
		show_repassword = (ImageView) findViewById(R.id.registered_imageview_showrepassword);
		show_pa_button = (Button) findViewById(R.id.register_button_showPassword);
		show_re_button = (Button) findViewById(R.id.register_button_showrePassword);

		InnerOnClickListener i = new InnerOnClickListener();
		return_button.setOnClickListener(i);
		getCode.setOnClickListener(i);
		login_button.setOnClickListener(i);
		show_pa_button.setOnClickListener(i);
		show_re_button.setOnClickListener(i);
		
		phone.addTextChangedListener(textWatcher);
		code.addTextChangedListener(textWatcher);
		password.addTextChangedListener(textWatcher);
		repassword.addTextChangedListener(textWatcher);
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
			case R.id.login_return_button: {
				setResult(401);
				Registered.this.finish();
				break;
			}
			case R.id.register_huoqu_button: {
				//
				phoneno = phone.getText().toString();
				if (phoneno.length() == 0) {
					Toast.makeText(Registered.this, "请输入手机号",
							Toast.LENGTH_SHORT).show();
				}
				if (phoneno.length() != 11) {
					Toast.makeText(Registered.this, "请输入正确的手机号",
							Toast.LENGTH_SHORT).show();
				} else {
					new Thread(new MyThread2(phoneno)).start();
					isSend = true;
					getCode.setVisibility(8);
					getCodeText.setVisibility(1);
					new Thread(new MyThread1()).start();
				}
				break;
			}
			case R.id.login_ImageButton_03: {
				phoneno = phone.getText().toString();
				code_userinput = code.getText().toString();
				password_userinput = password.getText().toString().trim();
				repassword_userinput = repassword.getText().toString().trim();

				if (phoneno.equals("")) {
					Toast.makeText(Registered.this, "手机号码不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (code_userinput.equals("")) {
					Toast.makeText(Registered.this, "验证码不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (password_userinput.equals("")
						|| repassword_userinput.equals("")) {
					Toast.makeText(Registered.this, "密码不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (!password_userinput.equals(repassword_userinput)) {
					Toast.makeText(Registered.this, "两次密码不相同",
							Toast.LENGTH_SHORT).show();
				} else {
					new Thread(new MyThread4(phoneno, code_userinput,
							password_userinput)).start();
				}
				break;
			}
			case R.id.register_button_showPassword:{
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
			case R.id.register_button_showrePassword:{
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
			}
		}

	}
	private TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			changeButtonImg();
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
	public void changeButtonImg(){
		String phone_txt = phone.getText().toString().trim();
		String code_txt =code.getText().toString().trim();
		String password_txt =password.getText().toString().trim();
		String repassword_txt =repassword.getText().toString().trim();
		if(phone_txt.length()==0){
			login_button.setImageResource(R.drawable.denglu_bar_zhuce_nor);
		}else if(code_txt.length()==0){
			
		}else if(password_txt.length()==0){
			
		}else if(repassword_txt.length()==0){
			
		}else{
			login_button.setImageResource(R.drawable.select_registered_button);
		}
	}
	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				if (currentTime > 1) {
					currentTime--;
					getCodeText.setText("已发送( " + currentTime + " )");
				} else {
					currentTime = 60;
					getCode.setVisibility(1);
					getCodeText.setVisibility(8);
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
			if (isSend) {
				while (true) {
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
					Toast.makeText(Registered.this, "手机号已经被注册",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("success")) {
					// 0，手机号未注册
					Toast.makeText(Registered.this, "可以申请", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(Registered.this, "",
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
					Toast.makeText(Registered.this, "发送短信成功",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("101")){
					Toast.makeText(Registered.this, "发送失败",
							Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(Registered.this, "",
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
		String password = "";
		String line = "";
		StringBuilder response;

		public MyThread4(String phone, String code, String password) {
			super();
			this.phone = phone;
			this.code = code;
			this.password = password;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(isstartLogin){
			try {
				isstartLogin =false;
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
					new Thread(new MyThread5(phone,password)).start();
				}else if("fail".equals(msg)){
					wrong=JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
				}
				message.what = 1;
				message.setData(data);
				handler4.sendMessage(message);
				if (response.toString().trim().equals("1")) {
					new Thread(new MyThread5(phone, password)).start();
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
				isstartLogin=true;

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
				 * "0"表示该手机号没有相对的验证码 "-2"表示该用户的验证码失效，过时了，不管对错 "1"表示验证码正确
				 * "-1"表示验证码错误
				 */
				if (val.equals("103")) {
					Toast.makeText(Registered.this, "请先获取验证码",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("102")) {
					Toast.makeText(Registered.this, "请重新获取验证",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("success")) {
					Toast.makeText(Registered.this, "验证码正确", Toast.LENGTH_SHORT)
							.show();

				} else if (val.equals("101")) {
					Toast.makeText(Registered.this, "验证码错误,请重新输入",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(Registered.this, "",
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
		String phone = "";
		String password = "";
		String line = "";
		StringBuilder response;

		public MyThread5(String phone, String password) {
			super();
			this.phone = phone;
			this.password = password;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				URL url = new URL(new UriFactory().getAddUserUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				MarkKey re = new MarkKey();
				String timekey =re.builderTimeKey();
				String phone_encry = re.Encrypt(phone,
						timekey);
				String password_encry = re.Encrypt(re.getMD5(password),
						timekey);
				String sendContext = "enPhone="
						+ URLEncoder.encode(phone_encry, "UTF-8")
						+ "&enPassword="
						+ URLEncoder.encode(password_encry, "UTF-8");
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
				data.putString("value", response.toString().trim());
				String msg=JSONAnalysis(response.toString().trim());
				String wrong="";
				if ("success".equals(msg)) {
					data.putString("value", msg);
				}else if("fail".equals(msg)){
					wrong=JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
				}
				
				if ("success".equals(msg)) {
					
					Intent intent = new Intent();
					intent.putExtra("phone", phone);
					intent.putExtra("password", password);
					setResult(100,intent);
					Registered.this.finish();
				}else if(wrong.equals("204")){
					new Thread(new MyThread5(phone,password)).start();
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
				 * -3，接收到的信息不完整 -2，接收到的信息为空值 -1，手机号已经注册 security_key 安全匙
				 */
				 if (val.equals("204")) {
					Toast.makeText(Registered.this, "解析错误",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("101")) {
					Toast.makeText(Registered.this, "手机号已经注册",
							Toast.LENGTH_SHORT).show();
				} else if (val.equals("success")){
					Toast.makeText(Registered.this, "注册成功", Toast.LENGTH_SHORT)
							.show();
					
				}
				break;
			}
			}
			super.handleMessage(msg);
		}
	};

	 
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registered, menu);
		return true;
	}

}
