package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Utils.Utils;


/**
 * Created by mot on 1/14/17.
 */

public class MenuScreen extends MyScreen{
    private Texture start_button;
    private Texture help_button;
    private Texture setting_button;
    private Texture level_button;
    private Texture background;
    private float BUTTON_WIDTH_RATIO = 0.3f;
    private float BUTTON_HEIGHT_RATIO = 0.13f;

    public MenuScreen(Puzzle game, MyScreen parent) {
        super(game, parent);
        this.start_button = new Texture("button_start.png");
        this.help_button = new Texture("button_help.png");
        this.setting_button = new Texture("button_setting.png");
        this.level_button = new Texture("button_level.png");
        this.background = new Texture("background.jpg");
        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);
        createMusic("music/level1.wav");
        createStage();
    }

    @Override
    public void createActors(final Stage stage){
        float width = (Utils.scaleWithPPM(this.game.V_WIDTH) * BUTTON_WIDTH_RATIO);
        float height = (Utils.scaleWithPPM(this.game.V_HEIGHT) * BUTTON_HEIGHT_RATIO);
        Vector2 startButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                                                  (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.8f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener startButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game, MenuScreen.this));
                stage.dispose();
                Gdx.app.log("Click", "Game");
                backgroundMusic.dispose();
            }
        };
        Actor startActor = createActorForButton(this.start_button, startButtonPosition, width, height, startButtonListener);

        Vector2 levelButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                                                  (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.3f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener levelButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelScreen(game, MenuScreen.this));
                Gdx.app.log("Click", "Level");
            }
        };
        Actor levelActor = createActorForButton(this.level_button, levelButtonPosition, width, height, levelButtonListener);

        Vector2 settingButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                                                    (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.8f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener settingButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new SettingScreen(game, MenuScreen.this));
                Gdx.app.log("Click", "Setting");
            }
        };
        Actor settingActor = createActorForButton(this.setting_button, settingButtonPosition, width, height, settingButtonListener);

        Vector2 helpButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                                                 (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.3f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener helpButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HelpScreen(game, MenuScreen.this));
                Gdx.app.log("Click", "Help");
            }
        };
        Actor helpActor = createActorForButton(this.help_button, helpButtonPosition, width, height, helpButtonListener);

        stage.addActor(startActor);
        stage.addActor(helpActor);
        stage.addActor(levelActor);
        stage.addActor(settingActor);
    }

    @Override
    public void show() {
        Gdx.app.log("Click", "show");
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
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
        Gdx.app.log("Click", "pause");
    }

    @Override
    public void resume() {
        Gdx.app.log("Click", "resume");
    }

    @Override
    public void hide() {
        Gdx.app.log("Click", "hide");
    }

    @Override
    public void dispose() {
        Gdx.app.log("Click", "dispose");
        this.stage.dispose();
        this.backgroundMusic.dispose();
    }

    public void handleInput() {

    }

    public void update(float dt) {
        handleInput();
    }
}
