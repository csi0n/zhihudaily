package com.csi0n.zhihudaily.utils.model.request;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public class ZhihuStartInfoRequest extends BaseRequest {
    public int width;

    public int height;

    public ZhihuStartInfoRequest(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
