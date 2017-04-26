package com.clv.parttimejobs.entity.consult.job.gs;

import android.graphics.Bitmap;

public class Image {
	private String path; // _data
	private Bitmap bitmap;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public Image(String path, Bitmap bitmap) {
		super();
		this.path = path;
		this.bitmap = bitmap;
	}
	

}
