package com.clv.parttimejobs.entity.consult.job.gs.comment;

import java.util.List;

public class PinglunBasis {

	private String imgUrl;
	private String name;
	private String date;
	private String context;
	private List imgUrlArray;
	private String youlanCount;
	private String youyongCount;
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getYoulanCount() {
		return youlanCount;
	}
	public void setYoulanCount(String youlanCount) {
		this.youlanCount = youlanCount;
	}
	public String getYouyongCount() {
		return youyongCount;
	}
	public void setYouyongCount(String youyongCount) {
		this.youyongCount = youyongCount;
	}
	public List getImgUrlArray() {
		return imgUrlArray;
	}
	public void setImgUrlArray(List imgUrlArray) {
		this.imgUrlArray = imgUrlArray;
	}
	public PinglunBasis(String imgUrl, String name, String date,
			String context, List imgUrlArray, String youlanCount,
			String youyongCount) {
		super();
		this.imgUrl = imgUrl;
		this.name = name;
		this.date = date;
		this.context = context;
		this.imgUrlArray = imgUrlArray;
		this.youlanCount = youlanCount;
		this.youyongCount = youyongCount;
	}
	
}
