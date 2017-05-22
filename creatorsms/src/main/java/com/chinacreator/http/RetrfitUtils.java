package com.chinacreator.http;

import android.content.Context;

import com.chinacreator.Rxjava.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Smile on 2017/5/12.
 */

public enum RetrfitUtils {
    RETRFIT_UTILS;

    /**
     * 注意Okhttp只支持GET的缓存
     *
     * @param mContext
     * @return
     */
    public OkHttpClient getNetWorkClient(final Context mContext) {
        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(1, TimeUnit.MINUTES);

                CacheControl cacheControl = cacheBuilder.build();


                Request request = chain.request();
                if (!NetworkUtil.hasNetwork(mContext)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (NetworkUtil.hasNetwork(mContext)) {
                    int maxAge = 60; // read from cache  有网络时候设置缓存超时未0个小时
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {
                    // int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale  无网络设置缓存时间为4周
//                    int maxStale=60;
//                    return originalResponse.newBuilder()
//                            .removeHeader("Pragma")
//                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                            .build();

                }
                return originalResponse;
            }
        };

        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        final OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache).build();

        return client;
    }

    public <S> S getRequestService(Context mContext, String BaseUrl, Class<S> myApiService) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getNetWorkClient(mContext))
                .build();
        return retrofit.create(myApiService);
    }

    public <S> Observable<S> getNetWorkDate(Observable<S> observable) {
        Observable<S> observable1 = observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
        return observable1;
    }


}
