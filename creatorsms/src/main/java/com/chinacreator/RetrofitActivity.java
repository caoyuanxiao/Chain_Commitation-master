package com.chinacreator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chinacreator.http.RequestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
                        //增加返回值为String的支持
                        .addConverterFactory(ScalarsConverterFactory.create())
                        //增加返回值为Gson的支持(以实体类返回)
                        .addConverterFactory(GsonConverterFactory.create())
                        //增加返回值为Oservable<T>的支持
                        // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                RequestService requestService = retrofit.create(RequestService.class);
                Call<String> call = requestService.getWeatherData("郴州", 1, key);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        System.out.println(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }
}
