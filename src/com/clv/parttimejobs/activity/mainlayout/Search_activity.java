package com.clv.parttimejobs.activity.mainlayout;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.history.ContactDao;
import com.clv.parttimejobs.dao.history.Histroy;
import com.clv.parttimejobs.entity.message.RD_message_bean;
import com.clv.parttimejobs.view.adapter.search.SousuoAdaper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Search_activity extends Activity {

	private Context context;
	private ImageButton imagebutton;
	private ListView listview;
	private Intent intentService;
	private SousuoAdaper adapter;
	private List<RD_message_bean> list;
	private SQLiteDatabase db;
	private Button button;
	private ContactDao dao;
	private Histroy histroy;
	private RD_message_bean histroy_list;
	private AutoCompleteTextView textview;
	private List<RD_message_bean> l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_activity);
		context=this;
		button = (Button) findViewById(R.id.sousuo_button);
		imagebutton = (ImageButton) findViewById(R.id.sousuo_imageview);
		textview = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		listview = (ListView) findViewById(R.id.search__listview02);
		button = (Button) findViewById(R.id.sousuo_button);

		InnerOnCLickOnListener i = new InnerOnCLickOnListener();
		imagebutton.setOnClickListener(i);
		button.setOnClickListener(i);

		l = new ArrayList<RD_message_bean>();
		flushAdapter();
		adapter = new SousuoAdaper(this, l);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(Search_activity.this,PartJobListActivity.class);
				startActivity(intent);
				Search_activity.this.finish();
			}
		});
	}

	public void flushAdapter(){
		dao = new ContactDao(this);
		Cursor cursor = dao.query();
		histroy = new Histroy();
		while (cursor.moveToNext()) {
			int personid = cursor.getInt(0); // 获取第一列的值,第一列的索引从0开始
			String name = cursor.getString(1);// 获取第二列的值
			histroy_list = new RD_message_bean(personid, name);
			l.add(histroy_list);
		}
		System.out.println("大小"+l.size());
	    dao.destory();
	}
	private class InnerOnCLickOnListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.sousuo_imageview: {
				Search_activity.this.setResult(1);
				Search_activity.this.finish();
				break;
			}
			case R.id.sousuo_button: {
				String a = textview.getText().toString();
				db = openOrCreateDatabase("myphone.db", MODE_PRIVATE, null);
				if (!a.equals("")) {
					if (!checkColumnExists(db, "histroy", a)) {
						histroy.setHistroy_name(a);
						dao = new ContactDao(context);
						long id = dao.insert(histroy);
						if (id > 0) {
						} else {
							Toast.makeText(Search_activity.this, "错误！！",
									Toast.LENGTH_SHORT).show();
						}
						dao.destory();
					}
				}
				l.clear();
				flushAdapter();
				adapter.notifyDataSetChanged();
				Intent intent =new Intent(Search_activity.this,PartJobListActivity.class);
				startActivity(intent);
				Search_activity.this.finish();
			}
				break;
			}
		}
	}

	// 检查表列是否存在
	private boolean checkColumnExists(SQLiteDatabase db, String tableName,
			String columnName) {
		boolean result = false;
		Cursor cursor = null;

		try {
			cursor = db
					.rawQuery(
							"select * from sqlite_master where name = ? and sql like ?",
							new String[] { tableName, "%" + columnName + "%" });
			result = null != cursor && cursor.moveToFirst();
		} catch (Exception e) {
		} finally {
			if (null != cursor && !cursor.isClosed()) {
				cursor.close();
			}
		}

		return result;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_activity, menu);
		return true;
	}

	// 增加数据
}
