package com.czh.myczhhistory.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.czh.myczhhistory.application.App;

/**
 * @Created By Admin  on 2020/9/27
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public class DataSharedPreferences {
    private static final String USER_TOKEN = "USER_TOKEN";

    private static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance().getApplicationContext());
    }

    public static void saveToken(String token) {
        getPreferences().edit().putString(USER_TOKEN, token).apply();
    }

    public static String getToken() {
        return getPreferences().getString(USER_TOKEN, "");
    }


}
