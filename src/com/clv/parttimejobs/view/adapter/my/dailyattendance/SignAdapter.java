package com.clv.parttimejobs.view.adapter.my.dailyattendance;
import java.util.List;

import com.clv.parttimejobs.entity.my.dailyattendance.SignEntity;
import com.clv.parttimejobs.view.ui.customview.SignView;

/**
 * SignAdapter
 * Created by E.M on 2016/4/21.
 */
public class SignAdapter extends CalendarAdapter {
    private List<SignEntity> data;

    public SignAdapter(List<SignEntity> data) {
        this.data = data;
    }

    @Override
    public SignView.DayType getType(int dayOfMonth) {
        return SignView.DayType.valueOf(data.get(dayOfMonth - 1).getDayType());
    }
}
