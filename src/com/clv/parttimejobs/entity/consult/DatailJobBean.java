package com.clv.parttimejobs.entity.consult;

import java.io.Serializable;
import java.util.List;

import com.clv.parttimejobs.entity.consult.job.JobDescriptionBean;
import com.clv.parttimejobs.entity.consult.job.ProblemBean;

public class DatailJobBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String claim;
	private String companyId;
	private String deadline;
	private String jobDescription;
	private String location;
	private int needNumber;
	private String numberOfapplicants;
	private String partTimeId;
	private String partTimeStatus;
	private String photoName;
	private String salary;
	private String settlementMethod;
	private String title;
	private String type;
	private String workDate;
	private String registrationType;//±¨Ãû×´Ì¬
//	private List<ProblemBean> problem;
	private List<JobDescriptionBean> description;
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getNeedNumber() {
		return needNumber;
	}
	public void setNeedNumber(int needNumber) {
		this.needNumber = needNumber;
	}
	public String getNumberOfapplicants() {
		return numberOfapplicants;
	}
	public void setNumberOfapplicants(String numberOfapplicants) {
		this.numberOfapplicants = numberOfapplicants;
	}
	public String getPartTimeId() {
		return partTimeId;
	}
	public void setPartTimeId(String partTimeId) {
		this.partTimeId = partTimeId;
	}
	public String getPartTimeStatus() {
		return partTimeStatus;
	}
	public void setPartTimeStatus(String partTimeStatus) {
		this.partTimeStatus = partTimeStatus;
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
	public String getSettlementMethod() {
		return settlementMethod;
	}
	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	public List<JobDescriptionBean> getDescription() {
		return description;
	}
	public void setDescription(List<JobDescriptionBean> description) {
		this.description = description;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public DatailJobBean() {
		super();
	}
	
	
	public DatailJobBean(String claim, String companyId, String deadline,
			String jobDescription, String location, int needNumber,
			String numberOfapplicants, String partTimeId,
			String partTimeStatus, String photoName, String salary,
			String settlementMethod, String title, String type,
			String workDate, List<JobDescriptionBean> description) {
		super();
		this.claim = claim;
		this.companyId = companyId;
		this.deadline = deadline;
		this.jobDescription = jobDescription;
		this.location = location;
		this.needNumber = needNumber;
		this.numberOfapplicants = numberOfapplicants;
		this.partTimeId = partTimeId;
		this.partTimeStatus = partTimeStatus;
		this.photoName = photoName;
		this.salary = salary;
		this.settlementMethod = settlementMethod;
		this.title = title;
		this.type = type;
		this.workDate = workDate;
		this.description = description;
	}
	
	
}
