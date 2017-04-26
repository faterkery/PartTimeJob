package com.clv.parttimejobs.entity.message;

import java.io.Serializable;

import com.clv.homework.R;

public class Message implements Serializable {

	private int imageid;
	private String Message_name;
	private String Message_context;
	private int drawid;//Õº∆¨
	private int flag;
	private long time;
	private boolean isTop =false;// «∑Ò÷√∂•
	private int mesagecount;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getImageid() {
		return imageid;
	}

	public void setImageid(int imageid) {
		this.imageid = imageid;
	}

	public String getMessage_name() {
		return Message_name;
	}

	public void setMessage_name(String message_name) {
		Message_name = message_name;
	}

	public String getMessage_context() {
		return Message_context;
	}

	public void setMessage_context(String message_context) {
		Message_context = message_context;
	}

	
	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public int getMesagecount() {
		return mesagecount;
	}

	public void setMesagecount(int mesagecount) {
		this.mesagecount = mesagecount;
	}

    
	
	public int getDrawid() {
		return drawid;
	}

	public void setDrawid(int drawid) {
		this.drawid = drawid;
	}

	public Message() {
		super();
	}

	public Message(int imageid, String message_name, String message_context,
			int drawid, int flag, long time, boolean isTop, int mesagecount) {
		super();
		this.imageid = imageid;
		Message_name = message_name;
		Message_context = message_context;
		this.drawid = drawid;
		this.flag = flag;
		this.time = time;
		this.isTop = isTop;
		this.mesagecount = mesagecount;
	}

	@Override
	public String toString() {
		return "Message [imageid=" + imageid + ", Message_name=" + Message_name
				+ ", Message_context=" + Message_context + ", flag=" + flag
				+ ", time=" + time + ", isTop=" + isTop + "]";
	}


}
