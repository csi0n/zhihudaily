package com.csi0n.zhihudaily.utils;

import com.csi0n.zhihudaily.utils.model.response.BaseResponse;

import rx.Subscriber;

/**
 * SimpleSubscriber <br/>
 * Created by xiaqiulei on 2015-10-22.
 */
public class SimpleSubscriber<T extends BaseResponse> extends Subscriber<T> {

    private boolean isSuccess = false; // 是否成功
    private T response; // 得到的数据结果

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public final void onNext(T response) {
        if (response == null) {

            return;
        }
        if (response.isSuccess()) {
            isSuccess = true;
            onHandleSuccess(response);
        } else {
            onHandleFail(response.getErrorMsg(), null);
        }
    }

    @Override
    public final void onError(Throwable throwable) {
        onHandleFail(null, throwable);
        onHandleFinish();
    }

    @Override
    public final void onCompleted() {
        onHandleFinish();
    }

    /**
     * 处理成功
     */
    public void onHandleSuccess(T response) {

    }

    /**
     * 处理失败
     */
    public void onHandleFail(String message, Throwable throwable) {

    }

    /**
     * 处理结束
     */
    public void onHandleFinish() {

    }

    public T getResponse() {
        return response;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}