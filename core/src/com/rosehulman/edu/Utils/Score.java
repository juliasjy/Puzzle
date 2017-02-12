package com.rosehulman.edu.Utils;

import com.rosehulman.edu.Puzzle;

import java.io.Serializable;

/**
 * Created by suj1 on 2/12/2017.
 */

public class Score {
    public final int NUM_SCORES = 10;
    private int[] highScores;
    private String[] names;


    public Score(){
        highScores = new int[NUM_SCORES];
        names = new String[NUM_SCORES];
    }

    public void init(){
        for(int i = 0; i < NUM_SCORES ;i ++){
            highScores[i] = 10 * (10 - i);
            names[i] = "---";
        }
    }

    public boolean isHighScore(int score){
        return score > highScores[NUM_SCORES - 1];
    }

    public void addHighScore(int newScore, String name){
        if(isHighScore(newScore)){
            highScores[NUM_SCORES - 1] = newScore;
            names[NUM_SCORES - 1] = name;
            sortHighScores();
        }
    }

    public void sortHighScores(){
        for(int i = 0; i < NUM_SCORES; i ++){
            int score = highScores[i];
            String name = names[i];
            int j;
            for(j = i -1 ; j >= 0 && highScores[j] < score; j ++){
                highScores[j + 1] = highScores[j];
                names[j + 1] = names[j];
            }
            highScores[j + 1] = score;
            names[j + 1] = name;
        }
    }

    public int[] getHighScores() {
        return highScores;
    }

    public void setHighScores(int[] highScores) {
        this.highScores = highScores;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

}
