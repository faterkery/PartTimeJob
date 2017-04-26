package com.clv.parttimejobs.view.adapter.consult.job.gs.comment;

import java.util.List;






import com.clv.homework.R;
import com.clv.parttimejobs.entity.consult.job.gs.comment.PinglunBasis;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class PinglunAdapter extends BaseAdapter{

	private Context context; 
	private List<PinglunBasis> list;
	
	public PinglunAdapter(Context context, List<PinglunBasis> list) {
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
	public PinglunBasis getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewpartents) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_pinglun_layout,
					null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		PinglunBasis item = getItem(position);
		if(!("").equals(item.getImgUrl()))holder.tvimageview.setImageBitmap(BitmapFactory.decodeFile(item.getImgUrl()));
		holder.tvtextview.setText(item.getName());
		holder.tvdate.setText(item.getDate());
		holder.tvcontext.setText(item.getContext());
		holder.tvtextpl.setText("游览("+item.getYoulanCount()+")");
		holder.tvtextyy.setText("有用("+item.getYouyongCount()+")");
		List<String> imgUrl =item.getImgUrlArray();
		if(imgUrl.size()!=0){
			PingLunImageAdapter imageAdapter = new PingLunImageAdapter(context, imgUrl);
		holder.tvgridview.setAdapter(imageAdapter);}
		return convertView;
	}
	class ViewHolder {
		ImageView tvimageview;
		TextView tvtextview;
		TextView tvdate;
		TextView tvcontext;
		GridView tvgridview;
		TextView tvtextpl;
		TextView tvtextyy;
		
		public ViewHolder(View view) {
			tvimageview = (ImageView) view.findViewById(R.id.item_pinglun_imageview);
			tvtextview =(TextView) view.findViewById(R.id.item_pinglun_name);
			tvdate =(TextView) view.findViewById(R.id.item_pinglun_date);
			tvcontext =(TextView) view.findViewById(R.id.item_pinglun_textview01);
			tvgridview =(GridView) view.findViewById(R.id.item_pinglun_gridview);
			tvtextpl =(TextView) view.findViewById(R.id.item_pinglun_textview02);
			tvtextyy =(TextView) view.findViewById(R.id.item_pinglun_textview03);
			view.setTag(this);
		}
	}

}
