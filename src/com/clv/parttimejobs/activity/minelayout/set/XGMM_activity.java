package com.clv.parttimejobs.activity.minelayout.set;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.minelayout.login.register.Registered;
import com.clv.parttimejobs.activity.minelayout.login.register.Registered.MyThread3;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.encrypt.MarkKey;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class XGMM_activity extends Activity {

	private ImageButton sz_return_button;
	private EditText xgmm_edittext01;
	private EditText xgmm_edittext02;
	private EditText xgmm_edittext03;
	private Button xgmm_submit;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xgmm_activity_layout);
		context=this;
		sz_return_button =(ImageButton) findViewById(R.id.sz_return_button);
		xgmm_edittext01=(EditText) findViewById(R.id.xgmm_edittext01);
		xgmm_edittext02=(EditText) findViewById(R.id.xgmm_edittext02);
		xgmm_edittext03=(EditText) findViewById(R.id.xgmm_edittext03);
		xgmm_submit=(Button) findViewById(R.id.xgmm_submit_button);
		
		InnerOnCLickListener i =new InnerOnCLickListener();
		sz_return_button.setOnClickListener(i);
		xgmm_submit.setOnClickListener(i);
	}

	private class InnerOnCLickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.sz_return_button:{
				XGMM_activity.this.finish();
				break;
			}
			case R.id.xgmm_submit_button:{
				String oldpassword =xgmm_edittext01.getText().toString();
				String newpassword =xgmm_edittext02.getText().toString();
				String renewpassword =xgmm_edittext03.getText().toString();
				if("".equals(oldpassword)){
					Toast.makeText(XGMM_activity.this, "旧密码不能为空",
							Toast.LENGTH_SHORT).show();
				}else if(("").equals(renewpassword)){
					Toast.makeText(XGMM_activity.this, "重复新密码不能为空",
							Toast.LENGTH_SHORT).show();
				}else if(("").equals(newpassword)){
					Toast.makeText(XGMM_activity.this, "新密码不能为空",
							Toast.LENGTH_SHORT).show();
				}else if(!newpassword.equals(renewpassword)){
			    	Toast.makeText(XGMM_activity.this, "两次密码不同",
							Toast.LENGTH_SHORT).show();
				}else{
					new Thread(new MyThread1(oldpassword,newpassword)).start();
				}
			}
			}
		}
		
	}

	private class MyThread1 implements Runnable{

		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String oldpassword = "";
		String newpassword="";
		String line = "";
		StringBuilder response;
		public MyThread1(String oldpassword,String newpassword){
			this.oldpassword=oldpassword;
			this.newpassword=newpassword;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			URL url;
			try {
				UserDao user =new UserDao(context);
				User u=user.detailMessage();
				long id = u.getUser_id();
				String phone =u.getUser_phoneNo();
				String security_key =u.getSecurity_key();
				MarkKey re = new MarkKey();
				
				
				url =new URL(new UriFactory().getModifyUserPasswordUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				
				String timekey = re.builderTimeKey();
				System.out.println(phone+"----------"+security_key);
				String message_key =re.builderMessageKey(phone,security_key);
				String oldpassword_encry = re.Encrypt(re.getMD5(oldpassword), message_key);
				String newpassword_encry = re.Encrypt(re.getMD5(newpassword), message_key);
				
			    String id_en=re.builderId(id+"",security_key);
				String id_enery =re.Encrypt(id_en, timekey);
				
				String a = "enOldPassword=" + URLEncoder.encode(oldpassword_encry, "UTF-8")+
						"&enNewPassword="+ URLEncoder.encode(newpassword_encry, "UTF-8")
						+"&enId="+URLEncoder.encode(id_enery, "UTF-8");
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
				System.out.println(response.toString().trim());
				String msg=JSONAnalysis(response.toString().trim());
				String wrong="";
				if ("success".equals(msg)) {
					data.putString("value", msg);
				}else if("fail".equals(msg)){
					wrong=JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
				}
				message.setData(data);
				handler1.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	final Handler handler1 =new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle data = msg.getData();
			String val = data.getString("value");
			if (val.equals("401")) {
				Toast.makeText(XGMM_activity.this, "信息解析错误",
						Toast.LENGTH_SHORT).show();
			} else if (val.equals("success")) {
				Toast.makeText(XGMM_activity.this, "更改成功", Toast.LENGTH_SHORT)
						.show();
				XGMM_activity.this.finish();
			} else if(val.equals("101")){
				Toast.makeText(XGMM_activity.this, "旧密码错误，不能更改密码",
						Toast.LENGTH_SHORT).show();
			}

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
}
