package com.csi0n.zhihudaily.utils;

import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

import com.csi0n.zhihudaily.App;
import com.csi0n.zhihudaily.Config;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class CLog {
    private static final String TAG = App.getApp.getPackageName();

    public static void iMessage(String message) {
        if (Config.DEBUG)
            Log.i(TAG, message);
    }

    public void eMessage(String message) {
        if (Config.DEBUG)
            Log.e(TAG, message);
    }

    public void dMessage(String message) {
        if (Config.DEBUG)
            Log.d(TAG, message);
    }

    public static void show(CharSequence text) {
        if (text.length() < 10) {
            Toast.makeText(App.getApp, text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(App.getApp, text, Toast.LENGTH_LONG).show();
        }
    }

    public static void showNormalError(int code, String str) {
        Toast.makeText(App.getApp, "错误码:" + code + ",错误信息:" + str, Toast.LENGTH_SHORT).show();
    }

    public static void show(@StringRes int resId) {
        show(App.getApp.getString(resId));
    }
}
