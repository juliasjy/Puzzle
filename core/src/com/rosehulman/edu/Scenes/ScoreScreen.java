package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Utils.Score;
import com.rosehulman.edu.Utils.Utils;

import java.util.List;

/**
 * Created by suj1 on 2/12/2017.
 */

public class ScoreScreen extends MyScreen {
    private Texture background;
    private Texture return_button;
    private String highScore = "High Scores";
    private List<Score> scoreList;
    private BitmapFont font;

    public ScoreScreen(Puzzle game, MenuScreen parent) {
        super(game, parent);
        this.background = new Texture("score_background.jpg");
        this.return_button = new Texture("buttons/button_return.png");
        this.scoreList = game.sk.getScores();
        this.font = game.font;
        this.gamePort = new StretchViewport(this.game.V_WIDTH, this.game.V_HEIGHT, gameCam);
        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);
        createStage();
    }

    @Override
    public void createActors(Stage stage){
        float side = (this.game.V_WIDTH * 0.1f);
        Vector2 returnButtonPosition = new Vector2((this.game.V_WIDTH * (0.05f) ) / 2,
                (this.game.V_HEIGHT * (0.05f)) / 2);
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



        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.padTop(200f);

        for (Score s : scoreList) {
            Label scoreLabel = new Label(String.format("%08d", s.getScore()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
            Label nameLabel = new Label(s.getPlayer(), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
            scoreLabel.setFontScale(3f);
            nameLabel.setFontScale(3f);

            table.add(scoreLabel).expandX().padTop(15f);
            table.add(nameLabel).expandX().padTop(15f);
            table.row();
        }
        stage.addActor(table);


    }


    @Override
    public void show() {
//        createStage();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);





        SpriteBatch batch = this.game.getBatch();
//        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        batch.draw(this.background, 0, 0, Utils.scaleWithPPM(this.game.V_WIDTH), Utils.scaleWithPPM(this.game.V_HEIGHT));
        batch.end();


//        this.stage.act(delta);
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
        font.dispose();
    }

    public void handleInput() {
    }

    public void update(float dt) {
        handleInput();
    }

}
