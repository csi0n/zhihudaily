package com.csi0n.zhihudaily.controller;

import android.os.AsyncTask;
import android.view.View;

import com.csi0n.zhihudaily.Config;
import com.csi0n.zhihudaily.domain.ZhihuDomain;
import com.csi0n.zhihudaily.domain.impl.ZhihuDomainImpl;
import com.csi0n.zhihudaily.ui.activity.AppStart;
import com.csi0n.zhihudaily.utils.AdvancedSubscriber;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;
import com.google.inject.Inject;

import rx.Observable;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class AppStartController extends BaseController {
    private AppStart mAppStart;
    private TimeAsync timeAsync;

    public AppStartController(AppStart appStart) {
        this.mAppStart = appStart;
        zhihuDomain = new ZhihuDomainImpl();
    }

    // @Inject
    private ZhihuDomain zhihuDomain;

    public Observable<ZhihuStartInfo> doGetStartInfo() {
        return zhihuDomain.getStartInfo(1080, 1776);
    }

    public void initAppStart() {
        doGetStartInfo()
                .subscribe(new AdvancedSubscriber<ZhihuStartInfo>() {
                    @Override
                    public void onHandleSuccess(ZhihuStartInfo response) {
                        super.onHandleSuccess(response);
                        update(response);
                    }
                });
        timeAsync = new TimeAsync();
    }

    @Override
    public void onClickReal(View view) {

    }

    public void update(final ZhihuStartInfo zhihuStartInfo) {
        mAppStart.setStartPic(zhihuStartInfo.getImg());
        mAppStart.setText(zhihuStartInfo.getText());
    }

    private class TimeAsync extends AsyncTask<Void, Void, Void> {
        public TimeAsync() {
            this.execute(null, null, null, null, null);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(Config.APP_START_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAppStart.startMain();
        }

    }
}
