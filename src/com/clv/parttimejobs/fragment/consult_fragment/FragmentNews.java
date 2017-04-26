package com.clv.parttimejobs.fragment.consult_fragment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.mainlayout.PartJobListActivity;
import com.clv.parttimejobs.activity.mainlayout.jobxq.JobXQ_activity;
import com.clv.parttimejobs.activity.minelayout.login.register.Registered;
import com.clv.parttimejobs.dao.parttimejob.PartJobDao;
import com.clv.parttimejobs.entity.consult.News;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.entity.my.resume.UserPhoto;
import com.clv.parttimejobs.fragment.consult_fragment.headviewpaper.HomeFragment_1;
import com.clv.parttimejobs.fragment.consult_fragment.headviewpaper.HomeFragment_2;
import com.clv.parttimejobs.fragment.consult_fragment.headviewpaper.HomeFragment_3;
import com.clv.parttimejobs.fragment.consult_fragment.headviewpaper.HomeFragment_4;
import com.clv.parttimejobs.model.callback.JobsListCallback;
import com.clv.parttimejobs.model.modelbackage.PartJobsModel;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshListView;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonEcute;
import com.clv.parttimejobs.util.ecutetime.Ecute_data_time;
import com.clv.parttimejobs.util.ecutetime.GetLastTime;
import com.clv.parttimejobs.util.networkinfo.CheckNetWerk;
import com.clv.parttimejobs.view.adapter.MyFragmentPagerAdapter;
import com.clv.parttimejobs.view.adapter.consult.News_list_adapter;
import com.clv.parttimejobs.view.adapter.consult.listviewhead.Fragement_Header_3_PaperAdapter;
import com.clv.parttimejobs.view.ui.customview.ExpandView_jiesuan;
import com.clv.parttimejobs.view.ui.customview.ExpandView_paixu;
import com.clv.parttimejobs.view.ui.customview.ExpandView_shijian;
import com.clv.parttimejobs.view.ui.customview.ExpandView_zhonglei;
import com.clv.parttimejobs.view.ui.customview.MarqueeText;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentNews extends Fragment {

	public final static int num = 2;// viewpaper的总页数
	private int currIndex = 0;// viewpaper的当前选项
	private int bottomLineWidth;
	private int offset = 0;

	private int time_under = 360;

	MarqueeText matqueeText;
	RelativeLayout rey;
	private View view;// 需要返回的布局

	private Fragment home1;// viewpaper的第一个页面
	private Fragment home2;// viewpaper的第二个页面
	private Fragment home3;
	private Fragment home4;
	private Context context;// 主context
	private Resources resources;
	private ViewPager mPager;// viewpaper
	private ViewPager mPaper_head3;
	private TextView time_text;// 倒计时
	private PullToRefreshListView listView;// listview
	private List<PartJobBean> news_list;// listview 数据list
	private News_list_adapter adapter;// listview 设配器
	private ArrayList<Fragment> fragmentsList;// viewpaper的资源list
	private ListView listView_dem;
	private ScheduledExecutorService scheduledExecutorService;
    
	private Button dqButton;
	private Button gxButton;
	private Button gfButton;
	private Button zmButton;
	
	private RadioButton radiobutton1;
	private RadioButton radiobutton2;
	private RadioButton radiobutton3;
	private RadioButton radiobutton4;

	private Button search_lei_radiobutton;
	private Button search_time_radiobutton;
	private Button search_jiesuan_radiobutton;
	private Button search_paixu_radiobutton;

	private ExpandView_jiesuan mExpandView_jiesuan;
	private ExpandView_paixu mExpandView_paixu;
	private ExpandView_shijian mExpandView_shijian;
	private ExpandView_zhonglei mExpandView_zhonglei;

	private ImageView msearch_time_imageview;
	private ImageView msearch_lei_imageview;
	private ImageView msearch_jiesuan_imageview;
	private ImageView msearch_paixu_imageview;

	private Ecute_data_time ecute = new Ecute_data_time();
	
	private View view_head_1;
	private View view_head_2;
	private View view_head_3;
	private View view_head_4;
	private View view_head_5;
	private View view_head_6;

	private boolean isSend=false;
	@Override
    public void onCreate(Bundle savedInstanceState) {
      setRetainInstance(true);
      super.onCreate(savedInstanceState);
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = this.getActivity();
		resources = context.getResources();

		view = inflater.inflate(R.layout.activity_fragment_news,  container, false);

		// listview
		// 开始
		listView = (PullToRefreshListView) view.findViewById(R.id.news_list);

		// 增加下拉刷新
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(context,
						System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
								| DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				flushList();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println(arg2+"----"+arg3);
				PartJobBean n =news_list.get(arg2-6);
				Intent intent  =new Intent(context,JobXQ_activity.class);
				intent.putExtra("partTimeId",n.getPartTimeId());
				startActivity(intent);
			}

		});
		// 增加声音
