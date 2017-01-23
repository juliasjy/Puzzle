package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Utils.Utils;


/**
 * Created by mot on 1/14/17.
 */

public class MenuScreen implements Screen {
    private Texture start_button;
    private Texture help_button;
    private Texture setting_button;
    private Texture level_button;
    private Texture background;

    public static final double BUTTON_HEIGHT_RATIO = 0.15;
    public static final double BUTTON_WIDTH_RATIO = 0.3;



    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Puzzle game;

    public MenuScreen(Puzzle game) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new StretchViewport(Utils.scaleWithPPM(this.game.V_WIDTH), Utils.scaleWithPPM(this.game.V_HEIGHT), gameCam);
        this.start_button = new Texture("button_start.png");

        this.help_button = new Texture("button_help.png");
        this.setting_button = new Texture("button_setting.png");
        this.level_button = new Texture("button_level.png");
        this.background = new Texture("background.jpg");

        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);
    }

    @Override
    public void show() {

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
        batch.draw(this.start_button, (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                                      (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.8  - this.BUTTON_HEIGHT_RATIO)) / 2,
                                      (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * BUTTON_WIDTH_RATIO),
                                      (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * BUTTON_HEIGHT_RATIO));

        batch.draw(this.level_button, (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * (1.3  - this.BUTTON_HEIGHT_RATIO)) / 2,
                (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * BUTTON_WIDTH_RATIO),
                (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * BUTTON_HEIGHT_RATIO));


        batch.draw(this.setting_button, (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.8  - this.BUTTON_HEIGHT_RATIO)) / 2,
                (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * BUTTON_WIDTH_RATIO),
                (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * BUTTON_HEIGHT_RATIO));

        batch.draw(this.help_button, (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * (1 - this.BUTTON_WIDTH_RATIO) ) / 2,
                (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * (0.3  - this.BUTTON_HEIGHT_RATIO)) / 2,
                (float) (Utils.scaleWithPPM(this.game.V_WIDTH) * BUTTON_WIDTH_RATIO),
                (float) (Utils.scaleWithPPM(this.game.V_HEIGHT) * BUTTON_HEIGHT_RATIO));




        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
        this.background.dispose();
        this.start_button.dispose();
        this.setting_button.dispose();
        this.help_button.dispose();
        this.level_button.dispose();
    }


    public void handleInput() {
        if (Gdx.input.justTouched()) {
            System.out.println("hello world");
            this.game.setScreen(new PlayScreen(this.game));
        }
    }

    public void update(float dt) {
        this.handleInput();
    }
}
