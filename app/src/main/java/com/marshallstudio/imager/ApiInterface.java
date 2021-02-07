package com.marshallstudio.imager;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("/api")
    Call<ImageData> getImagesData(@QueryMap Map<String, String> searchOptions);
}
