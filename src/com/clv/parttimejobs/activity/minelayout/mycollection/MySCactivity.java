package com.clv.parttimejobs.activity.minelayout.mycollection;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.mycollection.MySCbasis;
import com.clv.parttimejobs.view.adapter.my.mycollection.SCAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class MySCactivity extends Activity {

	private List<MySCbasis> list;
 	private ListView sc_listview;
 	private SCAdapter myadapter;
 	private ImageButton mycreadit_return_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sc_layout);
		sc_listview=(ListView) findViewById(R.id.gz_listview);
		mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
		mycreadit_return_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MySCactivity.this.finish();
			}
		});
		list= new ArrayList<MySCbasis>();
		MySCbasis m1 =new MySCbasis("","招聘手写","实习�?","月结","浙江舟山定海中大�?20�?","2016-12-03",true);
		MySCbasis m2 =new MySCbasis("","婚庆","实习�?","月结","浙江舟山定海中大�?20�?","2016-12-03",true);
		list.add(m1);
		list.add(m2);
		myadapter=new SCAdapter(this,list);
		sc_listview.setAdapter(myadapter);
	}

}
