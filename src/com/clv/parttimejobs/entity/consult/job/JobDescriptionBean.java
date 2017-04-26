package com.clv.parttimejobs.entity.consult.job;

public class JobDescriptionBean {

	private String partTimeId;
	private String content;
	private String title;
	private String descriptionId;
	public String getPartTimeId() {
		return partTimeId;
	}
	public void setPartTimeId(String partTimeId) {
		this.partTimeId = partTimeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public JobDescriptionBean() {
		super();
	}
	
	public String getDescriptionId() {
		return descriptionId;
	}
	public void setDescriptionId(String descriptionId) {
		this.descriptionId = descriptionId;
	}
	public JobDescriptionBean(String partTimeId, String content, String title,
			String descriptionId) {
		super();
		this.partTimeId = partTimeId;
		this.content = content;
		this.title = title;
		this.descriptionId = descriptionId;
	}
	
}
