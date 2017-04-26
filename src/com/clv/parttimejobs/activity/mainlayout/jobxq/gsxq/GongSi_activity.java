package com.clv.parttimejobs.activity.mainlayout.jobxq.gsxq;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.job.gs.GongSPicture;
import com.clv.parttimejobs.view.adapter.consult.job.gs.GongsixqAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class GongSi_activity extends Activity {

	private ListView gongsixq_listview;
	private View vHead01;
	private View vHead02;
	private View vHead03;
	private View vHead04;
	private GongsixqAdapter gsxqdapter;
	private List<GongSPicture> list;
	
	private ImageButton mycreadit_return_button;
	private ImageButton gongsi_head_1_imageview_add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gongsixq_activity_main);
		gongsixq_listview =(ListView) findViewById(R.id.gongsixq_listview);
		vHead01= View.inflate(this, R.layout.gongsixq_listview_head01, null);  
		vHead02= View.inflate(this, R.layout.gongsixq_listview_head02, null);  
		vHead03= View.inflate(this, R.layout.gongsixq_listview_head03, null); 
		vHead04= View.inflate(this, R.layout.gongsixq_head_nodate, null); 
		
		
		list =new ArrayList<GongSPicture>();
		GongSPicture a1 =new GongSPicture("","招聘淘宝头条写手","实习�?","月结","舟山定海中大�?21�?","2011-11-27�?2017�?-02-27","13","100");
		GongSPicture a2 =new GongSPicture("","招聘淘宝头条写手","实习�?","月结","舟山定海中大�?21�?","2011-11-27�?2017�?-02-27","13","100");
		list.add(a1);list.add(a2);
		gsxqdapter =new GongsixqAdapter(this, list);
		
		gongsixq_listview.addHeaderView(vHead01);
		gongsixq_listview.addHeaderView(vHead02);
		gongsixq_listview.addHeaderView(vHead03);
		if(list.size()==0){
			gongsixq_listview.addHeaderView(vHead04);
		}
		gongsixq_listview.setAdapter(gsxqdapter);
		gongsixq_listview.setVerticalScrollBarEnabled(false);
		mycreadit_return_button=(ImageButton) findViewById(R.id.mycreadit_return_button);
		//head1
		gongsi_head_1_imageview_add=(ImageButton) findViewById(R.id.gongsi_head_1_imageview_add);
		//
		MyOnClickListener listener =new MyOnClickListener();
		mycreadit_return_button.setOnClickListener(listener);
		gongsi_head_1_imageview_add.setOnClickListener(listener);
	}

	private class MyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.mycreadit_return_button:{
				GongSi_activity.this.finish();
				break;
			}
			case R.id.gongsi_head_1_imageview_add:{
				Intent intent = new Intent(GongSi_activity.this,gongsiimg_activity.class);
				startActivity(intent);
				break;
			}
			}
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
