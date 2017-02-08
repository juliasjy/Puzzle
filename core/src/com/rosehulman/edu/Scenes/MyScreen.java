package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by suj1 on 1/22/2017.
 */

public class MyScreen implements Screen {
    public Stage stage;

    //game related
    public OrthographicCamera gameCam;
    public Viewport gamePort;
    public Puzzle game;

    //parent screen
    public MyScreen parentScreen;

    public MyScreen(Puzzle game, MyScreen parent){
        this.game = game;
        this.parentScreen = parent;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new StretchViewport(Utils.scaleWithPPM(this.game.V_WIDTH), Utils.scaleWithPPM(this.game.V_HEIGHT), gameCam);
    }

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

    public void createStage () {
        stage = new Stage(this.gamePort);
        stage.getViewport().update((int) Utils.scaleWithPPM(this.game.V_WIDTH),(int) Utils.scaleWithPPM(this.game.V_HEIGHT));

        createActors(stage);
        Gdx.input.setInputProcessor(stage);
    }

    public void createActors(Stage stage){

    };

    public Actor createActorForButton(Texture texture, Vector2 position, float width, float height, ClickListener listener) {
        MyActor actor = new MyActor(texture, position, width, height);
        actor.setTouchable(Touchable.enabled);
        actor.addListener(listener);
        return actor;
    }
}
