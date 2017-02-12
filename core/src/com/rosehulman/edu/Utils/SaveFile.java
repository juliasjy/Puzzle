package com.rosehulman.edu.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by suj1 on 2/12/2017.
 */

public class SaveFile {

    public static Score s;
    private static FileHandle f;

    public SaveFile(){
        init();
    }

    public static void save(){
        try{
            String[] names = s.getNames();
            int[] highScores = s.getHighScores();
            f.write(false);
            for(int i  = 0; i < s.NUM_SCORES; i++) {
                f.writeString(names[i] + " ", true);
                f.writeString(String.valueOf(highScores[i]) + "\n", true);
            }
        } catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static void load(){
        try{
            String[] names = new String[s.NUM_SCORES];
            int[] highScores = new int[s.NUM_SCORES];
            String[] people = f.readString().split("\n");
            for(int i = 0 ; i < s.NUM_SCORES; i++){
                String[] person = people[i].split(" ");
                names[i] = person[0];
                highScores[i] = Integer.parseInt(person[1]);
            }
            s.setNames(names);
            s.setHighScores(highScores);
        } catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }


    public static void init(){
        f = Gdx.files.local("highScores.txt");
        s = new Score();
        s.init();
        save();
    }
}
