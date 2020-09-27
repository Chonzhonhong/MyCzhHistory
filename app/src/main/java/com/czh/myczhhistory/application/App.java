package com.czh.myczhhistory.application;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.os.Handler;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;

import com.czh.myczhhistory.R;
import com.czh.myczhhistory.nets.Net;
import com.liulishuo.filedownloader.FileDownloader;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.io.File;



/**
 * Created by GongLi on 2018/8/23.
 * Email：lc824767150@163.com
 */

public class App extends LitePalApplication {
    private static App sInstance;
    public static Context sApplication;
    public Handler mHandler = new Handler();

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.white, R.color.black);//全局设置主题颜色
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        init();
        initLogger();
        initUMeng();
        initDownLoader();
        sApplication = getApplicationContext();
        LitePal.initialize(this);



    }




    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isDebug();
            }
        });
        if (AppUtils.isAppDebug()) {
            LogUtils.getConfig().setLogSwitch(true);
        } else {
            LogUtils.getConfig().setLogSwitch(false);
        }
    }

    public boolean isDebug() {
        return getApplicationInfo() != null && (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    private void init() {
        String fileName = Environment.getExternalStorageDirectory() + File.separator + "PDD";
        new File(fileName).mkdirs();
    }

    private void initDownLoader() {
        FileDownloader.setupOnApplicationOnCreate(this);
    }

    private void initUMeng() {
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.openActivityDurationTrack(false);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.setLogEnabled(isDebug());
        UMConfigure.setEncryptEnabled(true);
    }






    /**
     * 获取自定义根目录
     */
    public static App getInstance() {
        return sInstance;
    }

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);

    }

    public static Context getContext() {
        return sInstance.getApplicationContext();
    }



}
