package com.clv.parttimejobs.entity.message.person;

public class MessageSend {

	private String name;//姓名
	private String headimg_url;//头像地址
	private String textcontext;//字
	private int istext;//是字
	private String musicUrl;//语音地址
	private int isMy;//是自己发的
	private float time;//时间
	private String datetime;
	private boolean isRead ;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getTextcontext() {
		return textcontext;
	}
	public void setTextcontext(String textcontext) {
		this.textcontext = textcontext;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public int getIsMy() {
		return isMy;
	}
	public void setIsMy(int isMy) {
		this.isMy = isMy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadimg_url() {
		return headimg_url;
	}
	public void setHeadimg_url(String headimg_url) {
		this.headimg_url = headimg_url;
	}
	public int getIstext() {
		return istext;
	}
	public void setIstext(int istext) {
		this.istext = istext;
	}
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public MessageSend(String name, String headimg_url, String textcontext,
			int istext, String musicUrl, int isMy, float time, String datetime,
			boolean isRead) {
		super();
		this.name = name;
		this.headimg_url = headimg_url;
		this.textcontext = textcontext;
		this.istext = istext;
		this.musicUrl = musicUrl;
		this.isMy = isMy;
		this.time = time;
		this.datetime = datetime;
		this.isRead = isRead;
	}
	
	
}
