package com.csi0n.zhihudaily.ui.fragment;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.adapter.ZhiHuDailyAdapter;
import com.csi0n.zhihudaily.api.okhttp.ZhihuApiImpl;
import com.csi0n.zhihudaily.domain.ZhihuDomain;
import com.csi0n.zhihudaily.domain.impl.ZhihuDomainImpl;
import com.csi0n.zhihudaily.utils.AdvancedSubscriber;
import com.csi0n.zhihudaily.utils.model.response.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;
import com.demievil.library.RefreshLayout;
import com.google.inject.Inject;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bingoogolapple.bgabanner.BGABanner;
import rx.Observable;

/**
 * Created by chqss on 2016/2/28 0028.
 */

public class ZhihuDailyFragment2 extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(value = R.id.swipe_container)
    RefreshLayout mRefreshLayout;
    @Bind(value = R.id.list)
    ListView mList;
    private ZhiHuDailyAdapter adapter;
    private ZhihuDomain zhihuDomain;

    public Observable<ZhihuLatest> doGetDailyInfo() {
        return zhihuDomain.getZhihuDaily();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.frag_zhihu_daily_detail_2;
    }

    @Override
    protected void initWidget() {
        zhihuDomain = new ZhihuDomainImpl();
        mRefreshLayout.setChildView(mList);
        adapter = new ZhiHuDailyAdapter(this);
        mList.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(this);
        doGetDailyInfo()
                .subscribe(new AdvancedSubscriber<ZhihuLatest>() {
                    @Override
                    public void onHandleSuccess(ZhihuLatest response) {
                        super.onHandleSuccess(response);
                        update(response);
                    }
                });
    }

    @Subscribe
    public void update(final ZhihuLatest zhihuLatest) {
        adapter.notifyDataSetChanged(zhihuLatest);
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        doGetDailyInfo()
                .subscribe(new AdvancedSubscriber<ZhihuLatest>() {
                    @Override
                    public void onHandleSuccess(ZhihuLatest response) {
                        super.onHandleSuccess(response);
                        update(response);
                    }
                });

    }
}
