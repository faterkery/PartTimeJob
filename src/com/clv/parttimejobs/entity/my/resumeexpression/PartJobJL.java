package com.clv.parttimejobs.entity.my.resumeexpression;

public class PartJobJL {

	private String year;
	private String month;
	private String day;
	private String job_name;
	private String job_people;
	private String job_way;
	private String job_money;
	private String job_imageurl;

	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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
	public String getJob_money() {
		return job_money;
	}
	public void setJob_money(String job_money) {
		this.job_money = job_money;
	}
	public String getJob_imageurl() {
		return job_imageurl;
	}
	public void setJob_imageurl(String job_imageurl) {
		this.job_imageurl = job_imageurl;
	}
	public PartJobJL(String year, String month, String day, String job_name,
			String job_people, String job_way, String job_money,
			String job_imageurl) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.job_name = job_name;
		this.job_people = job_people;
		this.job_way = job_way;
		this.job_money = job_money;
		this.job_imageurl = job_imageurl;
	}
	
	
}
