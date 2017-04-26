package com.clv.parttimejobs.activity.minelayout.mywallet;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.message.Message;
import com.clv.parttimejobs.entity.my.mywallet.MyBankCardBean;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenu;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuCreator;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuItem;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuListView;
import com.clv.parttimejobs.thirdparty.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.clv.parttimejobs.view.adapter.message.MessageAdapter;
import com.clv.parttimejobs.view.adapter.my.mywallet.BankAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MyWallet_mybd_activity extends Activity {

	private List<MyBankCardBean> mList = new ArrayList<MyBankCardBean>();
	private BankAdapter mAdapter;
	private SwipeMenuListView mListView;
	private Context context;
	private View view_head_1;
	private Button xgButton;
	private EditText edittext;
	private ImageButton returnButton;
	private RelativeLayout relativelayout;
	private RelativeLayout relativelayout_listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywallet_mybd_layout);
		context = this;
		init();
		initdata();
		initView();
		initChildView();
	}

	public void init() {
		mListView = (SwipeMenuListView)findViewById(R.id.mywallet_mybd_listView);
		relativelayout_listview=(RelativeLayout) findViewById(R.id.mymywallet_mybd_listview_rey);
	}
	
	public void initdata(){
		MyBankCardBean m1 =new MyBankCardBean();
		m1.setBank_site(0);m1.setSfz_no("**********5573");mList.add(m1);
		MyBankCardBean m2 =new MyBankCardBean();
		m2.setBank_site(1);m2.setSfz_no("**********8642");mList.add(m2);
		mAdapter= new BankAdapter(context, mList);
		view_head_1= View.inflate(this, R.layout.mywallet_mybd_head_layout, null);  
		mListView.addHeaderView(view_head_1);
		mListView.setAdapter(mAdapter);
		
	}
	public void initChildView()
	{
		xgButton = (Button) view_head_1.findViewById(R.id.mymywallet_mybd_button01);
		edittext =(EditText) view_head_1.findViewById(R.id.mymywallet_mybd_edittext);
		relativelayout=(RelativeLayout) view_head_1.findViewById(R.id.mymywallet_mybd_rey);
		returnButton = (ImageButton) view_head_1.findViewById(R.id.mymywallet_mybd_return_button);
		InnerOnClickListener i =new InnerOnClickListener();
		xgButton.setOnClickListener(i);
		relativelayout.setOnClickListener(i);
		relativelayout_listview.setOnClickListener(i);
		returnButton.setOnClickListener(i);
	}
	public class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.mymywallet_mybd_button01:{
				edittext.setFocusableInTouchMode(true);
				edittext.setFocusable(true);
				edittext.requestFocus();
				break;
			}
			case R.id.mymywallet_mybd_rey:{
				edittext.setFocusableInTouchMode(false);
				edittext.setFocusable(false);
				relativelayout.setFocusableInTouchMode(true);
				relativelayout.setFocusable(true);
				relativelayout.requestFocus();
				break;
			}
			case R.id.mymywallet_mybd_listview_rey:{
				edittext.setFocusableInTouchMode(false);
				edittext.setFocusable(false);
				relativelayout_listview.setFocusableInTouchMode(true);
				relativelayout_listview.setFocusable(true);
				relativelayout_listview.requestFocus();
				break;
			}
			case R.id.mymywallet_mybd_return_button:{
				MyWallet_mybd_activity.this.finish();
				break;
			}
			}
		
	    }
	}
	
	public void initView(){
		// step 1. create a MenuCreator
				SwipeMenuCreator creator = new SwipeMenuCreator() {

					@Override
					public void create(SwipeMenu menu) {
						// create "delete" item
						SwipeMenuItem deleteItem = new SwipeMenuItem(
								context.getApplicationContext());
						// set item background
						// set item width
						deleteItem.setWidth(dp2px(52));
						// set a icon
						deleteItem.setIcon(R.drawable.mywalt_bar_cheal_nor);
						// add to menu
						menu.addMenuItem(deleteItem);

					}
				};
				// set creator
				mListView.setMenuCreator(creator);

				// step 2. listener item click event
				mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public void onMenuItemClick(int position, SwipeMenu menu, int index) {
						MyBankCardBean m = mList.get(position);
						switch (index) {
						case 0:{
							mList.remove(position);
							mAdapter.notifyDataSetChanged();
							break;
						}
						}
					}
				});
	}
	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
}
