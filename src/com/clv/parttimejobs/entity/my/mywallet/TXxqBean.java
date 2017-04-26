package com.clv.parttimejobs.entity.my.mywallet;

public class TXxqBean {

	private String time1;//付款
	private String time2;//处理中
	private String time3;//成功到账
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getTime3() {
		return time3;
	}
	public void setTime3(String time3) {
		this.time3 = time3;
	}
	
	public TXxqBean() {
		super();
	}
	public TXxqBean(String time1, String time2, String time3) {
		super();
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
	}
	
	
}
