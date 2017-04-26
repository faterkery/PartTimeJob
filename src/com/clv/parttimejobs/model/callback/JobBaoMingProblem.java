package com.clv.parttimejobs.model.callback;

import java.util.List;

import com.clv.parttimejobs.entity.consult.job.ProblemBean;

public interface JobBaoMingProblem {

	void onJobsProblemListLoaded(String value,List<ProblemBean> bean);
}
