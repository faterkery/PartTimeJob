package com.clv.parttimejobs.activity.minelayout.scheduling;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.clv.homework.R;
import com.clv.parttimejobs.entity.my.scheduling.Myrcbasis;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.scheduling.ShowPopwindow;
import com.clv.parttimejobs.view.adapter.my.scheduling.MyCalendarAdapter;
import com.clv.parttimejobs.view.ui.customview.WheelView;
import com.clv.parttimejobs.view.ui.customview.WheelView.OnWheelViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;

public class Xcapactivity extends Activity implements ShowPopwindow {

	private static final String[] HOURS = new String[] { "00", "01", "02",
			"03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
			"14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
	private static final String[] MINUTES = new String[60];
	private PopupWindow popupWindow;
	public static List<Myrcbasis> l = new ArrayList<Myrcbasis>();
	public static int select_lie;
	public static int select_hang;
	public static boolean isshow = false;
	public String time_hour="";
	public String time_minute="";
	
	private ListView listview1;
	private ListView listview2;
	private ListView listview3;
	private ListView listview4;
	private ListView listview5;
	private ListView listview6;
	private ListView listview7;
	private ScrollView mycalendarscroll;

	private MyCalendarAdapter m1;
	private MyCalendarAdapter m2;
	private MyCalendarAdapter m3;
	private MyCalendarAdapter m4;
	private MyCalendarAdapter m5;
	private MyCalendarAdapter m6;
	private MyCalendarAdapter m7;

	private boolean isRunning = true;
	private Button mycalendar_button_edit;
	private ImageButton xcap_return_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xcap_activity_main);
		init();
		inintfind();
		m1 = new MyCalendarAdapter(this, l, 1, this);
		m2 = new MyCalendarAdapter(this, l, 2, this);
		m3 = new MyCalendarAdapter(this, l, 3, this);
		m4 = new MyCalendarAdapter(this, l, 4, this);
		m5 = new MyCalendarAdapter(this, l, 5, this);
		m6 = new MyCalendarAdapter(this, l, 6, this);
		m7 = new MyCalendarAdapter(this, l, 7, this);
		listview1.setAdapter(m1);
		listview2.setAdapter(m2);
		listview3.setAdapter(m3);
		listview4.setAdapter(m4);
		listview5.setAdapter(m5);
		listview6.setAdapter(m6);
		listview7.setAdapter(m7);
		listview1.setVerticalScrollBarEnabled(false);
		listview2.setVerticalScrollBarEnabled(false);
		listview3.setVerticalScrollBarEnabled(false);
		listview4.setVerticalScrollBarEnabled(false);
		listview5.setVerticalScrollBarEnabled(false);
		listview6.setVerticalScrollBarEnabled(false);
		listview7.setVerticalScrollBarEnabled(false);
		mycalendarscroll.setVerticalScrollBarEnabled(false);
		InnerOnClickListener i = new InnerOnClickListener();
		mycalendar_button_edit.setOnClickListener(i);
		xcap_return_button.setOnClickListener(i);
	}

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.mycalendar_button_edit: {
				if (isshow) {
					isshow = false;
				} else {
					isshow = true;
				}
				flush();
				break;
			}
			case R.id.xcap_return_button:{
				Xcapactivity.this.finish();
				break;
			}
			}
		}

	}


	public void flush(){
		m1.notifyDataSetChanged();
		m2.notifyDataSetChanged();
		m3.notifyDataSetChanged();
		m4.notifyDataSetChanged();
		m5.notifyDataSetChanged();
		m6.notifyDataSetChanged();
		m7.notifyDataSetChanged();
	}
	public void init() {
		xcap_return_button =(ImageButton) findViewById(R.id.xcap_return_button);
		
		Myrcbasis money_1 = new Myrcbasis("15:20", "语文", 1, 1);
		Myrcbasis money_2 = new Myrcbasis("15:20", "数学", 1, 2);
		Myrcbasis money_3 = new Myrcbasis("15:20", "英语", 1, 4);
		Myrcbasis money_16 = new Myrcbasis("15:20", "体育", 1, 5);

		Myrcbasis money_4 = new Myrcbasis("15:20", "语文", 2, 1);
		Myrcbasis money_5 = new Myrcbasis("15:20", "数学", 2, 2);
		Myrcbasis money_6 = new Myrcbasis("15:20", "英语", 2, 3);

		Myrcbasis money_7 = new Myrcbasis("15:20", "语文", 3, 2);
		Myrcbasis money_8 = new Myrcbasis("15:20", "英语", 3, 3);
		Myrcbasis money_9 = new Myrcbasis("15:20", "体育", 3, 4);
		Myrcbasis money_17 = new Myrcbasis("15:20", "美术", 3, 5);

		Myrcbasis money_10 = new Myrcbasis("15:20", "语文", 4, 3);
		Myrcbasis money_11 = new Myrcbasis("15:20", "体育", 4, 4);

		Myrcbasis money_12 = new Myrcbasis("15:20", "英语", 5, 2);

		Myrcbasis money_13 = new Myrcbasis("15:20", "数学", 6, 1);

		Myrcbasis money_14 = new Myrcbasis("15:20", "数学", 7, 3);
		Myrcbasis money_15 = new Myrcbasis("15:20", "英语", 7, 4);
		l.add(money_1);
		l.add(money_2);
		l.add(money_3);
		l.add(money_4);
		l.add(money_5);
		l.add(money_6);
		l.add(money_7);
		l.add(money_8);
		l.add(money_9);
		l.add(money_10);
		l.add(money_11);
		l.add(money_12);
		l.add(money_13);
		l.add(money_14);
		l.add(money_15);
		l.add(money_16);
		l.add(money_17);
		mycalendar_button_edit = (Button) findViewById(R.id.mycalendar_button_edit);
	}

	public void inintfind() {
		listview1 = (ListView) findViewById(R.id.mycal_list1);
		listview2 = (ListView) findViewById(R.id.mycal_list2);
		listview3 = (ListView) findViewById(R.id.mycal_list3);
		listview4 = (ListView) findViewById(R.id.mycal_list4);
		listview5 = (ListView) findViewById(R.id.mycal_list5);
		listview6 = (ListView) findViewById(R.id.mycal_list6);
		listview7 = (ListView) findViewById(R.id.mycal_list7);
		mycalendarscroll = (ScrollView) findViewById(R.id.mycalendarscroll);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		isRunning = false;
	}

	public void createView(View v) {
		
		// ��ȡ�Զ��岼���ļ�activity_popupwindow_left.xml����ͼ
		View popupWindow_view = getLayoutInflater().inflate(
				R.layout.showpopwindow_layout, null, false);

		init2();
		WheelView wva = (WheelView) popupWindow_view.findViewById(R.id.main_wv);
		wva.setOffset(1);
		wva.setItems(Arrays.asList(HOURS));
		wva.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				time_hour=item.trim();
			}
		});

		WheelView wva2 = (WheelView) popupWindow_view
				.findViewById(R.id.wheelview2);
		wva2.setOffset(1);
		wva2.setItems(Arrays.asList(MINUTES));
		wva2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				time_minute=item.trim();
			}
		});
		final EditText editview =(EditText) popupWindow_view.findViewById(R.id.wheelview_edit01);
        ImageButton imagebutton=(ImageButton) popupWindow_view.findViewById(R.id.imagebutton01);
        imagebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
					backgroundAlpha(1f);
					flush();
				}
			}
		});
		popupWindow = new PopupWindow(popupWindow_view,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setFocusable(true);

		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
		backgroundAlpha(0.7f);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow_view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (popupWindow != null && popupWindow.isShowing()) {
					String time=time_hour+":"+time_minute;
					time=time.trim();
					String name=editview.getText().toString().trim();
					if(!time.equals(":"))
					add(time,name);
					flush();
					popupWindow.dismiss();
					popupWindow = null;
					backgroundAlpha(1f);
				}
				return true;
			}
		});
		
	}
	public void add(String time,String name){
		Myrcbasis myrcbasis = new Myrcbasis(time,name, select_hang, select_lie);
		l.add(myrcbasis);
	}
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getWindow().setAttributes(lp);
	}

	public void init2() {
		for (int i = 0; i < 60; i++) {
			if (i < 10) {
				MINUTES[i] = "0" + i;
			} else {
				MINUTES[i] = i + "";
			}
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		createView(this.getCurrentFocus());
	}

	@Override
	public void remove(int hang, int lie) {
		// TODO Auto-generated method stub
		for(Myrcbasis m:l){
			if((m.getLieno()-1)==lie&&m.getHangno()==hang){
				l.remove(m) ;
				switch(hang){
				case 1:{m1.notifyDataSetChanged();break;}
				case 2:{m2.notifyDataSetChanged();break;}
				case 3:{m3.notifyDataSetChanged();break;}
				case 4:{m4.notifyDataSetChanged();break;}
				case 5:{m5.notifyDataSetChanged();break;}
				case 6:{m6.notifyDataSetChanged();break;}
				case 7:{m7.notifyDataSetChanged();break;}
				}
				break;
			}
		}
	}
}
