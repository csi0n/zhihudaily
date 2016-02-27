package com.csi0n.zhihudaily.controller;

import android.view.View;

import com.csi0n.zhihudaily.api.ZhihuApi;
import com.csi0n.zhihudaily.ui.activity.ZhiHuDailyDetailActivity;
import com.csi0n.zhihudaily.utils.HtmlUtil;
import com.csi0n.zhihudaily.utils.model.ZhihuDetail;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhiHuDailyDetailController extends BaseController {
    private ZhiHuDailyDetailActivity mZhiHuDailyDetailActivity;

    public ZhiHuDailyDetailController(ZhiHuDailyDetailActivity mZhiHuDailyDetailActivity) {
        this.mZhiHuDailyDetailActivity = mZhiHuDailyDetailActivity;
    }

    public void initZhiHuDailyDetail() {
        ZhihuApi api = new ZhihuApi();
        api.getZhihuDetail(mZhiHuDailyDetailActivity.getStories().getId());
    }

    @Override
    public void onClickReal(View view) {

    }

    public void onEvent(ZhihuDetail zhihuDetail) {
        mZhiHuDailyDetailActivity.setImageView(zhihuDetail.getImage());
        mZhiHuDailyDetailActivity.setmTvSource(zhihuDetail.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(zhihuDetail);
        mZhiHuDailyDetailActivity.setmWvNews(htmlData);
    }
}
