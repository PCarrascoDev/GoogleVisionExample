package com.desafiolatam.googlevisionexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desafiolatam.googlevisionexample.vision.models.result.LabelAnnotation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 26-07-2017.
 */

public class LabelsAdapter extends RecyclerView.Adapter<LabelsAdapter.ViewHolder>{

    private List<LabelAnnotation> labelAnnotations = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_label, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LabelAnnotation annotation = labelAnnotations.get(position);
        String description = annotation.getDescription();
        description = description.substring(0, 1).toUpperCase() + description.substring(1);
        holder.textView.setText(description + " - " + new DecimalFormat("#0.0").format(annotation.getScore()*100) + "%");
    }

    @Override
    public int getItemCount() {
        return labelAnnotations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.labelTv);
        }
    }

    public void updateAdapter(List<LabelAnnotation> labelAnnotations)
    {
        this.labelAnnotations = labelAnnotations;
        notifyDataSetChanged();
    }
}
