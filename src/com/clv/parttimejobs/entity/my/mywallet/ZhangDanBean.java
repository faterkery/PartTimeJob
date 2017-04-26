package com.clv.parttimejobs.entity.my.mywallet;

public class ZhangDanBean {

	private String name;//账单名称
	private String timeDate;//时间日期
	private String timeJT;//时分秒
	private String money;//账单金额
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(String timeDate) {
		this.timeDate = timeDate;
	}
	public String getTimeJT() {
		return timeJT;
	}
	public void setTimeJT(String timeJT) {
		this.timeJT = timeJT;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public ZhangDanBean() {
		super();
	}
	public ZhangDanBean(String name, String timeDate, String timeJT,
			String money) {
		super();
		this.name = name;
		this.timeDate = timeDate;
		this.timeJT = timeJT;
		this.money = money;
	}
	
	
}
