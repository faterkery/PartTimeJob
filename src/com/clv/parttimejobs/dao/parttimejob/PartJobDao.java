package com.clv.parttimejobs.dao.parttimejob;

import java.util.ArrayList;
import java.util.List;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.my.resume.UserPhoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PartJobDao {

	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	public PartJobDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
		createTable();
	}
	public boolean isTableExists() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='partjobtable';";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0) {
				result = true;
			}
		}
		return result;
	}
	public void createTable() {

		if (!isTableExists()) {
			String sql = "CREATE TABLE partjobtable ("
					+ "lastTime VARCHAR(16) NOT NULL, "
					+ "location VARCHAR(16) NOT NULL, "
					+ "partTimeId INTEGER PRIMARY KEY NOT NULL, "
					+ "partTimeQualification VARCHAR(16) NOT NULL, "
					+ "photoName VARCHAR(16) NOT NULL, "
					+ "salary VARCHAR(16) NOT NULL, "
					+ "title VARCHAR(16)  NOT NULL,"
					+ "workDate VARCHAR(16)  NOT NULL"
					+")";
			db.execSQL(sql);
		}
	}
	public long insert(PartJobBean bean){
		//
		long id = -1;
		String table = "partjobtable";
		String nullColumnHack = "partTimeId";
		ContentValues values = new ContentValues();
		values.put("lastTime",bean.getLastTime());
		values.put("location",bean.getLocation());
		values.put("partTimeId",bean.getPartTimeId());
		values.put("partTimeQualification",bean.getPartTimeQualification());
		values.put("photoName",bean.getPhotoName());
		values.put("salary",bean.getSalary());
		values.put("title",bean.getTitle());
		values.put("workDate",bean.getWorkDate());
		id = db.insert(table, nullColumnHack, values);
		return id;
	}
	public void deleJob(String partTimeId){
   	 String table = "partjobtable";
   	 db.delete(table,"partTimeId = "+partTimeId,null);
   }
	public List<PartJobBean> detail(){
		List<PartJobBean> partjob =new ArrayList<PartJobBean>();
		Cursor c = db.rawQuery("SELECT * FROM partjobtable ", null); 
		while (c.moveToNext()) { 
			PartJobBean p =new PartJobBean();
			p.setLastTime(c.getString(c.getColumnIndex("lastTime")));
			p.setLocation(c.getString(c.getColumnIndex("location")));
			p.setPartTimeId(c.getString(c.getColumnIndex("partTimeId")));
			p.setPartTimeQualification(c.getString(c.getColumnIndex("partTimeQualification")));
			p.setPhotoName(c.getString(c.getColumnIndex("photoName")));
			p.setSalary(c.getString(c.getColumnIndex("salary")));
			p.setTitle(c.getString(c.getColumnIndex("title")));
			p.setWorkDate(c.getString(c.getColumnIndex("workDate")));
			partjob.add(p);
		}
		return partjob;
	}
	public void updata(PartJobBean partjob) {  
        String table = "partjobtable";
        ContentValues values = new ContentValues();  
        values.put("lastTime", partjob.getLastTime());
		values.put("location", partjob.getLocation());
		values.put("partTimeId", partjob.getPartTimeId());
		values.put("partTimeQualification", partjob.getPartTimeQualification());
		values.put("photoName", partjob.getPhotoName());
		values.put("salary", partjob.getSalary());
		values.put("title", partjob.getTitle());
		values.put("workDate", partjob.getWorkDate());
        db.update(table, values,"partTimeId ="+partjob.getPartTimeId(),null);  
    }  
	public boolean isPartJobExistsAndisLate(PartJobBean bean){
		boolean isExist=false;
		List<PartJobBean> l =detail();
		for(PartJobBean p:l){
			if(p.getPartTimeId().equals(bean.getPartTimeId())){
				isExist=true;break;
			}
		}
		return isExist;
	}
	public void destory(){
    	db.close();
    }
	public void drop(){
		String sql ="DROP TABLE partjobtable";
		db.execSQL(sql);
	}
}
