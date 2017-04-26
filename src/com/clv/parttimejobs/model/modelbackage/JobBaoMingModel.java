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
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.clv.parttimejobs.dao.parttimejob.PartJobDao;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.consult.job.ProblemBean;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.model.callback.JobBaoMingProblem;
import com.clv.parttimejobs.model.callback.JobBaoMingReturn;
import com.clv.parttimejobs.model.callback.JobsListCallback;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonEcute;
import com.clv.parttimejobs.util.ecutejson.JsonToPartJobsParser;
import com.clv.parttimejobs.util.ecutetime.GetLastTime;
import com.clv.parttimejobs.util.encrypt.MarkKey;

public class JobBaoMingModel {

	//兼职报名
		public void loadPartJobBaoMingList(final Context context,final String parttimeId,final String answeer_content,final JobBaoMingReturn callback){
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
						String answeer_encry =re.Encrypt(answeer_content, message_key);
						isLoign=true;
						String userId_encry = re.Encrypt(id+"", message_key);
						String timekey = re.builderTimeKey();
						String id_en = re.builderId(id + "", security_key);
						String id_enery = re.Encrypt(id_en, timekey);
						a = "enUserId="
								+ URLEncoder.encode(id_enery, "UTF-8").trim()+
						"&enpartTimeId=" + URLEncoder.encode(parttimeId_encry, "UTF-8")
						+ "&enAnswer=" +URLEncoder.encode(answeer_encry, "UTF-8");
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
					callback.onJobMessageReturnVaule(msg);
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
				}
				
				//
				
		      }
			};
		 task.execute();	
	    }
	
}
