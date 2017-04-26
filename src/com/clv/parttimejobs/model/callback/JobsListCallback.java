package com.clv.parttimejobs.model.callback;

import com.clv.parttimejobs.entity.consult.DatailJobBean;

public interface JobsListCallback {

	/**
	 * 当列表加载完毕后将会调用的回调方法
	 * @param musics
	 */
	void onJobsListLoaded(String results);
	
}
