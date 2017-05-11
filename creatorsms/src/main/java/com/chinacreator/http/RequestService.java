package com.chinacreator.http;

import com.chinacreator.bean.PostBean;
import com.chinacreator.bean.weather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Smile on 2017/4/28.
 */

public interface RequestService {
    @POST("/weather/index")
    Call<weather> getWeatherData(@Query("cityname") String cityname, @Query("format") int type, @Query("key") String key);

    @POST("/weather/index")
    Call<weather> getMapWeatherData(@QueryMap Map<String, String> maps);

    @POST("/weather/index")
    Call<weather> getMapWeatherDataBean(@Body PostBean bean);


    @POST("index/")
    Call<weather> getPostWeatherData(@Query("cityname") String cityname, @Query("format") int type, @Query("key") String key);

    @GET("weather/index")
    Observable<weather> getPostWeatherData11(@Query("cityname") String cityname, @Query("format") int type, @Query("key") String key);
}
