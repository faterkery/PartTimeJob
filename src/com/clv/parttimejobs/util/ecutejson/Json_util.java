package com.clv.parttimejobs.util.ecutejson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.clv.parttimejobs.entity.my.User;

public class Json_util {


	/**
	 * JSON½âÎö·½·¨
	 */
	public static  User getListUser(byte[] data) throws Exception {
		User user = null;
        String jsonString =new String(data).trim();
        System.out.println(new String(data).trim());

        try {


        	JSONObject demoJson = new JSONObject(jsonString);
        	String security_key = demoJson.getString("security_key");
        	long user_id = demoJson.getInt("user_id");
        	String user_name = demoJson.getString("user_name");
        	String user_phoneNo = demoJson.getString("user_phoneNo");
//        	user =new User(user_id,user_name,user_phoneNo,security_key);
        	

        } catch (Exception e) {
            // TODO: handle exception
        }

        return user;

}

}
