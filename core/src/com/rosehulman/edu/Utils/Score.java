package com.rosehulman.edu.Utils;

import com.badlogic.gdx.Gdx;
import com.rosehulman.edu.Puzzle;

import java.io.Serializable;
import java.util.Collections;

/**
 * Created by suj1 on 2/12/2017.
 */

public class Score {
    public final int NUM_SCORES = 10;
    private int[] highScores;
    private String[] names;
    private static int score;


    public Score(){
        highScores = new int[NUM_SCORES];
        names = new String[NUM_SCORES];
        score = 0;
    }

    public void init(){
        for(int i = 0; i < NUM_SCORES ;i ++){
            highScores[i] = (10 - i);
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
            Gdx.app.log("TTT", "is high");
            sortHighScores();
        }
    }

    public void sortHighScores(){
        for(int i = 0; i < NUM_SCORES; i ++){
            int index = i;
            for(int j = i + 1 ; j < NUM_SCORES; j ++){
                if(highScores[j] > highScores[index]) {
                    index = j;
                }
            }
            int score = highScores[index];
            String name = names[index];
            highScores[index] = highScores[i];
            highScores[i] = score;
            names[index] = names[i];
            names[i] = name;
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

    public void addScore(int i){
        score = score + i;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int s){
        score = s;
    }

}
