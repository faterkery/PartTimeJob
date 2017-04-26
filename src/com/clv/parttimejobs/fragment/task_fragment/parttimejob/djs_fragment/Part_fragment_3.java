package com.clv.parttimejobs.fragment.task_fragment.parttimejob.djs_fragment;

import java.util.ArrayList;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.mainlayout.jobxq.JobXQ_activity;
import com.clv.parttimejobs.entity.consult.News;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.model.interfacebackage.orderfragment_interface;
import com.clv.parttimejobs.view.adapter.consult.News_list_adapter;
import com.clv.parttimejobs.view.adapter.task.parttimejob.djs.My_order_djs_Adapter;
import com.clv.parttimejobs.view.adapter.task.parttimejob.dsh.My_order_dsh_Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Part_fragment_3 extends Fragment implements orderfragment_interface{

	private ListView listview;
	 private Context context;
	List<PartJobBean> daishenhe;
	My_order_djs_Adapter myListpaerjobAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.partjob_fragment_4, null);
		context=this.getActivity();
		listview = (ListView) viewRoot.findViewById(R.id.listview_partjob_djs);

		daishenhe = new ArrayList<PartJobBean>();
		myListpaerjobAdapter = new My_order_djs_Adapter(
				this.getActivity(), daishenhe,this);

		listview.setAdapter(myListpaerjobAdapter);
		return viewRoot;
	}

	@Override
	public void gotoalayout(int position) {
		// TODO Auto-generated method stub
		PartJobBean n =daishenhe.get(position);
		Intent intent  =new Intent(context,JobXQ_activity.class);
		intent.putExtra("partTimeId",n.getPartTimeId());
		startActivity(intent);
	}

}
