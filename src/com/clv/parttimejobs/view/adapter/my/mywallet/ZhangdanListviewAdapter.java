package com.clv.parttimejobs.view.adapter.my.mywallet;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.mywallet.ZhangDanBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ZhangdanListviewAdapter  extends BaseAdapter{

	private Context context;
	private List<ZhangDanBean> data;
	
	public ZhangdanListviewAdapter(Context context,List<ZhangDanBean> data){
		this.context=context;
		this.data=data;
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
        ZhangDanBean mybankZD =data.get(position);
		
		ViewHolder holder1;
		convertView = View.inflate(context,R.layout.item_zhanddan_layout, null);
		holder1 = new ViewHolder(convertView);
		char f =mybankZD.getMoney().charAt(0);
		System.out.println(f);
		if(('+')==f){
			System.out.println("yes");
			holder1.zdmoneycount.setTextColor(context.getResources().getColor(R.color.green));
		}else if(('-')==f){
			holder1.zdmoneycount.setTextColor(context.getResources().getColor(R.color.gray_8));
		}else {
			holder1.zdmoneycount.setTextColor(context.getResources().getColor(R.color.gray_8));
		}
		holder1.zdname.setText(mybankZD.getName().trim());
		holder1.zddate.setText(mybankZD.getTimeDate().trim());
		holder1.zdtime.setText(mybankZD.getTimeJT().trim());
		holder1.zdmoneycount.setText(mybankZD.getMoney().trim());
		return convertView;
	}

	class ViewHolder{
		 TextView zdname;
		 TextView zddate;
		 TextView zdtime;
		 TextView zdmoneycount;
		public ViewHolder(View view){
			zdname =(TextView)view.findViewById(R.id.item_zhangdan_textview01);
			zddate=(TextView)view.findViewById(R.id.item_zhangdan_textview02);
			zdtime=(TextView)view.findViewById(R.id.item_zhangdan_textview03);
			zdmoneycount=(TextView)view.findViewById(R.id.item_zhangdan_textview04);
		}
	}
}
