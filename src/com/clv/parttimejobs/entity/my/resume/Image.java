package com.clv.parttimejobs.entity.my.resume;

import android.graphics.Bitmap;

public class Image {

	private long id;
	private String path; // _data
	private int size;
	private int width;
	private int height;
	private Bitmap bitmap;
	//是否选择标志�?
	private boolean isSelect;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", path=" + path + ", size=" + size + ", width=" + width + ", height=" + height
				+ "]";
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}


	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	
	public Image() {
		super();
	}

	public Image(long id, String path, int size, int width, int height,
			Bitmap bitmap, boolean isSelect) {
		super();
		this.id = id;
		this.path = path;
		this.size = size;
		this.width = width;
		this.height = height;
		this.bitmap = bitmap;
		this.isSelect = isSelect;
	}






	
}
