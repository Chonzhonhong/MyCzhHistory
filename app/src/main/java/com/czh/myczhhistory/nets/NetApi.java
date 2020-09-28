package com.czh.myczhhistory.nets;

import com.blankj.utilcode.util.AppUtils;
import com.czh.myczhhistory.entities.TohEntit;
import com.qiniu.android.storage.UploadManager;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Created By Admin  on 2020/9/27
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public interface NetApi {
    String domainReleaseName ="http://api.juheapi.com";
    String domainDebugName = "http://api.juheapi.com";

    String baseUrl = AppUtils.isAppDebug() ? domainDebugName : domainReleaseName;

    String HISTURLKEY = "d5b787db05b23a1c8ae4f83a813cba97";

    @POST("/japi/toh")
    @FormUrlEncoded
    Observable<List<TohEntit>> getToh(@Field("key") String key, @Field("v") String v,
                                     @Field("month") String month, @Field("day") String day);

    /*2.0忘记密码*/
    @POST("/japi/tohdet")
    @FormUrlEncoded
    Observable<List<TohEntit>> getResetPassword (@Field("key") String key,@Field("v") String v,@Field("id") String id );



}
