package com.clv.parttimejobs.dao.user;

import java.util.List;
import java.util.Map;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.my.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {

	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;

	public UserDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		
		db = dbOpenHelper.getWritableDatabase();
		createTable();
	}

	public boolean isTableExists() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='user';";
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
			String sql = "CREATE TABLE user ("
					+ "user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "user_name VARCHAR(16)  NOT NULL ," 
					+ "user_phoneNo VARCHAR(16)  NOT NULL,"
					+ "security_key VARCHAR(16)  NOT NULL,"
					+ "headPortraitPath VARCHAR(16)  NOT NULL,"
					+ "isnowlogin INTEGER NOT NULL"+
					")";
			db.execSQL(sql);
		}
	}

//	public void detele(){
//		dbOpenHelper.deleteDatabase(context);
//	}
	public long insert(User user) {
		//
		emptyTable();
		deleUser(user.getUser_id()+"");
		long id = -1;
		String table = "user";
		String nullColumnHack = "user_id";
		ContentValues values = new ContentValues();
		values.put("user_id", user.getUser_id());
		values.put("user_name", user.getUser_name());
		values.put("user_phoneNo", user.getUser_phoneNo());
		values.put("security_key", user.getSecurity_key());
		values.put("headPortraitPath", user.getHeadPortraitPath());
		values.put("isnowlogin",1);
		id = db.insert(table, nullColumnHack, values);
		db.close();
		return id;
	}
	public boolean isdetailLogin(){
		Long Id = (long) 0;
		Cursor c = db.rawQuery("SELECT user_id FROM user WHERE isnowlogin = 1", null); 
		if (c.moveToFirst()){
			Id=c.getLong(0);
			}
		if(Id!=0)return true;
		
		return false;
	}
	public long detailId(){
		long Id=0;
		Cursor c = db.rawQuery("SELECT user_id FROM user WHERE isnowlogin = 1", null);  
		if (c.moveToFirst())
			Id=c.getLong(0);
		return Id;
	}
	public String detailImgUrl(){
		String url="";
		Cursor c = db.rawQuery("SELECT headPortraitPath FROM user WHERE isnowlogin = 1", null);  
		if (c.moveToFirst())
			url=c.getString(0);
		return url;
	}
	public String detailLogin(){
		String name="";
		Cursor c = db.rawQuery("SELECT user_name FROM user WHERE isnowlogin = 1", null);  
		if (c.moveToFirst())
		name=c.getString(0);
		return name;
	}
	public String detailheadPortraitPath(){
		String headPortraitPath="";
		Cursor c = db.rawQuery("SELECT headPortraitPath FROM user WHERE isnowlogin = 1", null);  
		if (c.moveToFirst())
			headPortraitPath=c.getString(0);
		return headPortraitPath;
	}
	public String detailPhoneNo(){
		String phone="";
		Cursor c = db.rawQuery("SELECT user_phoneNo FROM user WHERE isnowlogin = 1", null);  
		if (c.moveToFirst())
			phone=c.getString(0);
		return phone;
	}
	public User detailMessage(){
		User u =new User();
		Cursor c = db.rawQuery("SELECT * FROM user WHERE isnowlogin = 1", null);  
		 while(c.moveToNext()){
			  u.setUser_id(c.getInt(c  
	                    .getColumnIndex("user_id")));  
			  u.setUser_name(c.getString(c  
	                    .getColumnIndex("user_name"))); 
			  u.setUser_phoneNo(c.getString(c  
	                    .getColumnIndex("user_phoneNo")));
			  u.setSecurity_key(c.getString(c  
	                    .getColumnIndex("security_key")));
			  u.setHeadPortraitPath(c.getString(c  
	                    .getColumnIndex("headPortraitPath")));
		 }
		 return u;
	}
	public boolean detailTable(){
		boolean a =false;
		try{
		Cursor c = db.rawQuery("SELECT * FROM user WHERE isnowlogin = 1", null); 
		if (c.moveToFirst())
		if(c.getString(0).length()>0){
			a=true;
		}
		}catch(Exception e){
			return false;
		}
	    return a;
	}
	public long detail(User user){
		long r =0;
		 //读取数据 
		Cursor c = db.rawQuery("SELECT* FROM user WHERE user_phoneNo ="+user.getUser_phoneNo(), null);  
		while (c.moveToNext()) { 
		    r++;
		} 
		c.close();
		return r;
	}
	  /**  
     * 改，修改指定id的数据  
     */  
    public void updata(User user) {  
    	emptyTable();
        String table = "user";
        ContentValues values = new ContentValues();  
        values.put("user_name", user.getUser_name());
		values.put("user_phoneNo", user.getUser_phoneNo());
		values.put("security_key", user.getSecurity_key()); 
		values.put("isnowlogin", 1);
        db.update(table, values,"user_phoneNo ="+user.getUser_phoneNo(),null);  
    }  
    public void updataHeadImg(String imgurl){
    	 String table = "user";
    	 ContentValues values = new ContentValues(); 
         values.put("headPortraitPath", imgurl);
         db.update(table, values,"isnowlogin = 1",null);  
    }
    public void emptyTable(){
    	
        String table = "user";
        ContentValues values = new ContentValues(); 
        values.put("isnowlogin", 0);
        db.update(table, values,null,null);
    }

    public void exitLogin(){
    	 String table = "user";
    	 ContentValues values = new ContentValues(); 
         values.put("isnowlogin", 0);
         db.update(table, values,"isnowlogin = 1",null);  
    }
    /**
     * 删除列
     */
    public void deleUser(String user_id){
    	 String table = "user";
    	db.delete(table,"user_id = "+user_id,null);
    }
    public void destory(){
    	db.close();
    }
}
