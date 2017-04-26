package com.clv.parttimejobs.dao.user.message;

import java.util.ArrayList;
import java.util.List;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.message.person.MessageSend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessageDao {

	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;

	public MessageDao(Context context) {
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
//		db.execSQL("drop table if exists  histroy"); 
//		db.execSQL("drop table if exists user"); 
//		db.execSQL("drop table if exists user_message"); 
//		db.execSQL("drop table if exists message"); 
//		db.execSQL("drop table if exists people_message"); 
		createTable();
	}

	public List<MessageSend> detail() {
		db = dbOpenHelper.getWritableDatabase();
		List<MessageSend> l = new ArrayList<MessageSend>();
		MessageSend messagesend;
		Cursor c1 = null;
		Cursor c2 = null;
		int user_id;
		int message_id;
		String name = "";// 姓名
		String headimg_url="";// 头像地址
		String textcontext="";// 字
		int istext = 0;// 是字
		String musicUrl="";// 语音地址
		int isMy = 0;// 是自己发的
		float time = 0;// 时间
		String datetime="";
		Cursor c = db.rawQuery("SELECT * FROM people_message ", null);
		while (c.moveToNext()) {
			user_id = c.getInt(1);
			message_id = c.getInt(2);
			System.out.println(user_id+"------"+message_id);
			c1 = db.rawQuery("SELECT * FROM other_message WHERE id ='"+user_id+"'", null);
			while (c1.moveToNext()) {
				name =c1.getString(1);
				headimg_url=c1.getString(2);
			}
			c2 = db.rawQuery("SELECT * FROM message WHERE id ='"+message_id+"'", null);
			while (c2.moveToNext()) {
				isMy=c2.getInt(1);
				istext=c2.getInt(2);
				textcontext=c2.getString(3);
				musicUrl=c2.getString(4);
				time=c2.getInt(5);
				datetime=c2.getString(6);
			}
			messagesend =new MessageSend(name,headimg_url,textcontext,istext,musicUrl,isMy,time,datetime,false);
			l.add(messagesend);
		}
		if (c1 != null)
			c1.close();
		if (c2 != null)
			c2.close();
		if (c != null)
			c.close();
		db.close();
		return l;

	}

	public long insertUser(MessageSend messagesend) {
		//
		long id = -1;
		db = dbOpenHelper.getWritableDatabase();
		String table = "other_message";
		String nullColumnHack = "id";
		ContentValues values = new ContentValues();
		values.put("user_name", messagesend.getName());
		values.put("user_headimage", messagesend.getHeadimg_url());
		long user_id = db.insert(table, nullColumnHack, values);

		String table2 = "message";
		String nullColumnHack2 = "id";
		ContentValues values2 = new ContentValues();
		values2.put("sendByUser", messagesend.getIsMy());
		values2.put("is_text", messagesend.getIstext());
		values2.put("text_content", messagesend.getTextcontext());
		values2.put("music_url", messagesend.getMusicUrl());
		values2.put("music_length", messagesend.getTime());
		values2.put("time_create", messagesend.getDatetime());
		
		long message_id = db.insert(table2, nullColumnHack2, values2);

		String table3 = "people_message";
		String nullColumnHack3 = "id";
		ContentValues values3 = new ContentValues();
		values3.put("user_id", user_id);
		values3.put("message_id", message_id);
		id = db.insert(table3, nullColumnHack3, values3);

		db.close();
		return id;
	}

	public void createTable() {
		if (!isTableExists1()) {
			String sql = "CREATE TABLE other_message ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "user_name VARCHAR(16)  NOT NULL ,"
					+ "user_headimage VARCHAR(16)  NOT NULL" + ")";
			db.execSQL(sql);
		}
		if (!isTableExists2()) {
			String sql = "CREATE TABLE message ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "sendByUser int  NOT NULL ,"
					+ "is_text VARCHAR(16)  NOT NULL ,"
					+ "text_content VARCHAR(16)  NOT NULL,"
					+ "music_url VARCHAR(16)  NOT NULL,"
					+ "music_length VARCHAR(16)  NOT NULL,"
					+ "time_create VARCHAR(16)  NOT NULL" + ")";
			db.execSQL(sql);
		}
		if (!isTableExists3()) {
			String sql = "CREATE TABLE people_message ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "user_id INTEGER  NOT NULL ,"
					+ "message_id INTEGER  NOT NULL,"
					+ "FOREIGN KEY (user_id) REFERENCES user_message  (id) ,"
					+ "FOREIGN KEY (message_id) REFERENCES message  (id) "
					+ ")";
			db.execSQL(sql);
		}
		db.close();
	}

	public boolean isTableExists1() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='other_message';";
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

	public boolean isTableExists2() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='message';";
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

	public boolean isTableExists3() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='people_message';";
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
}
