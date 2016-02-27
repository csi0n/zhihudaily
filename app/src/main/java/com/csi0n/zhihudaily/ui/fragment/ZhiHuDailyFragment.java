package com.csi0n.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.csi0n.zhihudaily.Config;
import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.controller.ZhiHuDailyController;
import com.csi0n.zhihudaily.ui.activity.ZhiHuDailyDetailActivity;
import com.csi0n.zhihudaily.utils.model.Stories;
import com.csi0n.zhihudaily.utils.model.ZhihuLatest;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by chqss on 2016/2/27 0027.
 */
@ContentView(R.layout.frag_zhihu_daily)
public class ZhiHuDailyFragment extends BaseFragment {
    private ZhiHuDailyController mZhiHuDailyController;
    @ViewInject(value = R.id.refreshLayout)
    private BGARefreshLayout mBGARefreshLayout;
    @ViewInject(value = R.id.banner)
    private BGABanner mBGABanner;
    @ViewInject(value = R.id.list)
    private ListView mList;
    public List<View> views;
    public List<String> strings;

    @Override
    protected void initWidget() {
        views = new ArrayList<>();
        strings = new ArrayList<>();
        EventBus.getDefault().register(this);
        mZhiHuDailyController = new ZhiHuDailyController(this);
        mZhiHuDailyController.initZhiHuDaily();
        mList.setOnItemClickListener(mZhiHuDailyController);
        mBGARefreshLayout.setDelegate(mZhiHuDailyController);
        mBGARefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(x.app(), false));
        mBGARefreshLayout.setPullDownRefreshEnable(false);
    }

    public void setBannerData(List<View> views, List<String> strings) {
        mBGABanner.setViewsAndTips(views, strings);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(ZhihuLatest zhihuLatest) {
        mZhiHuDailyController.onEvent(zhihuLatest);
    }


    public void setAdapter(BaseAdapter adapter) {
        mList.setAdapter(adapter);
    }

    public void startDetail(Stories stories) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.MARK_ZHI_HU_DAILY_DETAIL_ACTIVITY_STOREY, stories);
        startActivity(ZhiHuDailyDetailActivity.class, bundle);
    }
}
