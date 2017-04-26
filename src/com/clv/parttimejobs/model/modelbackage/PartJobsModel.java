package com.clv.parttimejobs.model.modelbackage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.clv.parttimejobs.dao.parttimejob.PartJobDao;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.consult.DatailJobBean;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.consult.job.JobDescriptionBean;
import com.clv.parttimejobs.entity.consult.job.ProblemBean;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.model.callback.JobBaoMingProblem;
import com.clv.parttimejobs.model.callback.JobsListCallback;
import com.clv.parttimejobs.model.callback.JobsListInformationCallback;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonEcute;
import com.clv.parttimejobs.util.ecutejson.JsonToPartJobsParser;
import com.clv.parttimejobs.util.ecutetime.GetLastTime;
import com.clv.parttimejobs.util.encrypt.MarkKey;

public class PartJobsModel {

	/*
	 * 
	 * 《-------------------------加载兼职列表-----------------------------》
	 * 
	 */
	public void loadPartJobsList(final Context context,final JobsListCallback callback){
		AsyncTask<Void,Void, String[]> task = new AsyncTask<Void,Void, String[]>(){

			HttpURLConnection connection = null;
			BufferedReader reader = null;
			String phone = "";
			String password = "";
			String line = "";
			StringBuilder response;
			String[] result =new String[1];
			@Override
			protected String[] doInBackground(Void... params) {
				// TODO Auto-generated method stub
				try {
					URL url = new URL(new UriFactory().getPartJobUrl());
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					DataOutputStream out = new DataOutputStream(
							connection.getOutputStream());
					PartJobDao partjobdao =new PartJobDao(context);
					GetLastTime getlasttime =new GetLastTime();
					String lasttime=getlasttime.getLastTime(context);
					String a = "lastTime=" + URLEncoder.encode(lasttime, "UTF-8");
					out.writeBytes(a);
					out.flush();
					InputStream in = connection.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					response = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					System.out.println( response.toString().trim());
					String msg=new JsonEcute().JSONAnalysis(response.toString().trim());
					String wrong="";
					if ("success".equals(msg)) {
						List<PartJobBean> list =JSONAnalysisMessage(response.toString().trim());
						PartJobDao dao =new PartJobDao(context);
						for(PartJobBean p : list){
							if(dao.isPartJobExistsAndisLate(p)){
								dao.updata(p);
							}else{
								dao.insert(p);
							}
						}
						dao.destory();
					}else if("fail".equals(msg)){
						wrong=new JsonEcute().JSONAnalysisWrong(response.toString().trim());
					}
					result[0]=msg;
				} catch (SocketTimeoutException e) {
					result[0]="sockettimeout";
					
				} catch (Exception e){
					e.printStackTrace();
				}finally {
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
				return result;
			}
			@Override
			protected void onPostExecute(String[] result) {
				// TODO Auto-generated method stub
				callback.onJobsListLoaded(result[0]);
				//
				
			}
		};
		task.execute();
	}
	
	//解析错误
	public String JSONAnalysisWrong(String string) {
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
	//解析兼职列表
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
	/*
	 * 
	 *<---------------------------------加载兼职详情------------------------------------------> 
	 * 
	 */
	public void loadPartJobsInforationList(final Context context,final String parttimeId,final JobsListInformationCallback callback){
		AsyncTask<Void,Void, String[]> task = new AsyncTask<Void,Void, String[]>(){

			HttpURLConnection connection = null;
			BufferedReader reader = null;
			String line = "";
			StringBuilder response;
			String[] result =new String[1];
			boolean isLoign =false;
			@Override
			protected String[] doInBackground(Void... params) {
				// TODO Auto-generated method stub
				try {
					UserDao user = new UserDao(context);
					User u = user.detailMessage();
					long id = u.getUser_id();
					String phone = u.getUser_phoneNo();
					String security_key = u.getSecurity_key();
					MarkKey re = new MarkKey();
					
					URL url = new URL(
							new UriFactory().getPartJobInformationUrl());
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					user.destory();
					DataOutputStream out = new DataOutputStream(
							connection.getOutputStream());
					String a="";
					if(id!=0){
						System.out.println("---"+phone+security_key);
						String message_key = re.builderMessageKey(phone, security_key);
						String parttimeId_encry = re.Encrypt(parttimeId, message_key);
						isLoign=true;
						System.out.println(id+"--"+parttimeId);
						
						String timekey = re.builderTimeKey();
						String id_en = re.builderId(id + "", security_key);
						String id_enery = re.Encrypt(id_en, timekey);
						 a =  "partTimeId="+ URLEncoder.encode(parttimeId_encry, "UTF-8")
						     + "&userId="+ URLEncoder.encode(id_enery, "UTF-8").trim();
					}else{
						 a = "partTimeId="
								+ URLEncoder.encode(parttimeId, "UTF-8");
					}
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
					result[0]=response.toString().trim();
					
				}  catch(SocketTimeoutException e){
					result[0]="sockettimeout";
			    }  catch (Exception e) {
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

				return result;
			}
			@Override
			protected void onPostExecute(String[] result) {
				// TODO Auto-generated method stub
				if(!result[0].equals("sockettimeout")){
				String msg = new JsonEcute().JSONAnalysis(result[0]);
				String wrong = "";
				JsonToPartJobsParser jsonparser=new JsonToPartJobsParser();
				List<DatailJobBean> list;
				if ("success".equals(msg)) {
					if(isLoign){
						//登陆
						 list = jsonparser.JSONAnalysisMessageForInformationOnLoginIn(result[0], parttimeId);
					}else{
						//为登陆
						 list = jsonparser.JSONAnalysisMessageForInformation(result[0], parttimeId);
					}
					
					callback.onJobsInformationListLoaded(list.get(0));
				} else if ("fail".equals(msg)) {
					wrong = new JsonEcute().JSONAnalysisWrong(result[0]);
					if (wrong.equals("401")) {
						Toast.makeText(context, "没有兼职信息", Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
					}
				}
				}else{
					Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
				}
				//
				
			}
		};
		task.execute();
	}
	//兼职报名
	public void loadPartJobBaoMingList(final Context context,final String parttimeId,final JobBaoMingProblem callback){
		AsyncTask<Void,Void, String[]> task = new AsyncTask<Void,Void, String[]>(){
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String line = "";
		StringBuilder response;
		String[] result =new String[1];
		boolean isLoign =false;
		@Override
		protected String[] doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				UserDao user = new UserDao(context);
				User u = user.detailMessage();
				long id = u.getUser_id();
				String phone = u.getUser_phoneNo();
				String security_key = u.getSecurity_key();
				MarkKey re = new MarkKey();
				
				URL url = new URL(
						new UriFactory().getPartRegistrationUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				user.destory();
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String a="";
					String message_key = re.builderMessageKey(phone, security_key);
					String parttimeId_encry = re.Encrypt(parttimeId, message_key);
					isLoign=true;
					String userId_encry = re.Encrypt(id+"", message_key);
					String timekey = re.builderTimeKey();
					String id_en = re.builderId(id + "", security_key);
					String id_enery = re.Encrypt(id_en, timekey);
					a = "enUserId="
							+ URLEncoder.encode(id_enery, "UTF-8").trim()+
					"&enpartTimeId=" + URLEncoder.encode(parttimeId_encry, "UTF-8");
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
				result[0]=response.toString().trim();
				
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

			return result;
		}
		@Override
		protected void onPostExecute(String[] result) {
			// TODO Auto-generated method stub
			String msg = new JsonEcute().JSONAnalysis(result[0]);
			String wrong = "";
			JsonToPartJobsParser jsonparser=new JsonToPartJobsParser();
			List<ProblemBean> list;
			if ("success".equals(msg)) {
					 list = jsonparser.JSONAnalysisMessageForProblem(result[0], parttimeId);
				callback.onJobsProblemListLoaded("success", list);
			} else if ("fail".equals(msg)) {
				wrong = new JsonEcute().JSONAnalysisWrong(result[0]);
				if (wrong.equals("401")) {
					Toast.makeText(context, "信息解密失败", Toast.LENGTH_SHORT)
							.show();
				} else if(wrong.equals("101")){
					Toast.makeText(context, "已处于报名或被录取或被拒绝的状态", Toast.LENGTH_SHORT)
					.show();
				}
				else {
					Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
				}
				List<ProblemBean> list12 =new ArrayList<ProblemBean>();
				callback.onJobsProblemListLoaded("wrong",list12);
			}
			
			//
			
	      }
		};
	 task.execute();	
    }
}