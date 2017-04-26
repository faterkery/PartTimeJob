package com.clv.parttimejobs.view.adapter.my.dailyattendance;

import com.clv.parttimejobs.view.ui.customview.SignView;
import com.clv.parttimejobs.view.ui.customview.SignView.DayType;

/**
 * 签到日历控件数据适配器
 * Created by E.M on 2016/4/20.
 */
public abstract class CalendarAdapter {
    public abstract SignView.DayType getType(int dayOfMonth);
}
