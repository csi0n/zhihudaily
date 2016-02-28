package com.csi0n.zhihudaily.domain;


import com.csi0n.zhihudaily.utils.model.response.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;

import rx.Observable;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public interface ZhihuDomain {
    Observable<ZhihuStartInfo> getStartInfo(int width, int height);

    Observable<ZhihuLatest> getZhihuDaily();

    Observable<ZhihuDetail> getZhihuDetail(int id);
}
