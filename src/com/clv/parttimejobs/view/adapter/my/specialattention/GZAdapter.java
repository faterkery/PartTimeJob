package com.clv.parttimejobs.view.adapter.my.specialattention;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.specialattention.MyGZbasis;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class GZAdapter extends BaseAdapter {

	private Context context;
	private List<MyGZbasis> list;

	public GZAdapter(Context context, List<MyGZbasis> list) {
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
	public View getView(int position, View convertView, ViewGroup partents) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = View
					.inflate(context, R.layout.item_tbgz_layout, null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		MyGZbasis item = getItem(position);
		if (!item.getImgUrl().equals(""))
			holder.image.setImageBitmap(BitmapFactory.decodeFile(item
					.getImgUrl()));
		holder.tvName.setText(item.getShop_name());
		holder.tvContext.setText(item.getShop_context());
	    holder.tvradio.setEnabled(item.isGZ());

		return convertView;
	}

	class ViewHolder {
		ImageView image;
		TextView tvName;
		TextView tvContext;
		CheckBox tvradio;

		public ViewHolder(View view) {
			image = (ImageView) view.findViewById(R.id.item_tbgz_image);
			tvName = (TextView) view.findViewById(R.id.item_tbgz_text01);
			tvContext = (TextView) view.findViewById(R.id.item_tbgz_text02);
			tvradio = (CheckBox) view
					.findViewById(R.id.item_tbgz_radiobutton01);
			view.setTag(this);
		}
	}

	@Override
	public MyGZbasis getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

}
