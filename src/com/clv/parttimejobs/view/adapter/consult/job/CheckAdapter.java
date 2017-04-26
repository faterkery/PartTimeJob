package com.clv.parttimejobs.view.adapter.consult.job;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.job.CheckAnsweerBean;
import com.clv.parttimejobs.entity.my.resume.Image;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.jobxq.gsxq.BaiMingListener;
import com.clv.parttimejobs.view.adapter.consult.job.GuidePageAdapter.CheckListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CheckAdapter extends BaseAdapter{

	private Context context;
	private List<CheckAnsweerBean> data;
	private BaiMingListener listener;
	
	private int pattern;//模式位  0 -单选,1-多选
	private int viewpattern;//第几个view
	
	public CheckAdapter(Context context,List<CheckAnsweerBean> data,int viewpattern,int pattern,BaiMingListener listener) {
		super();
		this.context=context;
		this.data = data;
		this.viewpattern=viewpattern;
		this.pattern=pattern;
		this.listener=listener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder ;
		if(convertView==null){
	    	convertView=View.inflate(context, R.layout.item_check_popwindowframgent_layout, null);
		   holder = new ViewHolder(convertView);
		   convertView.setTag(holder);
		}
		else{
			 holder =(ViewHolder) convertView.getTag();
		}
		
		
		CheckAnsweerBean question = data.get(position);
		holder.ivTextView.setText(question.getConnect());
		holder.ivcheckbox.setChecked(question.isChecked());
		holder.ivcheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					holder.ivTextView.setTextColor(context.getResources().getColor(R.color.qianblue));
				}else{
					holder.ivTextView.setTextColor(context.getResources().getColor(R.color.gray_7));
				}
				
			}
		});
		holder.ivcheckbox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(pattern){
				case 0:{//单选
					listener.daselect(viewpattern, position);
					break;
				}
				case 1:{
					listener.duselect(viewpattern,position);
					break;
				}
				}
			}
		});
		
		return convertView;
	}

	class ViewHolder {
		CheckBox ivcheckbox;
		TextView ivTextView;
		public ViewHolder(View view){
			ivcheckbox =(CheckBox)view.findViewById(R.id.item_check_popwindow_checkbox);
			ivTextView =(TextView)view.findViewById(R.id.item_check_popwindow_textview);
		}
	}
}
