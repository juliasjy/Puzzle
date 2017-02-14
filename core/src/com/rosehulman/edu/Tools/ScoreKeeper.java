package com.rosehulman.edu.Tools;

import com.rosehulman.edu.Utils.Score;
import com.rosehulman.edu.Utils.ScoreUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by mot on 2/13/17.
 */

public class ScoreKeeper {
    private TreeSet<Score> scores;
    private int size;

    public ScoreKeeper(List<Score> scoreList) {
        this();
        for (Score s: scoreList) {
            scores.add(s);
            System.out.println(s);
        }
        while (scores.size() > this.size) {
           scores.remove(scores.first());
        }
    }

    public ScoreKeeper() {
        this.size = 10;
        this.scores = new TreeSet<Score>();
    }

    public int getLowestScore() {
        return scores.first().getScore();
    }

    public void addScore(Score score) {
        this.scores.add(score);
        if (this.scores.size() > this.size) {
            this.scores.remove(scores.first());
        }
        ScoreUtils.saveToFile(this);
    }

    public List<Score> getScores() {
        return new ArrayList<Score>(this.scores);
    }
}
