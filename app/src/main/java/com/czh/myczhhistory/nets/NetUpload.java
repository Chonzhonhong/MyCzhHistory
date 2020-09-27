package com.czh.myczhhistory.nets;

import com.qiniu.android.storage.UploadManager;

/**
 * @Created By Admin  on 2020/9/27
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public class NetUpload {

    private NetApi netApi;
    private UploadManager uploadManager;

    NetUpload(NetApi netApi, UploadManager uploadManager) {
        this.netApi = netApi;
        this.uploadManager = uploadManager;
    }

}
