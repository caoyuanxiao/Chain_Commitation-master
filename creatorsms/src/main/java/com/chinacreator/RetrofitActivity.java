package com.chinacreator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chinacreator.bean.weather;
import com.chinacreator.http.RequestService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Smile on 2017/4/28.
 */

public class RetrofitActivity extends AppCompatActivity {

    public final String key = "7416ab7becf1e0e118070355bf9f1d36";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        findViewById(R.id.getDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://v.juhe.cn/")
                        //增加返回值为String的支持
                        .addConverterFactory(ScalarsConverterFactory.create())
                        //增加返回值为Gson的支持(以实体类返回)
                        .addConverterFactory(GsonConverterFactory.create())
                        //增加返回值为Oservable<T>的支持
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();




                final RequestService requestService = retrofit.create(RequestService.class);
                requestService.getPostWeatherData11("郴州", 1, key).subscribeOn(Schedulers.io()).filter(new Func1<weather, Boolean>() {
                    @Override
                    public Boolean call(weather weather) {
                        return weather.getResultcode().equals(200);
                    }
                }).subscribe(new Subscriber<weather>() {
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
                        System.out.println("onnext:" + weather.getResultcode());
                    }
                });

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
    }
}
