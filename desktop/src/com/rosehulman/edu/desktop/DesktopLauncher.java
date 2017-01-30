package com.rosehulman.edu.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rosehulman.edu.Puzzle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Puzzle.V_WIDTH;
		config.height = Puzzle.V_HEIGHT;
		new LwjglApplication(new Puzzle(), config);
	}
}
