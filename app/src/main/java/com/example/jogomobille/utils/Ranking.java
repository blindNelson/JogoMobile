package com.example.jogomobille.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ranking implements Serializable {

    @SerializedName("score")
    private int score;

    public Ranking(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "score= " + score;
    }
}
