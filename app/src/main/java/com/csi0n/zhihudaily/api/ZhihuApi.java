package com.csi0n.zhihudaily.api;

import com.csi0n.zhihudaily.utils.http.CStringCallBack;
import com.csi0n.zhihudaily.utils.http.Http;
import com.csi0n.zhihudaily.utils.http.HttpParams;
import com.csi0n.zhihudaily.utils.model.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.ZhihuStartInfo;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhihuApi implements I_ZhihuApi {
    public ZhihuApi(){

    }
    @Override
    public void getStartInfo(int width, int height) {
        HttpParams params=new HttpParams(String.format("start-image/%d*%d",width,height));
        Http http=new Http(params, new CStringCallBack<ZhihuStartInfo>(ZhihuStartInfo.class) {
            @Override
            public void SuccessResult(ZhihuStartInfo zhihuStartInfo) {
                EventBus.getDefault().post(zhihuStartInfo);
            }
        });
        http.get();
    }

    @Override
    public void getZhihuDaily() {
        HttpParams params=new HttpParams(String.format("news/latest"));
        Http http=new Http(params, new CStringCallBack<ZhihuLatest>(ZhihuLatest.class) {
            @Override
            public void SuccessResult(ZhihuLatest zhihuLatest) {
                EventBus.getDefault().post(zhihuLatest);
            }
        });
        http.get();
    }

    @Override
    public void getZhihuDetail(int id) {
        HttpParams params=new HttpParams(String.format("news/%d",id));
        Http http=new Http(params, new CStringCallBack<ZhihuDetail>(ZhihuDetail.class) {
            @Override
            public void SuccessResult(ZhihuDetail zhihuDetail) {
                EventBus.getDefault().post(zhihuDetail);
            }
        });
        http.get();
    }
}
