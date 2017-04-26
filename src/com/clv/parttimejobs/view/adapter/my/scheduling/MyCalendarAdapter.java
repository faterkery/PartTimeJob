package com.clv.parttimejobs.view.adapter.my.scheduling;

import java.util.ArrayList;
import java.util.List;







import com.clv.homework.R;
import com.clv.parttimejobs.activity.minelayout.scheduling.Xcapactivity;
import com.clv.parttimejobs.entity.my.scheduling.Myrcbasis;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.scheduling.ShowPopwindow;

import android.content.Context;
import android.opengl.Visibility;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyCalendarAdapter extends BaseAdapter{

	private Context context;
	
	private int hang;
	static Myrcbasis m;
	private boolean isshow ;
	private List<Myrcbasis> list=new ArrayList<Myrcbasis>();
	private ShowPopwindow listener;
	
	public MyCalendarAdapter(Context context, List<Myrcbasis> l,int hang,ShowPopwindow listener) {
		super();
		this.context = context;
		list=l;
		this.hang=hang;
		this.listener =listener;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return  5;
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	    m = null ;
		boolean isTrue =false;
		for(Myrcbasis m1:list){
			if((m1.getLieno()-1)==position&&m1.getHangno()==hang){
				isTrue =true;
				 m =m1;
			}
		}
		if(isTrue){
		    convertView = View.inflate(context, R.layout.mycalendaritem_layout, null);
			new ViewHolder(convertView);
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.item_text01.setText(m.getTime());
			holder.item_text02.setText(m.getContext());
			if(Xcapactivity.isshow){
				holder.item_button.setVisibility(View.VISIBLE);
				holder.item_button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.out.println(position);
						remove(position);
					}
				});
			}else{
				holder.item_button.setVisibility(View.INVISIBLE);
			}
			
		}else{
			if(Xcapactivity.isshow){
				convertView = View.inflate(context, R.layout.mycalendaritem_empty_layout, null);
				new ViewHolder2(convertView);
				ViewHolder2 holder2 = (ViewHolder2) convertView.getTag();
				holder2.item_button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						// TODO Auto-generated method stub
						Xcapactivity.select_lie=position+1;
						Xcapactivity.select_hang=hang;
						listener.show();
					}
					
				});
				
			}
			else{
				convertView = View.inflate(context, R.layout.mycalendaritem_empty_2_layout, null);
			}
				new ViewHolder(convertView);
		}
		
		return convertView;
	}
	class ViewHolder {
		TextView item_text01;
		TextView item_text02;
		ImageButton item_button;
		
		public ViewHolder(View view) {
			item_text01 = (TextView) view.findViewById(R.id.item_text01);
			item_text02 = (TextView) view.findViewById(R.id.item_text02);
		    item_button= (ImageButton)view.findViewById(R.id.item_imagebutton01);
			view.setTag(this);
		}
	}

	class ViewHolder2{
		ImageButton item_button;
		public ViewHolder2(View view) {
		    item_button= (ImageButton)view.findViewById(R.id.item_empty_imagebutton);
			view.setTag(this);
		}
	}
	public void remove(int position){
		listener.remove(hang, position);
	}
	public int select(int position){
		int a = 0;
		for(Myrcbasis m1:list){
			if((m1.getLieno()-1)==position&&m1.getHangno()==hang){
				break;
			}
			a++;
		}
		return a;
	}
	
}
