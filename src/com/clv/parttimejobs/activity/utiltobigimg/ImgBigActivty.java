package com.clv.parttimejobs.activity.utiltobigimg;

import com.clv.homework.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ImgBigActivty extends Activity {

	Intent intent;
	private DisplayImageOptions options;
	private ImageButton bigimg_imgbutton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_img_big_activty);
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.nologin_headimage) // 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.nologin_headimage) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.nologin_headimage) // 设置图片加载或解码过程中发生错误显示的图片
		.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
		.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
		.build(); // 创建配置过得DisplayImageOption对象
		
		bigimg_imgbutton =(ImageButton)findViewById(R.id.bigimg_imgbutton);
		
		intent =this.getIntent();
		String url=intent.getStringExtra("url");
		
		
		ImageLoader.getInstance().displayImage(url, bigimg_imgbutton, options);
		bigimg_imgbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImgBigActivty.this.finish();
			}
		});
	}

}
