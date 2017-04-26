package com.clv.parttimejobs.view.adapter.consult.job.gs;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.job.gs.GongSPicture;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class GongsixqAdapter extends BaseAdapter{

	private Context context;
	private List<GongSPicture> list;
	
	
	public GongsixqAdapter(Context context, List<GongSPicture> list) {
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
	public GongSPicture getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup partents) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_gongsixiangqing_job,
					null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		GongSPicture item = getItem(position);
		if(!item.getImgUrl().equals(""))
		holder.image.setImageBitmap(BitmapFactory.decodeFile(item.getImgUrl()));
		holder.tvName.setText(item.getJob_name());
		holder.tvPeople.setText(item.getJob_people());
		holder.tvWay.setText(item.getJob_way());
		holder.tvAddress.setText(item.getJob_address());
		holder.tvDateTime.setText(item.getJob_datetime());
		holder.tvMoney.setText(item.getJob_money()+"å…?/æ—?");
		holder.tvPLcount.setText("è¯„è®º("+item.getJob_pingluncount()+")");
		return convertView;
	}
	class ViewHolder {
		ImageView image;
		TextView tvName;
		TextView tvPeople;
		TextView tvWay;
		TextView tvAddress;
		TextView tvDateTime;
		TextView tvMoney;
		TextView tvPLcount;
		
		public ViewHolder(View view) {
			image =(ImageView) view.findViewById(R.id.shop_image);
			tvName=(TextView) view.findViewById(R.id.tv_contact_item_name);
			tvPeople=(TextView) view.findViewById(R.id.tv_contact_item_people);
			tvWay=(TextView) view.findViewById(R.id.tv_contact_item_pay_way);
			tvAddress=(TextView) view.findViewById(R.id.tv_address_contact);
			tvDateTime=(TextView) view.findViewById(R.id.tv_gongsixq_textview01);
			tvMoney =(TextView) view.findViewById(R.id.tv_gongsixq_textview02);
			tvPLcount=(TextView) view.findViewById(R.id.tv_gongsixq_textview03);
			view.setTag(this);
		}
	}
}
