package com.clv.parttimejobs.util.encrypt;

import android.content.Context;

import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.my.User;

public class Decrypt {

	public String toDecrypt(Context context,String message){
		UserDao user =new UserDao(context);
		User u=user.detailMessage();
		long id = u.getUser_id();
		String phone = u.getUser_phoneNo();
		String security_key =u.getSecurity_key();
		String text ="";
		text =new DecryptKey().decrypMessage(message, phone, security_key);
		user.destory();
		return text;
	}
}
