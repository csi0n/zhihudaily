package com.csi0n.zhihudaily.api;

import com.csi0n.zhihudaily.utils.model.*;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public interface I_ZhihuApi {
    void getStartInfo(int width, int height);
    void getZhihuDaily();
    void getZhihuDetail(int id);
}
