package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by suj1 on 1/22/2017.
 */

public class MyScreen implements Screen {

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Actor createActorForButton(Texture texture, Vector2 position, float width, float height, ClickListener listener) {
        MyActor actor = new MyActor(texture, position, width, height);
        actor.setTouchable(Touchable.enabled);
        actor.addListener(listener);
        return actor;
    }
}
