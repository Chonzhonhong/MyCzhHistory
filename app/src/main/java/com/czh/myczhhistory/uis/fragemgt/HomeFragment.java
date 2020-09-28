package com.czh.myczhhistory.uis.fragemgt;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.czh.myczhhistory.R;
import com.czh.myczhhistory.entities.TohEntit;
import com.czh.myczhhistory.group.GroupRecyclerView;
import com.czh.myczhhistory.nets.CustomApiCallback;
import com.czh.myczhhistory.nets.NetApi;
import com.czh.myczhhistory.nets.NetService;
import com.czh.myczhhistory.uis.adapder.HomeAdapter;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.leo.afbaselibrary.nets.exceptions.ApiException;
import com.leo.afbaselibrary.uis.fragments.BaseFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @Created By Admin  on 2020/9/28
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public class HomeFragment extends BaseFragment implements  CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener{

    @BindView(R.id.tv_month_day)
    TextView tvMonthDay;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lunar)
    TextView tvLunar;
    @BindView(R.id.ib_calendar)
    ImageView ibCalendar;
    @BindView(R.id.tv_current_day)
    TextView tvCurrentDay;
    @BindView(R.id.fl_current)
    FrameLayout flCurrent;
    @BindView(R.id.rl_tool)
    RelativeLayout rlTool;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.calendarLayout)
    CalendarLayout calendarLayout;
    private int mYear;
    private int page = 1;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        tvMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!calendarLayout.isExpand()) {
                    calendarLayout.expand();
                    return;
                }
                calendarView.showYearSelectLayout(mYear);
                tvLunar.setVisibility(View.GONE);
                tvYear.setVisibility(View.GONE);
                tvMonthDay.setText(String.valueOf(mYear));
            }
        });
        flCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.scrollToCurrent();
            }
        });
        calendarView.setOnCalendarSelectListener(this);
        calendarView.setOnYearChangeListener(this);
        tvYear.setText(String.valueOf(calendarView.getCurYear()));
        mYear = calendarView.getCurYear();
        tvMonthDay.setText(calendarView.getCurMonth() + "月" + calendarView.getCurDay() + "日");
        tvLunar.setText("今日");
        tvCurrentDay.setText(String.valueOf(calendarView.getCurDay()));
        initData(calendarView.getCurMonth(), calendarView.getCurDay());
    }

    private void initData(int curMonth, int curDay) {
        NetService.getInstance().getToh(NetApi.HISTURLKEY,"1.0",String.valueOf(curMonth),String.valueOf(curDay))
                .compose(this.bindLifeCycle())
                .subscribe(new CustomApiCallback<List<TohEntit>>() {
                    @Override
                    protected void onResultError(ApiException ex) {
                        LogUtils.e(ex.getCode(),ex.getDisplayMessage());
                    }

                    @Override
                    public void onNext(List<TohEntit> tohEntits) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(new HomeAdapter(getActivity(),tohEntits));
                    }
                });
    }
    @Override
    protected void dealLeackCanary() {
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {
    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        tvLunar.setVisibility(View.VISIBLE);
        tvYear.setVisibility(View.VISIBLE);
        tvMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        tvYear.setText(String.valueOf(calendar.getYear()));
        tvYear.setText(calendar.getLunar());
        mYear = calendar.getYear();
        initData(calendar.getMonth(),calendar.getDay());
    }

    @Override
    public void onYearChange(int year) {
        tvMonthDay.setText(String.valueOf(year));
    }
}
