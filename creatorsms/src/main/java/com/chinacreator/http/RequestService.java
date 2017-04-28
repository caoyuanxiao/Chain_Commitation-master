package com.chinacreator.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Smile on 2017/4/28.
 */

public interface RequestService {
    @GET("http://v.juhe.cn/weather/index")
    Call<String> getWeatherData(@Query("cityname") String cityname, @Query("format") int type,@Query("key") String key);
}
