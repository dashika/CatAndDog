package com.example.catanddog.api;

import com.example.catanddog.entities.AnimalData;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnimalAPIService {

    @GET("/xim/api.php")
    Call<AnimalData> getAnimal(@Query("query") String query);
}