//		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(
//				context);
//		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
//		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
//		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
//		listView.setOnPullEventListener(soundListener);

		//
		news_list = new ArrayList<PartJobBean>();
//		news_list.add(new PartJobBean("2017-2-24","宁波","XXX","XXXX","浙江省","1","111","","10元/时","兼职特写手招聘", "2016-2-10到2017-2-10"));

		listView_dem = listView.getRefreshableView();
		listView_dem.setVerticalScrollBarEnabled(false);// 隐藏进度条
		listView_dem.setDividerHeight(0);
		// 加入head_1
		 view_head_1 = inflater.inflate(R.layout.listview_head, null);
		listView_dem.addHeaderView(view_head_1);
		// 加入head_2
		 view_head_2 = inflater.inflate(R.layout.listview_head_2, null);
		listView_dem.addHeaderView(view_head_2);
		// 加入head_3
		 view_head_3 = inflater.inflate(R.layout.listview_head_3, null);
		matqueeText = (MarqueeText) view_head_3.findViewById(R.id.marqueeText);
		matqueeText.setText("恭喜XXX获得这次的最高奖励，真的厉害了！！！！");
		listView_dem.addHeaderView(view_head_3);
		// 加入head_4
		 view_head_4 = inflater.inflate(R.layout.listview_head_4, null);
		Button button = (Button) view_head_4.findViewById(R.id.btn_1);
		time_text = (TextView) view_head_4.findViewById(R.id.time);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
			}
		});
		new Thread(new MyThread()).start(); // start thread
		listView_dem.addHeaderView(view_head_4);
		// 加入head_5
		view_head_5 = inflater.inflate(R.layout.listview_head_5, null);
		listView_dem.addHeaderView(view_head_5);
		rey = (RelativeLayout) view.findViewById(R.id.rey_01);
		// 加入head_6
