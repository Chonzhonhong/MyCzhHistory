package com.czh.myczhhistory.application;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * Created by GongLi on 2018/8/23.
 * Email：lc824767150@163.com
 */

public class AppContent {
    private static AppContent instance;
    //线程池
    private ExecutorService threadPool;

    private Gson gson;

    private Long myUserId;
    private String myUserRole;


    //APP启动时调用
    public void init(Context context) {
    }

    private AppContent() {
    }

    public static AppContent getInstance() {
        if (instance == null) {
            synchronized (AppContent.class) {
                if (instance == null) {
                    instance = new AppContent();
                }
            }
        }
        return instance;
    }





    public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        return gson;
    }

    public ExecutorService getThreadPool() {
        if (threadPool == null) {
            threadPool = Executors.newFixedThreadPool(4);
        }
        return threadPool;
    }


    public void clear() {
        myUserId = null;
        myUserRole = null;
    }

}
