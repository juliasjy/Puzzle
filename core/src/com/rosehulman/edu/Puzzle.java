package com.rosehulman.edu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rosehulman.edu.Scenes.MenuScreen;

public class Puzzle extends Game {
	private SpriteBatch batch;
	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 800;
	public static final float PPM = 100;


	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this, null));
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
}
