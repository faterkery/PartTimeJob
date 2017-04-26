package com.clv.parttimejobs.dao.user;

import java.util.ArrayList;
import java.util.List;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.my.resume.UserPhoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserPhotoDao {

	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	public UserPhotoDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
		createTable();
	}
	public boolean isTableExists() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='userphoto';";
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
			String sql = "CREATE TABLE userphoto ("
					+ "user_id INTEGER NOT NULL, "
					+ "user_img VARCHAR(16)  PRIMARY KEY NOT NULL,"
					+ "img_url VARCHAR(16)  NOT NULL"
					+")";
			db.execSQL(sql);
		}
	}
	public long insert(String user_id,String user_img,String user_url){
		//
		
		long id = -1;
		String table = "userphoto";
		String nullColumnHack = "user_id";
		ContentValues values = new ContentValues();
		values.put("user_id",user_id);
		values.put("user_img",user_img);
		values.put("img_url",user_url);
		id = db.insert(table, nullColumnHack, values);
		return id;
	}
	public void deleUser(String user_id){
   	 String table = "userphoto";
   	db.delete(table,"user_id = "+user_id,null);
   }
	public void deleImg(String user_img){
		 String table = "userphoto";
		   	db.delete(table,"user_img = '"+user_img+"'",null);
	}
	public void deleImgAll(){
		 String table = "userphoto";
		   	db.delete(table,"",null);
	}
	//查詢所有的图片的url
	public List<UserPhoto> detailPhoto(Long user_id){
		List<UserPhoto> userphoto =new ArrayList<UserPhoto>();
		Cursor c = db.rawQuery("SELECT * FROM userphoto WHERE user_id = "+user_id, null);  
		while (c.moveToNext()) { 
			 UserPhoto u =new UserPhoto();
			 u.setUser_id(c.getInt(c  
	                    .getColumnIndex("user_id"))); 
			 u.setUser_imgname(c.getString(c.getColumnIndex("user_img")));
			 u.setUser_imgurl(c.getString(c.getColumnIndex("img_url")));
			 userphoto.add(u);
		 }
		return userphoto;
	}
	public void destory(){
    	db.close();
    }
}
