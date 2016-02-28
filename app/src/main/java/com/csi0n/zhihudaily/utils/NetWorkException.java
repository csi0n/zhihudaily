package com.csi0n.zhihudaily.utils;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public class NetWorkException extends Exception {
    private Throwable detailThrowable;

    public NetWorkException(Throwable throwable) {
        super(throwable);
        this.detailThrowable = throwable;
    }

    public Throwable getDetailThrowable() {
        return detailThrowable;
    }
}
