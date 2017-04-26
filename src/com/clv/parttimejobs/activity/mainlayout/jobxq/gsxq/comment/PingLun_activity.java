package com.clv.parttimejobs.activity.mainlayout.jobxq.gsxq.comment;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.job.gs.comment.PinglunBasis;
import com.clv.parttimejobs.view.adapter.consult.job.gs.comment.PinglunAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PingLun_activity extends Activity{

	private PinglunAdapter pingadapter;
	private List<PinglunBasis> list;
	private ListView listview;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pinglun_activity_layout);
		listview =(ListView) findViewById(R.id.pinglun_listview); 
		pingadapter =new PinglunAdapter(this,list);
		List l =new ArrayList();
		PinglunBasis p1 =new PinglunBasis("","赵XX","2016-11-10","好评！！",l,"12","3");
		list.add(p1);
		listview.setAdapter(pingadapter);
	}
}
