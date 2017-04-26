package com.clv.parttimejobs.entity.consult.job;

public class CheckAnsweerBean {

	private String connect;
	private boolean isChecked;
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	public CheckAnsweerBean() {
		super();
	}
	public CheckAnsweerBean(String connect, boolean isChecked) {
		super();
		this.connect = connect;
		this.isChecked = isChecked;
	}
	
}
