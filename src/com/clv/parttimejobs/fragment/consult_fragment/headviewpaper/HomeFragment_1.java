package com.clv.parttimejobs.fragment.consult_fragment.headviewpaper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.clv.homework.R;

public class HomeFragment_1 extends Fragment {

	private ImageButton button1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home_1, null);
		init(view);
		return view;
	}

	private void init(View view) {
		button1 = (ImageButton) view.findViewById(R.id.hot_paper_1_button);
		InnerOnClickListener i = new InnerOnClickListener();
		button1.setOnClickListener(i);
	}

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			System.out.println("111111");
		}

	}
}
