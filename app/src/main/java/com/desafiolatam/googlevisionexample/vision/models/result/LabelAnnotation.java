package com.desafiolatam.googlevisionexample.vision.models.result;

public class LabelAnnotation {
    private double score;
    private String mid;
    private String description;

    public double getScore() {
        return this.score;
    }

    public String getMid() {
        return this.mid;
    }

    public String getDescription() {
        return this.description;
    }
}
