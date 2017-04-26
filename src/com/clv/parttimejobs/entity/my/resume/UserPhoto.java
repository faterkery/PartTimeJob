package com.clv.parttimejobs.entity.my.resume;

public class UserPhoto {

	private long user_id;
	private String user_imgurl;
	private String user_imgname;
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_imgurl() {
		return user_imgurl;
	}
	public void setUser_imgurl(String user_imgurl) {
		this.user_imgurl = user_imgurl;
	}
	public String getUser_imgname() {
		return user_imgname;
	}
	public void setUser_imgname(String user_imgname) {
		this.user_imgname = user_imgname;
	}
	
	public UserPhoto() {
		super();
	}
	public UserPhoto(long user_id, String user_imgurl, String user_imgname) {
		super();
		this.user_id = user_id;
		this.user_imgurl = user_imgurl;
		this.user_imgname = user_imgname;
	}
	
	
}
