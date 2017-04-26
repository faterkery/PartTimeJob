package com.clv.parttimejobs.view.adapter.consult.job;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.job.JobDescriptionBean;
import com.clv.parttimejobs.view.adapter.consult.job.GuidePageAdapter.CheckListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class JobMessageAdapter extends BaseAdapter{

	private Context context;
	private List<JobDescriptionBean> data;
	
	public JobMessageAdapter(Context context, List<JobDescriptionBean> data) {
		super();
		this.context = context;
		this.data = data;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=View.inflate(context, R.layout.item_jobcontent_layout, null);}
		final ViewHolder holder = new ViewHolder(convertView);
		JobDescriptionBean bean= data.get(position);
		holder.ivTextViewtitle.setText(bean.getTitle());
		holder.ivTextViewcontent.setText(bean.getContent());
		return convertView;
	}

	class ViewHolder {
		TextView ivTextViewtitle;
		TextView ivTextViewcontent;
		public ViewHolder(View view){
			ivTextViewtitle =(TextView)view.findViewById(R.id.partjob_textview21);
			ivTextViewcontent =(TextView)view.findViewById(R.id.partjob_textview22);
		}
	}
}
