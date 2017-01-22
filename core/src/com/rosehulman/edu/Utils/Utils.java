package com.rosehulman.edu.Utils;

import com.rosehulman.edu.Puzzle;

/**
 * Created by mot on 1/14/17.
 */

public class Utils {

    public static float scaleWithPPM(int x) {
        return x / Puzzle.PPM;
    }


    public static float scaleWithPPM(float x) {
        return x / Puzzle.PPM;
    }
}
