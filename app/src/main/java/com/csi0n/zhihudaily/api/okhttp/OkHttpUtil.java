package com.csi0n.zhihudaily.api.okhttp;


import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;

/**
 * OkHttpUtil <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public class OkHttpUtil {

    static final int HTTP_TIME_OUT = 15;

    public static OkHttpClient newOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }
    public static CacheControl getCacheControl() {
        return new CacheControl.Builder().noCache().build();
    }
}