package com.clv.parttimejobs.dao.history;

public class Histroy {

	private int id;
	private String histroy_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHistroy_name() {
		return histroy_name;
	}

	public void setHistroy_name(String histroy_name) {
		this.histroy_name = histroy_name;
	}

	public Histroy() {
		super();
	}

	public Histroy(int id, String histroy_name) {
		super();
		this.id = id;
		this.histroy_name = histroy_name;
	}

}
