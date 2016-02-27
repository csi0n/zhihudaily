package com.csi0n.zhihudaily.controller;

import android.text.TextUtils;
import android.view.View;

import com.csi0n.zhihudaily.utils.StringUtils;

import org.xutils.x;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public abstract class BaseController implements View.OnClickListener {
    public abstract void onClickReal(View view);

    protected String getString(int id) {
        return StringUtils.getString(id);
    }

    @Override
    public void onClick(View v) {
        onClickReal(v);
    }
}
