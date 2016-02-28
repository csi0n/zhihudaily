package com.csi0n.zhihudaily.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.controller.AppStartController;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class AppStart extends BaseActivity {

    private AppStartController mAppStartController;
    @Bind(value = R.id.iv_img)
    ImageView mStartPic;
    @Bind(value = R.id.tv_text)
    TextView mTVtext;

    @Override
    protected void setRootView() {
        setContentView(R.layout.aty_app_start);
    }

    @Override
    protected void initWidget() {
        mAppStartController = new AppStartController(this);
        mAppStartController.initAppStart();

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
        super.onDestroy();
    }
}
