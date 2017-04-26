package com.clv.parttimejobs.util.ecutejson;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParse {

	public String JSONAnalysis(String string) { 
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
	public String JSONAnalysisWrong(String string) { 
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
