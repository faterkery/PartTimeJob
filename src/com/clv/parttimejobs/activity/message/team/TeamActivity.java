package com.clv.parttimejobs.activity.message.team;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.clv.homework.R;

public class TeamActivity extends Activity{

	private ImageButton team_returnButton;//·µ»Ø¼ü
	private ImageButton team_name;//ÈºÃû×Ö
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_message_layout);
		initview();
	}
	public void initview(){
		team_returnButton=(ImageButton) findViewById(R.id.team_message_imageview_1);
		team_name=(ImageButton) findViewById(R.id.team_message_textname);
		
		
	}
	
}
