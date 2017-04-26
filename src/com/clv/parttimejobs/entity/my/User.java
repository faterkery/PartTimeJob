package com.clv.parttimejobs.entity.my;

public class User {

	private String headPortraitPath;
	private String security_key;
	private long user_id;
	private String user_name;
	private String user_phoneNo;
	public String getHeadPortraitPath() {
		return headPortraitPath;
	}
	public void setHeadPortraitPath(String headPortraitPath) {
		this.headPortraitPath = headPortraitPath;
	}
	public String getSecurity_key() {
		return security_key;
	}
	public void setSecurity_key(String security_key) {
		this.security_key = security_key;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phoneNo() {
		return user_phoneNo;
	}
	public void setUser_phoneNo(String user_phoneNo) {
		this.user_phoneNo = user_phoneNo;
	}
	
	public User() {
		super();
	}
	public User(String headPortraitPath, String security_key, long user_id,
			String user_name, String user_phoneNo) {
		super();
		this.headPortraitPath = headPortraitPath;
		this.security_key = security_key;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_phoneNo = user_phoneNo;
	}


}
