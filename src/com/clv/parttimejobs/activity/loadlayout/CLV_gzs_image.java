package com.clv.parttimejobs.activity.loadlayout;

import com.clv.homework.R;
import com.clv.parttimejobs.activity.leadlayout.CLV__weclome_fragment;
import com.clv.parttimejobs.activity.mainlayout.MainActivity;
import com.clv.parttimejobs.dao.DBOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CLV_gzs_image extends Activity {
	SharedPreferences preferences;
	int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clv_gzs_image);
		preferences = getSharedPreferences("COUNTAPP",MODE_WORLD_READABLE);   
        count = preferences.getInt("COUNTAPP", 0);   
        new Thread(new Thread1()).start();
	}
	private class Thread1 implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  //判断程序与第几次运行，如果是第一次运行则跳转到引导页面     
	         if (count < 1) {    
	         Intent intent = new Intent();   
	         intent.setClass(getApplicationContext(),CLV__weclome_fragment.class);    
	         startActivity(intent);    
	         CLV_gzs_image.this.finish();
	         } 
	         else{
	        	 Intent intent = new Intent();   
		         intent.setClass(getApplicationContext(),MainActivity.class);    
		         startActivity(intent);    
		         CLV_gzs_image.this.finish();
	         }
	         Editor editor = preferences.edit();    
	         //存入数据      
	         editor.putInt("COUNTAPP", ++count); 
	         //提交修改      
	         editor.commit();
			
		}
		
	}

}
