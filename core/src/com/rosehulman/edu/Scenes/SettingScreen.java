package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by suj1 on 1/22/2017.
 */

public class SettingScreen extends MyScreen {
    private Texture background;
    private Texture return_button;

    public SettingScreen(Puzzle game, MyScreen parent) {
        super(game, parent);
        this.background = new Texture("background.jpg");
        this.return_button = new Texture("button_return.png");
        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);
        createStage();
    }

    @Override
    public void createActors(Stage stage){
        float side = (Utils.scaleWithPPM(this.game.V_WIDTH) * 0.1f);
        Vector2 returnButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (0.05f) ) / 2,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.05f)) / 2);
        ClickListener returnButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(parentScreen);
                Gdx.app.log("Click", "Menu");
            }
        };
        Actor returnActor = createActorForButton(this.return_button, returnButtonPosition, side, side, returnButtonListener);
        stage.addActor(returnActor);
    }


    @Override
    public void show() {
        stage = new Stage(this.gamePort);
        stage.getViewport().update((int) Utils.scaleWithPPM(this.game.V_WIDTH),(int) Utils.scaleWithPPM(this.game.V_HEIGHT));
        createActors(stage);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = this.game.getBatch();

        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        batch.draw(this.background, 0, 0, Utils.scaleWithPPM(this.game.V_WIDTH), Utils.scaleWithPPM(this.game.V_HEIGHT));

        batch.end();
        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.gamePort.update(width, height);
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
        stage.dispose();
    }

    public void handleInput() {

    }

    public void update(float dt) {
        handleInput();
    }
}
