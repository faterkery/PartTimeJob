package com.clv.parttimejobs.fragment.task_fragment.parttimejob.dsh_fragment;

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
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.mainlayout.jobxq.JobXQ_activity;
import com.clv.parttimejobs.dao.parttimejob.PartJobDao;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.consult.News;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.model.interfacebackage.orderfragment_interface;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonEcute;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.view.adapter.consult.News_list_adapter;
import com.clv.parttimejobs.view.adapter.task.parttimejob.dsh.My_order_dsh_Adapter;

public class Part_fragment_1 extends Fragment implements orderfragment_interface{

	private ListView listview;
    private Context context;
    private  List<PartJobBean> daishenhe;
    private My_order_dsh_Adapter myListpaerjobAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.partjob_fragment_2, null);
		context=this.getActivity();
		
		listview = (ListView) viewRoot.findViewById(R.id.listview_partjob_dsh);

		daishenhe = new ArrayList<PartJobBean>();
		
		
		myListpaerjobAdapter = new My_order_dsh_Adapter(
				this.getActivity(), daishenhe,this);
		listview.setDividerHeight(0);
		listview.setAdapter(myListpaerjobAdapter);
		
		new Thread(new MyThread1()).start();
		return viewRoot;
	}
	@Override
	public void gotoalayout(int position) {
		// TODO Auto-generated method stub
		PartJobBean n =daishenhe.get(position);
		Intent intent  =new Intent(context,JobXQ_activity.class);
		intent.putExtra("partTimeId",n.getPartTimeId());
		startActivity(intent);
	}

	//信息块
	public class MyThread1 implements Runnable {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
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
				
				URL url = new URL(new UriFactory().getPendingListUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String timekey = re.builderTimeKey();
				String id_en = re.builderId(id + "", security_key);
				String id_enery = re.Encrypt(id_en, timekey);
				
				String a = "enId=" + URLEncoder.encode(id_enery, "UTF-8");
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
				System.out.println( response.toString().trim());
				data.putString("value", response.toString().trim());
				String msg=new JsonEcute().JSONAnalysis(response.toString().trim());
				String wrong="";
				if ("success".equals(msg)) {
					data.putString("value", msg);
					List<PartJobBean> list =JSONAnalysisMessage(response.toString().trim());
					daishenhe.clear();
					for(PartJobBean b:list){
						daishenhe.add(b);
					}
				}else if("fail".equals(msg)){
					wrong=new JsonEcute().JSONAnalysisWrong(response.toString().trim());
					data.putString("value",wrong );
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

			}
		}
	}
	final Handler handler1 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1: {
				Bundle data = msg.getData();
				String val = data.getString("value");
				if (val.equals("success")) {
					myListpaerjobAdapter.notifyDataSetChanged();  
				} else if (val.equals("101")){
					Toast.makeText(context, "未知错误",
							Toast.LENGTH_SHORT).show();
				}else if (val.equals("202")){
					Toast.makeText(context, "身份验证相关的错误,请重新登录",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
			}
			super.handleMessage(msg);
		}
	};
	protected List<PartJobBean> JSONAnalysisMessage(String string) {
		List<PartJobBean> partjob =  new ArrayList<PartJobBean>();  
		try {
		JSONObject jsonObj = new JSONObject(string); 
		JSONArray resultJsonArray = jsonObj.getJSONArray("value");
        System.out.println(resultJsonArray.length());
        for(int i=0;i<resultJsonArray.length();i++){
        	JSONObject resultJsonObject= resultJsonArray.getJSONObject(i); 
        	PartJobBean p =new PartJobBean();
        	p.setLastTime(resultJsonObject.getString("lastTime"));
        	p.setLocation(resultJsonObject.getString("location"));
        	p.setPartTimeId(resultJsonObject.getString("partTimeId"));
        	System.out.println(p.getPartTimeId());
        	p.setPartTimeQualification(resultJsonObject.getString("partTimeQualification"));
        	p.setPhotoName(resultJsonObject.getString("photoName"));
        	p.setSalary(resultJsonObject.getString("salary"));
        	p.setTitle(resultJsonObject.getString("title"));
        	p.setWorkDate(resultJsonObject.getString("workDate"));
        	partjob.add(p);
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return partjob;
	}
}
