package com.clv.parttimejobs.util.ecutefile;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class FileMkdir {

	public void mkdirphoneSDfile(){
		  if (Environment.getExternalStorageState().equals(
                  Environment.MEDIA_MOUNTED)) {
              // 获取SD卡的目录
              File sdCardDir = Environment.getExternalStorageDirectory();
              String path = "/data/clv";
              File dir = null;
			try {
				dir = new File(sdCardDir.getCanonicalPath()+path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
              if (!dir.exists()) {  
                  dir.mkdirs();  
              } 
		  }
	}
	public static String getFileML(){
		try {
			return Environment.getExternalStorageDirectory().getCanonicalPath()+"/data/clv";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
