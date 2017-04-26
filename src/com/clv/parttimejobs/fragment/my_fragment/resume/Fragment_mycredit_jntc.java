package com.clv.parttimejobs.fragment.my_fragment.resume;

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
import com.clv.parttimejobs.dao.user.UserJntcDao;
import com.clv.parttimejobs.dao.user.UserPhotoDao;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.entity.my.resume.Jntc_basis;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.resume.DeteTCInterface;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.view.adapter.my.resume.Mycredit_jntc_adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_mycredit_jntc extends Fragment implements DeteTCInterface {
	private Context context;
	ListView listview;
	private Button button_add;
	private Button jntc_button;
	private List<String> l;
	private Mycredit_jntc_adapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mycredit_jntc, null);
		context = this.getActivity();
		listview = (ListView) view.findViewById(R.id.mycredit_jntc_listview);
		l = new ArrayList<String>();
		adapter = new Mycredit_jntc_adapter(this.getActivity(), l, this);
		listview.setAdapter(adapter);
		// listview.setVerticalScrollBarEnabled(false);

		InnerOnClickListener i = new InnerOnClickListener();
		button_add = (Button) view.findViewById(R.id.mycredit_jntc_button);
		jntc_button = (Button) view.findViewById(R.id.jntc_button);
		button_add.setOnClickListener(i);
		jntc_button.setOnClickListener(i);

		button_add.requestFocus();
		listview.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				listview.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				listview.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			}
		});

		
		new Thread(new MyThread1()).start();
		return view;
	}

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.mycredit_jntc_button: {
				addSData("");
				l.add(" ");
				adapter.notifyDataSetChanged();
				listview.smoothScrollToPosition(adapter.getCount() - 1);
				
				break;
			}
			case R.id.jntc_button: {
				jntc_button.setFocusable(true);
				jntc_button.setFocusableInTouchMode(true);
				jntc_button.requestFocus();
				break;
			}
			}
		}

	}

	// 查询用户�?�?
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
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();

				URL url = new URL(
						new UriFactory().getSelectSkillUrl());
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
				List<Jntc_basis> imgnameArray = JSONAnalysisMessagetc(id,
						response.toString().trim());
				addJntc(id + "", imgnameArray);
				user.destory();

				Message message = new Message();
				message.what = 1;
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
			UserDao user = new UserDao(context);
			User u = user.detailMessage();
			long id = u.getUser_id();
			UserJntcDao userJntc = new UserJntcDao(context);
			List<Jntc_basis> userjntcList = userJntc.detailJntc(id);
			l.clear();
			for (Jntc_basis userj : userjntcList) {
				addJnt(userj.getContext());
			}
			user.destory();
			userJntc.destory();
			super.handleMessage(msg);
		}
	};

	// 增加用户特长
	private class MyThread2 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String skill_content = "";
		String line = "";
		StringBuilder response;

		public MyThread2(String skill_content) {
			this.skill_content = skill_content;
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
						new UriFactory().getAddKillUrl());
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
						+ "&skill_content="
						+ URLEncoder.encode(skill_content, "UTF-8").trim();
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
				data.putString("value", response.toString().trim());
				message.what = 1;
				message.setData(data);
				handler2.sendMessage(message);
				String val = JSONAnalysis(response.toString().trim());
				if ("101".equals(val)) {

				} else if ("201".equals(val)) {

				} else {
					UserJntcDao jntcdao = new UserJntcDao(context);
					jntcdao.insert(id+"",val,skill_content
							);
					jntcdao.destory();
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

	final Handler handler2 = new Handler() {
		public void handleMessage(Message msg) { // handle message
			Bundle data = msg.getData();
			String val = data.getString("value");
			val = JSONAnalysis(val);
			if ("fail".equals(val)) {

			} else {
				
			}
			super.handleMessage(msg);
		}
	};

	// 删除用户特长
	private class MyThread3 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		int position = 0;
		String line = "";
		StringBuilder response;

		public MyThread3(int position) {
			this.position = position;
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
				String messagekey = re.builderMessageKey(u.getUser_phoneNo(),
						security_key);
				UserJntcDao jntc = new UserJntcDao(context);
				String jntcid = jntc.detailId(id, position);
				System.out.println("删除�?" + jntcid);
				String jntcid_entry = re.Encrypt(jntcid, messagekey);

				URL url = new URL(
						new UriFactory().getDeleteKillUrl());
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
						+ "&skill_id="
						+ URLEncoder.encode(jntcid_entry, "UTF-8").trim();
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				user.destory();
				String s = JSONAnalysis(response.toString().trim());
				if (("success").equals(s)) {
					jntc.removeJntc(id, position);
				}
				jntc.destory();
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

	private class MyThread4 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		int position = 0;
		String content = "";
		String line = "";
		StringBuilder response;

		public MyThread4(int position, String content) {
			this.position = position;
			this.content = content;
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
				String messagekey = re.builderMessageKey(u.getUser_phoneNo(),
						security_key);
				UserJntcDao jntc = new UserJntcDao(context);
				String jntcid = jntc.detailId(id, position);
				String jntcid_entry = re.Encrypt(jntcid, messagekey);
				jntc.destory();
				URL url = new URL(
						new UriFactory().getModifySkillUrl());
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
						+ "&skill_id="
						+ URLEncoder.encode(jntcid_entry, "UTF-8").trim()
						+ "&skill_content="
						+ URLEncoder.encode(content, "UTF-8").trim();
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

	protected List<Jntc_basis> JSONAnalysisMessagetc(Long user_id, String string) {
		List<Jntc_basis> JntcArray = new ArrayList<Jntc_basis>();
		String content = "";
		String id = "";
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			int length = resultJsonArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject resultJsonObject = resultJsonArray.getJSONObject(i);
				content = resultJsonObject.getString("skill_content");
				id = resultJsonObject.getString("skill_id");
				Jntc_basis j = new Jntc_basis(user_id, id, content);
				JntcArray.add(j);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JntcArray;
	}

	public void addJntc(String user_id, List<Jntc_basis> jntcArray) {
		UserJntcDao userjntc = new UserJntcDao(context);
		userjntc.deleImgAll();
		for (Jntc_basis jnbasis : jntcArray) {
			userjntc.insert(user_id, jnbasis.getId(), jnbasis.getContext());
		}
		userjntc.destory();
	}

	public void addJnt(String path) {
		l.add(path);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void detaTC(int position) {
		// TODO Auto-generated method stub
		l.remove(position);
	}

	@Override
	public void addData(int position, String content) {
		// TODO Auto-generated method stub
		new Thread(new MyThread4(position, content)).start();
		l.set(position, content);
//		adapter.notifyDataSetChanged();
		
	}

	public void deteItem(int position) {
		l.remove(position);
		adapter.notifyDataSetChanged();

		new Thread(new MyThread3(position)).start();
	}

	@Override
	public void addSData(String content) {
		// TODO Auto-generated method stub
		new Thread(new MyThread2(content)).start();
	}

}