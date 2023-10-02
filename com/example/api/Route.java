package com.example.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Route {
    @FormUrlEncoded
    @POST("/")
    Call<JsonObject> getPosts(@Field("number") int number, @Field("type") String type);
}
