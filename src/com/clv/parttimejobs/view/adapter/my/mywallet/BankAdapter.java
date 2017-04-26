package com.clv.parttimejobs.view.adapter.my.mywallet;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.mywallet.MyBankCardBean;

public class BankAdapter extends BaseAdapter {

	private Context context;
	private List<MyBankCardBean> list;

	public BankAdapter(Context context, List<MyBankCardBean> list) {
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
	public MyBankCardBean getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup viewpartents) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_mywallet_mybd_layout,
					null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		MyBankCardBean item = getItem(position);
		switch (item.getBank_site()) {
		case 0: {
			holder.iv_icon.setImageResource(R.drawable.tixian_nongyeyinh);
			holder.tv_name.setText("中国农业银行");
			break;
		}
		case 1: {
			holder.iv_icon.setImageResource(R.drawable.tixian_jiansyinh);
			holder.tv_name.setText("中国建设银行");
			break;
		}
		case 2: {
			holder.iv_icon.setImageResource(R.drawable.tixian_gongshangyinh);
			holder.tv_name.setText("中国工商银行");
			break;
		}
		}
		
		holder.tv_text.setText(item.getSfz_no());
			
		return convertView;
	}

	class ViewHolder {
		ImageView iv_icon;
		TextView tv_name;
		TextView tv_text;

		public ViewHolder(View view) {
			iv_icon = (ImageView) view.findViewById(R.id.item_mywallet_mybd_imageview);
			tv_name = (TextView) view.findViewById(R.id.item_mywallet_mybd_textview_name);
			tv_text = (TextView) view.findViewById(R.id.item_mywallet_mybd_textview_sfzno);
			view.setTag(this);
		}
	}

}
