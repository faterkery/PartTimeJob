package com.clv.parttimejobs.activity.minelayout.resumeexperience;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.resumeexpression.PartJobJL;
import com.clv.parttimejobs.view.adapter.my.resumepression.JzjlAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class JZjlactivity extends Activity {

	private List<PartJobJL> list;
	private ListView listview;
	private ImageButton mycreadit_return_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jzjl);
		listview =(ListView) findViewById(R.id.jzjl_listview);
		list =new ArrayList<PartJobJL>();
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  

		View view_head_1 = inflater.inflate(R.layout.jzjl_head_layout, null);
		listview.addHeaderView(view_head_1);
		mycreadit_return_button =(ImageButton) view_head_1.findViewById(R.id.mycreadit_return_button);
		mycreadit_return_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JZjlactivity.this.finish();
			}
		});
		
		PartJobJL p1 =new PartJobJL("2016","11�?","1�?","婚庆帮手","大学�?","月结","+780","");
		PartJobJL p2 =new PartJobJL("2016","12�?","1�?","招聘淘宝头条写手","大学�?","月结","+780","");
		PartJobJL p3 =new PartJobJL("2017","12�?","1�?","勤工俭学","大学�?","月结","+780","");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list=change(list);
		JzjlAdapter adapter =new JzjlAdapter(this, list);
		listview.setAdapter(adapter);
		listview.setVerticalScrollBarEnabled(false);
	}

	public List<PartJobJL> change(List<PartJobJL> l){
		String year="",month="";
		for(PartJobJL p:l){
			if(year.equals("")){
				year=p.getYear();
			}else if(year.equals(p.getYear())){
				p.setYear("");
			}
			if(month.equals("")){
				month=p.getMonth();
			}else if(month.equals(p.getMonth())){
				p.setMonth("");
			}
		}
		return l;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
