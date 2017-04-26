package com.clv.parttimejobs.view.adapter.task.internship;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class My_order_sx_ypl_Adapter extends BaseAdapter {

	private List<News> list;
	private Context context;

	public My_order_sx_ypl_Adapter(Context context, List<News> list) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewpartents) {
		// TODO Auto-generated method stub
		News n = list.get(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.intershop_fragment_3, null);
		ImageView image = (ImageView) view.findViewById(R.id.shop_image);
		TextView tvName = (TextView) view
				.findViewById(R.id.tv_contact_item_name);
		TextView tvePeopleName = (TextView) view
				.findViewById(R.id.tv_contact_item_people);
		TextView tvetel = (TextView) view
				.findViewById(R.id.tv_contact_item_pay_way);
		// 4. 将数据现在模板的控件中
		image.setImageResource(n.getDrawimg_id());
		tvName.setText(n.getName());
		tvePeopleName.setText(n.getPeople_name());
		tvetel.setText(n.getPeople_tel());
		return view;
	}

}
