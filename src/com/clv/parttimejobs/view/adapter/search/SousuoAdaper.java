package com.clv.parttimejobs.view.adapter.search;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.News;
import com.clv.parttimejobs.entity.message.RD_message_bean;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SousuoAdaper extends BaseAdapter {

	private Context context;
	private List<RD_message_bean> list;

	public SousuoAdaper(Context context, List<RD_message_bean> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public String getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0).getName();
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viwgroup) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = View.inflate(context, R.layout.item_sousuo_histroy, null);
			new ViewHolder(view);
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		RD_message_bean n = list.get(position);
		Log.w("teg", n.getName());

		holder.t.setText(n.getName());
		return view;
	}

	class ViewHolder {
		TextView t;

		public ViewHolder(View view) {
			t = (TextView) view.findViewById(R.id.sousuo_histroy_text);
			view.setTag(this);
		}
	}
}
