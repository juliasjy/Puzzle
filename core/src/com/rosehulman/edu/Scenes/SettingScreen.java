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
import com.rosehulman.edu.Sounds.MyMusic;
import com.rosehulman.edu.Sounds.MySoundEffect;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by suj1 on 1/22/2017.
 */

public class SettingScreen extends MyScreen {
    private Texture background;
    private Texture return_button;
    private Texture mute_button;
    private Texture unmute_button;
    private MyMusic music;
    private MySoundEffect soundEffect = new MySoundEffect("sounds/die.wav");
    private float BUTTON_WIDTH_RATIO = 0.25f;
    private float BUTTON_HEIGHT_RATIO = 0.05f;

    public SettingScreen(Puzzle game, MyScreen parent, MyMusic music) {
        super(game, parent);
        this.music = music;
        this.background = new Texture("setting_background.jpg");
        this.return_button = new Texture("buttons/button_return.png");
        this.mute_button = new Texture("buttons/button_mute.png");
        this.unmute_button = new Texture("buttons/button_unmute.png");
        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);
        createStage();
    }


    @Override
    public void createActors(final Stage stage){
        float side = (Utils.scaleWithPPM(this.game.V_WIDTH) * 0.1f);
        float width = (Utils.scaleWithPPM(this.game.V_WIDTH) * BUTTON_WIDTH_RATIO);
        float height = (Utils.scaleWithPPM(this.game.V_HEIGHT) * BUTTON_HEIGHT_RATIO);
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

        Vector2 musicMuteButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 4,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.3f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener musicMuteButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "mute");
                music.pauseMusic();
                parentScreen.setMute(true);
            }
        };
        Actor musicMuteActor = createActorForButton(this.mute_button, musicMuteButtonPosition, width, height, musicMuteButtonListener);
        stage.addActor(musicMuteActor);

        Vector2 soundMuteButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 4,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.7f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener soundMuteButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "mute");
                soundEffect.setMute(true);
            }
        };
        Actor soundMuteActor = createActorForButton(this.mute_button, soundMuteButtonPosition, width, height, soundMuteButtonListener);
        stage.addActor(soundMuteActor);

        Vector2 musicunMuteButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) * 3) / 4,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.3f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener musicunMuteButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "unmute");
                music.contiuneMusic();
                parentScreen.setMute(false);
            }
        };
        Actor musicunMuteActor = createActorForButton(this.unmute_button, musicunMuteButtonPosition, width, height, musicunMuteButtonListener);
        stage.addActor(musicunMuteActor);

        Vector2 soundunMuteButtonPosition = new Vector2((Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) * 3 ) / 4,
                (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.7f  - this.BUTTON_HEIGHT_RATIO)) / 2);
        ClickListener soundunMuteButtonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Click", "unmute");
                soundEffect.setMute(false);
            }
        };
        Actor soundunMuteActor = createActorForButton(this.unmute_button, soundunMuteButtonPosition, width, height, soundunMuteButtonListener);
        stage.addActor(soundunMuteActor);
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
