package com.clv.parttimejobs.view.ui.customview;

import com.clv.homework.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ExpandView_zhonglei extends FrameLayout {
	private Animation mCollapseAnimation;
	private Animation mExpandAnimation;
	private boolean mIsExpand;

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10;
	private Button button11;
	private Button button12;
	private Button button13;
	private Button button14;
	private Button button15;
	
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private ImageView imageview6;
	private ImageView imageview7;
	private ImageView imageview8;
	private ImageView imageview9;
	private ImageView imageview10;
	private ImageView imageview11;
	private ImageView imageview12;
	private ImageView imageview13;
	private ImageView imageview14;
	private ImageView imageview15;
	
	public ExpandView_zhonglei(Context paramContext) {
		this(paramContext, null);
	}

	public ExpandView_zhonglei(Context paramContext,
			AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public ExpandView_zhonglei(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		initExpandView();
	}

	private void initExpandView() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.news_rey_select_zhonglei, this, true);
		this.mExpandAnimation = AnimationUtils.loadAnimation(getContext(),
				R.anim.expand);
		this.mExpandAnimation
				.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationEnd(Animation paramAnonymousAnimation) {
						ExpandView_zhonglei.this.setVisibility(0);
					}

					public void onAnimationRepeat(
							Animation paramAnonymousAnimation) {
					}

					public void onAnimationStart(
							Animation paramAnonymousAnimation) {
					}
				});
		this.mCollapseAnimation = AnimationUtils.loadAnimation(getContext(),
				R.anim.collapse);
		this.mCollapseAnimation
				.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationEnd(Animation paramAnonymousAnimation) {
						ExpandView_zhonglei.this.setVisibility(4);
					}

					public void onAnimationRepeat(
							Animation paramAnonymousAnimation) {
					}

					public void onAnimationStart(
							Animation paramAnonymousAnimation) {
					}
				});
		initView();
	}

	private void initView(){
		InnerOnclickListener i =new InnerOnclickListener();
		button1 =(Button) findViewById(R.id.button__01);
		button2 =(Button) findViewById(R.id.button__02);
		button3 =(Button) findViewById(R.id.button__03);
		button4 =(Button) findViewById(R.id.button__04);
		button5 =(Button) findViewById(R.id.button__05);
		button6 =(Button) findViewById(R.id.button__06);
		button7 =(Button) findViewById(R.id.button__07);
		button8 =(Button) findViewById(R.id.button__08);
		button9 =(Button) findViewById(R.id.button__09);
		button10 =(Button) findViewById(R.id.button__10);
		button11 =(Button) findViewById(R.id.button__11);
		button12 =(Button) findViewById(R.id.button__12);
		button13 =(Button) findViewById(R.id.button__13);
		button14 =(Button) findViewById(R.id.button__14);
		button15 =(Button) findViewById(R.id.button__15);
		imageview1 = (ImageView) findViewById(R.id.img__01);
		imageview2 = (ImageView) findViewById(R.id.img__02);
		imageview3 = (ImageView) findViewById(R.id.img__03);
		imageview4 = (ImageView) findViewById(R.id.img__04);
		imageview5 = (ImageView) findViewById(R.id.img__05);
		imageview6 = (ImageView) findViewById(R.id.img__06);
		imageview7 = (ImageView) findViewById(R.id.img__07);
		imageview8 = (ImageView) findViewById(R.id.img__08);
		imageview9 = (ImageView) findViewById(R.id.img__09);
		imageview10 = (ImageView) findViewById(R.id.img__10);
		imageview11 = (ImageView) findViewById(R.id.img__11);
		imageview12 = (ImageView) findViewById(R.id.img__12);
		imageview13 = (ImageView) findViewById(R.id.img__13);
		imageview14 = (ImageView) findViewById(R.id.img__14);
		imageview15 = (ImageView) findViewById(R.id.img__15);
		button1.setOnClickListener(i);
		button2.setOnClickListener(i);
		button3.setOnClickListener(i);
		button4.setOnClickListener(i);
		button5.setOnClickListener(i);
		button6.setOnClickListener(i);
		button7.setOnClickListener(i);
		button8.setOnClickListener(i);
		button9.setOnClickListener(i);
		button10.setOnClickListener(i);
		button11.setOnClickListener(i);
		button12.setOnClickListener(i);
		button13.setOnClickListener(i);
		button14.setOnClickListener(i);
		button15.setOnClickListener(i);
	}
	public void collapse() {
		if (this.mIsExpand) {
			this.mIsExpand = false;
			clearAnimation();
			startAnimation(this.mCollapseAnimation);
		}
	}

	public void expand() {
		if (!this.mIsExpand) {
			this.mIsExpand = true;
			clearAnimation();
			startAnimation(this.mExpandAnimation);
		}
	}

	public boolean isExpand() {
		return this.mIsExpand;
	}

	public void setContentView() {
		View localView = LayoutInflater.from(getContext()).inflate(
				R.layout.news_rey_select_jiesuan, null);
		removeAllViews();
		addView(localView);
	}
	public void hideImg(){
		imageview1.setVisibility(View.GONE);
		imageview2.setVisibility(View.GONE);
		imageview3.setVisibility(View.GONE);
		imageview4.setVisibility(View.GONE);
		imageview5.setVisibility(View.GONE);
		imageview6.setVisibility(View.GONE);
		imageview7.setVisibility(View.GONE);
		imageview8.setVisibility(View.GONE);
		imageview9.setVisibility(View.GONE);
		imageview10.setVisibility(View.GONE);
		imageview11.setVisibility(View.GONE);
		imageview12.setVisibility(View.GONE);
		imageview13.setVisibility(View.GONE);
		imageview14.setVisibility(View.GONE);
		imageview15.setVisibility(View.GONE);
	}
	private class InnerOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.button__01:{hideImg();
				imageview1.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__02:{hideImg();
				imageview2.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__03:{hideImg();
				imageview3.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__04:{hideImg();
				imageview4.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__05:{hideImg();
				imageview5.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__06:{hideImg();
				imageview6.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__07:{hideImg();
				imageview7.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__08:{hideImg();
				imageview8.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__09:{hideImg();
				imageview9.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__10:{hideImg();
				imageview10.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__11:{hideImg();
				imageview11.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__12:{hideImg();
				imageview12.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__13:{hideImg();
				imageview13.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__14:{hideImg();
				imageview14.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			case R.id.button__15:{hideImg();
				imageview15.setVisibility(View.VISIBLE);
				collapse();
				break;
			}
			}
		}
		
	}
}