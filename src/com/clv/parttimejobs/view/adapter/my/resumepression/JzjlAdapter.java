package com.clv.parttimejobs.view.adapter.my.resumepression;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.resumeexpression.PartJobJL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class JzjlAdapter extends BaseAdapter {

	private Context context;
	private List<PartJobJL> list;

	public JzjlAdapter(Context context, List<PartJobJL> list) {
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
	public PartJobJL getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewpartents) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(convertView== null){
		convertView = View.inflate(context, R.layout.item_jzjl_model, null);
		new ViewHolder(convertView);}
		
		ViewHolder holder = (ViewHolder) convertView.getTag();
		System.out.println(getCount());
		PartJobJL p = getItem(position);
		if(p.getYear().equals("")){
			holder.ivyear.setVisibility(View.GONE);
		}else{
		holder.ivyear.setText(p.getYear());
		}
		if(p.getMonth().equals("")){
			holder.ivmonth.setVisibility(View.GONE);
		}else{
		holder.ivmonth.setText(p.getMonth());}
		holder.ivday.setText(p.getDay());
		holder.ivjobname.setText(p.getJob_name());
		holder.ivjobpeople.setText(p.getJob_people());
		holder.ivjobway.setText(p.getJob_way());
		holder.ivjobmoney.setText(p.getJob_money());
		if (!p.getJob_imageurl().equals("")) {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 3;
			Bitmap bm = BitmapFactory.decodeFile(p.getJob_imageurl(), opts);
			holder.ivjobimage.setImageBitmap(bm);
		}
		return convertView;
	}

	class ViewHolder {
		TextView ivyear;
		TextView ivmonth;
		TextView ivday;
		TextView ivjobname;
		TextView ivjobpeople;
		TextView ivjobway;
		TextView ivjobmoney;
		ImageView ivjobimage;

		public ViewHolder(View view) {
			ivyear = (TextView) view.findViewById(R.id.item_jzjl_textview01);
			ivmonth = (TextView) view
					.findViewById(R.id.item_jzjl_textview_month);
			ivday = (TextView) view.findViewById(R.id.item_jzjl_textview_day);
			ivjobname = (TextView) view.findViewById(R.id.item_jzjl_textview03);
			ivjobpeople = (TextView) view
					.findViewById(R.id.item_jzjl_textview04);
			ivjobway = (TextView) view.findViewById(R.id.item_jzjl_textview05);
			ivjobmoney = (TextView) view.findViewById(R.id.item_jzjl_money);
			view.setTag(this);
		}
	}

}
