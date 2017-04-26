package com.clv.parttimejobs.util.ecutetime;

public class Ecute_data_time {

	public String ecute_time_data(int a) {
		String time = "";
		int time_minute = a / 60;
		int time_miao = a % 60;
		if (time_minute >= 10) {
			time += time_minute;
		} else {
			time += "0";
			time += time_minute;
		}
		time += " : ";
		if (time_miao > 10) {
			time += time_miao;
		} else {
			time += "0";
			time += time_miao;
		}
		return time;
	}
}
