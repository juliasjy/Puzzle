package com.rosehulman.edu.Utils;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by suj1 on 2/12/2017.
 */

public class InputName implements Input.TextInputListener{
    private static String text;

    @Override
    public void input(String text) {
        this.text = text;
    }

    @Override
    public void canceled() {

    }

    public String getText(){
        return this.text;
    }
}
