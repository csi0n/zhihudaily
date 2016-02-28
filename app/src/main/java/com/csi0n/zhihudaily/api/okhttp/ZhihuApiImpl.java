package com.csi0n.zhihudaily.api.okhttp;

import com.csi0n.zhihudaily.api.I_ZhihuApi;
import com.csi0n.zhihudaily.utils.NetWorkException;
import com.csi0n.zhihudaily.utils.model.response.ZhihuDetail;
import com.csi0n.zhihudaily.utils.model.response.ZhihuLatest;
import com.csi0n.zhihudaily.utils.model.response.ZhihuStartInfo;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDailyRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuDetailRequest;
import com.csi0n.zhihudaily.utils.model.request.ZhihuStartInfoRequest;


/**
 * Created by chqss on 2016/2/27 0027.
 */
public class ZhihuApiImpl implements I_ZhihuApi {
    @Override
    public ZhihuStartInfo getStartInfo(ZhihuStartInfoRequest zhihuStartInfoRequest) throws NetWorkException {
        return null;
    }

    @Override
    public ZhihuLatest getZhihuDaily(ZhihuDailyRequest zhihuDailyRequest) throws NetWorkException {
        return null;
    }

    @Override
    public ZhihuDetail getZhihuDetail(ZhihuDetailRequest zhihuDetailRequest) throws NetWorkException {
        return null;
    }

   /* private OkHttpClient okHttpClient;
    private Context mContext;

    public ZhihuApiImpl(Context context) {
        okHttpClient = OkHttpUtil.newOkHttpClient();
        this.mContext = context;
    }

    @Override
    public void getStartInfo(int width, int height) {
        String url = String.format("start-image/%d*%d", width, height);
        get(url, ZhihuStartInfo.class);
    }

    @Override
    public void getZhihuDaily() {
        String url = String.format("news/latest");
        get(url, ZhihuLatest.class);
    }

    @Override
    public void getZhihuDetail(int id) {
        String url = String.format("news/%d", id);
        get(url, ZhihuDetail.class);
    }

    private void get(String url, final Class<?> tClass) {
        Request request = new Request.Builder()
                .url(Config.ZHIHU_BASE_URL + url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                CLog.showNormalError(1002, "IO异常");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    final String body = response.body().string();
                    CLog.iMessage("---------html----------" + body);
                    EventBus.getDefault().post(App.gson.fromJson(body, tClass));
                } else {
                    CLog.showNormalError(1003, "数据为空");
                }
            }
        });
    }*/
}
