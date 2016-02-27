package com.csi0n.zhihudaily.utils.http;

import com.csi0n.zhihudaily.App;
import com.csi0n.zhihudaily.utils.CLog;
import com.csi0n.zhihudaily.utils.model.ZhihuStartInfo;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by chqss on 2016/1/24 0024.
 */
public abstract class CStringCallBack<T> implements Callback.CommonCallback<String> {
    private Class<T> tClass;

    public CStringCallBack(Class<T> tClass) {
        this.tClass = tClass;
    }

    public abstract void SuccessResult(T t);

    @Override
    public void onSuccess(String result) {
        SuccessResult(App.gson.fromJson(result, tClass));
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        if (ex.getClass() == SocketTimeoutException.class) {
            ErrorResult(1000, "连接超时!");
        } else if (ex.getClass() == ConnectException.class) {
            ErrorResult(1010, "数据服务器已关闭,请等待服务器重新开启!");
        } else {
            ErrorResult(1001, "未知异常!");
        }
    }

    @Override
    public void onCancelled(CancelledException cex) {
        ErrorResult(1002, "用户取消操作!");
    }

    @Override
    public void onFinished() {
    }

    public void ErrorResult(int code, String str) {
        CLog.showNormalError(code, str);
    }
}
