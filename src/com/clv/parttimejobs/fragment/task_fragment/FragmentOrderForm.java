package com.clv.parttimejobs.fragment.task_fragment;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.clv.homework.R;
import com.clv.parttimejobs.fragment.task_fragment.Internship.Internshop_fragment;
import com.clv.parttimejobs.fragment.task_fragment.parttimejob.Part_fragment;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class FragmentOrderForm extends Fragment {

	private FragmentManager fManager;
	private Fragment home1;
	private Fragment home2;
	private FragmentTransaction trans;
	private RadioGroup radio_group;
	private RadioButton radio_parttimejob;
	private RadioButton radio_internship;

	PullToRefreshScrollView mPullRefreshScrollView;
	ScrollView mScrollView;
	View viewRoot;
	private Context context;

	@Override
    public void onCreate(Bundle savedInstanceState) {
      setRetainInstance(true);
      super.onCreate(savedInstanceState);
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fManager = getChildFragmentManager();

		viewRoot = inflater.inflate(R.layout.activity_fragment_orderform,  container, false);
		context = this.getActivity().getApplicationContext();

		mPullRefreshScrollView = (PullToRefreshScrollView) viewRoot
				.findViewById(R.id.pull_refresh_scrollview);
		mPullRefreshScrollView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
		mPullRefreshScrollView.getLoadingLayoutProxy().setRefreshingLabel(
				"正在刷新");
		mPullRefreshScrollView.getLoadingLayoutProxy().setReleaseLabel("释放刷新");

		radio_parttimejob = (RadioButton) viewRoot
				.findViewById(R.id.parttimejob);
		radio_internship = (RadioButton) viewRoot.findViewById(R.id.internship);
		radio_group = (RadioGroup) viewRoot.findViewById(R.id.order_group);

		home1 = new Part_fragment();
		home2 = new Internshop_fragment();

		InnerOnCheckedListener i = new InnerOnCheckedListener();
		radio_group.setOnCheckedChangeListener(i);
		radio_parttimejob.setChecked(true);

		// 上拉、下拉设定
		mPullRefreshScrollView.setMode(Mode.PULL_FROM_START);

		// 上拉监听函数
		mPullRefreshScrollView
				.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ScrollView> refreshView) {
						// 执行刷新函数
						new GetDataTask().execute();
					}
				});

		// 获取ScrollView布局，此文中用不到
		mScrollView = mPullRefreshScrollView.getRefreshableView();

		return viewRoot;
	}

	private class InnerOnCheckedListener implements
			RadioGroup.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int groupid) {
			// TODO Auto-generated method stub
			trans = fManager.beginTransaction();
			switch (groupid) {
			case R.id.parttimejob: {
				trans.remove(home2);
				trans.replace(R.id.order_fragment, home1);
				trans.addToBackStack(null);
				break;
			}
			case R.id.internship: {
				trans.remove(home1);
				trans.replace(R.id.order_fragment, home2);
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

	// 刷新
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mPullRefreshScrollView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}
