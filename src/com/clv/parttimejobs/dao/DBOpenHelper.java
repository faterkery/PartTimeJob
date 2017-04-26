package com.clv.parttimejobs.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "myphone.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		  db.execSQL("drop table if exists histroy");  
		  db.execSQL("drop table if exists user");  
		  db.execSQL("drop table if exists user_message");  
		  db.execSQL("drop table if exists message");  
		  db.execSQL("drop table if exists people_message");  
		  db.execSQL("drop table if exists userphoto");
		  db.execSQL("drop table if exists datailpartjobtable");
		  db.execSQL("drop table if exists answeertable");
		// TODO Auto-generated method stub
		if (!tabbleIsExist(db, "histroy")) {
			String sql = "CREATE TABLE histroy ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "histroy_name VARCHAR(16)  NOT NULL " + ")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "user")) {
			String sql = "CREATE TABLE user ("
					+ "user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "user_name VARCHAR(16)  NOT NULL ," 
					+ "user_phoneNo VARCHAR(16)  NOT NULL,"
					+ "security_key VARCHAR(16)  NOT NULL,"
					+ "headPortraitPath VARCHAR(16)  NOT NULL,"
					+ "isnowlogin INTEGER NOT NULL"
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "user_message")) {
			String sql = "CREATE TABLE user_message ("
					+ "userId INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "dateBirth VARCHAR(16)  NOT NULL,"
					+ "email VARCHAR(16)  NOT NULL,"
					+ "gender VARCHAR(16)  NOT NULL,"
					+ "height VARCHAR(16)  NOT NULL,"
					+ "user_name VARCHAR(16)  NOT NULL "
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "other_message")) {
			String sql = "CREATE TABLE other_message ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "user_name VARCHAR(16)  NOT NULL ,"
					+ "user_headimage VARCHAR(16)  NOT NULL" + ")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "message")) {
			String sql = "CREATE TABLE message ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "sendByUser int  NOT NULL ,"
					+ "is_text VARCHAR(16)  NOT NULL ," 
					+ "text_content VARCHAR(16)  NOT NULL,"
					+ "music_url VARCHAR(16)  NOT NULL,"
					+ "music_length VARCHAR(16)  NOT NULL,"
					+ "time_create VARCHAR(16)  NOT NULL"
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "people_message")) {
			String sql = "CREATE TABLE people_message ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "user_id INTEGER  NOT NULL ," 
					+ "message_id INTEGER  NOT NULL,"
					+ "FOREIGN KEY (user_id) REFERENCES user_message  (id) ,"
					+ "FOREIGN KEY (message_id) REFERENCES message  (id) "
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "userphoto")) {
			String sql = "CREATE TABLE userphoto ("
					+ "user_id INTEGER NOT NULL, "
					+ "user_img VARCHAR(16) PRIMARY KEY NOT NULL,"
					+ "img_url VARCHAR(16)  NOT NULL"
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "partjobtable")) {
			String sql = "CREATE TABLE partjobtable ("
					+ "lastTime VARCHAR(16) NOT NULL, "
					+ "location VARCHAR(16) NOT NULL, "
					+ "partTimeId INTEGER PRIMARY KEY NOT NULL, "
					+ "partTimeQualification VARCHAR(16) NOT NULL, "
					+ "photoName VARCHAR(16) NOT NULL, "
					+ "salary VARCHAR(16) NOT NULL, "
					+ "title VARCHAR(16)   NOT NULL,"
					+ "workDate VARCHAR(16)  NOT NULL"
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "datailpartjobtable")) {
			String sql = "CREATE TABLE datailpartjobtable ("
					+ "part_id auto_increment PRIMARY KEY ,"
					+ "claim VARCHAR(16) NOT NULL, "
					+ "companyId VARCHAR(16) NOT NULL, "
					+ "deadline VARCHAR(16) NOT NULL, "
					+ "jobDescription VARCHAR(16) NOT NULL, "
					+ "location VARCHAR(16) NOT NULL, "
					+ "needNumber INTEGER  NOT NULL, "
					+ "numberOfapplicants VARCHAR(16) NOT NULL, "
					+ "partTimeId VARCHAR(16) NOT NULL, "
					+ "partTimeStatus VARCHAR(16) NOT NULL, "
					+ "photoName VARCHAR(16) NOT NULL, "
					+ "salary VARCHAR(16) NOT NULL, "
					+ "settlementMethod VARCHAR(16)  NOT NULL,"
					+ "title VARCHAR(16)  NOT NULL,"
					+ "type VARCHAR(16)  NOT NULL,"
					+ "workDate VARCHAR(16)  NOT NULL"
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "jobdescriptiontable")) {
			String sql = "CREATE TABLE jobdescriptiontable ("
					+ "job_id auto_increment PRIMARY KEY ,"
					+ "partTimeId VARCHAR(16)  NOT NULL, "
					+ "content VARCHAR(16) NOT NULL, "
					+ "title VARCHAR(16) NOT NULL"
					+")";
			db.execSQL(sql);
		}
		if (!tabbleIsExist(db, "answeertable")) {
			String sql = "CREATE TABLE answeertable ("
					+ "id INT indentity(1,1) PRIMARY KEY,"
					+ "partTimeId INTEGER NOT NULL, "
					+ "problemId INTEGER  NOT NULL,"
					+ "answerContent VARCHAR(16) NOT NULL ,"
					+ "userId INTEGER NOT NULL "
					+")";
			db.execSQL(sql);
		}
	}
	 public boolean deleteDatabase(Context context) {
		  return context.deleteDatabase("myphone.db");
    }
	// 是否存在表
	public boolean tabbleIsExist(SQLiteDatabase db, String tableName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+tableName+"'";
		System.out.println(sql);
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

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// 当数据库版本升级时
	}

}
