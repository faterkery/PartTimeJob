package com.clv.parttimejobs.activity.mainlayout.jobxq.gsxq;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.utiltobigimg.ImgBigActivty;
import com.clv.parttimejobs.entity.consult.job.gs.Image;
import com.clv.parttimejobs.model.interfacebackage.mainlayout.jobxq.gsxq.GongsiimgInterfer;
import com.clv.parttimejobs.view.adapter.consult.job.gs.ImageAdapter;

public class gongsiimg_activity extends Activity implements GongsiimgInterfer{
	private Context content;
	private GridView gvImages;
	public List<Image> images;
	private ImageAdapter imageAdapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gongsixq_img_layout);
		
		content=this;
		gvImages = (GridView) findViewById(R.id.gsxq_img_gridview01);
		images = new ArrayList<Image>();
		imageAdapter = new ImageAdapter(content, images,this);
		gvImages.setAdapter(imageAdapter);
		
	}
	@Override
	public void imgToBig(String url) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(content,ImgBigActivty.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}
}
