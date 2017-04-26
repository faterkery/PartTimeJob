package com.clv.parttimejobs.dao.parttimejob.problemansweer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.consult.job.gs.BaoMingAnsweerBean;

public class JobAnsweerDao {

	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	public JobAnsweerDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
		createTable();
	}
	public boolean isTableExists() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='answeertable';";
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
	public long insert(String parttimeId,BaoMingAnsweerBean bean){
		if(isExist(parttimeId,bean)){
		String sql ="DELETE from answeertable WHERE partTimeId ="+parttimeId+" and problemId ="+bean.getProblemId()+" and userId ="+bean.getUserId()+";";
		db.execSQL(sql);}
		//
		long id = -1;
		String table = "answeertable";
		String nullColumnHack = "partTimeId";
		ContentValues values = new ContentValues();
		values.put("partTimeId",parttimeId);
		values.put("problemId",bean.getProblemId());
		values.put("answerContent",bean.getAnswerContent());
		values.put("userId",bean.getUserId());
		id = db.insert(table, nullColumnHack, values);
		return id;
	}
	public List<BaoMingAnsweerBean> detail(String partTimeId){
		List<BaoMingAnsweerBean> partjob =new ArrayList<BaoMingAnsweerBean>();
		Cursor c = db.rawQuery("SELECT * FROM answeertable WHERE partTimeId ="+partTimeId, null); 
		while (c.moveToNext()) { 
			BaoMingAnsweerBean p =new BaoMingAnsweerBean();
			p.setProblemId(c.getInt(c.getColumnIndex("problemId")));
			p.setAnswerContent(c.getString(c.getColumnIndex("answerContent")));
			p.setUserId(c.getInt(c.getColumnIndex("userId")));
			partjob.add(p);
		}
		return partjob;
	}
	public boolean isExist(String partTimeId,BaoMingAnsweerBean bean){
		boolean isExist=false;
		List<BaoMingAnsweerBean> l =detail(partTimeId);
		for(BaoMingAnsweerBean p:l){
			if(p.getProblemId()==(bean.getProblemId())&&(p.getUserId()==bean.getUserId())){
				isExist=true;break;
			}
		}
		return isExist;
	}
    //通过parttimejob,userid,problemid 查找其中的值，有则返回，无则空
	public String detailAnsweerContent(String userid,String partTimeId,String problemId){
		String content="";
		Cursor c = db.rawQuery("SELECT * FROM answeertable WHERE partTimeId ="+partTimeId +" AND problemId="
				+ problemId +" AND userId="+userid, null); 
		while (c.moveToNext()) { 
			content=c.getString(c.getColumnIndex("answerContent"));
		}
		return content;
		
	}
	public void destory(){
    	db.close();
    }
}
