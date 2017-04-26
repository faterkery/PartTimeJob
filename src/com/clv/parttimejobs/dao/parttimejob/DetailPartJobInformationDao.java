package com.clv.parttimejobs.dao.parttimejob;

import java.util.ArrayList;
import java.util.List;

import com.clv.parttimejobs.dao.DBOpenHelper;
import com.clv.parttimejobs.entity.consult.DatailJobBean;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.consult.job.JobDescriptionBean;
import com.clv.parttimejobs.entity.consult.job.ProblemBean;
import com.clv.parttimejobs.entity.my.resume.Jntc_basis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DetailPartJobInformationDao {

	private Context context;
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	public DetailPartJobInformationDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
		createTable();
	}
	public boolean isTableExists_partjob() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='datailpartjobtable';";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0) {
				result = true;
			}
		}
		return result;
	}
	public boolean isTableExists_description() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='jobdescriptiontable';";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0) {
				result = true;
			}
		}
		return result;
	}
	public boolean isTableExists_problem() {
		boolean result = false;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='jobproblemtable';";
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

		if (!isTableExists_partjob()) {
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
		if (!isTableExists_description()) {
			String sql = "CREATE TABLE jobdescriptiontable ("
					+ "job_id auto_increment PRIMARY KEY ,"
					+ "partTimeId VARCHAR(16)  NOT NULL, "
					+ "content VARCHAR(16) NOT NULL, "
					+ "title VARCHAR(16) NOT NULL"
					+")";
			db.execSQL(sql);
		}
		if (!isTableExists_problem()) {
			String sql = "CREATE TABLE jobproblemtable ("
					+ "problem_id auto_increment PRIMARY KEY ,"
					+ "partTimeId VARCHAR(16)  NOT NULL, "
					+ "content VARCHAR(16) NOT NULL, "
					+ "problemId VARCHAR(16)  NOT NULL,"
					+ "topic VARCHAR(16) NOT NULL,"
					+ "type  VARCHAR(16) NOT NULL"
					+")";
			db.execSQL(sql);
		}
	}
	public long insert(DatailJobBean bean){
		//
		long id = -1;
		String table = "datailpartjobtable";
		String nullColumnHack = "part_id";
		ContentValues values = new ContentValues();
		values.put("claim",bean.getClaim());
		values.put("companyId",bean.getDeadline());
		values.put("deadline",bean.getDeadline());
		values.put("jobDescription",bean.getJobDescription());
		values.put("location",bean.getLocation());
		values.put("needNumber",bean.getNeedNumber());
		values.put("numberOfapplicants",bean.getNumberOfapplicants());
		values.put("partTimeId",bean.getPartTimeId());
		values.put("partTimeStatus",bean.getPartTimeStatus());
		values.put("photoName",bean.getPhotoName());
		values.put("salary",bean.getSalary());
		values.put("settlementMethod",bean.getSettlementMethod());
		values.put("title",bean.getTitle());
		values.put("type",bean.getType());
		values.put("workDate",bean.getWorkDate());
		id = db.insert(table, nullColumnHack, values);
		
		String partTimeId = bean.getPartTimeId();
		if(isExist("jobdescriptiontable",partTimeId)){
			remove("jobdescriptiontable",partTimeId);
		}
		if(isExist("jobproblemtable",partTimeId)){
			remove("jobproblemtable",partTimeId);
		}
		
		List<JobDescriptionBean> description=bean.getDescription();
        for(int i=0;i<description.size();i++){
        	table = "jobdescriptiontable";
    		nullColumnHack = "job_id";
    		ContentValues values2 = new ContentValues();
    		values2.put("partTimeId",description.get(i).getPartTimeId());
    		values2.put("content",description.get(i).getContent());
    		values2.put("title",description.get(i).getTitle());
    		id = db.insert(table, nullColumnHack, values2);
		}   
       
		return id;
	}
	public long insertProblem(List<ProblemBean> problem){
		 String str2 = "DROP TABLE jobproblemtable";
		 db.execSQL(str2);
		 //
		 if (!isTableExists_problem()) {
				String sql = "CREATE TABLE jobproblemtable ("
						+ "problem_id auto_increment PRIMARY KEY ,"
						+ "partTimeId VARCHAR(16)  NOT NULL, "
						+ "content VARCHAR(16) NOT NULL, "
						+ "problemId VARCHAR(16)  NOT NULL,"
						+ "topic VARCHAR(16) NOT NULL,"
						+ "type  VARCHAR(16) NOT NULL"
						+")";
				db.execSQL(sql);
			}
		 //
		 long id = 0;
		 for(int i=0;i<problem.size();i++){
	        	String table = "jobproblemtable";
	        	String nullColumnHack = "problem_id";
	    		ContentValues values3 = new ContentValues();
	    		values3.put("partTimeId",problem.get(i).getPartTimeId());
	    		values3.put("content",problem.get(i).getProblem_content());
	    		values3.put("problemId",problem.get(i).getProblemId());
	    		values3.put("topic",problem.get(i).getProblem_topic());
	    		values3.put("type",problem.get(i).getTopy());
	    		id = db.insert(table, nullColumnHack, values3);
			}   
		 return id;
	}
	public boolean isExist(DatailJobBean bean){
		Cursor c = db.rawQuery("SELECT * FROM datailpartjobtable WHERE partTimeId = "+bean.getPartTimeId(), null);
		while (c.moveToNext()) {
			return true;
		}
		return false;
	}
	public void updata(DatailJobBean bean) {  
        String table = "datailpartjobtable";
        ContentValues values = new ContentValues();  
        values.put("claim",bean.getClaim());
		values.put("companyId",bean.getDeadline());
		values.put("deadline",bean.getDeadline());
		values.put("jobDescription",bean.getJobDescription());
		values.put("location",bean.getLocation());
		values.put("needNumber",bean.getNeedNumber());
		values.put("numberOfapplicants",bean.getNumberOfapplicants());
		values.put("partTimeId",bean.getPartTimeId());
		values.put("partTimeStatus",bean.getPartTimeStatus());
		values.put("photoName",bean.getPhotoName());
		values.put("salary",bean.getSalary());
		values.put("settlementMethod",bean.getSettlementMethod());
		values.put("title",bean.getTitle());
		values.put("type",bean.getType());
		values.put("workDate",bean.getWorkDate());
        db.update(table, values,"partTimeId ="+bean.getPartTimeId(),null);  
        
        String table1 = "datailpartjobtable";
        ContentValues values2 = new ContentValues();  
        values2.put("claim",bean.getClaim());
		values.put("companyId",bean.getDeadline());
		values.put("deadline",bean.getDeadline());
		values.put("jobDescription",bean.getJobDescription());
		values.put("location",bean.getLocation());
		values.put("needNumber",bean.getNeedNumber());
		values.put("numberOfapplicants",bean.getNumberOfapplicants());
		values.put("partTimeId",bean.getPartTimeId());
		values.put("partTimeStatus",bean.getPartTimeStatus());
		values.put("photoName",bean.getPhotoName());
		values.put("salary",bean.getSalary());
		values.put("settlementMethod",bean.getSettlementMethod());
		values.put("title",bean.getTitle());
		values.put("type",bean.getType());
		values.put("workDate",bean.getWorkDate());
        db.update(table1, values2,"partTimeId ="+bean.getPartTimeId(),null); 
    }  
	public DatailJobBean detailPartJobMessage(String partTimeId){
		DatailJobBean bean =new DatailJobBean();
		Cursor c = db.rawQuery("SELECT * FROM datailpartjobtable WHERE partTimeId = "+partTimeId, null);
		while (c.moveToNext()) { 
			bean.setClaim(c.getString(c.getColumnIndex("claim")));
			bean.setCompanyId(c.getString(c.getColumnIndex("companyId")));
			bean.setDeadline(c.getString(c.getColumnIndex("deadline")));
			bean.setJobDescription(c.getString(c.getColumnIndex("jobDescription")));
			bean.setLocation(c.getString(c.getColumnIndex("location")));
			bean.setNeedNumber(c.getInt(c.getColumnIndex("needNumber")));
			bean.setNumberOfapplicants(c.getString(c.getColumnIndex("numberOfapplicants")));
			bean.setPartTimeId(c.getString(c.getColumnIndex("partTimeId")));
			bean.setPartTimeStatus(c.getString(c.getColumnIndex("partTimeStatus")));
			bean.setPhotoName(c.getString(c.getColumnIndex("photoName")));
			bean.setSalary(c.getString(c.getColumnIndex("salary")));
			bean.setSettlementMethod(c.getString(c.getColumnIndex("settlementMethod")));
			bean.setTitle(c.getString(c.getColumnIndex("title")));
			bean.setType(c.getString(c.getColumnIndex("type")));
			bean.setWorkDate(c.getString(c.getColumnIndex("workDate")));
		 }
		List<JobDescriptionBean> description=new ArrayList<JobDescriptionBean>();
		//
		
		Cursor c1 = db.rawQuery("SELECT * FROM jobdescriptiontable WHERE partTimeId = "+partTimeId, null);
		while (c1.moveToNext()) {
			JobDescriptionBean j =new JobDescriptionBean();
			j.setPartTimeId(c1.getString(c1.getColumnIndex("partTimeId")));
			System.out.println("选中的"+c1.getString(c1.getColumnIndex("partTimeId")));
			j.setContent(c1.getString(c1.getColumnIndex("content")));
			j.setTitle(c1.getString(c1.getColumnIndex("title")));
			description.add(j);
		}
		bean.setDescription(description);
		
		return bean;
	}
	public List<ProblemBean> detailProblem(int partTimeId){
		List<ProblemBean> problem=new ArrayList<ProblemBean>();
		Cursor c2 = db.rawQuery("SELECT * FROM jobproblemtable WHERE partTimeId = "+partTimeId, null);
		while (c2.moveToNext()) {
			ProblemBean p =new ProblemBean();
			p.setPartTimeId(c2.getString(c2.getColumnIndex("partTimeId")));
			p.setProblemId(c2.getString(c2.getColumnIndex("problemId")));
			p.setProblem_content(c2.getString(c2.getColumnIndex("content")));
			p.setProblem_topic(c2.getString(c2.getColumnIndex("topic")));
			p.setTopy(c2.getString(c2.getColumnIndex("type")));
			problem.add(p);
		}
		return problem;
	}
	public void remove(String table,String partTimeId){
	   	db.delete(table,"partTimeId = '"+partTimeId+"'",null);
	}
	public boolean isExist(String table,String partTimeId){
		boolean isExist=false;
		Cursor c1 = db.rawQuery("SELECT * FROM "+table+" WHERE partTimeId = "+partTimeId, null);
		while (c1.moveToNext()) {
			isExist=true;
		}
		return isExist;
	}
	public List<ProblemBean>  detailProblem(String partTimeId){
		List<ProblemBean> problem=new ArrayList<ProblemBean>();
		Cursor c2 = db.rawQuery("SELECT * FROM jobproblemtable WHERE partTimeId = "+partTimeId, null);
		while (c2.moveToNext()) {
			ProblemBean p =new ProblemBean();
			p.setPartTimeId(c2.getString(c2.getColumnIndex("partTimeId")));
			p.setProblemId(c2.getString(c2.getColumnIndex("problemId")));
			p.setProblem_content(c2.getString(c2.getColumnIndex("content")));
			p.setProblem_topic(c2.getString(c2.getColumnIndex("topic")));
			p.setTopy(c2.getString(c2.getColumnIndex("type")));
			//更改
			problem.add(p);
		}
		return problem;
	}
	public void destory(){
    	db.close();
    }
	public void drop(){
		String str = "DROP TABLE datailpartjobtable";
		db.execSQL(str);
		String str1 = "DROP TABLE jobdescriptiontable";
		db.execSQL(str1);
		String str2 = "DROP TABLE jobproblemtable";
		db.execSQL(str2);
	}
	public void dropProblemTable(){
		String str2 = "DROP TABLE jobproblemtable";
		db.execSQL(str2);
	}
}
