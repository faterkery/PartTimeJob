package com.clv.parttimejobs.entity.my.resume;

public class Jntc_basis {

	private Long user_id;
	private String id;
	private String context;
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	public Jntc_basis() {
		super();
	}
	public Jntc_basis(Long user_id, String id, String context) {
		super();
		this.user_id = user_id;
		this.id = id;
		this.context = context;
	}
	
}
