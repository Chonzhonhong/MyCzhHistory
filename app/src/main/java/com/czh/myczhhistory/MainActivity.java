package com.czh.myczhhistory;


import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.czh.myczhhistory.entities.TohEntit;
import com.czh.myczhhistory.nets.CustomApiCallback;
import com.czh.myczhhistory.nets.NetService;
import com.leo.afbaselibrary.nets.exceptions.ApiException;
import com.leo.afbaselibrary.uis.activities.BaseActivity;

import java.util.List;


public class MainActivity extends BaseActivity {
    String key = "d5b787db05b23a1c8ae4f83a813cba97";
    String v = "1";
    String month ="10";
    String day ="1";

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }


    @Override
    public void init(Bundle savedInstanceState) {
        NetService.getInstance().getToh(key,v,month,day)
                .compose(this.bindLifeCycle())
                .subscribe(new CustomApiCallback<List<TohEntit>>() {
                    @Override
                    protected void onResultError(ApiException ex) {
                        LogUtils.e(ex.getCode(),ex.getDisplayMessage());
                    }

                    @Override
                    public void onNext(List<TohEntit> tohEntits) {
                        LogUtils.e(tohEntits.toString());
                    }
                });
    }
}
