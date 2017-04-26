package com.clv.parttimejobs.activity.minelayout.mywallet;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.mywallet.ZhangDanBean;
import com.clv.parttimejobs.view.adapter.my.mywallet.ZhangdanListviewAdapter;

public class MyWallet_zdmx_activity extends Activity{

	private Context context ;
	private List<ZhangDanBean> data;
	private ListView listview;
	private ZhangdanListviewAdapter adapter;
	private ImageButton returnButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywallet_zdmx_layout);
		init();
		initdata();
	}
	public void init(){
		context=this;
		returnButton =(ImageButton) findViewById(R.id.mymywallet_zdmx_return_button);
		listview =(ListView) findViewById(R.id.mymywallet_zdmx_listview);
		InnerOnClickListener i =new InnerOnClickListener();
		returnButton.setOnClickListener(i);
	}
	public void initdata(){
		data =new ArrayList<ZhangDanBean>();
		ZhangDanBean z1 =new ZhangDanBean("支付宝转账","2016-12-04","11:50:24","+100");
		ZhangDanBean z2 =new ZhangDanBean("提现","2016-12-04","11:50:24","-100");
		data.add(z1);
		data.add(z2);
		adapter =new ZhangdanListviewAdapter(context,data);
		listview.setAdapter(adapter);
	}
	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.mymywallet_zdmx_return_button:{
				MyWallet_zdmx_activity.this.finish();
				break;
			}
			}
		}
		
	}
}
