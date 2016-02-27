package com.csi0n.zhihudaily.controller;

import android.os.AsyncTask;
import android.view.View;

import com.csi0n.zhihudaily.Config;
import com.csi0n.zhihudaily.api.ZhihuApi;
import com.csi0n.zhihudaily.ui.activity.AppStart;
import com.csi0n.zhihudaily.utils.model.ZhihuStartInfo;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class AppStartController extends BaseController {
    private AppStart mAppStart;
    private TimeAsync timeAsync;

    public AppStartController(AppStart appStart) {
        this.mAppStart = appStart;
    }

    public void initAppStart() {
        ZhihuApi zhihuApi = new ZhihuApi();
        zhihuApi.getStartInfo(1080, 1776);
        timeAsync = new TimeAsync();
    }

    @Override
    public void onClickReal(View view) {

    }

    public void onEvent(ZhihuStartInfo zhihuStartInfo) {
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
