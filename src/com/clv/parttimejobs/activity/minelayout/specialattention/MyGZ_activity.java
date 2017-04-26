package com.clv.parttimejobs.activity.minelayout.specialattention;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.specialattention.MyGZbasis;
import com.clv.parttimejobs.view.adapter.my.specialattention.GZAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class MyGZ_activity extends Activity {

	private List<MyGZbasis> list;
 	private ListView gz_listview;
 	private GZAdapter myadapter;
 	private ImageButton mycreadit_return_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gz_layout);
		gz_listview=(ListView) findViewById(R.id.gz_listview);
		mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
		mycreadit_return_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyGZ_activity.this.finish();
			}
		});
		list= new ArrayList<MyGZbasis>();
		MyGZbasis m1 =new MyGZbasis("","风味食堂","好吃,安全,卫生,便宜",true);
		MyGZbasis m2 =new MyGZbasis("","婚庆公司","我们是最安全的，�?让人放心的，�?好的，最棒的，最便宜的，�?多人员的，最高工资的公司",true);
		
		list.add(m1);
		list.add(m2);
		myadapter=new GZAdapter(this,list);
		gz_listview.setAdapter(myadapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
