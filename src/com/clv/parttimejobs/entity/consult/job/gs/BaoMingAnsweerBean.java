package com.clv.parttimejobs.entity.consult.job.gs;

public class BaoMingAnsweerBean {

	private int problemId;
	private String answerContent;
	private int userId;
	public int getProblemId() {
		return problemId;
	}
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public BaoMingAnsweerBean() {
		super();
	}
	public BaoMingAnsweerBean(int problemId, String answerContent, int userId) {
		super();
		this.problemId = problemId;
		this.answerContent = answerContent;
		this.userId = userId;
	}
	
	
}
