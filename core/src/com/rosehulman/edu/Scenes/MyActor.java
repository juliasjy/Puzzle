package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rosehulman.edu.Puzzle;

/**
 * Created by suj1 on 1/22/2017.
 */

public class MyActor extends Actor {
    Texture texture;
    private Vector2 position;
    float width;
    float height;

    public MyActor () {
    }

    public MyActor(Texture texture, Vector2 vec2, float width, float height){
        this.position = vec2;
        this.texture = texture;
        this.width = width;
        this.height = height;
        setBounds(position.x, position.y, width, height);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, this.position.x, this.position.y, this.width, this.height);
    }
}
