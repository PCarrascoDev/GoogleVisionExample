package com.desafiolatam.googlevisionexample.vision.network;

import android.os.AsyncTask;

import com.desafiolatam.googlevisionexample.vision.models.payload.Payload;
import com.desafiolatam.googlevisionexample.vision.models.result.Result;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Pedro on 26-07-2017.
 */

public class GetResult extends AsyncTask<Payload, Integer, Result>{
    private static String apiKey = "AIzaSyCskFsu9ia6J6H_mid3F22MyxvCB7QiY9U";
    private ResultInterface resultInterface = new ResultInterceptor().get();
    public static final String LABEL_DETECTION = "LABEL_DETECTION";

    @Override
    protected Result doInBackground(Payload... params) {
        publishProgress(1);
        Result result = null;
        Call<Result> call = resultInterface.newResult(apiKey, params[0]);
        try {
            Response<Result> response = call.execute();
            if (response.isSuccessful())
            {
                result = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*List<LabelAnnotation> labelAnnotations = new ArrayList<>();
        Collections.addAll(labelAnnotations, result.getResponses()[0].getLabelAnnotations());*/
        return result;
    }

}
