package com.clv.parttimejobs.entity.my.scheduling;

public class Myrcbasis {

	private String time;
	private String context;
	private int hangno;
	private int lieno;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getHangno() {
		return hangno;
	}
	public void setHangno(int hangno) {
		this.hangno = hangno;
	}
	public int getLieno() {
		return lieno;
	}
	public void setLieno(int lieno) {
		this.lieno = lieno;
	}
	public Myrcbasis(String time, String context, int hangno, int lieno) {
		super();
		this.time = time;
		this.context = context;
		this.hangno = hangno;
		this.lieno = lieno;
	}
	
}
