package com.leo.afbaselibrary.nets.converters;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.leo.afbaselibrary.nets.entities.ResultEntity;
import com.leo.afbaselibrary.nets.entities.WxPayEntity;
import com.leo.afbaselibrary.nets.exceptions.PayResultException;
import com.leo.afbaselibrary.nets.exceptions.ResultException;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * created by czh on 16/10/24 17:24
 *email：1632365610@qq.com
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type adapter;

    public GsonResponseBodyConverter(Gson gson, Type adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            Logger.i(response);
            Logger.json(response);
            ResultEntity resultModel = gson.fromJson(response, ResultEntity.class);
            if (resultModel.getError_code()==0) {
                if (resultModel.getResult() != null) {
                    return gson.fromJson(resultModel.getResult(), adapter);
                }
                return null;
            }/* else if (resultModel.getError_code() == 8001 || resultModel.getStatus() == 8002) {
                throw new PayResultException(resultModel.getStatus(), resultModel.getMessage(), gson.fromJson(resultModel.getContent(), WxPayEntity.class));
            }*//*else if (resultModel.getStatus()==100200){
                throw new ResultException(resultModel.getStatus(), resultModel.getMessage());
            }*/else {
                throw new ResultException(resultModel.getError_code(), resultModel.getReason());
            }
        } finally {
            value.close();
        }
    }
}
