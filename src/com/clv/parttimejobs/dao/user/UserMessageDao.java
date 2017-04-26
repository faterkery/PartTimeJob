package com.clv.parttimejobs.dao.user;

import java.util.ArrayList;
import java.util.List;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.my.resume.UserMessageBean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserMessageDao {
	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	public UserMessageDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
		createTable();
	}
	public boolean isTableExists() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='user_message';";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0) {
				result = true;
			}
		}
		System.out.println(result);
		return result;
	}
	public void createTable() {

		if (!isTableExists()) {
			String sql = "CREATE TABLE user_message ("
					+ "userId INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "dateBirth VARCHAR(16)  NOT NULL,"
					+ "email VARCHAR(16)  NOT NULL,"
					+ "gender VARCHAR(16)  NOT NULL,"
					+ "height VARCHAR(16)  NOT NULL,"
					+ "user_name VARCHAR(16)  NOT NULL "
					+")";
			db.execSQL(sql);
		}else{
//			String sql="DROP TABLE user_message";
//			db.execSQL(sql);
//			String sql2 = "CREATE TABLE user_message ("
//					+ "userId INTEGER PRIMARY KEY AUTOINCREMENT, "
//					+ "dateBirth VARCHAR(16)  NOT NULL,"
//					+ "email VARCHAR(16)  NOT NULL,"
//					+ "gender VARCHAR(16)  NOT NULL,"
//					+ "height VARCHAR(16)  NOT NULL,"
//					+ "user_name VARCHAR(16)  NOT NULL "
//					+")";
//			db.execSQL(sql2);
		}
	}
	public long insert(UserMessageBean usermessagebean){
		//
		long id = -1;
		String table = "user_message";
		String nullColumnHack = "userId";
		ContentValues values = new ContentValues();
		values.put("userId",usermessagebean.getUserId());
		values.put("dateBirth",usermessagebean.getDateBirth());
		values.put("email",usermessagebean.getEmail());
		values.put("gender",usermessagebean.getGender());
		values.put("height",usermessagebean.getHeight());
		values.put("user_name",usermessagebean.getUser_name());
		id = db.insert(table, nullColumnHack, values);
		System.out.println("增加成功");
		System.out.println(usermessagebean.getUserId()+usermessagebean.getUser_name());
		return id;
	}
	public void deleAll(){
		 String table = "user_message";
		   	db.delete(table,"",null);
	}
	public List<UserMessageBean> detailMessage(Long user_id){
		List<UserMessageBean> userMessage =new ArrayList<UserMessageBean>();
		Cursor c = db.rawQuery("SELECT * FROM user_message WHERE userId = "+user_id, null);  
		while (c.moveToNext()) { 
			UserMessageBean j =new UserMessageBean();
			 j.setUserId(c.getInt(c  
	                    .getColumnIndex("userId"))); 
			 j.setDateBirth(c.getString(c.getColumnIndex("dateBirth")));
			 j.setEmail(c.getString(c.getColumnIndex("email")));
			 j.setGender(c.getString(c.getColumnIndex("gender")));
			 j.setHeight(c.getString(c.getColumnIndex("height")));
			 j.setUser_name(c.getString(c.getColumnIndex("user_name")));
			 userMessage.add(j);
		 }
		return userMessage;
	}
	
	public void removeMessage(Long user_id,int position){
		List<UserMessageBean> l = detailMessage(user_id);
		UserMessageBean basis = l.get(position);
		String table = "user_message";
	   	db.delete(table,"userId = '"+basis.getUserId()+"'",null);
	}
	public void destory(){
    	db.close();
    }
}
