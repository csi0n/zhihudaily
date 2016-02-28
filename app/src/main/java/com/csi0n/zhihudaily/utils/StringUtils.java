package com.csi0n.zhihudaily.utils;

import android.support.annotation.StringRes;


import com.csi0n.zhihudaily.App;

import java.text.SimpleDateFormat;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class StringUtils {
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new java.util.Date());
    }
    public static String getString(@StringRes int id) {
        return App.getApp.getString(id);
    }
}
