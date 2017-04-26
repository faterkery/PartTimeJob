package com.clv.parttimejobs.entity.my.resume;

public class UserMessageBean {

	private int userId;
	private String dateBirth;
	private String email;
	private String gender;
	private String height;
	private String user_name;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public UserMessageBean() {
		super();
	}
	public UserMessageBean(int userId, String dateBirth, String email,
			String gender, String height,  String user_name) {
		super();
		this.userId = userId;
		this.dateBirth = dateBirth;
		this.email = email;
		this.gender = gender;
		this.height = height;
		this.user_name = user_name;
	}
	@Override
	public String toString() {
		return "UserMessageBean [userId=" + userId + ", dateBirth=" + dateBirth
				+ ", email=" + email + ", gender=" + gender + ", height="
				+ height + ",  user_name=" + user_name + "]";
	}
	
	
	
}
