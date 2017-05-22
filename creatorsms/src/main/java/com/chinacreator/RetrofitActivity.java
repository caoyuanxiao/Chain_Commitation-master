package com.chinacreator;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chinacreator.Rxjava.NetworkUtil;
import com.chinacreator.bean.PostBean;
import com.chinacreator.service.myIntentService;
import com.chinacreator.utils.RxBus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Smile on 2017/4/28.
 */

public class RetrofitActivity extends AppCompatActivity {

    public final String key = "7416ab7becf1e0e118070355bf9f1d36";
    TextView tv_response;
    private static final String TAG = "RetrofitActivity";
    Subscription subscribe;
    IBinder mIBinder;
    IMyAidlInterface myInterface;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBinder = service;
            System.out.println("连接上了服务");
            myInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.i(TAG, "连接Service 成功");
            try {
                int userCount = myInterface.getUserCount();
                System.out.println("服务器回来的数据" + userCount);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("断开连接");
        }
    };
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    Subscription update;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        sp = getSharedPreferences("mode", MODE_MULTI_PROCESS);
        sp.edit().putInt("count", sp.getInt("count", 0)).apply();

        tv_response = (TextView) findViewById(R.id.tv_response);

        tv_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* System.out.println(sp.getInt("count", -1));
                try {
                    int userCount = myInterface.getUserCount();
                    System.out.println(userCount);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/

                Intent intent = new Intent(RetrofitActivity.this, myIntentService.class);
                intent.setAction("UPDATE");
                startService(intent);

                // RxBus.get().post("update","1111");

            }
        });


        update = RxBus.get().register("update", PostBean.class).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<PostBean>() {
                    @Override
                    public void call(PostBean s) {
                        Toast.makeText(RetrofitActivity.this, "信息来了：" + s.toString(), Toast.LENGTH_SHORT).show();
                        tv_response.setText(s.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
       /* MyRxBus.getDefault().toObservable(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(RetrofitActivity.this, "信息来了：" + s, Toast.LENGTH_SHORT).show();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });*/

        findViewById(R.id.getDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // startService(new Intent(RetrofitActivity.this, AidlService.class));

               /* sp.edit().putInt("count", sp.getInt("count", -1) + 1).apply();
                Intent intent = new Intent();
                intent.setClassName(RetrofitActivity.this, "com.chinacreator.service.AidlService");
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);*/
                startActivity(new Intent(RetrofitActivity.this, SecondActivity.class));
                // Intent intent = new Intent(RetrofitActivity.this, AidlService.class);
                // bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

                //  startActivity(new Intent(RetrofitActivity.this, SecondActivity.class));


//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://v.juhe.cn/")
//                        //增加返回值为String的支持
//                        .addConverterFactory(ScalarsConverterFactory.create())
//                        //增加返回值为Gson的支持(以实体类返回)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        //增加返回值为Oservable<T>的支持
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                        .client(client)
//                        .build();

/*
                final RequestService requestService = RetrfitUtils.RETRFIT_UTILS.getRequestService(RetrofitActivity.this, "http://v.juhe.cn/", RequestService.class);

                RetrfitUtils.RETRFIT_UTILS.getNetWorkDate(requestService.getPostWeatherData11("郴州", 1, key)).subscribe(new Subscriber<weather>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(weather weather) {
                        tv_response.setText("请求结果:" + weather.getResultcode());
                    }
                });*/



                /*requestService.getPostWeatherData11("郴州", 1, key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new Subscriber<weather>() {
                            @Override
                            public void onCompleted() {

                                System.out.println("complete");
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("error:" + e.toString());
                            }

                            @Override
                            public void onNext(weather weather) {
                                //System.out.println("onnext:" + weather.getResultcode());
                                tv_response.setText(weather.getReason());
                            }
                        });*/

              /*  HashMap<String, String> maps = new HashMap<String, String>();
                maps.put("cityname", "郴州");
                maps.put("format", "1");
                maps.put("key", key);

                Call<weather> call = requestService.getMapWeatherDataBean(new PostBean("郴州", 1, key));
                // Call<weather> call = requestService.getWeatherData("郴州", 1, key);
                //Call<weather> call = requestService.getPostWeatherData("郴州", 1, key);
                call.enqueue(new Callback<weather>() {
                    @Override
                    public void onResponse(Call<weather> call, Response<weather> response) {
                        System.out.println("成功：" + response.body());
                    }

                    @Override
                    public void onFailure(Call<weather> call, Throwable t) {
                        System.out.println("请求失败");
                    }
                });*/
//                Call<String> call = requestService.getWeatherData("郴州", 1, key);
//                call.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        System.out.println("成功：" + response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        System.out.println("失败：" + t.toString());
//                    }
//                });

               /* requestService.getPostWeatherData11("郴州", 1, key).flatMap(new Func1<weather, Observable<weather>>() {
                    @Override
                    public Observable<weather> call(weather weather) {
                        System.out.println("郴州：" + weather.getReason());
                        if (weather.getResultcode().equals("200")) {

                            return requestService.getPostWeatherData11("北京", 1, key);
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io()).subscribe(new Action1<weather>() {
                    @Override
                    public void call(weather weather) {
                        System.out.println("北京：" + weather.getReason());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println(throwable.toString());
                    }
                });*/

                /*requestService.getPostWeatherData11("郴州", 1, key).subscribe(new Action1<weather>() {
                    @Override
                    public void call(weather weather) {
                        //System.out.println(weather.getReason());
                    }
                });*/
            }
        });


        subscribe = RxBus.get().register("111", PostBean.class).subscribe(new Action1<PostBean>() {
            @Override
            public void call(PostBean bean) {
                tv_response.setText("消息来了：" + bean.toString());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });


        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(1, TimeUnit.MINUTES);

                CacheControl cacheControl = cacheBuilder.build();


                Request request = chain.request();
                if (!NetworkUtil.hasNetwork(RetrofitActivity.this)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (NetworkUtil.hasNetwork(RetrofitActivity.this)) {
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


        File httpCacheDirectory = new File(getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        final OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache).build();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
        }

        if (update.isUnsubscribed()) {
            update.unsubscribe();
        }
    }
}
