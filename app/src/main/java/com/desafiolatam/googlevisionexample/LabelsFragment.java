package com.desafiolatam.googlevisionexample;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.desafiolatam.googlevisionexample.vision.models.payload.Payload;
import com.desafiolatam.googlevisionexample.vision.models.result.Result;
import com.desafiolatam.googlevisionexample.vision.network.GetResult;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class LabelsFragment extends Fragment{

    private LabelsAdapter adapter;
    public LabelsCallback callback;
    private Bitmap imageBitmap;

    public LabelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_labels, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callback = (LabelsCallback) getActivity();
        RecyclerView recyclerView = (RecyclerView) view;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new LabelsAdapter();
        recyclerView.setAdapter(adapter);
    }


    private class UpdateAdapter extends GetResult{

        private ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("BACKGROUND", "onPreExecute:");
            loading = new ProgressDialog(getActivity());
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.setCancelable(false);
            loading.setMessage("Encoding image...");
            loading.show();
        }

        @Override
        protected Result doInBackground(Payload... params) {
            Log.d("BACKGROUND", "this is backgrounded");
            return super.doInBackground(new Payload(imageBitmap).addRequestFeature(GetResult.LABEL_DETECTION, 20));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values[0] == 1)
            {
                loading.setMessage("Sending to Vision Api");
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            if (result != null)
            {
                /*List<LabelAnnotation> labels = new ArrayList<>();
                Collections.addAll(labels, result.getResponses()[0].getLabelAnnotations());*/
                adapter.updateAdapter(Arrays.asList(result.getResponses()[0].getLabelAnnotations()));
                loading.dismiss();
                Toast.makeText(getContext(), "Labels found: " + result.getResponses()[0].getLabelAnnotations().length, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        imageBitmap = (Bitmap) fab.getTag();

        //imageBitmap = callback.getBitmap();
        if (imageBitmap != null)
        {
            new UpdateAdapter().execute();
        }
    }
}
