package com.clv.parttimejobs.fragment.task_fragment.Internship.ylq_fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.mainlayout.jobxq.JobXQ_activity;
import com.clv.parttimejobs.entity.consult.News;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.model.interfacebackage.orderfragment_interface;
import com.clv.parttimejobs.view.adapter.task.parttimejob.ylq.My_order_ylq_Adapter;

public class Intershop_fragment_2 extends Fragment implements orderfragment_interface{

	private ListView listview;
	private  List<PartJobBean> daishenhe;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.intershop_fragment_2, null);

		listview = (ListView) viewRoot
				.findViewById(R.id.listview_intershop_dsh);

		daishenhe = new ArrayList<PartJobBean>();
		My_order_ylq_Adapter myListpaerjobAdapter = new My_order_ylq_Adapter(
				this.getActivity(), daishenhe,this);

		listview.setAdapter(myListpaerjobAdapter);
		return viewRoot;
	}

	@Override
	public void gotoalayout(int position) {
		// TODO Auto-generated method stub
		Intent intent  =new Intent(this.getActivity(),JobXQ_activity.class);
		intent.putExtra("partTimeId", 1);
		startActivity(intent);
	}

}
