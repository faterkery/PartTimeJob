package com.clv.parttimejobs.entity.my.resume;

import java.io.Serializable;

public class RZMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String user_name;
	private String gender;
	private String idNum;//身份证号
	private int result;//认证结果
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
	public RZMessage() {
		super();
	}
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public RZMessage(int userId, String user_name, String gender, String idNum,
			int result) {
		super();
		this.userId = userId;
		this.user_name = user_name;
		this.gender = gender;
		this.idNum = idNum;
		this.result = result;
	}
	@Override
	public String toString() {
		return "RZMessage [userId=" + userId + ", user_name=" + user_name
				+ ", gender=" + gender + ", idNum=" + idNum + ", result="
				+ result + "]";
	}

	
	
}
