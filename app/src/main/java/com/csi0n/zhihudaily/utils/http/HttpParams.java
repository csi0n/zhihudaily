package com.csi0n.zhihudaily.utils.http;
import com.csi0n.zhihudaily.Config;
import com.csi0n.zhihudaily.utils.CLog;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by csi0n on 2015/12/15 0015.
 */
public class HttpParams {
    private RequestParams mRequestParams;

    public HttpParams(int url) {
        mRequestParams = new RequestParams(Config.ZHIHU_BASE_URL + x.app().getString(url));
    }

    public HttpParams(String url) {
        mRequestParams = new RequestParams(Config.ZHIHU_BASE_URL + url);
        CLog.iMessage("get request url:"+Config.ZHIHU_BASE_URL+url);
    }

    public void put(String key, String value) {
        mRequestParams.addQueryStringParameter(key, value);
    }

    public RequestParams getParams() {
        return mRequestParams;
    }
}
