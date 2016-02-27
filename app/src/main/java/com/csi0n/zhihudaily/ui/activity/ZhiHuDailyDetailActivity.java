package com.csi0n.zhihudaily.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi0n.zhihudaily.Config;
import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.controller.ZhiHuDailyDetailController;
import com.csi0n.zhihudaily.utils.HtmlUtil;
import com.csi0n.zhihudaily.utils.model.Stories;
import com.csi0n.zhihudaily.utils.model.ZhihuDetail;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by chqss on 2016/2/27 0027.
 */
@ContentView(R.layout.aty_zhihu_daily_detail)
public class ZhiHuDailyDetailActivity extends BaseActivity {
    private ZhiHuDailyDetailController mZhiHuDailyDetailController;
    @ViewInject(value = R.id.wv_news)
    private WebView mWvNews;
    @ViewInject(value = R.id.iv_header)
    private ImageView mImageView;
    @ViewInject(value = R.id.tv_source)
    private TextView mTvSource;
    @ViewInject(value = R.id.collapsingToolbarLayout)
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    @ViewInject(value = R.id.toolbar)
    private Toolbar mToolbar;
    @ViewInject(value = R.id.nested_view)
    private NestedScrollView mNestedScrollView;
    private Stories stories;

    @Override
    protected void initWidget() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            finish();
        else
            stories = (Stories) bundle.getSerializable(Config.MARK_ZHI_HU_DAILY_DETAIL_ACTIVITY_STOREY);
        //setHasOptionsMenu(true);
        mNestedScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        //mNestedScrollView.setElevation(0);
        mWvNews.setOverScrollMode(View.OVER_SCROLL_NEVER);
        //mWvNews.setElevation(0);
        mWvNews.getSettings().setLoadsImagesAutomatically(true);
        //设置 缓存模式
        mWvNews.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        mWvNews.getSettings().setDomStorageEnabled(true);
        //为可折叠toolbar设置标题
        mCollapsingToolbarLayout.setTitle(getString(R.string.app_name));
        EventBus.getDefault().register(this);
        mZhiHuDailyDetailController = new ZhiHuDailyDetailController(this);
        mZhiHuDailyDetailController.initZhiHuDailyDetail();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Stories getStories() {
        return stories;
    }

    @Subscribe
    public void onEvent(ZhihuDetail zhihuDetail) {
        mZhiHuDailyDetailController.onEvent(zhihuDetail);
    }

    public void setImageView(String url) {
        Picasso.with(this).load(url).into(mImageView);
    }

    public void setmTvSource(String string) {
        mTvSource.setText(string);
    }

    public void setmWvNews(String data) {
        mWvNews.loadData(data, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
