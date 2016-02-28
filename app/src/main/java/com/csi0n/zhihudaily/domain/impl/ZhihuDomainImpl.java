package com.csi0n.zhihudaily.domain.impl;

import com.csi0n.zhihudaily.api.I_ZhihuApi;
import com.csi0n.zhihudaily.api.retrofit.RZhihuApiImpl;
import com.csi0n.zhihudaily.domain.ZhihuDomain;
import com.csi0n.zhihudaily.utils.NetWorkException;
import com.csi0n.zhihudaily.utils.model.response.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDailyRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDetailRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuStartInfoRequest;
import com.google.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public class ZhihuDomainImpl implements ZhihuDomain {
    private I_ZhihuApi zhihuApi;

    public ZhihuDomainImpl() {
        zhihuApi = new RZhihuApiImpl();
    }


    @Override
    public Observable<ZhihuStartInfo> getStartInfo(int width, int height) {
        return Observable.just(new ZhihuStartInfoRequest(width, height))
                .flatMap(new Func1<ZhihuStartInfoRequest, Observable<ZhihuStartInfo>>() {
                    @Override
                    public Observable<ZhihuStartInfo> call(ZhihuStartInfoRequest request) {
                        try {
                            ZhihuStartInfo response = zhihuApi.getStartInfo(request);
                            return Observable.just(response);
                        } catch (NetWorkException e) {
                            return Observable.error(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ZhihuLatest> getZhihuDaily() {
        return Observable.just(new ZhihuDailyRequest())
                .flatMap(new Func1<ZhihuDailyRequest, Observable<ZhihuLatest>>() {
                    @Override
                    public Observable<ZhihuLatest> call(ZhihuDailyRequest request) {
                        try {
                            ZhihuLatest response = zhihuApi.getZhihuDaily(request);
                            return Observable.just(response);
                        } catch (NetWorkException e) {
                            return Observable.error(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ZhihuDetail> getZhihuDetail(int id) {
        return Observable.just(new ZhihuDetailRequest(id))
                .flatMap(new Func1<ZhihuDetailRequest, Observable<ZhihuDetail>>() {
                    @Override
                    public Observable<ZhihuDetail> call(ZhihuDetailRequest request) {
                        try {
                            ZhihuDetail response = zhihuApi.getZhihuDetail(request);
                            return Observable.just(response);
                        } catch (NetWorkException e) {
                            return Observable.error(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
