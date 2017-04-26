package com.clv.parttimejobs.entity.consult;

import java.io.Serializable;

public class News implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int drawimg_id;
	private String name;
	private String people_name;
	private String people_tel;

	public int getDrawimg_id() {
		return drawimg_id;
	}

	public void setDrawimg_id(int drawimg_id) {
		this.drawimg_id = drawimg_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeople_name() {
		return people_name;
	}

	public void setPeople_name(String people_name) {
		this.people_name = people_name;
	}

	public String getPeople_tel() {
		return people_tel;
	}

	public void setPeople_tel(String people_tel) {
		this.people_tel = people_tel;
	}

	public News(String name, String people_name, String people_tel,
			int drawimg_id) {
		this.drawimg_id = drawimg_id;
		this.name = name;
		this.people_name = people_name;
		this.people_tel = people_tel;
	}

}
