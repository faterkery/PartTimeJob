package com.clv.parttimejobs.view.adapter.my.mywallet;

import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.mywallet.MyBankCardBean;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.mywallet.YingLianListViewListener;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class YingLianListviewAdapter extends BaseAdapter{

	private Context context;
	private List<MyBankCardBean> data;
	private YingLianListViewListener listener;
	
	public YingLianListviewAdapter(Context context,List<MyBankCardBean> data){
		this.context=context;
		this.data=data;
	}
	public void setListener( YingLianListViewListener listener){
		this.listener=listener;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyBankCardBean mybank =data.get(position);
		
		ViewHolder holder1;
		convertView = View.inflate(context,R.layout.item_ying_lian_layout, null);
		holder1 = new ViewHolder(convertView);
		switch(mybank.getBank_site()){
		case 0:{
			//0-农行 
			holder1.bankimg.setImageResource(R.drawable.tixian_nongyeyinh);
			holder1.bankname.setText("中国农业银行");
			holder1.sfzno.setText(mybank.getSfz_no()+"");
			holder1.isSelected.setChecked(mybank.isSelected());
			break;
		}
		case 1:{
			//1-建设
			holder1.bankimg.setImageResource(R.drawable.tixian_jiansyinh);
			holder1.bankname.setText("中国建设银行");
			holder1.sfzno.setText(mybank.getSfz_no()+"");
			holder1.isSelected.setChecked(mybank.isSelected());
			break;
		}
		case 2:{
			//2-工商
			holder1.bankimg.setImageResource(R.drawable.tixian_gongshangyinh);
			holder1.bankname.setText("中国工商银行");
			holder1.sfzno.setText(mybank.getSfz_no()+"");
			holder1.isSelected.setChecked(mybank.isSelected());
			break;
		}
		}
		holder1.isSelected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//执行选中事件
					if(listener!=null){
					listener.onChangeCheck(position);
					}
				}
			}
		});//设置监听
		return convertView;
	}

	class ViewHolder{
		private ImageView bankimg;
		private TextView bankname;
		private TextView sfzno;
		private CheckBox isSelected;
		public ViewHolder(View view){
			bankimg =(ImageView)view.findViewById(R.id.item_mybank_layout_imageview);
			bankname=(TextView)view.findViewById(R.id.item_mybank_layout_textview01);
			sfzno=(TextView)view.findViewById(R.id.item_mybank_layout_textview02);
			isSelected=(CheckBox)view.findViewById(R.id.item_mybank_layout_checkbox);
		}
	}
}
