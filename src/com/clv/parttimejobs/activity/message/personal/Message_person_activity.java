package com.clv.parttimejobs.activity.message.personal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.homework.R.layout;
import com.clv.homework.R.menu;
import com.clv.parttimejobs.activity.mainlayout.MainActivity;
import com.clv.parttimejobs.dao.user.message.MessageDao;
import com.clv.parttimejobs.entity.message.Message;
import com.clv.parttimejobs.entity.message.person.MessageSend;
import com.clv.parttimejobs.fragment.message_fragment.FragmentMessage;
import com.clv.parttimejobs.util.ecutetime.GetCurrentTime;
import com.clv.parttimejobs.util.music.MediaManager;
import com.clv.parttimejobs.view.adapter.message.person_message.SendAndReceiveAdapter;
import com.clv.parttimejobs.view.adapter.message.person_message.SendAndReceiveAdapter.Callback;
import com.clv.parttimejobs.view.ui.customview.AudioRecordButton;
import com.clv.parttimejobs.view.ui.customview.AudioRecordButton.AudioFinishRecorderListener;

import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Message_person_activity extends Activity implements Callback{

	Intent intent;
	Context context;

	ImageButton imagebutton;
	EditText imagetextinput;
	Button imagesend;
	
	ImageButton return_button;
	ImageButton message_toset_button;
	ImageButton imageyinyubutton;
	AudioRecordButton imageSendyuyin;
	ImageButton imageBackage;
    List<MessageSend> list;
    SendAndReceiveAdapter adapter;
    ListView listview;
    private View viewanim;
    private GetCurrentTime getTime =new GetCurrentTime();
    MessageDao messagedao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_person_activity);
		init();
		messagedao = new MessageDao(context);
		list =new ArrayList<MessageSend>();
		list=messagedao.detail();//查找本地中的数据
		Intent intent = this.getIntent();
		Message m = (Message) intent.getSerializableExtra("message");
        String name = m.getMessage_name();
		
		adapter =new SendAndReceiveAdapter(this, list,this);
		listview.setAdapter(adapter);
		listview.setVerticalScrollBarEnabled(false);// 隐藏进度条
		InnerOnclickListener l = new InnerOnclickListener();
		return_button.setOnClickListener(l);
		imagebutton.setOnClickListener(l);
		imageyinyubutton.setOnClickListener(l);
		imagesend.setOnClickListener(l);
		message_toset_button.setOnClickListener(l);
		listview.setSelection(list.size()-1);
		
		
		imageSendyuyin.setAudioFinishRecorderListener(new AudioFinishRecorderListener() {

			@Override
			public void onFinished(float seconds, String filePath) {
				// TODO Auto-generated method stub
				DecimalFormat fnum = new DecimalFormat("##0.0");
				String dd=fnum.format(seconds);
				float f=Float.parseFloat(dd);
				MessageSend recorder = new MessageSend("我", "","",0,filePath,1,f,getTime.getDate(),false);
				list.add(recorder);
				messagedao.insertUser(recorder);
				adapter.notifyDataSetChanged();
				listview.setSelection(list.size()-1);
			}
		},context);
	}

	private void init(){
		context= this;
		imagebutton =(ImageButton) findViewById(R.id.message_person_image01_text);
		message_toset_button=(ImageButton) findViewById(R.id.message_toset_button);
		imagetextinput =(EditText) findViewById(R.id.message_person_image02_text);
		imagesend=(Button) findViewById(R.id.message_person_image03_text_qd);
		return_button = (ImageButton) findViewById(R.id.message_person_imageview_1);
		imageyinyubutton=(ImageButton) findViewById(R.id.message_person_image01_yuyin);
		imageSendyuyin=(AudioRecordButton) findViewById(R.id.recordButton);
		imageBackage=(ImageButton) findViewById(R.id.message_person_image03);
		listview =(ListView) findViewById(R.id.message_person_listview);
	}
	
	private class InnerOnclickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.message_person_imageview_1: {
				Message_person_activity.this.setResult(2);
				Message_person_activity.this.finish();
				break;
			}
			case R.id.message_person_image01_text:{
				imagebutton.setVisibility(View.GONE);
				imagetextinput.setVisibility(View.GONE);
				imagesend.setVisibility(View.GONE);
     			imageyinyubutton.setVisibility(View.VISIBLE);
     			imageSendyuyin.setVisibility(View.VISIBLE);
     			imageBackage.setVisibility(View.VISIBLE);
     			break;
			}
			case R.id.message_person_image01_yuyin:{
				imagebutton.setVisibility(View.VISIBLE);
				imagetextinput.setVisibility(View.VISIBLE);
				imagesend.setVisibility(View.VISIBLE);
     			imageyinyubutton.setVisibility(View.GONE);
     			imageSendyuyin.setVisibility(View.GONE);
     			imageBackage.setVisibility(View.GONE);
     			break;
			}
			case R.id.message_person_image03_text_qd:{
				String a = imagetextinput.getText().toString();
				if(a.length()!=0){
					MessageSend recorder = new MessageSend("我", "",a,1,"",1,0.0f,getTime.getDate(),false);
					list.add(recorder);
					messagedao.insertUser(recorder);
					adapter.notifyDataSetChanged();
					listview.setSelection(list.size()-1);
					imagetextinput.setText("");
				}
				break;
			}
			case R.id.message_toset_button:{
				Intent intent =new Intent(Message_person_activity.this,Message_person_set_activity.class);
				startActivity(intent);
				break;
			}
			}

		}
	}
	
	@Override
	public void click(final View v) {
		// TODO Auto-generated method stub
		String po=(String) v.getTag();
		System.out.println(po);
		int position= Integer.parseInt(po.substring(0,po.length()-1));
		String end = po.substring(po.length()-1,po.length());
		if(  end.equals("1")){
			v.setBackgroundResource(R.drawable.play);
		AnimationDrawable drawable = (AnimationDrawable) v
				.getBackground();
		drawable.start();

//		// 播放音频
		MediaManager.playSound(list.get(position).getMusicUrl(),
				new MediaPlayer.OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						v.setBackgroundResource(R.drawable.adj);

					}
				});
		}
	}
}
