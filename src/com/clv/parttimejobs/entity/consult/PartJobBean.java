package com.clv.parttimejobs.entity.consult;

public class PartJobBean {

	private String lastTime;// 该兼职最后修改时间
	private String location;// 地址城市
	private String partTimeId;// 兼职id
	private String partTimeQualification;// 兼职资质
	private String photoName;// 兼职图片
	private String salary;// 薪资
	private String title;// 兼职标题
	private String workDate;// 工作时间段

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPartTimeId() {
		return partTimeId;
	}

	public void setPartTimeId(String partTimeId) {
		this.partTimeId = partTimeId;
	}

	public String getPartTimeQualification() {
		return partTimeQualification;
	}

	public void setPartTimeQualification(String partTimeQualification) {
		this.partTimeQualification = partTimeQualification;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public PartJobBean() {
		super();
	}

	public PartJobBean(String lastTime, String location, String partTimeId,
			String partTimeQualification, String photoName, String salary,
			String title, String workDate) {
		super();
		this.lastTime = lastTime;
		this.location = location;
		this.partTimeId = partTimeId;
		this.partTimeQualification = partTimeQualification;
		this.photoName = photoName;
		this.salary = salary;
		this.title = title;
		this.workDate = workDate;
	}


}
