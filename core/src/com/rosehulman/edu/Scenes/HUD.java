package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Interface.Temporal;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Utils.Score;

/**
 * Created by mot on 12/27/16.
 */

public class HUD implements Disposable, Temporal{
    public Stage stage;
    private Viewport viewport;
    private Score score;
    Label scoreLabel;

    public HUD(SpriteBatch sb, Score score) {
        this.score = score;
        viewport = new FitViewport(Puzzle.V_WIDTH, Puzzle.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score.getScore()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel.setFontScale(2f);
        table.row();
        table.add(scoreLabel).expandX().padRight(10f).padTop(0.2f);
        stage.addActor(table);
    }

    @Override
    public void dispose()
    {
        this.stage.dispose();
    }

    @Override
    public void update(float dt) {
        this.scoreLabel.setText(String.format("%06d", score.getScore()));
    }
}

