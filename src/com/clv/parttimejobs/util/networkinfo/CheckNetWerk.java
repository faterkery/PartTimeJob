package com.clv.parttimejobs.util.networkinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


public class CheckNetWerk {

	public boolean isConnectNetWork(Context act) {
		NetworkInfo netIntfo = null;
		ConnectivityManager cm ;
		try {
			cm = (ConnectivityManager) act
					.getSystemService(act.CONNECTIVITY_SERVICE);
			netIntfo = cm.getActiveNetworkInfo();
		} catch (Exception e) {
			// 异常处理
			Toast.makeText(act, "没有网络权限，请给予相关权限", Toast.LENGTH_LONG).show();
		}

		if (netIntfo == null) {
			// 如果没有网络 显示不正常
			return false;

		} else {
			// 如果没有网络 显示不正常
			return true;

		}
	}
}
