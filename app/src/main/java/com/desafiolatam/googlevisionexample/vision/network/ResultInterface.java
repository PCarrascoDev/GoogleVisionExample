package com.desafiolatam.googlevisionexample.vision.network;

import com.desafiolatam.googlevisionexample.vision.models.payload.Payload;
import com.desafiolatam.googlevisionexample.vision.models.result.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Pedro on 26-07-2017.
 */

public interface ResultInterface {
    @POST("v1/images:annotate")
    Call<Result> newResult(@Query("key") String apiKey, @Body Payload payload);
}
