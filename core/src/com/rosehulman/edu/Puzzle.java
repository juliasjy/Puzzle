package com.rosehulman.edu;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.rosehulman.edu.Scenes.MenuScreen;
import com.rosehulman.edu.Tools.ScoreKeeper;
import com.rosehulman.edu.Utils.ScoreUtils;

public class Puzzle extends Game {
	public BitmapFont font;
	public ScoreKeeper sk;
	public SpriteBatch batch;
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 32 * 40;
	public static final float PPM = 100;


	@Override
	public void create () {
		sk = new ScoreKeeper(ScoreUtils.loadScores());
//		sk.addScore(new Score(500, "Jack"));
//		sk.addScore(new Score(300, "Jack"));
//		sk.addScore(new Score(101, "Jack"));
//		sk.addScore(new Score(211, "Jack"));
//		sk.addScore(new Score(221, "Jackkie"));
//		sk.addScore(new Score(101, "123"));
//		sk.addScore(new Score(211, "ooo"));
//		sk.addScore(new Score(221, "qwe"));
//		sk.addScore(new Score(201, "Jack"));
//		sk.addScore(new Score(241, "Jack"));
//		sk.addScore(new Score(101, "Jk"));
//		sk.addScore(new Score(211, "Jk"));
//		sk.addScore(new Score(221, "Jaie"));
//		ScoreUtils.saveToFile(sk);
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this, null));
		initBitmap();
	}

	@Override
	public void render () {
		super.render();
	}


	public SpriteBatch getBatch() {
		return this.batch;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void initBitmap(){

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Pacifico.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 5;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?";
		this.font = generator.generateFont(parameter);
		this.font.setColor(Color.WHITE);
		generator.dispose();
	}
}

