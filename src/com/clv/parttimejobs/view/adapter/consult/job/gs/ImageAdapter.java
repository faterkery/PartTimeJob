package com.clv.parttimejobs.view.adapter.consult.job.gs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.utiltobigimg.ImgBigActivty;
import com.clv.parttimejobs.entity.consult.job.gs.Image;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.jobxq.gsxq.GongsiimgInterfer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private List<Image> data;
	private DisplayImageOptions options;
	private GongsiimgInterfer interfer;

	public ImageAdapter(Context context, List<Image> data,GongsiimgInterfer interfer) {
		super();
		this.context = context;
		this.data=new ArrayList<Image>();
		this.data =data;
		this.interfer=interfer;
		
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.nologin_headimage) // 设置图片下载期间显示的图�?
		.showImageForEmptyUri(R.drawable.nologin_headimage) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.nologin_headimage) // 设置图片加载或解码过程中发生错误显示的图�?
		.cacheInMemory(true) // 设置下载的图片是否缓存在内存�?
		.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图�?
		.build(); // 创建配置过得DisplayImageOption对象
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Image getItem(int poisiton) {
		// TODO Auto-generated method stub
		return data.get(poisiton);
	}

	@Override
	public long getItemId(int poisiton) {
		// TODO Auto-generated method stub
		return poisiton;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewpartents) {
		// TODO Auto-generated method stub
		// 1. 获取数据
		final Image image = data.get(position);

		// 2. 判断convertView是否为null，并获取ViewHolder对象
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.list_item_image_gongsixq, null);
			holder = new ViewHolder();
			holder.ivImage = (ImageView) convertView
					.findViewById(R.id.iv_image_item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 3. 显示
		holder.ivImage.setImageResource(R.drawable.ic_launcher);
		// �?查该控件是否有匹配的任务
		ImageLoader.getInstance().displayImage(image.getPath(), holder.ivImage, options);
		holder.ivImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				interfer.imgToBig(image.getPath());
			}
		});
		return convertView;
	}
	private static final int MAX_SIZE = 45;
	class ViewHolder {
		ImageView ivImage;
	}
}
