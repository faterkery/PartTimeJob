package com.clv.parttimejobs.model.callback;

import com.clv.parttimejobs.entity.consult.DatailJobBean;

public interface JobsListInformationCallback {
	void onJobsInformationListLoaded(DatailJobBean jobbean);
}
