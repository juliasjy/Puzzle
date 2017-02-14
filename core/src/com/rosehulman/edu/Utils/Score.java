package com.rosehulman.edu.Utils;

/**
 * Created by mot on 2/13/17.
 */

public class Score implements Comparable {
    private int score;
    private String player;

    public Score(int score, String player) {
        this.score = score;
        this.player = player;
    }

    public Score(String player) {
        this(0, player);
    }

    public Score() {
        this(0, "DEFAULT PLAYER");
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getScore() {
        return this.score;
    }

    public String getPlayer() {
        return this.player;
    }


    @Override
    public int compareTo(Object o) {
        Score object = (Score) o;
        //Low to High
        if (this.score == object.score) {
            return this.player.compareTo(object.player);
        }
        return this.score - object.score;
    }

    @Override
    public String toString() {
        return this.player + " " + this.score;
    }
}
