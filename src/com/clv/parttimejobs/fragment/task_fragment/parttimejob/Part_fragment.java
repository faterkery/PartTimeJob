package com.clv.parttimejobs.fragment.task_fragment.parttimejob;

import java.lang.reflect.Field;

import com.clv.homework.R;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.djs_fragment.Part_fragment_3;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.dpl_fragment.Part_fragment_4;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.dsh_fragment.Part_fragment_1;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.ylq_fragment.Part_fragment_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Part_fragment extends Fragment {

	private FragmentManager fManager;
	private FragmentTransaction trans;
	private Fragment home1;
	private Fragment home2;
	private Fragment home3;
	private Fragment home4;
	private RadioGroup radio_group;
	private RadioButton radio_part_shenhe;
	private RadioButton radio_part_lvqu;
	private RadioButton radio_part_jiesuan;
	private RadioButton radio_part_pinglun;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewRoot = inflater.inflate(R.layout.partjob_fragment_main, null);
		fManager = getChildFragmentManager();
		radio_part_shenhe = (RadioButton) viewRoot
				.findViewById(R.id.part_radio_1);
		radio_part_lvqu = (RadioButton) viewRoot
				.findViewById(R.id.part_radio_2);
		radio_part_jiesuan = (RadioButton) viewRoot
				.findViewById(R.id.part_radio_3);
		radio_part_pinglun = (RadioButton) viewRoot
				.findViewById(R.id.part_radio_4);
		radio_group = (RadioGroup) viewRoot.findViewById(R.id.part_radio);
		home1 = new Part_fragment_1();
		home2 = new Part_fragment_2();
		home3 = new Part_fragment_3();
		home4 = new Part_fragment_4();

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
			case R.id.part_radio_1: {
				trans.remove(home1);
				trans.replace(R.id.partjob, home1);
				trans.addToBackStack(null);
				break;
			}
			case R.id.part_radio_2: {
				trans.remove(home2);
				trans.replace(R.id.partjob, home2);
				trans.addToBackStack(null);
				break;
			}
			case R.id.part_radio_3: {
				trans.remove(home3);
				trans.replace(R.id.partjob, home3);
				trans.addToBackStack(null);
				break;
			}
			case R.id.part_radio_4: {
				trans.remove(home4);
				trans.replace(R.id.partjob, home4);
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
