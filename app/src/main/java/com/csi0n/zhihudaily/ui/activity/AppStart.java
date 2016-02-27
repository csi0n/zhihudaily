package com.csi0n.zhihudaily.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.controller.AppStartController;
import com.csi0n.zhihudaily.utils.model.ZhihuStartInfo;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chqss on 2016/2/27 0027.
 */
@ContentView(R.layout.aty_app_start)
public class AppStart extends BaseActivity {
    private AppStartController mAppStartController;
    @ViewInject(value = R.id.iv_img)
    private ImageView mStartPic;
    @ViewInject(value = R.id.tv_text)
    private TextView mTVtext;

    @Override
    protected void initWidget() {
        mAppStartController = new AppStartController(this);
        mAppStartController.initAppStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(ZhihuStartInfo zhihuStartInfo) {
        mAppStartController.onEvent(zhihuStartInfo);
    }

    public void setStartPic(String url) {
        Picasso.with(aty).load(url).into(mStartPic);
    }

    public void setText(String string) {
        mTVtext.setText(string);
    }

    public void startMain() {
        skipActivity(aty, Main.class);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
