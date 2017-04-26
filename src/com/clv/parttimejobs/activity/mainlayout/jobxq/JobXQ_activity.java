package com.clv.parttimejobs.activity.mainlayout.jobxq;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.mainlayout.jobxq.gsxq.GongSi_activity;
import com.clv.parttimejobs.dao.parttimejob.DetailPartJobInformationDao;
import com.clv.parttimejobs.dao.parttimejob.PartJobDao;
import com.clv.parttimejobs.dao.parttimejob.problemansweer.JobAnsweerDao;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.consult.DatailJobBean;
import com.clv.parttimejobs.entity.consult.PartJobBean;
import com.clv.parttimejobs.entity.consult.job.JobDescriptionBean;
import com.clv.parttimejobs.entity.consult.job.ProblemBean;
import com.clv.parttimejobs.entity.consult.job.gs.BaoMingAnsweerBean;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.model.callback.JobBaoMingProblem;
import com.clv.parttimejobs.model.callback.JobBaoMingReturn;
import com.clv.parttimejobs.model.callback.JobsListCallback;
import com.clv.parttimejobs.model.callback.JobsListInformationCallback;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.jobxq.ChangeViewPaperListener;
import com.clv.parttimejobs.model.modelbackage.JobBaoMingModel;
import com.clv.parttimejobs.model.modelbackage.PartJobsModel;
import com.clv.parttimejobs.util.Factory.UriFactory;
import com.clv.parttimejobs.util.ecutejson.JsonEcute;
import com.clv.parttimejobs.util.encrypt.MarkKey;
import com.clv.parttimejobs.view.adapter.consult.job.GuidePageAdapter;
import com.clv.parttimejobs.view.adapter.consult.job.JobMessageAdapter;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class JobXQ_activity extends Activity  implements ChangeViewPaperListener{

	private Context context;
	private PopupWindow popupWindow;
	private PopupWindow popupWindow2;
	private ScrollView scrollView;
	private ImageButton mycreadit_return_button;
	private ImageButton jzxq_baoming_button;
	private Button jzxq_button_togongsi;// 公司详情按钮
	private List<JobDescriptionBean> data;
	private JobMessageAdapter adapter;
	private List<String> answeer;

	private RelativeLayout bottomreyout;
	private RelativeLayout freshlsyout;
	private ScrollView reyscroll_view;
	private ImageView mImageFreshView;// 刷新�?
	private ImageView mImageView;// 大图
	private ImageView job_textview01;// 小图
	private TextView job_textview02;// 名字

	private TextView textpeople;// 人员
	private TextView textmonth;// 结算方式
	private TextView textmoney;// 工资
	private TextView textdate;// 时间�?
	private TextView jzrq_textview;// 截止日期
	private TextView textplaece;// 地点

	private TextView gzjj_textview;// 工作�?�?
	private TextView jbyq_textview;// 基本要求
	private TextView xcsm_textview;// 薪酬说明
	private TextView tsxq_textview;// 特殊�?�?

	private TextView xqcount;// �?求人�?
	private TextView ybcount;// 已报人数
	private String imgurl = "http://images.haidaojobs.cn/parttimePhoto/";

	// 记录首次按下位置
	private float mFirstPosition = 0;
	// 是否正在放大
	private Boolean mScaling = false;
	private AnimationDrawable animationDrawable;

	private DisplayMetrics metric;

	private ViewPager viewPager;
	private GuidePageAdapter paperadapter;
	private List<Integer> pageViews;
	private View popupWindow_view;
	private ListView listview;

	public String partjob_id = "";// 兼职id存放
	private DisplayImageOptions options;
	private PartJobsModel partjobmodel;

	private List<ProblemBean> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jzxq_layout);
		init();
		partjobmodel = new PartJobsModel();
		initAnim();
		Intent intent = this.getIntent();
		partjob_id = intent.getStringExtra("partTimeId");
		initdata();

		metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		scrollView = (ScrollView) findViewById(R.id.parallax_scroll_view);
		scrollView.setVerticalScrollBarEnabled(false);

		// 监听滚动事件
		scrollView.setOnTouchListener(new OnTouchListener() {
			@SuppressLint({ "ClickableViewAccessibility", "NewApi" })
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mImageView
						.getLayoutParams();
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					// 手指离开后恢复图�??
					mScaling = false;
					replyImage();
					break;
				case MotionEvent.ACTION_MOVE:
					if (!mScaling) {
						if (scrollView.getScrollY() == 0) {
							mFirstPosition = event.getY();// 滚动到顶部时记录位置，否则正常返�??
						} else {
							break;
						}
					}
					int distance = (int) ((event.getY() - mFirstPosition) * 0.6); // 滚动距离乘以�??��系数
					if (distance < 0) { // 当前位置比记录位置要小，正常返回
						break;
					}

					// 处理放大
					mScaling = true;
					lp.width = metric.widthPixels + distance;
					lp.height = (metric.widthPixels + distance) * 9 / 16;
					mImageView.setLayoutParams(lp);
					return true; // 返回true表示已经完成触摸事件，不再处�??
				}
				return false;
			}
		});

	}

	public void init() {
		context = this;
		bottomreyout = (RelativeLayout) findViewById(R.id.partjob_bottom_rey09);
		freshlsyout = (RelativeLayout) findViewById(R.id.wait_fresh_layout);
		reyscroll_view = (ScrollView) findViewById(R.id.parallax_scroll_view);
		mImageFreshView = (ImageView) findViewById(R.id.parallax_imageview);
		listview = (ListView) findViewById(R.id.partjob_listview);
		listview.setFocusable(false);
		mycreadit_return_button = (ImageButton) findViewById(R.id.mycreadit_return_button);
		jzxq_baoming_button = (ImageButton) findViewById(R.id.jzxq_baoming_button);
		jzxq_button_togongsi = (Button) findViewById(R.id.jzxq_button_togongsi);
		mImageView = (ImageView) findViewById(R.id.headview);// 大图
		job_textview01 = (ImageView) findViewById(R.id.job_textview01);// 小图
		job_textview02 = (TextView) findViewById(R.id.job_textview02);// 名字
		textpeople = (TextView) findViewById(R.id.job_textview03);// 人员
		textmonth = (TextView) findViewById(R.id.job_textview_month);// 结算方式
		textmoney = (TextView) findViewById(R.id.job_textview04);// 工资
		textdate = (TextView) findViewById(R.id.textview_date);// 时间�?
		jzrq_textview = (TextView) findViewById(R.id.job_textview06);// 截止日期
		textplaece = (TextView) findViewById(R.id.partjob_textview09);// 地点

		gzjj_textview = (TextView) findViewById(R.id.partjob_textview13);// 工作�?�?
		jbyq_textview = (TextView) findViewById(R.id.partjob_textview16);// 基本要求

		xqcount = (TextView) findViewById(R.id.partjob_textview26);// �?求人�?
		ybcount = (TextView) findViewById(R.id.partjob_textview28);// 已报人数
		InnerOnCLickListener i = new InnerOnCLickListener();
		mycreadit_return_button.setOnClickListener(i);
		jzxq_baoming_button.setOnClickListener(i);
		jzxq_button_togongsi.setOnClickListener(i);
	}

	public void initAnim() {
		animationDrawable = (AnimationDrawable) this.getResources()
				.getDrawable(R.anim.anim_waittodata);
		mImageFreshView.setImageDrawable(animationDrawable);
		animationDrawable.start();
	}

	public void initdata() {
		data = new ArrayList<JobDescriptionBean>();
		answeer = new ArrayList<String>();
		adapter = new JobMessageAdapter(context, data);
		listview.setAdapter(adapter);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.jianzhi2) // 设置图片下载期间显示的图�?
				.showImageForEmptyUri(R.drawable.jianzhi2) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.jianzhi2) // 设置图片加载或解码过程中发生错误显示的图�?
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存�?
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图�?
				.build(); // 创建配置过得DisplayImageOption对象
		setLayout();
	}

	public void setLayout(){
		partjobmodel.loadPartJobsInforationList(context, partjob_id,
				new JobsListInformationCallback() {

					@Override
					public void onJobsInformationListLoaded(
							DatailJobBean jobbean) {
						// TODO Auto-generated method stub
						setLayoutView(jobbean);
						DetailPartJobInformationDao dao = new DetailPartJobInformationDao(
								context);
						dao.insert(jobbean);
						dao.destory();
					}
				});
	}
	private class InnerOnCLickListener implements OnClickListener {

		@Override
		public void onClick(final View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.mycreadit_return_button: {
				JobXQ_activity.this.finish();
				break;
			}
			case R.id.jzxq_baoming_button: {
				UserDao userdao =new UserDao(context);
				boolean isLogin=userdao.isdetailLogin();
				userdao.destory();
				if(isLogin){
				// 跳出界面
				createViewtoWait(v);
				partjobmodel.loadPartJobBaoMingList(context, partjob_id,
						new JobBaoMingProblem() {

							@Override
							public void onJobsProblemListLoaded(String values,
									List<ProblemBean> bean) {
								// TODO Auto-generated method stub
								popupWindow2.dismiss();
								backgroundAlpha(1f);
								if(("success").equals(values)){
								if (bean.size() > 0) {
									createView(v);
									setPopWindowLayout(bean);
								}else{
									//报名成功，商家没有设置问题
									setLayout();
								}}
								else if(("wrong").equals(values)){
									//失败
								}

							}
						});
				}
				else{
					Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
				}

				break;
			}
			case R.id.jzxq_button_togongsi: {
				Intent intent = new Intent(JobXQ_activity.this,
						GongSi_activity.class);
				startActivity(intent);
				break;
			}
			}
		}

	}

	// 报名确认界面
	private void createView(View v) {
		// 获取自定义布�?文件activity_popupwindow_left.xml的视�?
		popupWindow_view = this.getLayoutInflater().inflate(
				R.layout.popwindow_registration_confirm_layout, null, false);

		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		DisplayMetrics metrics = getMetrics(context);
		int width = getMetrics(context).widthPixels * 11 / 12;
		int height = getMetrics(context).heightPixels * 21 / 25;
		popupWindow = new PopupWindow(popupWindow_view, width, height, true);
		popupWindow.setFocusable(true);
		backgroundAlpha(0.7f);

		list = new ArrayList<ProblemBean>();
		viewPager = (ViewPager) popupWindow_view.findViewById(R.id.viewpager);
		paperadapter = new GuidePageAdapter(context, list,partjob_id,this);
		viewPager.setAdapter(paperadapter);
		//
		// 设置动画效果
		// popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 这里是位置显示方�?,在屏幕的左侧

		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

		popupWindow_view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				popupWindow = null;
				backgroundAlpha(1f);
				return true;
			}
		});
	}

	private void createViewtoWait(View v) {
		popupWindow_view = this.getLayoutInflater().inflate(
				R.layout.popwindow_registration_wait_layout, null, false);
		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		DisplayMetrics metrics = getMetrics(context);
		int width = getMetrics(context).widthPixels * 2 / 3;
		int height = getMetrics(context).heightPixels * 14 / 25;
		popupWindow2 = new PopupWindow(popupWindow_view, width, height, true);
		popupWindow2.setFocusable(true);
		backgroundAlpha(0.7f);
		popupWindow2.showAtLocation(v, Gravity.CENTER, 0, 0);
		ImageView imageview=(ImageView) popupWindow_view.findViewById(R.id.parallax_imageview);
		animationDrawable = (AnimationDrawable) this.getResources()
				.getDrawable(R.anim.anim_waittodata);
		imageview.setImageDrawable(animationDrawable);
		animationDrawable.start();
	}

	public void setPopWindowLayout(List<ProblemBean> bean1) {
		if (partjob_id.length() != 0) {
			DetailPartJobInformationDao dao = new DetailPartJobInformationDao(
					context);
			DatailJobBean bean = dao.detailPartJobMessage(partjob_id);
			TextView textname = (TextView) popupWindow_view
					.findViewById(R.id.popwin_confirm_textview01);
			TextView textmoney = (TextView) popupWindow_view
					.findViewById(R.id.popwin_confirm_textview02);
			textname.setText(bean.getTitle());
			textmoney.setText(bean.getSalary());
			setDefaultViewPaper(bean1);
			dao.destory();
		}
	}

	public DisplayMetrics getMetrics(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		manager.getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

	private void setDefaultViewPaper(List<ProblemBean> list2) {

		for (ProblemBean b : list2) {
			list.add(b);
		}
		paperadapter.notifyDataSetChanged();
	}

	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		this.getWindow().setAttributes(lp);
	}

	// 回弹动画 (使用了属性动�??
	@SuppressLint("NewApi")
	public void replyImage() {
		final ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mImageView
				.getLayoutParams();
		final float w = mImageView.getLayoutParams().width;// 图片当前宽度
		final float h = mImageView.getLayoutParams().height;// 图片当前高度
		final float newW = metric.widthPixels;// 图片原宽�??
		final float newH = metric.widthPixels * 9 / 16;// 图片原高�??

		// 设置动画
		ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F)
				.setDuration(200);

		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float cVal = (Float) animation.getAnimatedValue();
				lp.width = (int) (w - (w - newW) * cVal);
				lp.height = (int) (h - (h - newH) * cVal);
				mImageView.setLayoutParams(lp);
			}
		});
		anim.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 柔化效果(高斯模糊)(优化后比上面快三�??
	 * 
	 * @param bmp
	 * @return
	 */
	private Bitmap blurImageAmeliorate(Bitmap bmp) {
		long start = System.currentTimeMillis();
		// 高斯矩阵
		int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int delta = 16; // 值越小图片会越亮，越大则越暗

		int idx = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				idx = 0;
				for (int m = -1; m <= 1; m++) {
					for (int n = -1; n <= 1; n++) {
						pixColor = pixels[(i + m) * width + k + n];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);

						newR = newR + (int) (pixR * gauss[idx]);
						newG = newG + (int) (pixG * gauss[idx]);
						newB = newB + (int) (pixB * gauss[idx]);
						idx++;
					}
				}

				newR /= delta;
				newG /= delta;
				newB /= delta;

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));

				pixels[i * width + k] = Color.argb(255, newR, newG, newB);

				newR = 0;
				newG = 0;
				newB = 0;
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "used time=" + (end - start));
		return bitmap;
	}


	public void setLayoutView(DatailJobBean bean) {
		mImageFreshView.clearAnimation();
		bottomreyout.setVisibility(View.VISIBLE);
		freshlsyout.setVisibility(View.GONE);
		reyscroll_view.setVisibility(View.VISIBLE);
		ImageLoader.getInstance().displayImage(imgurl + bean.getPhotoName(),
				mImageView, options);
		ImageLoader.getInstance().displayImage(imgurl + bean.getPhotoName(),
				job_textview01, options);
		job_textview02.setText(bean.getTitle());
		switch (Integer.parseInt(bean.getType())) {
		case 1: {
			textpeople.setText("发传单");
			break;
		}
		case 2: {
			textpeople.setText("家教");
			break;
		}
		}
		textmonth.setText(bean.getSettlementMethod());
		textmoney.setText(bean.getSalary());
		textdate.setText(bean.getWorkDate());
		jzrq_textview.setText(bean.getDeadline());
		textplaece.setText(bean.getLocation());

		gzjj_textview.setText(bean.getJobDescription());

		System.out.println(bean.getClaim());
		String[] claim = toExcuteAray(bean.getClaim());
		String text = "";
		for (int i = 0; i < claim.length; i++) {
			text += (i + 1) + "." + claim[i].trim() + "\n";
		}
		jbyq_textview.setText(text);// 基本要求
		List<JobDescriptionBean> descriptionlist = bean.getDescription();
		for (JobDescriptionBean b : descriptionlist) {
			data.add(b);
		}
		// xcsm_textview;//薪酬说明
		xqcount.setText(bean.getNeedNumber() + "");
		ybcount.setText(bean.getNumberOfapplicants());

		UserDao user = new UserDao(context);
		User u = user.detailMessage();
		long id = u.getUser_id();
		user.destory();

		String type = bean.getPartTimeStatus();
		if (("101").equals(type)) {
			// 101：审核中

		}
		if (("102").equals(type)) {
			// 102：审核不通过

		} else if (("201").equals(type)) {
			// 201：审核通过/招募中
			changeByUser(id, bean);
		} else if (("202").equals(type)) {
			// 202：招募完成/等待兼职进行
			changeByUser(id, bean);
		} else if (("203").equals(type)) {
			// 203：工作中/兼职进行中
			// 这边要换图
		} else if (("301").equals(type)) {
			// 301：待结算

		} else if (("302").equals(type)) {
			// 302：结算结束

		} else if (("401").equals(type)) {
			// 401：已完结

		} else if (("402").equals(type)) {
			// 402：已撤销

		} else if (("403").equals(type)) {
			// 403：用户投诉中
		} else if (("405").equals(type)) {
			// 405: 投诉处理中
		} else if (("406").equals(type)) {
			// 406：投诉处理结束
		}
	}

	public void changeByUser(Long id, DatailJobBean bean) {
		if (id != 0) {
			String resgistrationtype = bean.getRegistrationType();
			if (("0").equals(resgistrationtype)) {
				// 0、未报名
				jzxq_baoming_button
						.setImageResource(R.drawable.myrwxq_bar_ljbm_nor);
			} else if (("1").equals(resgistrationtype)) {
				// 1、待审核
				jzxq_baoming_button
						.setImageResource(R.drawable.myrwxq_bar_ljbm_set_dsh);
			} else if (("2").equals(resgistrationtype)) {
				// 2、取消报名
				jzxq_baoming_button
						.setImageResource(R.drawable.myrwxq_bar_ljbm_nor);
			} else if (("3").equals(resgistrationtype)) {
				// 3、被录用

			} else if (("4").equals(resgistrationtype)) {
				// 4、被拒绝

			}
		} else {

		}
	}

	public String[] toExcuteAray(String m) {
		String[] a = m.split("\\*/");
		return a;
	}

	public void baoming(){
		JobAnsweerDao dao = new JobAnsweerDao(context);
		List<BaoMingAnsweerBean> answeerlist=dao.detail(partjob_id);
		dao.destory();
		Gson gson = new Gson();
		String json = gson.toJson(answeerlist);
		new JobBaoMingModel().loadPartJobBaoMingList(context, partjob_id, json, new JobBaoMingReturn() {
			
			@Override
			public void onJobMessageReturnVaule(String results) {
				// TODO Auto-generated method stub
				if ("success".equals(results)) {
					Toast.makeText(context, "报名成功", Toast.LENGTH_SHORT)
					.show();
				}
			}
		});
	}
	@Override
	public void changeView(int position) {
		// TODO Auto-generated method stub
		System.out.println("调转到"+position+1);
		viewPager.setCurrentItem(position+1);
	}


	@Override
	public void toBaoMing() {
		// TODO Auto-generated method stub
		popupWindow.dismiss();
		backgroundAlpha(1f);
		baoming();
	}



}
