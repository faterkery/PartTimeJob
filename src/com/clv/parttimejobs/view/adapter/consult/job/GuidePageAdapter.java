package com.clv.parttimejobs.view.adapter.consult.job;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clv.homework.R;
import com.clv.parttimejobs.dao.parttimejob.problemansweer.JobAnsweerDao;
import com.clv.parttimejobs.dao.user.UserDao;
import com.clv.parttimejobs.entity.consult.job.CheckAnsweerBean;
import com.clv.parttimejobs.entity.consult.job.ProblemBean;
import com.clv.parttimejobs.entity.consult.job.gs.BaoMingAnsweerBean;
import com.clv.parttimejobs.entity.my.User;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.jobxq.ChangeViewPaperListener;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.jobxq.gsxq.BaiMingListener;
import com.google.gson.Gson;

public class GuidePageAdapter extends PagerAdapter implements BaiMingListener {

	private Context context;
	private List<ProblemBean> list;
	private ChangeViewPaperListener listener;
	private List<List<CheckAnsweerBean>> lists = new ArrayList<List<CheckAnsweerBean>>();
	private CheckAdapter adapter;
	private List<CheckAdapter> adapterlsit = new ArrayList<CheckAdapter>();

	private String partTimeId="";//兼职id
	private String editString = "";
	private int temp;// 临时暂放位

	public GuidePageAdapter(Context context, List<ProblemBean> problemlist,String partTimeId,
			ChangeViewPaperListener listener) {
		super();
		this.context = context;
		this.list = problemlist;
		this.partTimeId=partTimeId;
		this.listener = listener;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View container, int position, Object arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int arg1) {
		// TODO Auto-generated method stub
		View convertView;
		final ViewHolder viewHolder = new ViewHolder();
		;
		//
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.questionfragment_layout, null);
		// rey01
		viewHolder.rey01_layout_topic01 = (RelativeLayout) convertView
				.findViewById(R.id.layout_topic01);
		viewHolder.rey01_question_textview = (TextView) convertView
				.findViewById(R.id.questionfragment_topic01_textview);
		viewHolder.rey01_edittext_answeer = (EditText) convertView
				.findViewById(R.id.questionfragment_topic01_edittext);
		viewHolder.rey01_next_layout = (RelativeLayout) convertView
				.findViewById(R.id.reyout__topic01_rey00);
		viewHolder.topic01_button = (Button) convertView
				.findViewById(R.id.topic01_button);
		viewHolder.rey01_reylayout_next = (RelativeLayout) convertView
				.findViewById(R.id.reyout_topic01);
		viewHolder.topic01_submit_button = (Button) convertView
				.findViewById(R.id.popwin_topic01_confirm_imageButton);
		// rey02
		viewHolder.rey02_layout_topic02 = (RelativeLayout) convertView
				.findViewById(R.id.layout_topic02);
		viewHolder.rey02_question_textview = (TextView) convertView
				.findViewById(R.id.questionfragment_topic02_textview);
		viewHolder.topic02_next_layout = (RelativeLayout) convertView
				.findViewById(R.id.reyout__topic02_rey00);
		viewHolder.topic02_listview = (ListView) convertView
				.findViewById(R.id.questionfragment_listview);
		viewHolder.topic02_button = (Button) convertView
				.findViewById(R.id.topic02_button_next);
		viewHolder.rey02_reylayout_next = (RelativeLayout) convertView
				.findViewById(R.id.reyout_topic02);
		viewHolder.topic02_submit_button = (Button) convertView
				.findViewById(R.id.popwin_topic02_confirm_imageButton);
		convertView.setTag(viewHolder);
		// }else{
		// viewHolder=(ViewHolder)convertView.getTag();
		// }
		ProblemBean bean = list.get(arg1);
		temp=arg1;
		JobAnsweerDao answeer =new JobAnsweerDao(context);
		UserDao userdao = new UserDao(context);
		User user = userdao.detailMessage();
		int userId = (int) user.getUser_id();
		userdao.destory();
		String tontent =answeer.detailAnsweerContent(userId+"", bean.getPartTimeId(), bean.getProblemId());
		answeer.destory();
		if (("1").equals(bean.getTopy())) {
			temp = arg1;
			handler.postDelayed(delayRun, 800);
			// 无问题，自主回答
			viewHolder.rey01_layout_topic01.setVisibility(View.VISIBLE);
			viewHolder.rey01_question_textview.setText(bean.getProblem_topic());
			adapterlsit.add(adapter);
			List<CheckAnsweerBean> listff = new ArrayList<CheckAnsweerBean>();
			lists.add(listff);
			
			viewHolder.rey01_edittext_answeer.setText(tontent);
			viewHolder.rey01_edittext_answeer
					.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence s, int start,
								int before, int count) {
							// TODO Auto-generated method stub

						}

