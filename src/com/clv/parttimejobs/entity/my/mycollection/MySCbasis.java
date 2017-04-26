package com.clv.parttimejobs.entity.my.mycollection;
public class MySCbasis {

	private String imgUrl;
	private String job_name;
	private String job_people;
	private String job_way;
	private String job_address;
	private String job_datetime;
	private boolean isSC;
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getJob_people() {
		return job_people;
	}
	public void setJob_people(String job_people) {
		this.job_people = job_people;
	}
	public String getJob_way() {
		return job_way;
	}
	public void setJob_way(String job_way) {
		this.job_way = job_way;
	}
	public String getJob_address() {
		return job_address;
	}
	public void setJob_address(String job_address) {
		this.job_address = job_address;
	}
	public String getJob_datetime() {
		return job_datetime;
	}
	public void setJob_datetime(String job_datetime) {
		this.job_datetime = job_datetime;
	}
	public boolean isSC() {
		return isSC;
	}
	public void setSC(boolean isSC) {
		this.isSC = isSC;
	}
	public MySCbasis(String imgUrl, String job_name, String job_people,
			String job_way, String job_address, String job_datetime,
			boolean isSC) {
		super();
		this.imgUrl = imgUrl;
		this.job_name = job_name;
		this.job_people = job_people;
		this.job_way = job_way;
		this.job_address = job_address;
		this.job_datetime = job_datetime;
		this.isSC = isSC;
	}
	
	
}
