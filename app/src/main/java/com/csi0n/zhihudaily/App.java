package com.csi0n.zhihudaily;

import android.app.Application;

import com.csi0n.zhihudaily.utils.SharePreferenceManager;
import com.google.gson.Gson;

import org.xutils.x;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class App extends Application {
    public static Gson gson;
    @Override
    public void onCreate() {
        super.onCreate();
        gson=new Gson();
        SharePreferenceManager.init(this, Config.ZHIHUDAILY_CONFIGS);
        CrashHandler.create(this);
        x.Ext.init(this);
        x.Ext.setDebug(Config.DEBUG);
    }
}
