package com.clv.parttimejobs.activity.minelayout.mywallet;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.mywallet.TXjdBean;
import com.clv.parttimejobs.entity.my.mywallet.TXxqBean;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyWallet_txjd_activity extends Activity {
	private ImageButton returnbutton;
	private ExpandableListView mListView;
	private List<TXjdBean> group_list;
	private TXjdBean strbean;
	private TXxqBean membean;
	private LayoutInflater mInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywallet_txjd_layout);
		init();
		initView();
	}

	public void init(){
		returnbutton =(ImageButton)findViewById(R.id.mymywallet_txjd_return_button);
		returnbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyWallet_txjd_activity.this.finish();
			}
		});
	}
	private void initView() {
		mListView = (ExpandableListView) findViewById(R.id.list);
		mInflater = LayoutInflater.from(MyWallet_txjd_activity.this);
		group_list = new ArrayList<TXjdBean>();
		strbean = new TXjdBean();
		strbean.setZtNO(0);
		strbean.setZtZT(0);
		strbean.setMoney("-100");
		membean = new TXxqBean();
		membean.setTime1("11-10 14:24");
		membean.setTime2("11-10 14:24");
		membean.setTime3("预计11-10 16:24之前");
		strbean.setBean(membean);
		group_list.add(strbean);
		TXjdBean strbean1 = new TXjdBean();
		strbean1.setZtNO(1);
		strbean1.setZtZT(1);
		strbean1.setMoney("-200");
		TXxqBean membean1 = new TXxqBean();
		membean1.setTime1("11-10 14:24");
		membean1.setTime2("11-10 14:24");
		membean1.setTime3("预计11-10 16:24之前");
		strbean1.setBean(membean1);
		group_list.add(strbean1);

		Adapter adapter = new Adapter();
		mListView.setGroupIndicator(null);
		/**
		 * ExpandableListView的组监听事件
		 */
		mListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
		/**
		 * ExpandableListView的组展开监听
		 */
		mListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
			}
		});
		/**
		 * ExpandableListView的组合拢监听
		 */
		mListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
			}
		});
		/**
		 * ExpandableListView的子元素点击监听
		 */
		mListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				return true;
			}
		});

		mListView.setAdapter(adapter);
		// 设置Group默认展开
		int groupCount = mListView.getCount();
		// for(int i=0;i<groupCount;i++){
		// mListView.expandGroup(i);
		// }
	}

	// 自定义适配器
	class Adapter extends BaseExpandableListAdapter {
		// 获取子元素对象
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return null;
		}

		// 获取子元素Id
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		// 加载子元素并显示
		@Override
		public View getChildView(final int groupPosition,
				final int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {
			View view = null;
			ChildHolder childholder = null;
			if (convertView != null) {
				view = convertView;
				childholder = (ChildHolder) view.getTag();
			} else {
				view = View.inflate(MyWallet_txjd_activity.this, R.layout.mywallet_txjd_child, null);
				childholder = new ChildHolder();
				childholder.mImage = (ImageView) view
						.findViewById(R.id.item_txjd_child_imageview);
				childholder.mTime1 = (TextView) view
						.findViewById(R.id.item_txjd_child_textview01);
				childholder.mTime2 = (TextView) view
						.findViewById(R.id.item_txjd_child_textview02);
				childholder.mTime3 = (TextView) view
						.findViewById(R.id.item_txjd_child_textview03);
				view.setTag(childholder);
			}
			TXjdBean b = group_list.get(groupPosition);
			TXxqBean t = group_list.get(groupPosition).getBean();
			switch (b.getZtZT()) {
			case 0: {
				childholder.mImage
						.setImageResource(R.drawable.mywaltxq_pic_jingdu_step);
				break;
			}
			case 1: {
				childholder.mImage
						.setImageResource(R.drawable.mywaltxq_bar_chengg_nor);
				break;
			}
			}
			childholder.mTime1.setText(t.getTime1());
			childholder.mTime2.setText(t.getTime2());
			childholder.mTime3.setText(t.getTime3());
			return view;
		}

		// 获取子元素数目
		@Override
		public int getChildrenCount(int groupPosition) {
			return 1;
		}

		// 获取组元素对象
		@Override
		public Object getGroup(int groupPosition) {
			return group_list.get(groupPosition);
		}

		// 获取组元素数目
		@Override
		public int getGroupCount() {
			return group_list.size();
		}

		// 获取组元素Id
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		// 加载并显示组元素
		@Override
		public View getGroupView(final int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			View view = null;
			GroupHolder groupholder = null;
			if (convertView != null) {
				view = convertView;
				groupholder = (GroupHolder) view.getTag();
			} else {
				view = View.inflate(MyWallet_txjd_activity.this, R.layout.mywallet_txjd_group, null);
				groupholder = new GroupHolder();
				groupholder.mYingHang = (ImageView) view
						.findViewById(R.id.txjd_group_imageview);
				groupholder.mYingHangName = (TextView) view
						.findViewById(R.id.txjd_group_textview01);
				groupholder.mYingHangZT = (TextView) view
						.findViewById(R.id.txjd_group_textview02);
				groupholder.money = (TextView) view
						.findViewById(R.id.txjd_group_textview03);
				groupholder.mSpaceText = (Button) view
						.findViewById(R.id.txjd_group_button);
				view.setTag(groupholder);
			}
			TXjdBean b = group_list.get(groupPosition);
			switch (b.getZtNO()) {
			case 0: {
				groupholder.mYingHang
						.setImageResource(R.drawable.tixian_nongyeyinh);
				groupholder.mYingHangName.setText("中国农业银行");
				break;
			}
			case 1: {
				groupholder.mYingHang
						.setImageResource(R.drawable.tixian_jiansyinh);
				groupholder.mYingHangName.setText("中国建设银行");
				break;
			}
			case 2: {
				groupholder.mYingHang
						.setImageResource(R.drawable.tixian_gongshangyinh);
				groupholder.mYingHangName.setText("中国工商银行");
				break;
			}
			}
			switch (b.getZtZT()) {
			case 0: {
				groupholder.mYingHangZT.setText("处理中");
				groupholder.mYingHangZT.setTextColor(getResources().getColor(R.color.orange));
				break;
			}
			case 1: {
				groupholder.mYingHangZT.setText("处理完成");
				groupholder.mYingHangZT.setTextColor(getResources().getColor(R.color.green));
				break;
			}
			}
			groupholder.money.setText(b.getMoney());
			groupholder.mSpaceText.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (mListView.isGroupExpanded(groupPosition)) {
						mListView.collapseGroup(groupPosition);
					} else {
						mListView.expandGroup(groupPosition);
					}

				}
			});
			return view;
		}

		@Override
		public boolean hasStableIds() {

			return true;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {

			return false;
		}

	}

	static class GroupHolder {
		ImageView mYingHang;
		TextView mYingHangName;
		TextView mYingHangZT;
		TextView money;
		Button mSpaceText;
	}

	static class ChildHolder {
		ImageView mImage;
		TextView mTime1;
		TextView mTime2;
		TextView mTime3;
	}
}
