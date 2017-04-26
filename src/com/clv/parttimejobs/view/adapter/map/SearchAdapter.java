package com.clv.parttimejobs.view.adapter.map;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.map.AddressBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter {
	private List<AddressBean> data = new ArrayList<AddressBean>();
	private LayoutInflater li;

	public SearchAdapter(Context context) {
		li = LayoutInflater.from(context);
	}

	/**
	 * 设置数据集
	 * 
	 * @param data
	 */
	public void setData(List<AddressBean> data) {
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = li.inflate(R.layout.item_map_address, null);
			vh.title = (TextView) convertView.findViewById(R.id.item_title);
			vh.text = (TextView) convertView.findViewById(R.id.item_text);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.title.setText(data.get(position).getTitle());
		vh.text.setText(data.get(position).getText());
		return convertView;

	}

	private class ViewHolder {
		public TextView title;
		public TextView text;
	}
}
