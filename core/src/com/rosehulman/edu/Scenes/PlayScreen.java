package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Sprites.Hero;
import com.rosehulman.edu.Sprites.Weapon.commonWeapon;
import com.rosehulman.edu.Tools.B2WorldCreator;
import com.rosehulman.edu.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.SysexMessage;

/**
 * Created by mot on 1/14/17.
 */

public class PlayScreen implements Screen {

    //game related
    private Puzzle game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private List<commonWeapon> weaponList = new ArrayList<commonWeapon>();
    private List<commonWeapon> weaponToRemove = new ArrayList<commonWeapon>();

    //tile map related
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    private TextureAtlas heroAtlas;
    private TextureAtlas bulletsAtlas;

    //box2d related
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator b2wc;

    private Hero hero;

    public PlayScreen(Puzzle game) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("tiledMap/Fly.tmx");

        this.heroAtlas = new TextureAtlas("HeroSheet.txt");
        this.bulletsAtlas = new TextureAtlas("SpriteSheet/bullets.txt");
        this.renderer = new OrthogonalTiledMapRenderer(map, 1.0f / this.game.PPM);

        this.game = game;

        this.gameCam = new OrthographicCamera();
        this.gamePort = new StretchViewport(Utils.scaleWithPPM(this.game.V_WIDTH), Utils.scaleWithPPM(this.game.V_HEIGHT), gameCam);

        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        this.b2wc = new B2WorldCreator();
        b2wc.addStaicBodiesToWorld(world, map);

        hero = new Hero(world,this);
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
        game.getBatch().setProjectionMatrix(gameCam.combined);
        this.game.getBatch().begin();
        this.hero.draw(this.game.getBatch());
        for (commonWeapon weapon : weaponList) {
            weapon.draw(this.game.getBatch());
        }
        this.game.getBatch().end();
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
        this.hero.handleInput(dt);
    }


    public void update(float dt) {
        this.handleInput(dt);

        world.step(1/60f, 6, 2);
//        gameCam.position.x = hero.body.getPosition().x;
        gameCam.position.y = hero.body.getPosition().y;
        gameCam.update();
        hero.update(dt);

        for (commonWeapon weapon : weaponList) {
//            System.out.println("Weapon" + weapon.body.getPosition().y);
//            System.out.println("Cam " + gameCam.position.y);
            weapon.update(dt);
            if (weapon.body.getPosition().y - gameCam.position.y > this.game.V_HEIGHT / 2.0f / Puzzle.PPM) {
                weaponToRemove.add(weapon);
            }
        }
//        System.out.print("removing weapons");

        for (commonWeapon weapon : weaponToRemove) {
            weaponList.remove(weapon);
//            System.out.print("weapon removed");
        }
        weaponToRemove.clear();

        renderer.setView(gameCam);

    }

    public TextureAtlas getHeroAtlas()
    {
        return this.heroAtlas;
    }

    public TextureAtlas getBulletsAtlas() {
        return this.bulletsAtlas;
    }

    public void addBullet(commonWeapon weapon) {
        this.weaponList.add(weapon);
    }

}
