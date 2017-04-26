package com.clv.parttimejobs.dao.user;

import java.util.ArrayList;
import java.util.List;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.my.resume.Jntc_basis;
import com.clv.parttimejobs.entity.my.resume.UserPhoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserJntcDao {

	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	public UserJntcDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
		createTable();
	}
	public boolean isTableExists() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='userjntc';";
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
			String sql = "CREATE TABLE userjntc ("
					+ "user_id INTEGER NOT NULL, "
					+ "skill_id VARCHAR(16)  PRIMARY KEY NOT NULL,"
					+ "skill_context VARCHAR(16)  NOT NULL"
					+")";
			db.execSQL(sql);
		}
	}
	public long insert(String user_id,String skill_id,String skill_context){
		//
		long id = -1;
		String table = "userjntc";
		String nullColumnHack = "user_id";
		ContentValues values = new ContentValues();
		values.put("user_id",user_id);
		values.put("skill_id",skill_id);
		values.put("skill_context",skill_context);
		id = db.insert(table, nullColumnHack, values);
		return id;
	}
	public void deleImgAll(){
		 String table = "userjntc";
		   	db.delete(table,"",null);
	}
	public List<Jntc_basis> detailJntc(Long user_id){
		List<Jntc_basis> userjntc =new ArrayList<Jntc_basis>();
		Cursor c = db.rawQuery("SELECT * FROM userjntc WHERE user_id = "+user_id, null);  
		while (c.moveToNext()) { 
			Jntc_basis j =new Jntc_basis();
			 j.setUser_id(c.getLong(c  
	                    .getColumnIndex("user_id"))); 
			 j.setId(c.getString(c.getColumnIndex("skill_id")));
			 j.setContext(c.getString(c.getColumnIndex("skill_context")));
			 userjntc.add(j);
		 }
		return userjntc;
	}
	public String detailId(Long user_id,int position){
		List<Jntc_basis> l = detailJntc(user_id);
		Jntc_basis basis = l.get(position);
		return basis.getId();
	}
	public void removeJntc(Long user_id,int position){
		List<Jntc_basis> l = detailJntc(user_id);
		Jntc_basis basis = l.get(position);
		String table = "userjntc";
	   	db.delete(table,"skill_id = '"+basis.getId()+"'",null);
	}
	public void destory(){
    	db.close();
    }
}
