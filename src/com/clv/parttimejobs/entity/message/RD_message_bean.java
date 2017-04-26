package com.clv.parttimejobs.entity.message;

public class RD_message_bean {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RD_message_bean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
