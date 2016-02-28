package com.csi0n.zhihudaily.api.retrofit;

import com.csi0n.zhihudaily.utils.NetWorkException;
import com.csi0n.zhihudaily.utils.model.response.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public interface I_RZhihuApi {
    @GET("api/4/start-image/{width}*{height}")
    Call<ZhihuStartInfo> getStartInfo(@Path("width") int width, @Path("height") int height) throws NetWorkException;

    @GET("/api/4/news/latest")
    Call<ZhihuLatest> getDaily() throws NetWorkException;

    @GET("api/4/news/{id}")
    Call<ZhihuDetail> getNewsDetail(@Path("id") int id) throws NetWorkException;
}
