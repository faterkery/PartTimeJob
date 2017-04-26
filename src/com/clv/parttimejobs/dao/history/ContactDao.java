package com.clv.parttimejobs.dao.history;

import com.clv.parttimejobs.dao.DBOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactDao {

	private Context context;

	DBOpenHelper dbOpenHelper;
	SQLiteDatabase db;
	public ContactDao(Context context) {
		super();
		this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getWritableDatabase();
	}

	public long insert(Histroy histroy) {
		//
		long id = -1;
		String table = "histroy";
		String nullColumnHack = "histroy_name";
		ContentValues values = new ContentValues();
		values.put("histroy_name", histroy.getHistroy_name());
		id = db.insert(table, nullColumnHack, values);
		db.close();
		return id;
	}

	public Cursor query() {
		Cursor c = null;
		c = db.query("histroy", null, null, null, null, null, null);
		return c;
	}
	 public void destory(){
	    	db.close();
	  }
}