//		view_head_6 = inflater.inflate(R.layout.listview_head_6, null);
//		listView_dem.addHeaderView(view_head_6);
		// 加入滚动
		InnerOnScrollListener innerScroll = new InnerOnScrollListener();
		listView.setOnScrollListener(innerScroll);
		listView.setVerticalScrollBarEnabled(false);// 隐藏进度条
		adapter = new News_list_adapter(this.getActivity(), news_list);
		listView_dem.setAdapter(adapter);
		//
		radiobutton1 = (RadioButton) view.findViewById(R.id.radio_1);
		radiobutton2 = (RadioButton) view.findViewById(R.id.radio_2);
		radiobutton3 = (RadioButton) view.findViewById(R.id.radio_3);
		radiobutton4 = (RadioButton) view.findViewById(R.id.radio_4);
		//
		dqButton =(Button)view.findViewById(R.id.button_dq);
		gxButton =(Button)view.findViewById(R.id.button_gx);
		gfButton =(Button)view.findViewById(R.id.button_gf);
		zmButton =(Button)view.findViewById(R.id.button_zm);
		// 初始化
		InitWidth(view);
		InitViewPager(view_head_1);
		// 每隔2秒钟切换一张图片
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 3,
				4, TimeUnit.SECONDS);
		//
		msearch_time_imageview = (ImageView) view
				.findViewById(R.id.search_time_imageview);
		msearch_lei_imageview = (ImageView) view
				.findViewById(R.id.search_lei_imageview);
		msearch_jiesuan_imageview = (ImageView) view
				.findViewById(R.id.search_jiesuan_imageview);
		msearch_paixu_imageview = (ImageView) view
				.findViewById(R.id.search_paixu_imageview);
		search_lei_radiobutton = (Button) view.findViewById(R.id.search_lei);
		search_time_radiobutton = (Button) view.findViewById(R.id.search_time);
		search_jiesuan_radiobutton = (Button) view
				.findViewById(R.id.search_jiesuan);
		search_paixu_radiobutton = (Button) view
				.findViewById(R.id.search_paixu);
		mExpandView_jiesuan = (ExpandView_jiesuan) view
				.findViewById(R.id.expandView_jiesuan);
		mExpandView_paixu = (ExpandView_paixu) view
				.findViewById(R.id.expandView_paixu);
		mExpandView_shijian = (ExpandView_shijian) view
				.findViewById(R.id.expandView_shijian);
		mExpandView_zhonglei = (ExpandView_zhonglei) view
				.findViewById(R.id.expandView_zhonglei);
		//

		// 点击事件
		InnerOnClickListener i = new InnerOnClickListener();
		search_lei_radiobutton.setOnClickListener(i);
		search_time_radiobutton.setOnClickListener(i);
		search_jiesuan_radiobutton.setOnClickListener(i);
		search_paixu_radiobutton.setOnClickListener(i);

		dqButton.setOnClickListener(i);
		gxButton.setOnClickListener(i);
		gfButton.setOnClickListener(i);
		zmButton.setOnClickListener(i);
		
		//刷新网络
		PartJobDao dao =new PartJobDao(context);
		List<PartJobBean> list =dao.detail();
		
		for(PartJobBean p:list)
		{
			if(!isExiste(p,news_list)){
				news_list.add(p);
			}
		}
		adapter.notifyDataSetChanged();
		flushList();
		return view;
		// 结束

	}

	public void flushList(){
		PartJobsModel model =new PartJobsModel();
		model.loadPartJobsList(context, new JobsListCallback() {
			
			@Override
			public void onJobsListLoaded(String results) {
				// TODO Auto-generated method stub
				System.out.println(results);
				if(("success").equals(results)){
					PartJobDao dao =new PartJobDao(context);
					List<PartJobBean> list =dao.detail();
					for(PartJobBean p:list)
					{
						if(!isExiste(p,news_list)){
							news_list.add(p);
						}
					}
					adapter.notifyDataSetChanged();
				}else if(("sockettimeout").equals(results)){
					Toast.makeText(context, "网路连接错误", Toast.LENGTH_SHORT).show();
				}
				listView.onRefreshComplete();
			}
		});
	}
	public void hide() {
		Drawable imageDrawable = resources
				.getDrawable(R.drawable.my_icon_chose_nor); // 图片在drawable目录下
		if (mExpandView_jiesuan.isExpand()) {
			msearch_jiesuan_imageview.setImageDrawable(imageDrawable);
			mExpandView_jiesuan.collapse();
		}
		if (mExpandView_paixu.isExpand()) {
			msearch_paixu_imageview.setImageDrawable(imageDrawable);
			mExpandView_paixu.collapse();
		}
		if (mExpandView_shijian.isExpand()) {
			msearch_time_imageview.setImageDrawable(imageDrawable);
			mExpandView_shijian.collapse();
		}
		if (mExpandView_zhonglei.isExpand()) {
			msearch_lei_imageview.setImageDrawable(imageDrawable);
			mExpandView_zhonglei.collapse();
		}
	}

	private class InnerOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Drawable imageDrawable = resources
					.getDrawable(R.drawable.my_icon_chose_top); // 图片在drawable目录下
			switch (v.getId()) {
			case R.id.search_lei: {
				if (mExpandView_zhonglei.isExpand()) {
					hide();
				} else {
					hide();
					mExpandView_zhonglei.expand();
					msearch_lei_imageview.setImageDrawable(imageDrawable);
				}
				break;
			}
			case R.id.search_time: {
				if (mExpandView_shijian.isExpand()) {
					hide();
				} else {
					hide();
					mExpandView_shijian.expand();
					msearch_time_imageview.setImageDrawable(imageDrawable);
				}
				break;
			}
			case R.id.search_jiesuan: {
				if (mExpandView_jiesuan.isExpand()) {
					hide();
				} else {
					hide();
					mExpandView_jiesuan.expand();
					msearch_jiesuan_imageview.setImageDrawable(imageDrawable);
				}
				break;
			}
			case R.id.search_paixu: {
				if (mExpandView_paixu.isExpand()) {
					hide();
				} else {
					hide();
					mExpandView_paixu.expand();
					msearch_paixu_imageview.setImageDrawable(imageDrawable);
				}
				break;
			}
			case R.id.button_dq:{
				Intent intent =new Intent(context,PartJobListActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.button_gx:{
				Intent intent =new Intent(context,PartJobListActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.button_gf:{
				Intent intent =new Intent(context,PartJobListActivity.class);
				startActivity(intent);
				break;
			}
			case R.id.button_zm:{
				Intent intent =new Intent(context,PartJobListActivity.class);
				startActivity(intent);
				break;
			}
			}
		}

	}

	final Handler handler2 = new Handler() {

		public void handleMessage(Message msg) { // handle message
			switch (msg.what) {
			case 1:
				time_under--;
				String temp = "";
				temp = ecute.ecute_time_data(time_under);
				time_text.setText(temp);

				if (time_under > 0) {
					Message message = handler.obtainMessage(1);
					handler.sendMessageDelayed(message, 1000); // send message
				} else {
					time_text.setText("已结束");
				}
			}

			super.handleMessage(msg);
		}
	};

	public class MyThread implements Runnable { // thread
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000); // sleep 1000ms
					Message message = new Message();
					message.what = 1;
					handler2.sendMessage(message);
				} catch (Exception e) {
				}
			}
		}
	}


	public boolean isExiste(PartJobBean p,List<PartJobBean> list){
		boolean isExist =false;
		for(PartJobBean j:list){
			if(p.getPartTimeId().equals(j.getPartTimeId())){
			    isExist=true;
			}
		}
		return isExist;
	}
	// -------------------------热点推送head_1----------------------------------------------
	// 初始化头界面1
	// start
	@SuppressWarnings("deprecation")
	private void InitViewPager(View parentView) {
		mPager = (ViewPager) parentView.findViewById(R.id.vPager);
		// 加入fragment资源
		fragmentsList = new ArrayList<Fragment>();
		home1 = new HomeFragment_1();
		home2 = new HomeFragment_2();
		home3 = new HomeFragment_3();
		home4 = new HomeFragment_4();
		fragmentsList.add(home1);
		fragmentsList.add(home2);
		fragmentsList.add(home3);
		fragmentsList.add(home4);
		FragmentManager f = getChildFragmentManager();
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(f,
				fragmentsList);
		mPager.setAdapter(adapter);
		// 初始化第一次加载为第0页
		mPager.setCurrentItem(0);
		radiobutton1.setChecked(true);
		//
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < 4; i++) {
					if (arg0 != i) {
						currIndex=arg0;
						mPager.setCurrentItem(arg0);
					}
				}
				switch (arg0) {
				case 0: {
					radiobutton1.setChecked(true);
					break;
				}
				case 1: {
					radiobutton2.setChecked(true);
					break;
				}
				case 2: {
					radiobutton3.setChecked(true);
					break;
				}
				case 3: {
					radiobutton4.setChecked(true);
					break;
				}

				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int poisiton) {
				// TODO Auto-generated method stub
				switch (poisiton) {
				}
			}
		});

	}

	// end
	// 切换图片进程
	// start
	private class ViewPagerTask implements Runnable {
		@Override
		public void run() {
			currIndex = (currIndex + 1) % 4;
			// 更新界面
			handler.obtainMessage().sendToTarget();
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 设置当前页面
			mPager.setCurrentItem(currIndex);
			switch (currIndex) {
			case 0: {
				radiobutton1.setChecked(true);
				break;
			}
			case 1: {
				radiobutton2.setChecked(true);
				break;
			}
			case 2: {
				radiobutton3.setChecked(true);
				break;
			}
			case 3: {
				radiobutton4.setChecked(true);
				break;
			}

			}
		}
	};

	// end
	// -------------------------------------------------------------------------------------

	// -------------------------消息 viewPaper
	// head_2----------------------------------------------

	// -------------------------------------------------------------------------------------

	// -------------------------小喇叭head_3----------------------------------------------

	// -------------------------------------------------------------------------------------

	// -------------------------兼职信息head_4----------------------------------------------

	// -------------------------------------------------------------------------------------

	// -------------------------兼职列表list----------------------------------------------
	// 滚动内部类
	public class InnerOnScrollListener implements OnScrollListener {
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			int[] location = new int[2];
			view_head_5.getLocationInWindow(location);
			int x = location[0];
			int y = location[1];
			String l = "";
			l += y;
			if (y < 130 && y != 0) {
				rey.setVisibility(1);
			} else {
				rey.setVisibility(8);
				hide();
			}
			// view_head_3.setTop(R.id.news_list);
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub

		}
	}

	// end
	// -------------------------------------------------------------------------------------

	public void smoothToTop(){
		hideHead();
	}
	public void hideHead(){
		listView_dem.setSelection(7);
		rey.setVisibility(1);
	}
	private void InitWidth(View parentView) {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / num - bottomLineWidth) / 2;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	public void frush(){
		PartJobDao dao =new PartJobDao(context);
		news_list=dao.detail();
		
		for(PartJobBean p:news_list){
			System.out.println(p.getLastTime());
		}
		System.out.println("列表为长度"+news_list.size());
		listView_dem.setAdapter(adapter);
		adapter.notifyDataSetChanged();  
		dao.destory();
	}
	public class Intervalometer implements Runnable { // 定时器
		@Override
		public void run() {
				while (true) {
					if (isSend) {
					try {
						flushList();
						Thread.sleep(5000); // sleep 1000ms
					} catch (Exception e) {
					}
				    }else{
				    	break;
				    }
			}
		}
	}
	protected List<PartJobBean> JSONAnalysisMessage(String string) {
		List<PartJobBean> partjob =  new ArrayList<PartJobBean>();  
		try {
		JSONObject jsonObj = new JSONObject(string); 
		JSONArray resultJsonArray = jsonObj.getJSONArray("value");
        System.out.println(resultJsonArray.length());
        for(int i=0;i<resultJsonArray.length();i++){
        	JSONObject resultJsonObject= resultJsonArray.getJSONObject(i); 
        	PartJobBean p =new PartJobBean();
        	p.setLastTime(resultJsonObject.getString("lastTime"));
        	p.setLocation(resultJsonObject.getString("location"));
        	p.setPartTimeId(resultJsonObject.getString("partTimeId"));
        	System.out.println(p.getPartTimeId());
        	p.setPartTimeQualification(resultJsonObject.getString("partTimeQualification"));
        	p.setPhotoName(resultJsonObject.getString("photoName"));
        	p.setSalary(resultJsonObject.getString("salary"));
        	p.setTitle(resultJsonObject.getString("title"));
        	p.setWorkDate(resultJsonObject.getString("workDate"));
        	partjob.add(p);
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return partjob;
	}
	
	
	
}
