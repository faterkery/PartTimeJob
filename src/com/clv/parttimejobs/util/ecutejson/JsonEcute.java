package com.clv.parttimejobs.util.ecutejson;

import java.util.ArrayList;
import java.util.List;







import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.clv.parttimejobs.entity.my.User;

public class JsonEcute {

	public String JSONAnalysis(String string) {
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

	public List<User> JSONAnalysisMessage(String string) {
		List<User> user =  new ArrayList<User>();  
		if(string != null && string.startsWith("\ufeff"))  
		{  
			string =  string.substring(1);  
		}  
		try {
		JSONObject jsonObj = new JSONObject(string); 
		JSONArray resultJsonArray = jsonObj.getJSONArray("value");
        System.out.println(resultJsonArray.length());
		String resultJsonObjectstring = resultJsonArray.getJSONObject(0).toString(); 
//		String jsonstr =resultJsonObject.toString();
		JSONObject resultJsonObject =new JSONObject(resultJsonObjectstring);
		User u1 =new User();
		u1.setHeadPortraitPath(resultJsonObject.getString("headPortraitPath"));
		u1.setSecurity_key(resultJsonObject.getString("security_key"));
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
