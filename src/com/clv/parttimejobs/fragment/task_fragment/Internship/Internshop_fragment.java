package com.clv.parttimejobs.fragment.task_fragment.Internship;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.clv.homework.R;
import com.clv.parttimejobs.fragment.task_fragment.Internship.djs_fragment.Intershop_fragment_3;
import com.clv.parttimejobs.fragment.task_fragment.Internship.dsh_fragment.Intershop_fragment_1;
import com.clv.parttimejobs.fragment.task_fragment.Internship.ylq_fragment.Intershop_fragment_2;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.djs_fragment.Part_fragment_3;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.dpl_fragment.Part_fragment_4;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.dsh_fragment.Part_fragment_1;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.ylq_fragment.Part_fragment_2;

public class Internshop_fragment extends Fragment {

	private FragmentManager fManager;
	private FragmentTransaction trans;
	private Fragment home1;
	private Fragment home2;
	private Fragment home3;
	private RadioGroup radio_group;
	private RadioButton radio_part_shenhe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fManager = getChildFragmentManager();
		View viewRoot = inflater
				.inflate(R.layout.intershop_fragment_main, null);
		radio_part_shenhe = (RadioButton) viewRoot
				.findViewById(R.id.inter_radiobutton_1);
		radio_group = (RadioGroup) viewRoot.findViewById(R.id.inter_radiogroup);
		home1 = new Intershop_fragment_1();
		home2 = new Intershop_fragment_2();
		home3 = new Intershop_fragment_3();

		InnerOnCheckedListener i = new InnerOnCheckedListener();
		radio_group.setOnCheckedChangeListener(i);
		radio_part_shenhe.setChecked(true);
		return viewRoot;
	}

	private class InnerOnCheckedListener implements
			RadioGroup.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int groupid) {
			// TODO Auto-generated method stub
			trans = fManager.beginTransaction();
			switch (groupid) {
			case R.id.inter_radiobutton_1: {
				trans.remove(home1);
				trans.replace(R.id.shop, home1);
				trans.addToBackStack(null);
				break;
			}
			case R.id.inter_radiobutton_2: {
				trans.remove(home2);
				trans.replace(R.id.shop, home2);
				trans.addToBackStack(null);
				break;
			}
			case R.id.inter_radiobutton_3: {
				trans.remove(home3);
				trans.replace(R.id.shop, home3);
				trans.addToBackStack(null);
				break;
			}
			}
			trans.commit();
		}

	}

	@Override
	public void onDetach() {
		super.onDetach();
		try {
			// 参数是固定写法
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
