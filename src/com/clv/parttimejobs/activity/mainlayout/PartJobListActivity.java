package com.clv.parttimejobs.activity.mainlayout;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.parttimejob.PartJobDao;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshListView;
import com.clv.parttimejobs.thirdparty.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonEcute;
import com.clv.parttimejobs.view.adapter.consult.News_list_adapter;
import com.clv.parttimejobs.view.ui.customview.ExpandView_jiesuan;
import com.clv.parttimejobs.view.ui.customview.ExpandView_paixu;
import com.clv.parttimejobs.view.ui.customview.ExpandView_shijian;
import com.clv.parttimejobs.view.ui.customview.ExpandView_zhonglei;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class PartJobListActivity extends Activity {

	private Context context;// 主context
	private Resources resources;
	private PullToRefreshListView listView;// listview
	private List<PartJobBean> news_list;// listview 数据list
	private News_list_adapter adapter;// listview 设配器
	private ListView listView_dem;

	private ImageButton returnButton;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partjoblist_layout);
		initView();
		addListener();
		initdata();
		initListViewdata();
		new GetDataTask().execute();
	}

	public void initView() {
		context = this;
		returnButton = (ImageButton) findViewById(R.id.mycreadit_return_button);
		listView = (PullToRefreshListView) findViewById(R.id.news_list);

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
				new GetDataTask().execute();
			}
		});
		listView_dem = listView.getRefreshableView();
		listView_dem.setVerticalScrollBarEnabled(false);// 隐藏进度条
		listView_dem.setDividerHeight(0);
		msearch_time_imageview = (ImageView) findViewById(R.id.search_time_imageview);
		msearch_lei_imageview = (ImageView) findViewById(R.id.search_lei_imageview);
		msearch_jiesuan_imageview = (ImageView) findViewById(R.id.search_jiesuan_imageview);
		msearch_paixu_imageview = (ImageView) findViewById(R.id.search_paixu_imageview);
		search_lei_radiobutton = (Button) findViewById(R.id.search_lei);
		search_time_radiobutton = (Button) findViewById(R.id.search_time);
		search_jiesuan_radiobutton = (Button) findViewById(R.id.search_jiesuan);
		search_paixu_radiobutton = (Button) findViewById(R.id.search_paixu);
		mExpandView_jiesuan = (ExpandView_jiesuan) findViewById(R.id.expandView_jiesuan);
		mExpandView_paixu = (ExpandView_paixu) findViewById(R.id.expandView_paixu);
		mExpandView_shijian = (ExpandView_shijian) findViewById(R.id.expandView_shijian);
		mExpandView_zhonglei = (ExpandView_zhonglei) findViewById(R.id.expandView_zhonglei);
	}

	public void addListener() {
		// 点击事件
		InnerOnClickListener i = new InnerOnClickListener();
		search_lei_radiobutton.setOnClickListener(i);
		search_time_radiobutton.setOnClickListener(i);
		search_jiesuan_radiobutton.setOnClickListener(i);
		search_paixu_radiobutton.setOnClickListener(i);
		returnButton.setOnClickListener(i);
	}

	public void initdata() {
		resources = context.getResources();
		news_list = new ArrayList<PartJobBean>();
		listView.setVerticalScrollBarEnabled(false);// 隐藏进度条
		adapter = new News_list_adapter(this, news_list);
		listView.setAdapter(adapter);

	}

	public void initListViewdata() {
		PartJobDao dao = new PartJobDao(context);
		List<PartJobBean> list = dao.detail();
		for (PartJobBean p : list) {
			if (!isExiste(p, news_list)) {
				news_list.add(p);
			}
		}
		adapter.notifyDataSetChanged();
		dao.destory();
	}

	public boolean isExiste(PartJobBean p, List<PartJobBean> list) {
		boolean isExist = false;
		for (PartJobBean j : list) {
			if (p.getPartTimeId().equals(j.getPartTimeId())) {
				isExist = true;
			}
		}
		return isExist;
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
			case R.id.mycreadit_return_button: {
				PartJobListActivity.this.finish();
				break;
			}
			}
		}

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

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String phone = "";
		String password = "";
		String line = "";
		StringBuilder response;

		@Override
		protected String[] doInBackground(Void... params) {

			String[] string =new String[1];
			try {
				UriFactory factory =new UriFactory();
				URL url = new URL(
						factory.getPartJobUrl());
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(8000);
				connection.setReadTimeout(8000);
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				PartJobDao partjobdao = new PartJobDao(context);
				String lasttime = getLastTime();
				System.out.println(lasttime);
				String a = "lastTime=" + URLEncoder.encode(lasttime, "UTF-8");
				out.writeBytes(a);
				out.flush();
				InputStream in = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response.toString().trim());
				String msg = new JsonEcute().JSONAnalysis(response.toString()
						.trim());
				String wrong = "";
				if ("success".equals(msg)) {
					List<PartJobBean> list = JSONAnalysisMessage(response
							.toString().trim());
					PartJobDao dao = new PartJobDao(context);
					for (PartJobBean p : list) {
						if (dao.isPartJobExistsAndisLate(p)) {
							dao.updata(p);
						} else {
							dao.insert(p);
						}
					}
					string[0]="success";
					dao.destory();
				} else if ("fail".equals(msg)) {
					wrong = new JsonEcute().JSONAnalysisWrong(response
							.toString().trim());
					string[0]="wrong";
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (connection != null) {
					connection.disconnect();
				}

			}
			return string;
		}

		@Override
		protected void onPostExecute(String[] result) {

			if(result[0].equals("success")){
				PartJobDao dao =new PartJobDao(context);
				List<PartJobBean> list =dao.detail();
				for(PartJobBean p:list)
				{
					if(!isExiste(p,news_list)){
						news_list.add(p);
					}
				}
				dao.destory();
				adapter.notifyDataSetChanged();
			}
			  
			listView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

	protected List<PartJobBean> JSONAnalysisMessage(String string) {
		List<PartJobBean> partjob = new ArrayList<PartJobBean>();
		try {
			JSONObject jsonObj = new JSONObject(string);
			JSONArray resultJsonArray = jsonObj.getJSONArray("value");
			System.out.println(resultJsonArray.length());
			for (int i = 0; i < resultJsonArray.length(); i++) {
				JSONObject resultJsonObject = resultJsonArray.getJSONObject(i);
				PartJobBean p = new PartJobBean();
				p.setLastTime(resultJsonObject.getString("lastTime"));
				p.setLocation(resultJsonObject.getString("location"));
				p.setPartTimeId(resultJsonObject.getString("partTimeId"));
				System.out.println(p.getPartTimeId());
				p.setPartTimeQualification(resultJsonObject
						.getString("partTimeQualification"));
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

	public String getLastTime() {
		String time = "2017-2-22 22:12:20";
		PartJobDao partjobdao = new PartJobDao(context);
		List<PartJobBean> l = partjobdao.detail();
		if (l.size() == 0) {
			return time;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String timetemp = "";
			timetemp = l.get(0).getLastTime();
			for (PartJobBean p : l) {
				java.util.Date d1;
				try {
					d1 = df.parse(timetemp);
					java.util.Date d2 = df.parse(p.getLastTime());
					if (d1.getTime() < d2.getTime()) {
						timetemp = p.getLastTime();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			time = timetemp;
		}
		return time;
	}

}
