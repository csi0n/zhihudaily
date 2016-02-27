package com.csi0n.zhihudaily.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.csi0n.zhihudaily.adapter.ZhiHuDailyAdapter;
import com.csi0n.zhihudaily.api.ZhihuApi;
import com.csi0n.zhihudaily.ui.fragment.ZhiHuDailyFragment;
import com.csi0n.zhihudaily.utils.model.ZhihuLatest;
import com.squareup.picasso.Picasso;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhiHuDailyController extends BaseController implements BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {
    private ZhiHuDailyFragment mZhiHuDailyFragment;
    private ZhiHuDailyAdapter adapter;

    public ZhiHuDailyController(ZhiHuDailyFragment zhiHuDailyFragment) {
        this.mZhiHuDailyFragment = zhiHuDailyFragment;
    }

    public void initZhiHuDaily() {
        adapter = new ZhiHuDailyAdapter(mZhiHuDailyFragment.aty);
        mZhiHuDailyFragment.setAdapter(adapter);
        ZhihuApi zhihuApi = new ZhihuApi();
        zhihuApi.getZhihuDaily();
    }

    @Override
    public void onClickReal(View view) {

    }

    public void onEvent(ZhihuLatest zhihuLatest) {
        mZhiHuDailyFragment.views.clear();
        mZhiHuDailyFragment.strings.clear();
        for (int i = 0; i < zhihuLatest.getTop_stories().size(); i++) {
            mZhiHuDailyFragment.views.add(getPageView(zhihuLatest.getTop_stories().get(i).getImage()));
            mZhiHuDailyFragment.strings.add(zhihuLatest.getTop_stories().get(i).getTitle());
        }
        mZhiHuDailyFragment.setBannerData(mZhiHuDailyFragment.views, mZhiHuDailyFragment.strings);
        adapter.datas = zhihuLatest.getStories();
        adapter.notifyDataSetChanged();
    }

    private View getPageView(String url) {
        ImageView imageView = new ImageView(mZhiHuDailyFragment.aty);
        Picasso.with(mZhiHuDailyFragment.aty).load(url).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mZhiHuDailyFragment.startDetail(adapter.getItem(position));
    }
}
