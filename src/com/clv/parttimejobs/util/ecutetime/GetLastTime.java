package com.clv.parttimejobs.util.ecutetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;

import com.clv.parttimejobs.dao.parttimejob.PartJobDao;
import com.clv.parttimejobs.entity.consult.PartJobBean;

public class GetLastTime {
	public String getLastTime(Context context){
		String time = "2017-2-22 22:12:20";
		PartJobDao partjobdao =new PartJobDao(context);
		List<PartJobBean> l =partjobdao.detail();
		if(l.size()==0){
			return time;
		}else{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String timetemp="";
			timetemp=l.get(0).getLastTime();
			for(PartJobBean p:l){
				java.util.Date d1;
				try {
					d1 = df.parse(timetemp);
				    java.util.Date d2 = df.parse(p.getLastTime());
				    if (d1.getTime() < d2.getTime())
				    {
				    	timetemp = p.getLastTime();
				    }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			time = timetemp;
		}
		return time;
	}
}
