package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Tools.B2WorldCreator;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 1/14/17.
 */

public class PlayScreen extends MyScreen {

    //tile map related
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2d related
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator b2wc ;

    public PlayScreen(Puzzle game, MyScreen parent) {
        super(game, parent);
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("Puzzle.tmx");

        this.renderer = new OrthogonalTiledMapRenderer(map, 1 / this.game.PPM);
        
        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        this.b2wc = new B2WorldCreator();
        b2wc.addStaicBodiesToWorld(world, map);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, this.gameCam.combined);
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

    }


    public void handleInput(float dt) {
        if (Gdx.input.justTouched()) {
            this.gameCam.position.x = this.gameCam.position.x + dt;
        }
    }

    public void update(float dt) {
        this.handleInput(dt);
        world.step(1/60f, 6, 2);
        gameCam.update();
        renderer.setView(gameCam);
    }
}
