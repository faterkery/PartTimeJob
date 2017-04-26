package com.clv.parttimejobs.fragment.my_fragment.mywallet;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.minelayout.mywallet.MyWallet_TJYingHangKa_activity;
import com.clv.parttimejobs.entity.my.mywallet.MyBankCardBean;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.mywallet.YingLianListViewListener;
import com.clv.parttimejobs.view.adapter.my.mywallet.YingLianListviewAdapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


public class YingLianFragment extends Fragment implements YingLianListViewListener{
	private View view;
	private Context context;
    private ListView listview;
    private YingLianListviewAdapter listviewadapter;
    private List<MyBankCardBean> list;
    
    private Button button_addYLCard;
	 @Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {  
		 view = inflater.inflate(R.layout.ying_lian_fragmentactivity, container, false);
		 context=this.getActivity();
		init();
		initListView();
        return view;  
    } 

    private void init(){
    	listview  = (ListView) view.findViewById(R.id.ying_lian_fragment_listview);
    	button_addYLCard =(Button)view.findViewById(R.id.ying_lian_fragment_button_add);
    	
    	InnerOnClickListenr i =new InnerOnClickListenr();
    	button_addYLCard.setOnClickListener(i);
    }
    private void initListView(){
    	list =new ArrayList<MyBankCardBean>();
    	MyBankCardBean m1 =new MyBankCardBean(0,"12121212121111",false);
    	MyBankCardBean m2 =new MyBankCardBean(1,"12121212121111",true);
    	MyBankCardBean m3 =new MyBankCardBean(2,"12121212121111",false);
    	list.add(m1);list.add(m2);list.add(m3);
    	listviewadapter = new YingLianListviewAdapter(context,list);
    	listviewadapter.setListener(this);
    	listview.setAdapter(listviewadapter);
    	setListViewHeightBasedOnChildren(listview);
    }
    private class InnerOnClickListenr implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.ying_lian_fragment_button_add:{
				Intent intent =new Intent(context,MyWallet_TJYingHangKa_activity.class);
				startActivity(intent);
				break;
			}
			}
		}
    	
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
         return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
         View listItem = listAdapter.getView(i, null, listView);
         listItem.measure(0, 0);
         totalHeight += listItem.getMeasuredHeight();
        }
       
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        
        params.height = totalHeight
          + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        System.out.println( params.height);
        listView.setLayoutParams(params);
     }

	@Override
	public void onChangeCheck(int position) {
		// TODO Auto-generated method stub
		for(MyBankCardBean m:list){
			m.setSelected(false);
		}
		list.get(position).setSelected(true);
		listviewadapter.notifyDataSetChanged();
	}
}