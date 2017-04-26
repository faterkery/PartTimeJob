package com.clv.parttimejobs.fragment.my_fragment.resume;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
import com.clv.parttimejobs.activity.minelayout.set.GHBD_activity;
import com.clv.parttimejobs.activity.minelayout.set.GHYX_activity;
import com.clv.parttimejobs.activity.minelayout.set.XGMM_activity;
import com.clv.parttimejobs.activity.minelayout.set.ZHAQ_activity;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.dao.user.UserMessageDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.entity.my.resume.UserMessageBean;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.encrypt.Decrypt;
import com.clv.parttimejobs.util.encrypt.MarkKey;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_mycredit_jbxx extends Fragment {

	private Context context;
	private View view;
	private Button main_button;

	private EditText edittext01;
	private EditText edittext02;
	private EditText edittext03;
	private TextView mycredit_phone_textview;
	private TextView mycredit_rey04_textview_xb;
	private TextView mycredit_rey05_textview_birth;
	private TextView mycredit_rey01_textview2;

	private ImageButton mycredit_imageview1;
	private ImageButton mycredit_imageview1_1;
	private ImageButton mycredit_imageview2;
	private ImageButton mycredit_imageview3;
	private ImageButton mycredit_imageview4;

	private String textcontent = "";
	private boolean isNameChange = false;
	private boolean isHeightChange = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mycredit_jbxx, null);
		init();
		context = this.getActivity();
		
		return view;
	}

	private void init() {
		edittext01 = (EditText) view.findViewById(R.id.mycredit_edittext1);
		edittext02 = (EditText) view
				.findViewById(R.id.mycredit_rey04_edittext1);
		edittext03 = (EditText) view.findViewById(R.id.mycredit_bxx_edittext02);
		mycredit_rey04_textview_xb = (TextView) view
				.findViewById(R.id.mycredit_rey04_textview_xb);
		mycredit_rey05_textview_birth = (TextView) view
				.findViewById(R.id.mycredit_rey05_textview_birth);
		mycredit_rey01_textview2 = (TextView) view
				.findViewById(R.id.mycredit_rey01_textview2);
		mycredit_imageview1 = (ImageButton) view
				.findViewById(R.id.mycredit_imageview1);
		mycredit_imageview1_1 = (ImageButton) view
				.findViewById(R.id.mycredit_imageview1_1);
		mycredit_imageview2 = (ImageButton) view
				.findViewById(R.id.mycredit_rey04_imageview1);
		mycredit_imageview3 = (ImageButton) view
				.findViewById(R.id.mycredit_rey06_imageview1);
		mycredit_imageview4 = (ImageButton) view
				.findViewById(R.id.mycredit_rey07_imageview1);
		mycredit_phone_textview = (TextView) view
				.findViewById(R.id.mycredit_phone);
		InnerOnClickListener i = new InnerOnClickListener();
		mycredit_imageview1.setOnClickListener(i);
		mycredit_imageview1_1.setOnClickListener(i);
		mycredit_imageview2.setOnClickListener(i);
		mycredit_imageview3.setOnClickListener(i);
		mycredit_imageview4.setOnClickListener(i);
		main_button = (Button) view.findViewById(R.id.mycredit_jbxx_rey_button);
		main_button.setOnClickListener(i);
		initdata();
		edittext01
				.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
							// 此处为得到焦点时的处理内�?
							textcontent = edittext01.getText().toString()
									.trim();
							edittext01.setSelection(textcontent.length());

							mycredit_imageview1.setVisibility(View.GONE);
							mycredit_imageview1_1.setVisibility(View.VISIBLE);
							isNameChange = true;
						} else {
							// 此处为失去焦点时的处理内�?
							mycredit_imageview1
									.setImageResource(R.drawable.myjxx_icon_pan_nor);
							UserDao user = new UserDao(context);
							User u = user.detailMessage();
							long id = u.getUser_id();
							UserMessageDao usermessagedao = new UserMessageDao(
									context);
							List<UserMessageBean> usermessage = usermessagedao
									.detailMessage(id);
							edittext01.setText(usermessage.get(0)
									.getUser_name());
							user.destory();
							usermessagedao.destory();
						}
					}
				});
		edittext02
				.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
							// 此处为得到焦点时的处理内�?
							textcontent = edittext02.getText().toString()
									.trim();
							edittext02.setSelection(textcontent.length());
							mycredit_imageview2
									.setImageResource(R.drawable.myjxx_icon_right_gou);

						} else {
							// 此处为失去焦点时的处理内�?
							mycredit_imageview2
									.setImageResource(R.drawable.myjxx_icon_pan_nor);
							UserDao user = new UserDao(context);
							User u = user.detailMessage();
							long id = u.getUser_id();
							UserMessageDao usermessagedao = new UserMessageDao(
									context);
							List<UserMessageBean> usermessage = usermessagedao
									.detailMessage(id);
							edittext02.setText(usermessage.get(0).getHeight());
							user.destory();
							usermessagedao.destory();
						}
					}
				});
	}

	public void initdata() {
		UserDao user = new UserDao(this.getActivity());
		if (user.detailTable()) {
			User u = user.detailMessage();
			edittext01.setText(u.getUser_name());
			mycredit_phone_textview.setText(u.getUser_phoneNo());
		}
		user.destory();
		new Thread(new MyThread3()).start();
	}

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.mycredit_imageview1: {
				System.out.println("------" + isNameChange);
				edittext01.setFocusableInTouchMode(true);
				edittext01.setFocusable(true);
				edittext01.requestFocus();
				mycredit_imageview1.setVisibility(View.GONE);
				mycredit_imageview1_1.setVisibility(View.VISIBLE);
				break;
			}
			case R.id.mycredit_imageview1_1: {
				String content = edittext01.getText().toString().trim();
				mycredit_imageview1.setVisibility(View.VISIBLE);
				mycredit_imageview1_1.setVisibility(View.GONE);
				mycredit_imageview1.setFocusableInTouchMode(true);
				mycredit_imageview1.setFocusable(true);
				mycredit_imageview1.requestFocus();
				System.out.println("姓名+++++++++++++++++++" + content);
				if (content.length() != 0)
					new Thread(new MyThread1(content)).start();
				break;
			}
			case R.id.mycredit_rey04_imageview1: {
				System.out.println(isHeightChange);
				if (isHeightChange) {
					String content = edittext02.getText().toString().trim();
					isHeightChange = false;
					mycredit_imageview2.setFocusableInTouchMode(true);
					mycredit_imageview2.setFocusable(true);
					mycredit_imageview2.requestFocus();
					new Thread(new MyThread2(content)).start();
				} else {
					edittext02.setFocusableInTouchMode(true);
					edittext02.setFocusable(true);
					edittext02.requestFocus();
					isHeightChange = true;
				}

				break;
			}
			case R.id.mycredit_rey06_imageview1: {
				Intent intent = new Intent(context, GHBD_activity.class);
				startActivity(intent);
				break;
			}
			case R.id.mycredit_rey07_imageview1: {
				Intent intent = new Intent(context, GHYX_activity.class);
				startActivity(intent);
				break;
			}
			case R.id.mycredit_jbxx_rey_button: {
				edittext01.setFocusable(false);
				edittext01.setFocusableInTouchMode(false);
				edittext02.setFocusable(false);
				edittext02.setFocusableInTouchMode(false);
				edittext03.setFocusable(false);
				edittext03.setFocusableInTouchMode(false);
				break;
			}
			}
		}

	}

	private class MyThread1 implements Runnable {

		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String newname = "";
		String line = "";
		StringBuilder response;

		public MyThread1(String newname) {
			this.newname = newname;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			URL url;
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String phone = u.getUser_phoneNo();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				url = new URL(
						new UriFactory().getModifyUserNameUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());

				String timekey = re.builderTimeKey();
				System.out.println(phone + "----------" + security_key);
				String message_key = re.builderMessageKey(phone, security_key);
				String newname_encry = re.Encrypt(newname, message_key);

				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);

				String a = "user_name="
						+ URLEncoder.encode(newname_encry, "UTF-8").trim()
						+ "&enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim();
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(a);
				Message message = new Message();
				Bundle data = new Bundle();
				System.out.println(response.toString().trim());
				String msg = JSONAnalysis(response.toString().trim());
				String wrong = "";
				if ("success".equals(msg)) {
					data.putString("value", msg);
					List<User> l = JSONAnalysisMessage(response.toString()
							.trim());
					for (User u1 : l) {
						UserDao user1 = new UserDao(context);
						user1.insert(u1);
					}
					user.destory();
				} else if ("fail".equals(msg)) {
					wrong = JSONAnalysisWrong(response.toString().trim());
					data.putString("value", wrong);
				}
				message.setData(data);
				handler1.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	final Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle data = msg.getData();
			String val = data.getString("value");
			if (val.equals("401")) {
				// Toast.makeText(context, "信息解析错误",
				// Toast.LENGTH_SHORT).show();
			} else if (val.equals("success")) {
				UserDao user = new UserDao(context);
				if (user.detailTable()) {
					User u = user.detailMessage();
					edittext01.setText(u.getUser_name());
					mycredit_phone_textview.setText(u.getUser_phoneNo());
				}
				user.destory();
			}

		}
	};

	// 修改用户身高
	private class MyThread2 implements Runnable {

		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String height = "";
		String line = "";
		StringBuilder response;

		public MyThread2(String height) {
			this.height = height;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			URL url;
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String phone = u.getUser_phoneNo();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				url = new URL(
						new UriFactory().getModifyUserHeightUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());

				String timekey = re.builderTimeKey();

				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);

				String a = "enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim()
						+ "&height="
						+ URLEncoder.encode(height, "UTF-8").trim();
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
				Bundle data = new Bundle();
				String msg = JSONAnalysis(response.toString().trim());
				String wrong = "";
				if ("success".equals(msg)) {
					data.putString("value", msg);
					new Thread(new MyThread3()).start();
				} else if ("fail".equals(msg)) {
					wrong = JSONAnalysisWrong(response.toString().trim());
					data.putString("value", wrong);
				}
				message.setData(data);
				handler2.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	final Handler handler2 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle data = msg.getData();
			String val = data.getString("value");
			if (("101").equals(val)) {
				Toast.makeText(context, "身高信息不合常理", Toast.LENGTH_SHORT).show();
			} else if (("success").equals(val)) {

			}

		}
	};

	// 查询用户信息
	private class MyThread3 implements Runnable {

		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String line = "";
		StringBuilder response;

		public MyThread3() {
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			URL url;
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				url = new URL(
						new UriFactory().getInformationUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());

				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);

				String a = "enId="
						+ URLEncoder.encode(id_enery, "UTF-8").trim();
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
				Bundle data = new Bundle();
				String msg = JSONAnalysis(response.toString().trim());
				String wrong = "";
				if ("success".equals(msg)) {
					data.putString("value", msg);
					/*
					 * 加一个解�?
					 */

					UserMessageBean usermessage = JSONAnalysisUserMessage(response
							.toString().trim());
					UserMessageDao usermessagedao = new UserMessageDao(context);
					usermessagedao.deleAll();
					usermessagedao.insert(usermessage);
					usermessagedao.destory();
				} else if ("fail".equals(msg)) {
					wrong = JSONAnalysisWrong(response.toString().trim());
					data.putString("value", wrong);
				}
				message.setData(data);
				user.destory();
				handler3.sendMessage(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	final Handler handler3 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle data = msg.getData();
			String val = data.getString("value");
			if (("success").equals(val)) {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				UserMessageDao usermessagedao = new UserMessageDao(context);
				List<UserMessageBean> usermessage = usermessagedao
						.detailMessage(id);
				mycredit_rey01_textview2.setText(usermessage.get(0)
						.getUser_name());
				mycredit_rey04_textview_xb.setText(usermessage.get(0)
						.getGender());
				edittext02.setText(usermessage.get(0).getHeight());
				mycredit_rey05_textview_birth.setText(usermessage.get(0)
						.getDateBirth());
				edittext03.setText(usermessage.get(0).getEmail());
				user.destory();
				usermessagedao.destory();
			}
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
		List<User> user = new ArrayList<User>();
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			System.out.println(resultJsonArray.length());
			JSONObject resultJsonObject = resultJsonArray.getJSONObject(0);
			User u1 = new User();
			u1.setHeadPortraitPath(resultJsonObject
					.getString("headPortraitName"));
			u1.setSecurity_key(resultJsonObject.getString("securityKey"));
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

	protected UserMessageBean JSONAnalysisUserMessage(String string) {
		UserMessageBean usermessage = new UserMessageBean();
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			JSONObject jsonobject = resultJsonArray.getJSONObject(0);
			String entrydata = jsonobject.getString("informaion");
			String sString = new Decrypt().toDecrypt(context, entrydata.trim());
			System.out.println(sString);
			JSONObject resultJsonObject = new JSONObject(sString);
			usermessage.setDateBirth(resultJsonObject.getString("dateBirth"));
			usermessage.setEmail(resultJsonObject.getString("email"));
			usermessage.setGender(resultJsonObject.getString("gender"));
			usermessage.setHeight(resultJsonObject.getString("height"));
			usermessage.setUser_name(resultJsonObject.getString("name"));
			usermessage.setUserId(resultJsonObject.getInt("userId"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usermessage;
	}
}