package com.czh.myczhhistory.nets;

import com.czh.myczhhistory.entities.TohEntit;
import com.qiniu.android.storage.UploadManager;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Created By Admin  on 2020/9/27
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public class NetService {

    private Net mNet;
    private NetUpload netUpload;
    private static NetService sInstance;

    private NetApi getApi() {
        return mNet.getApi();
    }

    private NetService() {
        mNet = Net.getInstance();
        netUpload = new NetUpload(getApi(), new UploadManager());
    }

    public static NetService getInstance() {
        if (sInstance == null) {
            sInstance = new NetService();
        }
        return sInstance;
    }


    private <K> Observable<K> addSchedulers(Observable<K> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<List<TohEntit>> getToh(String key, String v, String month, String day){
        return addSchedulers(getApi().getToh(key, v, month, day));
    }
}
