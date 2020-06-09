package com.btk.pagination.network;

import com.btk.pagination.model.Hits;
import com.btk.pagination.model.ImageDataResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("api")
    Call<ImageDataResponse> fetchData(@Query("key") String key, @Query("page") int page, @Query("per_page") int per_page);

    @GET("api")
    Call<ImageDataResponse> fetchData(@Query("key") String key, @QueryMap Map<String,Integer> queryParam);
}
