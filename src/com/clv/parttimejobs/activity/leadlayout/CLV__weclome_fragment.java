package com.clv.parttimejobs.activity.leadlayout;

import java.util.ArrayList;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.mainlayout.MainActivity;
import com.clv.parttimejobs.view.adapter.ViewPagerAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;


/**
 * @author yangyu
 *	功能描述：主程序入口类
 */
public class CLV__weclome_fragment extends Activity implements OnClickListener,OnPageChangeListener {
	//定义ViewPager对象
	private ViewPager viewPager;
	
	//定义ViewPager适配器
	private ViewPagerAdapter vpAdapter;
	
	//定义一个ArrayList来存放View
	private ArrayList<View> views = new ArrayList<View>();;

	//引导图片资源
    private static final int[] pics = {R.drawable.weclome1,R.drawable.weclome2,R.drawable.weclome3,R.drawable.weclome4};
    
    //底部小点的图片
    private RadioButton[] points =new RadioButton[4];
    private ImageButton gotoLandButton;
    //记录当前选中位置
    private int currentIndex=0;
    
    private int screenWdith ;
    private int screenHeight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clv__weclome_fragment);
		initView();
		initData();	
		
	}

	/**
	 * 初始化组件
	 */
	private void initView(){
		WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);

		screenWdith = wm.getDefaultDisplay().getWidth();
		screenHeight = wm.getDefaultDisplay().getHeight();
		//实例化ArrayList对象
		
		
		//实例化ViewPager
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		
		//初始化底部小点
		points[0] =(RadioButton)findViewById(R.id.radiobutton_wecl1);
		points[1] =(RadioButton)findViewById(R.id.radiobutton_wecl2);
		points[2] =(RadioButton)findViewById(R.id.radiobutton_wecl3);
		points[3] =(RadioButton)findViewById(R.id.radiobutton_wecl4);
		//实例化ViewPager适配器
		gotoLandButton =(ImageButton) findViewById(R.id.gotoland);
		InnerOnClickListener i =new InnerOnClickListener();
		gotoLandButton.setOnClickListener(i);
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		//定义一个布局并设置参数
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
																  LinearLayout.LayoutParams.MATCH_PARENT);
       
	//初始化引导图片列表
	for(int i=0; i<pics.length; i++) {
	    ImageView iv = new ImageView(this);
	    iv.setLayoutParams(mParams);
	    
	    Bitmap bm = BitmapFactory.decodeResource( getResources(),pics[i]);
	    bm  = Bitmap.createScaledBitmap(bm, screenWdith, screenHeight, false);
	    iv.setBackgroundResource(pics[i]);
//	    iv.setImageResource(pics[i]);
	    views.add(iv);
	} 
	vpAdapter = new ViewPagerAdapter(views);
	//设置数据
	viewPager.setAdapter(vpAdapter);
	//设置监听
	viewPager.setOnPageChangeListener(this);
	
	
	}
	private class InnerOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.gotoland:{
				CLV__weclome_fragment.this.finish();
				Intent intent =new Intent(CLV__weclome_fragment.this,MainActivity.class);
				startActivity(intent);
				break;
			}
			}
		}
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
	
	/**
	 * 当当前页面被滑动时调用
	 */

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}
	
	/**
	 * 当新的页面被选中时调用
	 */

	@Override
	public void onPageSelected(int position) {
		//设置底部小点选中状态
		if(position==3){
			gotoLandButton.setVisibility(View.VISIBLE);
		}else{
			gotoLandButton.setVisibility(View.INVISIBLE);
		}
	    setCurDot(position);
	}

	/**
	 * 通过点击事件来切换当前的页面
	 */
	@Override
	public void onClick(View v) {
		 int position = (Integer)v.getTag();
	 setCurView(position);
	 setCurDot(position);		
	}

	/**
	 * 设置当前页面的位置
	 */
	private void setCurView(int position){
	 if (position < 0 || position >= pics.length) {
	     return;
	 }
	 if(position==3){
		 
	 }
	 viewPager.setCurrentItem(position);
     }
//
     /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon){
	 if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
	     return;
	 }
	 points[positon].setChecked(true);
	 points[currentIndex].setChecked(false);

	 currentIndex = positon;
     }
	
}