package com.desafiolatam.googlevisionexample.vision.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pedro on 26-07-2017.
 */

public class ResultInterceptor {

    private static final String BASE_URL ="https://vision.googleapis.com/";

    public ResultInterface get(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return interceptor.create(ResultInterface.class);
    }
}
