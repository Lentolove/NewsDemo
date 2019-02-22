package com.tsp.newsdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {
    public static boolean getBoolean(Context context, String key) {
        //存储缓冲值 --可清理应用的缓冲数据清理掉
        SharedPreferences sp = context.getSharedPreferences("tsp",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

    /**
     * 保存软件记录的值
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("tsp",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
}
