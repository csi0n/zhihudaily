package com.csi0n.zhihudaily.api.retrofit;

import com.csi0n.zhihudaily.api.I_ZhihuApi;
import com.csi0n.zhihudaily.utils.NetWorkException;
import com.csi0n.zhihudaily.utils.model.response.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDailyRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDetailRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuStartInfoRequest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public class RZhihuApiImpl implements I_ZhihuApi {
    I_RZhihuApi httpApi;

    public RZhihuApiImpl() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // set time out interval
        builder.readTimeout(15, TimeUnit.MINUTES);
        builder.connectTimeout(15, TimeUnit.MINUTES);
        builder.writeTimeout(15, TimeUnit.MINUTES);
        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        });

        httpApi = new Retrofit
                .Builder()
                .client(builder.build())
                .baseUrl("http://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(I_RZhihuApi.class);
    }

    @Override
    public ZhihuStartInfo getStartInfo(final ZhihuStartInfoRequest zhihuStartInfoRequest) throws NetWorkException {
        return new RetrofitAdapter<ZhihuStartInfo>() {
            @Override
            ZhihuStartInfo call() throws Exception {
                return httpApi.getStartInfo(zhihuStartInfoRequest.width, zhihuStartInfoRequest.height).execute().body();
            }
        }.get();
    }

    @Override
    public ZhihuLatest getZhihuDaily(final ZhihuDailyRequest zhihuDailyRequest) throws NetWorkException {

        return new RetrofitAdapter<ZhihuLatest>() {
            @Override
            ZhihuLatest call() throws Exception {
                return httpApi.getDaily().execute().body();
            }
        }.get();
    }


    @Override
    public ZhihuDetail getZhihuDetail(final ZhihuDetailRequest zhihuDetailRequest) throws NetWorkException {

        return new RetrofitAdapter<ZhihuDetail>() {
            @Override
            ZhihuDetail call() throws Exception {
                return httpApi.getNewsDetail(zhihuDetailRequest.id).execute().body();
            }
        }.get();
    }
}
