package com.csi0n.zhihudaily.utils.http;

import org.xutils.common.Callback;
import org.xutils.x;

/**
 * Created by csi0n on 2015/12/14 0014.
 */
public class Http {
    private HttpParams mHttpParams;
    private Callback.CommonCallback<String> mCallback;
    public Http(HttpParams mHttpParams, Callback.CommonCallback<String> mCallback) {
        this.mHttpParams = mHttpParams;
        this.mCallback = mCallback;
    }
    public void post() {
        x.http().post(mHttpParams.getParams(), mCallback);
    }
    public void get(){x.http().get(mHttpParams.getParams(),mCallback);}
}
