package com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.message.Message;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuView.OnSwipeItemClickListener;

/**
 * 
 * @author wjj
 * @date 2017
 * 
 */
public class SwipeMenuAdapter implements WrapperListAdapter,
		OnSwipeItemClickListener {

	private ListAdapter mAdapter;
	private Context mContext;
	private OnMenuItemClickListener onMenuItemClickListener;
	private List<Message> list;

	public SwipeMenuAdapter(Context context, ListAdapter adapter,List<Message> list) {
		mAdapter = adapter;
		mContext = context;
		this.list=list;
	}

	@Override
	public int getCount() {
		return mAdapter.getCount();
	}

	@Override
	public Object getItem(int position) {
		return mAdapter.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return mAdapter.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SwipeMenuLayout layout = null;
		if (convertView == null) {
			View contentView = mAdapter.getView(position, convertView, parent);
			SwipeMenu menu = new SwipeMenu(mContext);
			menu.setViewType(mAdapter.getItemViewType(position));
			if(list.get(position).getMesagecount()!=0){
			if(list.get(position).isTop()){
				ll(mContext,menu,2,1);//添加滑动块
			}else{
				ll(mContext,menu,1,1);//添加滑动块
			}}
			else {
				if(list.get(position).isTop()){
					ll(mContext,menu,2,2);//添加滑动块
				}else{
					ll(mContext,menu,1,2);//添加滑动块
				}
			}
			
			SwipeMenuView menuView = new SwipeMenuView(menu,
					(SwipeMenuListView) parent);
			menuView.setOnSwipeItemClickListener(this);
			SwipeMenuListView listView = (SwipeMenuListView) parent;
			layout = new SwipeMenuLayout(contentView, menuView,
					listView.getCloseInterpolator(),
					listView.getOpenInterpolator());
			layout.setPosition(position);
		} else {
			layout = (SwipeMenuLayout) convertView;
			layout.closeMenu();
			layout.setPosition(position);
			View view = mAdapter.getView(position, layout.getContentView(),
					parent);
		}
		return layout;
	}
	private int dp2px(Context context,int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}
	public void ll(Context context,SwipeMenu menu,int a ,int b){
		SwipeMenuItem openItem = new SwipeMenuItem(
				context.getApplicationContext());
		// set item background
		// set item width
		
		// set item title fontsize
		if(a==1){
			openItem.setWidth(dp2px(context,63));
			openItem.setIcon(R.drawable.mes_button_top_nor);
		}else if(a==2){
			openItem.setWidth(dp2px(context,80));
			openItem.setIcon(R.drawable.mes_button_notop_nor);
		}
		openItem.setTitleSize(18);
		// set item title font color
		// add to menu
		menu.addMenuItem(openItem);

		SwipeMenuItem flagItem = new SwipeMenuItem(
				context.getApplicationContext());
		// set item background
		// set item width
		flagItem.setWidth(dp2px(context,80));
		// set a icon
		if(b==1){
        	flagItem.setIcon(R.drawable.mes_button_noread_nor);
		}else if(b==2){
			flagItem.setIcon(R.drawable.mes_button_read_nor);
		}
		
		// add to menu
		menu.addMenuItem(flagItem);

		// create "delete" item
		SwipeMenuItem deleteItem = new SwipeMenuItem(
				context.getApplicationContext());
		// set item background
		// set item width
		deleteItem.setWidth(dp2px(context,63));
		
		// set a icon
		deleteItem.setIcon(R.drawable.mes_button_del_nor);
		// add to menu
		menu.addMenuItem(deleteItem);

	}
	public void createMenu(SwipeMenu menu) {
		// Test Code
		SwipeMenuItem item = new SwipeMenuItem(mContext);
		item.setTitle("Item 1");
		item.setBackground(new ColorDrawable(Color.GRAY));
		item.setWidth(100);
		menu.addMenuItem(item);

		item = new SwipeMenuItem(mContext);
		item.setTitle("Item 2");
		item.setBackground(new ColorDrawable(Color.RED));
		item.setWidth(100);
		menu.addMenuItem(item);
	}

	@Override
	public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {
		if (onMenuItemClickListener != null) {
			onMenuItemClickListener.onMenuItemClick(view.getPosition(), menu,
					index);
		}
	}

	public void setOnMenuItemClickListener(
			OnMenuItemClickListener onMenuItemClickListener) {
		this.onMenuItemClickListener = onMenuItemClickListener;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		mAdapter.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		mAdapter.unregisterDataSetObserver(observer);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return mAdapter.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int position) {
		return mAdapter.isEnabled(position);
	}

	@Override
	public boolean hasStableIds() {
		return mAdapter.hasStableIds();
	}

	@Override
	public int getItemViewType(int position) {
		return mAdapter.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		return mAdapter.getViewTypeCount();
	}

	@Override
	public boolean isEmpty() {
		return mAdapter.isEmpty();
	}

	@Override
	public ListAdapter getWrappedAdapter() {
		return mAdapter;
	}

}
