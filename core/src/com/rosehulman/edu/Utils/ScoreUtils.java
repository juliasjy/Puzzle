package com.rosehulman.edu.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rosehulman.edu.Tools.ScoreKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 2/13/17.
 */

public class ScoreUtils {

    public static String DEFAULT_FILE_NAME = "highScores.txt";

    //Takes either a score keeper or a list of scores
    //Using default file name while file name is not provided
    public static void saveToFile(ScoreKeeper sk) {
        ScoreUtils.saveToFile(DEFAULT_FILE_NAME, sk);
    }
    public static void saveToFile(String fileName, ScoreKeeper sk) {
        ScoreUtils.saveToFile(fileName, sk.getScores());
    }
    public static void saveToFile(List<Score> scores) {
        ScoreUtils.saveToFile(DEFAULT_FILE_NAME, scores);
    }
    public static void saveToFile(String fileName, List<Score> scores){
        FileHandle f = Gdx.files.local(fileName);
        try{
            //clear the previous file
            f.write(false);
            System.out.println("writing");
            for(int i  = 0; i < scores.size(); i++) {
                Score sc = scores.get(i);
                f.writeString(sc.getPlayer() + " " + sc.getScore() + "\n",true);
            }
        } catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }
    public static List<Score> loadScores() {
        return ScoreUtils.loadScores(DEFAULT_FILE_NAME);
    }
    public static List<Score> loadScores(String fileName){
        List<Score> result = new ArrayList<Score>();
        FileHandle f = Gdx.files.local(fileName);
        try{
            String[] Score = f.readString().split("\n");
            for(int i = 0 ; i < Score.length; i++){
                String[] playerScore = Score[i].split(" ");
                Score score = new Score(Integer.parseInt(playerScore[1]), playerScore[0]);
                result.add(score);
            }
        } catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
        return result;
    }
}
