package com.clv.parttimejobs.util.ecutetime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CalculateGiftDate {

	public Integer toCalculateDate(List<String> dayArray){
		List<Integer> list2 =new ArrayList<Integer>();
		for(String date:dayArray){
			list2.add(Integer.parseInt(date));
		}
		Collections.sort(list2);
		
		Calendar calendar1 = Calendar.getInstance();
		int mcurrent = calendar1.get(Calendar.MONTH);
		int ycurrent = calendar1.get(Calendar.YEAR);
		int daycurrent =calendar1.get(Calendar.DAY_OF_MONTH);
		int length=0;
		if(isHas(daycurrent,list2)){
			length++;
		}
		for(int i=daycurrent-1;i>0;i--){
			if(isHas(i,list2)){
				length++;
			}else{
				break;
			}
		}
		return length;
	}
	public boolean isHas(int current,List<Integer> list){
		boolean ishas=false;
		for(int a:list){
			if(a==current){
				ishas=true;
			}
		}
		return ishas;
		
	}
}
