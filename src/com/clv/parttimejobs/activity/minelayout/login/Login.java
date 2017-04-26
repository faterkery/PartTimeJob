package com.clv.parttimejobs.activity.minelayout.login;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.clv.parttimejobs.activity.map.MainMapLocation;
import com.clv.parttimejobs.activity.minelayout.login.register.Registered;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.Json_util;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.util.encrypt.RegisterEncryption;
import com.clv.homework.R;
import com.clv.homework.R.layout;
import com.clv.homework.R.menu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends Activity {

	// 注册信息加密关键匙，不可更改
	private final String addUserKey = "18410528a3f29f4d";

	// 登录信息加密关键匙，不可更改
	private final String signInKey = "7bb42c9d58378826";
	private EditText edit;
	private EditText edit2;
	private Bitmap bitmap;
	private Button registerButton;
	private ImageButton loginbutton;
	private Button return_button;
	private Button forget_button;
	private ImageView login_imageview_showPassword;
	private Button login_showpassword;
	private Context context;
	private boolean isShow = false;
	private boolean isstartLogin = true;
	private Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginxml);
		context = this;
		resources = context.getResources();

		edit = (EditText) findViewById(R.id.login_editview_02);
		edit2 = (EditText) findViewById(R.id.login_editview_03);
		loginbutton = (ImageButton) findViewById(R.id.login_ImageButton_03);
		registerButton = (Button) findViewById(R.id.admin_register_button);
		return_button = (Button) findViewById(R.id.login_reutrn_button);
		forget_button = (Button) findViewById(R.id.admin_froget_button);
		login_imageview_showPassword = (ImageView) findViewById(R.id.login_imageview_showPassword);
		login_showpassword = (Button) findViewById(R.id.login_button_showPassword);
		InnerOnClickListener i = new InnerOnClickListener();
		registerButton.setOnClickListener(i);
		loginbutton.setOnClickListener(i);
		return_button.setOnClickListener(i);
		forget_button.setOnClickListener(i);
		login_showpassword.setOnClickListener(i);
		
		edit.addTextChangedListener(textWatcher);  
		edit2.addTextChangedListener(textWatcher);  
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
		String phone = edit.getText().toString().trim();
		String password =edit2.getText().toString().trim();
		if(phone.length()==0){
			loginbutton.setImageResource(R.drawable.denglu_bar_denglu_nor);
		}else if(password.length()==0){
			loginbutton.setImageResource(R.drawable.denglu_bar_denglu_nor);
		}else{
			loginbutton.setImageResource(R.drawable.select_login_button);
		}
	}
	private class InnerOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.login_ImageButton_03: {
				String phone = edit.getText().toString().trim();
				String password = edit2.getText().toString().trim();
				new Thread(new MyThread1(phone, password)).start();
				break;
			}
			case R.id.admin_register_button: {
				Intent intent = new Intent(context, Registered.class);
				startActivityForResult(intent, 2);
				break;
			}
			case R.id.login_reutrn_button: {
				Login.this.finish();
				break;
			}
			case R.id.admin_froget_button: {
				Intent intent = new Intent(context, ForgetPssword.class);
				startActivityForResult(intent, 3);
				break;
			}
			case R.id.login_button_showPassword: {
				Drawable imageDrawable = resources
						.getDrawable(R.drawable.mylogin_icon_key_no); // 图片在drawable目录下
				Drawable imageDrawable_show = resources
						.getDrawable(R.drawable.mylogin_icon_key_ok);
				if (isShow) {
					login_imageview_showPassword
							.setImageDrawable(imageDrawable);
					edit2.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					isShow = false;
				} else {
					login_imageview_showPassword
							.setImageDrawable(imageDrawable_show);
					edit2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					isShow = true;
				}
			}
			}
		}

	}

	public class MyThread1 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String password = "";
		String line = "";
		StringBuilder response;
		byte[] data = new byte[1024];
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		public MyThread1(String phone, String password) {
			super();
			this.phone = phone;
			this.password = password;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (isstartLogin) {
				try {
					isstartLogin = false;
					URL url = new URL(
							new UriFactory().getSignInUrl());
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					DataOutputStream out = new DataOutputStream(
							connection.getOutputStream());
					MarkKey re = new MarkKey();
					String timekey = re.builderTimeKey();
					String phone_encry = re.Encrypt(phone, timekey);
					
					String password_encry = re.Encrypt(re.getMD5(password),
							timekey);
					String sendContext = "user_phoneNo="
							+ URLEncoder.encode(phone_encry, "UTF-8")
							+ "&user_password="
							+ URLEncoder.encode(password_encry, "UTF-8");
					System.out.println(sendContext);
					out.writeBytes(sendContext);
					out.flush();
					InputStream in = connection.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					response = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}

					// if(outStream.size()>2){
					// new Thread(new
					// MyThread2(outStream.toByteArray())).start();
					// }else if(outStream.toString().trim().equals("-2")){
					// new Thread(new MyThread1(phone,password)).start();
					// }
					Message message = new Message();
					Bundle data = new Bundle();
					System.out.println(response.toString().trim());
					String msg = JSONAnalysis(response.toString().trim());
					String wrong = "";
					if ("success".equals(msg)) {
						data.putString("value", msg);
						
						List<User> l=JSONAnalysisMessage(response.toString().trim());
						for(User u :l){
							UserDao user =new UserDao(context);
							user.insert(u);
						}
						
						
					} else if ("fail".equals(msg)) {
						wrong = JSONAnalysisWrong(response.toString().trim());
						System.out.println(wrong);
						if("102".equals(wrong)){
							new Thread(new MyThread1(phone, password)).start();
						}else if("101".equals(wrong)){
						data.putString("value", wrong);}
					}

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

					isstartLogin = true;
				}
			}
		}
	}

	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String val= "";
				val= data.getString("value");
				/*
				 * security_key 安全匙 表示成功登录 "-1" 表示账户密码不正确 "-2" 接收到的信息为空值 "-3"
				 * 接收到的信息不完整
				 */
				if (("101").equals(val)) {
					Toast.makeText(Login.this, "账户密码不正确", Toast.LENGTH_SHORT)
							.show();
				} 
				else if ("success".equals(val)){
					Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT)
							.show();
					setResult(100);
					Login.this.finish();
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
			jsonObject =new JSONObject(string);
			msg = jsonObject.getString("msg");
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
			jsonObject = new JSONObject(string);
			context = jsonObject.getString("code");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return context;
	}

	protected List<User> JSONAnalysisMessage(String string) {
		List<User> user =  new ArrayList<User>();  
		try {
		JSONObject jsonObj = new JSONObject(string); 
		JSONArray resultJsonArray = jsonObj.getJSONArray("value");
        System.out.println(resultJsonArray.length());
        JSONObject resultJsonObject= resultJsonArray.getJSONObject(0); 
		User u1 =new User();
		u1.setHeadPortraitPath(resultJsonObject.getString("headPortraitName"));
		u1.setSecurity_key(resultJsonObject.getString("securityKey"));
		System.out.println(resultJsonObject.getString("securityKey"));
		u1.setUser_id(resultJsonObject.getInt("userId"));
		u1.setUser_name(resultJsonObject.getString("userName"));
		u1.setUser_phoneNo(resultJsonObject.getString("userPhoneNo"));
		user.add(u1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return user;
	}

	/**
	 * @author wangjiajun
	 *
	 */
	private class MyThread2 implements Runnable {

		User user = null;
		byte[] data;

		public MyThread2(byte[] data) {
			this.data = data;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Json_util j = new Json_util();
			try {
				user = j.getListUser(data);
				UserDao userdao = new UserDao(context);
				if (userdao.detail(user) > 0) {
					userdao.updata(user);
				} else {
					userdao.insert(user);
				}
				userdao.destory();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if (requestCode == 2) {
			if (resultCode == 100) {
				String phone = data.getExtras().getString("phone").trim();
				String password = data.getExtras().getString("password").trim();
				new Thread(new MyThread1(phone, password)).start();
			}
			else if(resultCode == 104){
				Login.this.finish();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
