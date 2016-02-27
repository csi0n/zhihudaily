package com.csi0n.zhihudaily.utils.model;

import java.io.Serializable;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhihuStartInfo implements Serializable{
    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
