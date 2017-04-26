package com.clv.parttimejobs.entity.my.mywallet;


public class TXjdBean {

	private int ztNO;//银行标志0  1  2
	private int ztZT;//状态 0 1 
	private String money;//金额
	private TXxqBean bean;
	public int getZtNO() {
		return ztNO;
	}
	public void setZtNO(int ztNO) {
		this.ztNO = ztNO;
	}
	public int getZtZT() {
		return ztZT;
	}
	public void setZtZT(int ztZT) {
		this.ztZT = ztZT;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public TXxqBean getBean() {
		return bean;
	}
	public void setBean(TXxqBean bean) {
		this.bean = bean;
	}
	
	public TXjdBean() {
		super();
	}
	public TXjdBean(int ztNO, int ztZT, String money, TXxqBean bean) {
		super();
		this.ztNO = ztNO;
		this.ztZT = ztZT;
		this.money = money;
		this.bean = bean;
	}
	
	

}