						@Override
						public void beforeTextChanged(CharSequence s,
								int start, int count, int after) {
							// TODO Auto-generated method stub

						}

						@Override
						public void afterTextChanged(Editable s) {
							// TODO Auto-generated method stub
							if (delayRun != null) {
								// 每次editText有变化的时候，则移除上次发出的延迟线程
								handler.removeCallbacks(delayRun);
							}
							editString = s.toString();
							temp = arg1;
							// 延迟800ms，如果不再输入字符，则执行该线程的run方法
							handler.postDelayed(delayRun, 300);
						}
					});
			if (arg1 == (list.size() - 1)) {
				viewHolder.rey01_reylayout_next.setVisibility(View.VISIBLE);
				viewHolder.topic01_submit_button
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								listener.toBaoMing();
							}
						});
			} else {
				viewHolder.rey01_next_layout.setVisibility(View.VISIBLE);
				viewHolder.topic01_button
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								listener.changeView(arg1);
							}
						});
			}
		} else if (("2").equals(bean.getTopy())) {
			// 自由行回�?
			saveDataDaX(arg1, 0);
			viewHolder.rey02_layout_topic02.setVisibility(View.VISIBLE);
			viewHolder.rey02_question_textview.setText(bean.getProblem_topic());
			if (arg1 == (list.size() - 1)) {
				viewHolder.rey02_reylayout_next.setVisibility(View.VISIBLE);
				viewHolder.topic02_submit_button
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								listener.toBaoMing();
							}
						});
			} else {
				viewHolder.topic02_next_layout.setVisibility(View.VISIBLE);
				viewHolder.topic02_button
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								listener.changeView(arg1);
							}
						});
			}
			String string = bean.getProblem_content();
			lists.add(getList(string));
			if(tontent.length()!=0){
				for(CheckAnsweerBean p:lists.get(arg1)){
					p.setChecked(false);
				}
				lists.get(arg1).get(Integer.parseInt(tontent)).setChecked(true);
			}
			adapter = new CheckAdapter(context, lists.get(arg1), arg1, 0, this);
			adapterlsit.add(adapter);
			viewHolder.topic02_listview.setAdapter(adapterlsit.get(arg1));

		} else if (("3").equals(bean.getTopy())) {
			// 选择性回�?
			saveDataDuX(arg1, "");
			viewHolder.rey02_layout_topic02.setVisibility(View.VISIBLE);
			viewHolder.rey02_question_textview.setText(bean.getProblem_topic());
			if (arg1 == (list.size() - 1)) {
				viewHolder.rey02_reylayout_next.setVisibility(View.VISIBLE);
				viewHolder.topic02_submit_button
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								listener.toBaoMing();
							}
						});
			} else {
				viewHolder.topic02_next_layout.setVisibility(View.VISIBLE);
				viewHolder.topic02_submit_button
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								listener.changeView(arg1);
							}
						});
			}
			String string = bean.getProblem_content();
			lists.add(getList(string));
			adapter = new CheckAdapter(context, lists.get(arg1), arg1, 1, this);
			adapterlsit.add(adapter);
			viewHolder.topic02_listview.setAdapter(adapterlsit.get(arg1));
		}

		container.addView(convertView, 0);
		return convertView;
	}

	public List<CheckAnsweerBean> getList(String s) {
		List<CheckAnsweerBean> list = new ArrayList<CheckAnsweerBean>();
		String[] array = s.split("\\u002A/");
		for (String string : array) {
			list.add(new CheckAnsweerBean(string, false));
		}
		list.get(0).setChecked(true);
		return list;
	}

	private Handler handler = new Handler();

	/**
	 * 延迟线程，看是否还有下一个字符输入
	 */
	private Runnable delayRun = new Runnable() {

		@Override
		public void run() {

			// 执行保存操作
			ProblemBean bean = list.get(temp);
			UserDao userdao = new UserDao(context);
			User user = userdao.detailMessage();
			int userId = (int) user.getUser_id();
			userdao.destory();
			BaoMingAnsweerBean answeerbean = new BaoMingAnsweerBean(
					Integer.parseInt(bean.getProblemId()), editString, userId);
			JobAnsweerDao answeerdao = new JobAnsweerDao(context);
			answeerdao.insert(bean.getPartTimeId(),answeerbean);
			answeerdao.destory();
		}
	};

	class ViewHolder {
		TextView text_question;

		// rey01
		RelativeLayout rey01_layout_topic01;
		TextView rey01_question_textview;// 问题
		EditText rey01_edittext_answeer;// 答案
		RelativeLayout rey01_next_layout;
		Button topic01_button;
		RelativeLayout rey01_reylayout_next;
		Button topic01_submit_button;

		// rey02
		RelativeLayout rey02_layout_topic02;
		TextView rey02_question_textview;
		RelativeLayout topic02_next_layout;
		ListView topic02_listview;
		Button topic02_button;
		RelativeLayout rey02_reylayout_next;
		Button topic02_submit_button;
	}

	public String[] toExcuteAray(String m) {
		String[] a = m.split("\\*/");
		return a;
	}

	public interface CheckListener {

		public void show();

		public void noshow();
	}

	@Override
	public void finishUpdate(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void daselect(int viewpattern, int poisiton) {
		// TODO Auto-generated method stub
		// 单选
		for (CheckAnsweerBean bean : lists.get(viewpattern)) {
			bean.setChecked(false);
		}
		lists.get(viewpattern).get(poisiton).setChecked(true);
		adapterlsit.get(viewpattern).notifyDataSetChanged();
		saveDataDaX(viewpattern, poisiton);
	}

	@Override
	public void duselect(int viewpattern, int poisiton) {
		// TODO Auto-generated method stub

		// 多选
		if (lists.get(viewpattern).get(poisiton).isChecked()) {
			lists.get(viewpattern).get(poisiton).setChecked(false);
		} else {
			lists.get(viewpattern).get(poisiton).setChecked(true);
		}
		adapterlsit.get(viewpattern).notifyDataSetChanged();
		sendDX(viewpattern, poisiton);
	}

	public void sendDX(int viewpattern, int poisiton) {
		String[] xzx = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N" };
		String content = "";
		for (int i = 0; i < lists.get(viewpattern).size(); i++) {
			if (lists.get(viewpattern).get(i).isChecked()) {
				content += xzx[i] + "*/";
			}
		}
		saveDataDuX(viewpattern, content);
	}

	// 保存
	public void saveDataDaX(int position, int selectId) {
		// 单选
		ProblemBean bean = list.get(position);
		UserDao userdao = new UserDao(context);
		User user = userdao.detailMessage();
		int userId = (int) user.getUser_id();
		userdao.destory();
		BaoMingAnsweerBean answeerbean = new BaoMingAnsweerBean(
				Integer.parseInt(bean.getProblemId()), selectId + "", userId);
		JobAnsweerDao answeerdao = new JobAnsweerDao(context);
		answeerdao.insert(partTimeId,answeerbean);
		answeerdao.destory();
	}

	public void saveDataDuX(int position, String selectId) {
		// 多选
		ProblemBean bean = list.get(position);
		UserDao userdao = new UserDao(context);
		User user = userdao.detailMessage();
		int userId = (int) user.getUser_id();
		userdao.destory();
		BaoMingAnsweerBean answeerbean = new BaoMingAnsweerBean(
				Integer.parseInt(bean.getProblemId()), selectId + "", userId);
		JobAnsweerDao answeerdao = new JobAnsweerDao(context);
		answeerdao.insert(partTimeId,answeerbean);
		answeerdao.destory();
	}
}