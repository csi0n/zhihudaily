package com.csi0n.zhihudaily.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class SharePreferenceManager {
    static SharedPreferences sp;
    public static void init(Context context,String name){
        sp=context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }
}
