package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by suj1 on 1/22/2017.
 */

public class LevelScreen extends MyScreen {
    private Texture background;
    private Texture return_button;
    private Texture level1_button;
    private Texture level2_button;
    private Texture level3_button;
    private Texture level4_button;
    private float BUTTON_WIDTH_RATIO = 0.5f;
    private float BUTTON_HEIGHT_RATIO = 0.1f;

    public LevelScreen(Puzzle game, MyScreen parent) {
        super(game, parent);
        this.background = new Texture("level_background.jpg");
        this.return_button = new Texture("button_return.png");
        this.level1_button = new Texture("button_level-1.png");
        this.level2_button = new Texture("button_level-2.png");
        this.level3_button = new Texture("button_level-3.png");
        this.level4_button = new Texture("button_level-4.png");
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

        float width = (Utils.scaleWithPPM(this.game.V_WIDTH) * BUTTON_WIDTH_RATIO);
        float height = (Utils.scaleWithPPM(this.game.V_HEIGHT) * BUTTON_HEIGHT_RATIO);
        Vector2 level1ButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.4f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener level1ButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "level1");
                //TODO:
            }
        };
        Actor level1Actor = createActorForButton(this.level1_button, level1ButtonPosition, width, height, level1ButtonListener);

        Vector2 level2ButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.05f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener level2ButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "Level2");
                //TODO:
            }
        };
        Actor level2Actor = createActorForButton(this.level2_button, level2ButtonPosition, width, height, level2ButtonListener);

        Vector2 level3ButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.7f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener level3ButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "level3");
                //TODO:
            }
        };
        Actor level3Actor = createActorForButton(this.level3_button, level3ButtonPosition, width, height, level3ButtonListener);

        Vector2 level4ButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.35f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener level4ButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "level4");
                //TODO:
            }
        };
        Actor level4Actor = createActorForButton(this.level4_button, level4ButtonPosition, width, height, level4ButtonListener);

        stage.addActor(level1Actor);
        stage.addActor(level2Actor);
        stage.addActor(level3Actor);
        stage.addActor(level4Actor);
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
