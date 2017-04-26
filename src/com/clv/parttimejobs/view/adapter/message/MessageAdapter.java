package com.clv.parttimejobs.view.adapter.message;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.message.Message;
import com.clv.parttimejobs.model.interfacebackage.message.MessageListener;
import com.clv.parttimejobs.view.ui.customview.DragPointView;
import com.clv.parttimejobs.view.ui.customview.DragPointView.OnDragListencer;

public class MessageAdapter extends BaseAdapter {

	private Context context;
	private List<Message> list;
	private MessageListener listener;

	public MessageAdapter(Context context, List<Message> list,MessageListener listener) {
		super();
		this.context = context;
		this.list = list;
		this.listener=listener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Message getItem(int arg0) {
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
			convertView = View.inflate(context, R.layout.item_message_content,
					null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Message item = getItem(position);
		switch (item.getFlag()) {
		case 1: {
			holder.iv_icon.setImageResource(item.getDrawid());
			break;
		}
		case 2: {
			holder.iv_icon.setImageResource(R.drawable.mes_img_team_nor);
			break;
		}
		case 3: {
			holder.iv_icon.setImageResource(R.drawable.mes_img_sys_nor);
			break;
		}
		}
		if(item.isTop()){
			holder.relativelayout.setBackgroundResource(R.color.gray_1);
		}
		holder.tv_name.setText(item.getMessage_name());
		holder.tv_text.setText(item.getMessage_context());
		if(list.get(position).getMesagecount()==0){
			holder.tv_dpoint.setVisibility(View.INVISIBLE);
		}else{
			holder.tv_dpoint.setVisibility(View.VISIBLE);
			int count=list.get(position).getMesagecount();
			if(count>99){
				count=99;
			}
			holder.tv_dpoint.setText(String.valueOf(count));
			holder.tv_dpoint.setDragListencer(new OnDragListencer() {
				
				@Override
				public void onDragOut() {
					//ÍÏ×§ÆøÅÝ
					listener.dismissMessage(position);
				}
			});
		}
		
		return convertView;
	}

	class ViewHolder {
		ImageView iv_icon;
		TextView tv_name;
		TextView tv_text;
		DragPointView tv_dpoint;
		RelativeLayout relativelayout;

		public ViewHolder(View view) {
			iv_icon = (ImageView) view.findViewById(R.id.item_message_image1);
			tv_name = (TextView) view.findViewById(R.id.item_message_text1);
			tv_text = (TextView) view.findViewById(R.id.item_message_text2);
			tv_dpoint = (DragPointView) view.findViewById(R.id.dragview);
			relativelayout= (RelativeLayout) view.findViewById(R.id.item_meessage_person_reyout);
			view.setTag(this);
		}
	}

}
