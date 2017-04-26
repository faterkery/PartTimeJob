package com.clv.parttimejobs.view.adapter.my.mycollection;


import java.util.List;




import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.mycollection.MySCbasis;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class SCAdapter extends BaseAdapter {

	private Context context;
	private List<MySCbasis> list;

	public SCAdapter(Context context, List<MySCbasis> list) {
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
			convertView = View.inflate(context, R.layout.item_tbsc_layout,
					null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		MySCbasis item = getItem(position);
		if(!item.getImgUrl().equals(""))
		holder.image.setImageBitmap(BitmapFactory.decodeFile(item.getImgUrl()));
		holder.tvName.setText(item.getJob_name());
		holder.tvPeople.setText(item.getJob_people());
		holder.tvWay.setText(item.getJob_way());
		holder.tvAddress.setText(item.getJob_address());
		holder.tvDateTime.setText(item.getJob_datetime());
		holder.tvradio.setEnabled(item.isSC());
		return convertView;
	}
	class ViewHolder {
		ImageView image;
		TextView tvName;
		TextView tvPeople;
		TextView tvWay;
		TextView tvAddress;
		TextView tvDateTime;
		CheckBox tvradio;
		
		public ViewHolder(View view) {
			image =(ImageView) view.findViewById(R.id.shop_image);
			tvName=(TextView) view.findViewById(R.id.tv_contact_item_name);
			tvPeople=(TextView) view.findViewById(R.id.tv_contact_item_people);
			tvWay=(TextView) view.findViewById(R.id.tv_contact_item_pay_way);
			tvAddress=(TextView) view.findViewById(R.id.tv_address_contact);
			tvDateTime=(TextView) view.findViewById(R.id.tv_data_textview);
			tvradio =(CheckBox) view.findViewById(R.id.item_sc_radiobutton);
			view.setTag(this);
		}
	}
	@Override
	public MySCbasis getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

}

