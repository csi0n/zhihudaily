package com.csi0n.zhihudaily.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csi0n.zhihudaily.R;
import com.csi0n.zhihudaily.ui.widget.RoundImageView;
import com.csi0n.zhihudaily.utils.model.Stories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhiHuDailyAdapter extends BaseAdapter {
    private Context mContext;
    public List<Stories> datas;

    public ZhiHuDailyAdapter(Context context) {
        this.mContext = context;
        datas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Stories getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.view_adapter_zhihu_daily_item, null);
            holder = new ViewHolder();
            holder.mhead = (RoundImageView) convertView.findViewById(R.id.ri_head);
            holder.mtv = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Picasso.with(mContext).load(getItem(position).getImages().get(0)).into(holder.mhead);
        holder.mtv.setText(getItem(position).getTitle());
        return convertView;
    }

    private class ViewHolder {
        RoundImageView mhead;
        TextView mtv;
    }
}
