package com.clv.parttimejobs.fragment.message_fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.clv.parttimejobs.activity.message.personal.Message_person_activity;
import com.clv.parttimejobs.entity.message.Message;
import com.clv.parttimejobs.model.interfacebackage.message.MessageListener;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenu;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuCreator;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuItem;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuListView;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuListView.OnSwipeListener;
import com.clv.parttimejobs.view.adapter.message.MessageAdapter;
import com.clv.homework.R;

public class FragmentMessage extends Fragment {

	private List<Message> mAppList = new ArrayList<Message>();
    private List<Message> temp = new ArrayList<Message>();
	private MessageAdapter mAdapter;
	private SwipeMenuListView mListView;
	private Context context;
	private Context Appcontext;
	
	private SwipeMenuItem openItem;
	private SwipeMenuCreator creator;

	@Override
    public void onCreate(Bundle savedInstanceState) {
      setRetainInstance(true);
      super.onCreate(savedInstanceState);
    }

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.activity_fragment_message,
				 container, false);
		context = this.getActivity();
		Appcontext = this.getActivity().getApplicationContext();
		mListView = (SwipeMenuListView) viewRoot
				.findViewById(R.id.message_listView);
		
		View view_head_1 = inflater
				.inflate(R.layout.message_head_content, null);
		mListView.addHeaderView(view_head_1);

		mAppList.add(new Message(0,"陈老师", "我今天不来了",R.drawable.mes_img_person1_nor, 1, 1480578174358L,false,1));
		mAppList.add(new Message(1,"王先生", "我今天不来了", R.drawable.mes_img_person2_nor,1, 1480578174358L,false,3));
		mAppList.add(new Message(2,"婚庆兼职群", "我今天不来了",0, 2, 1480578174358L,false,3));
		mAppList.add(new Message(3,"李医生", "我今天不来了",R.drawable.mes_img_person3_nor, 1, 1480578174358L,false,2));
		mAppList.add(new Message(4,"小弟", "我今天不来了", 1,R.drawable.mes_img_person1_nor, 1480578174358L,false,1));
		mAppList.add(new Message(5,"勤工俭学群", "我今天不来了",0, 2, 1480578174358L,false,2));
		mAppList.add(new Message(6,"系统推送", "我今天不来了",0, 3, 1480578174358L,false,3));

		//加载数据
		buildTable(temp,mAppList);
		mAdapter = new MessageAdapter(context, temp,new MessageListener() {
			
			@Override
			public void dismissMessage(int position) {
				// TODO Auto-generated method stub
				mAppList.get(position).setMesagecount(0);
			}
		});

		mListView.setDividerHeight(1);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println(arg2);
				Message m = mAppList.get(arg2-1);
				if(m.getFlag()==1){
					Intent intent = new Intent(context,
							Message_person_activity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("message", m);
					intent.putExtras(bundle);
					startActivity(intent);
				}else if(m.getFlag()==2){
					
				}if(m.getFlag()==3){
					
				}
			}

		});
		// step 1. create a MenuCreator
//		setCreator(2,2);
//		// set creator
//		mListView.setMenuCreator(creator);

		// step 2. listener item click event
		mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
				
				Message m = mAppList.get(position);
				switch (index) {
				case 0:{
					// open
					int posit =getCheckPoisiton(position,temp);
					if(mAppList.get(posit).isTop()){
						mAppList.get(posit).setTop(false);
					}else{
						mAppList.get(posit).setTop(true);
					}
					buildTable(temp,mAppList);
					mAdapter.notifyDataSetChanged();
					mListView.setList(temp);
					mListView.setAdapter(mAdapter);
					break;}
				case 1: {
					System.out.println("------------"+position);
					int posit =getCheckPoisiton(position,temp);
					System.out.println(posit);
					int count =mAppList.get(posit).getMesagecount();
					
					if(count==0){
						mAppList.get(posit).setMesagecount(1);
					}else{
						mAppList.get(posit).setMesagecount(0);
					}
					
					mAdapter.notifyDataSetChanged();
					mListView.setList(temp);
					mListView.setAdapter(mAdapter);
                    break;
				}
				case 2:
					// delete
					mAppList.remove(temp.get(position));
					temp.remove(position);
					//初始化
					initarray(mAppList);
					buildTable(temp,mAppList);
					mAdapter.notifyDataSetChanged();
					mListView.setList(temp);
					mListView.setAdapter(mAdapter);
					break;
				}
			}
		});

		// set SwipeListener
		mListView.setOnSwipeListener(new OnSwipeListener() {

			@Override
			public void onSwipeStart(int position) {
				// swipe start
//				setCreator(1,2);
//				mListView.setMenuCreator(creator);
			}

			@Override
			public void onSwipeEnd(int position) {
				// swipe end
			}
		});
		//
		// // other setting
		// listView.setCloseInterpolator(new BounceInterpolator());
		//
		// // test item long click
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				return false;
			}
		});
		mListView.setList(temp);
		mListView.setAdapter(mAdapter);
		mListView.setVerticalScrollBarEnabled(false);
		return viewRoot;
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
	private void initarray(List<Message> list){
		for(int i=0;i<list.size();i++){
			list.get(i).setImageid(i);
		}
	}
	private int getCheckPoisiton(int position,List<Message> temp){

		return temp.get(position).getImageid();
	}
	
		
	
	private void buildTable(List<Message> temp,List<Message> mAppList)
	{
		temp.clear();
		for(Message m :mAppList)
		{
		   if(m.isTop()){
			   temp.add(m);
			  
		   }
		}
		for(Message m :mAppList)
		{
		   if(!m.isTop()){
			   temp.add(m);
		   }
		}
		
	}
	/**
	 * View
	 * 
	 * @param ev
	 * @return
	 */

}
