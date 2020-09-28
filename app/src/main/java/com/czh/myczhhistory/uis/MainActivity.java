package com.czh.myczhhistory.uis;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.LogUtils;
import com.czh.myczhhistory.R;
import com.czh.myczhhistory.entities.TohEntit;
import com.czh.myczhhistory.nets.CustomApiCallback;
import com.czh.myczhhistory.nets.NetApi;
import com.czh.myczhhistory.nets.NetService;
import com.czh.myczhhistory.uis.fragemgt.CommunityFragment;
import com.czh.myczhhistory.uis.fragemgt.HomeFragment;
import com.czh.myczhhistory.uis.fragemgt.PersonalFragment;
import com.leo.afbaselibrary.nets.exceptions.ApiException;
import com.leo.afbaselibrary.uis.activities.BaseActivity;
import com.leo.afbaselibrary.uis.fragments.BaseFragment;
import com.leo.afbaselibrary.widgets.TabStripView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends BaseActivity implements TabStripView.OnTabSelectedListener{

    @BindView(R.id.layout_content)
    FrameLayout layoutContent;
    @BindView(R.id.tabBar)
    TabStripView tabBar;
    @BindView(R.id.ll_tab_bar)
    RelativeLayout llTabBar;

    private String currentTag;
    private BaseFragment currentFragment;
    private long logTime;
    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }
    @Override
    public void init(Bundle savedInstanceState) {
        currentTag = "首页";
        initTab(savedInstanceState);
        tabBar.setDefaultSelectedTab(0);
    }

    private void initTab(Bundle savedInstanceState) {
        //设置tab栏
        tabBar.addTab(HomeFragment.class, new TabStripView.TabParam(R.color.home
                , R.mipmap.home, R.mipmap.home1, "首页"));
        tabBar.addTab(CommunityFragment.class, new TabStripView.TabParam(R.color.home
                , R.mipmap.community, R.mipmap.community1, "社区"));
        tabBar.addTab(PersonalFragment.class, new TabStripView.TabParam(R.color.home
                , R.mipmap.personal, R.mipmap.personal1, "个人"));
        tabBar.onRestoreInstanceState(savedInstanceState);
        tabBar.setTabSelectListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onTabSelected(TabStripView.ViewHolder holder) {
        currentTag = holder.tag;
        currentFragment = tabBar.getCurrentFragment(currentTag);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (currentFragment == null) {
            tabBar.getCurrentFragment(currentTag);
        }
        if (currentFragment != null) {
            currentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        tabBar.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentFragment == null) {
            currentFragment = tabBar.getCurrentFragment(currentTag);
        }
        if (currentFragment != null) {
            currentFragment.setUserVisibleHint(true);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (currentFragment == null) {
            currentFragment = tabBar.getCurrentFragment(currentTag);
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - logTime < 2000) {
            finish();
        } else {
            showToast("再次点击退出应用");
            logTime = currentTime;
        }
    }
}
