package com.csi0n.zhihudaily.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.ui.fragment.BaseFragment;
import com.csi0n.zhihudaily.ui.fragment.TopItemFragment;
import com.csi0n.zhihudaily.ui.widget.RoundImageView;
import com.csi0n.zhihudaily.utils.model.Stories;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhiHuDailyAdapter extends BaseAdapter {
    private BaseFragment mContext;
    static final int TYPE_HEADER = 1;
    static final int TYPE_TITLE = 3;
    private List<Stories> tops;
    private List<Stories> datas;
    LayoutInflater layoutInflater;

    public ZhiHuDailyAdapter(BaseFragment context) {
        this.mContext = context;
        layoutInflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tops = new ArrayList<>();
        datas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (datas != null)
            return datas.size() + 1;
        else
            return 1;
    }

    @Override
    public Stories getItem(int position) {
        return datas.get(position-1);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_TITLE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewItemHolder viewItemHolder = null;
        ViewHeaderHolder viewHeaderHolder = null;
        if (convertView == null) {
            switch (getItemViewType(position)) {
                case TYPE_HEADER:
                    convertView = View.inflate(mContext.getActivity(), R.layout.include_view_pager, null);
                    viewHeaderHolder = new ViewHeaderHolder();
                    viewHeaderHolder.mViewPager = (ViewPager) convertView.findViewById(R.id.viewPager);
                    convertView.setTag(viewHeaderHolder);
                    break;
                case TYPE_TITLE:
                    convertView = View.inflate(mContext.getActivity(), R.layout.view_adapter_zhihu_daily_item, null);
                    viewItemHolder = new ViewItemHolder();
                    viewItemHolder.mhead = (RoundImageView) convertView.findViewById(R.id.ri_head);
                    viewItemHolder.mtv = (TextView) convertView.findViewById(R.id.tv_text);
                    convertView.setTag(viewItemHolder);
                    break;
            }


        } else {
            switch (getItemViewType(position)) {
                case TYPE_HEADER:
                    viewHeaderHolder = (ViewHeaderHolder) convertView.getTag();
                    break;
                case TYPE_TITLE:
                    viewItemHolder = (ViewItemHolder) convertView.getTag();
                    break;
            }
        }
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                viewHeaderHolder.mViewPager.setAdapter(new MyFragmentPagerAdapter(mContext, tops));
                break;
            case TYPE_TITLE:
                Picasso.with(mContext.getActivity()).load(getItem(position).getImages().get(0)).into(viewItemHolder.mhead);
                viewItemHolder.mtv.setText(getItem(position).getTitle());
                break;
        }
        return convertView;
    }


    public void notifyDataSetChanged(ZhihuLatest zhihulast) {
        datas.clear();
        datas = zhihulast.getStories();
        tops.clear();
        tops = zhihulast.getTop_stories();
        super.notifyDataSetChanged();
    }


    static class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        List<Stories> tops;

        public MyFragmentPagerAdapter(BaseFragment fm, List<Stories> tops) {
            super(fm.getFragmentManager());
            this.tops = tops;
        }

        @Override
        public Fragment getItem(int position) {
            TopItemFragment fragment = new TopItemFragment();
            fragment.setStories(tops.get(position));
            return fragment;
        }

        @Override
        public int getCount() {
            if (tops != null) {
                return tops.size();
            }
            return 0;
        }
    }


    private class ViewItemHolder {
        RoundImageView mhead;
        TextView mtv;
    }

    private class ViewHeaderHolder {
        ViewPager mViewPager;
    }
}
