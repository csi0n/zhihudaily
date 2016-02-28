package com.csi0n.zhihudaily.utils.model.response;

import com.csi0n.zhihudaily.utils.model.Stories;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhihuLatest extends BaseResponse {
    private String date;
    private List<Stories> stories;
    private List<Stories> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Stories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<Stories> top_stories) {
        this.top_stories = top_stories;
    }
}
