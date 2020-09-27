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

    @POST("/japi/toh")
    @FormUrlEncoded
    Observable<List<TohEntit>> getToh(@Field("key") String key, @Field("v") String v,
                                     @Field("month") String month, @Field("day") String day);

    /*2.0忘记密码*/
    @POST("userSecurity/resetPassword")
    @FormUrlEncoded
    Observable<String> getResetPassword (@Field("phone") String phone,@Field("verifyCode") String verifyCode,@Field("newPassword") String newPassword );



}
