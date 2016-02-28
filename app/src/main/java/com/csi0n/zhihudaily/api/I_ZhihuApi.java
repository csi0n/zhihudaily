package com.csi0n.zhihudaily.api;

import com.csi0n.zhihudaily.utils.NetWorkException;
import com.csi0n.zhihudaily.utils.model.response.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDailyRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDetailRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuStartInfoRequest;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public interface I_ZhihuApi {
    ZhihuStartInfo getStartInfo(ZhihuStartInfoRequest zhihuStartInfoRequest) throws NetWorkException;

    ZhihuLatest getZhihuDaily(ZhihuDailyRequest zhihuDailyRequest) throws NetWorkException;

    ZhihuDetail getZhihuDetail(ZhihuDetailRequest zhihuDetailRequest) throws NetWorkException;
}
