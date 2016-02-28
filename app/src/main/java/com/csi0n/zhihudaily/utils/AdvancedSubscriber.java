package com.csi0n.zhihudaily.utils;

import android.text.TextUtils;

import com.csi0n.zhihudaily.utils.model.response.BaseResponse;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public class AdvancedSubscriber<T extends BaseResponse> extends SimpleSubscriber<T> {

    public AdvancedSubscriber() {
    }

    @Override
    public void onHandleSuccess(T response) {

    }

    @Override
    public void onHandleFail(String message, Throwable throwable) {
        super.onHandleFail(message, throwable);

        if (message != null) { // 业务异常
            doHandleBusinessFail(message);
        } else if (throwable != null) { // 运行异常
            doHandleException(throwable);
        } else { // 未知异常

        }
    }

    private void doHandleBusinessFail(String msg) {
        if (TextUtils.isEmpty(msg)) {
            CLog.show("未知错误");
        } else {
            CLog.show(msg);
        }
    }

    private void doHandleException(Throwable throwable) {

        if (throwable != null) {
        }

        String toastText = null;
        if (throwable instanceof NetWorkException) {
            NetWorkException netWorkException = (NetWorkException) throwable;
            Throwable detailException = netWorkException.getDetailThrowable();
            if (detailException instanceof ConnectException) {
                toastText = "Connect Fail";
            } else if (detailException instanceof UnknownHostException) {
                toastText = "Unknown Host";
            } else if (detailException instanceof TimeoutException || detailException instanceof InterruptedIOException) {
                toastText = "Time out";
            } else if (detailException instanceof JSONException) {
                toastText = "Json error";
            } else if (detailException instanceof JsonParseException) {
                toastText = "Gson parse error";
            }
        }
        if (TextUtils.isEmpty(toastText)) {
            CLog.show("请打开网络");
        } else {
            CLog.show("[" + toastText + "]");
        }
    }


    @Override
    public void onHandleFinish() {
        super.onHandleFinish();

    }


}