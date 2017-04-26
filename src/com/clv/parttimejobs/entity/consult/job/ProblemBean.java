package com.clv.parttimejobs.entity.consult.job;

import java.util.HashMap;
import java.util.List;

public class ProblemBean {

	private String partTimeId;//兼职id
	private String problemId;//问题id
	private String topy;//问题
	private String problem_topic;//问题主题  0---无问题 1---自主性问题  2---选择性问题
	private String problem_content;//问题选项
	
	public String getPartTimeId() {
		return partTimeId;
	}
	public void setPartTimeId(String partTimeId) {
		this.partTimeId = partTimeId;
	}
	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	public String getTopy() {
		return topy;
	}
	public void setTopy(String topy) {
		this.topy = topy;
	}
	public String getProblem_topic() {
		return problem_topic;
	}
	public void setProblem_topic(String problem_topic) {
		this.problem_topic = problem_topic;
	}
   
	public String getProblem_content() {
		return problem_content;
	}
	public void setProblem_content(String problem_content) {
		this.problem_content = problem_content;
	}
	public ProblemBean() {
		super();
	}
	public ProblemBean(String partTimeId, String problemId, String topy,
			String problem_topic, String problem_content) {
		super();
		this.partTimeId = partTimeId;
		this.problemId = problemId;
		this.topy = topy;
		this.problem_topic = problem_topic;
		this.problem_content = problem_content;
	}
	
	
}
