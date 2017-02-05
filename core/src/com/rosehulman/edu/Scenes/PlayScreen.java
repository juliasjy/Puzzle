package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Sprites.Animation.AbstractAnimationSprite;
import com.rosehulman.edu.Sprites.Bullet.AbstractBullet;
import com.rosehulman.edu.Sprites.GameObject.Enemies.Enemy;
import com.rosehulman.edu.Sprites.GameObject.Hero;
import com.rosehulman.edu.Tools.B2WorldCreator;
import com.rosehulman.edu.Tools.PuzzleContactListener;
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 1/14/17.
 */

public class PlayScreen implements Screen {

    //game related
    private Puzzle game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;


    private List<AbstractBullet> bulletList = new ArrayList<AbstractBullet>();
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<AbstractAnimationSprite> animationList = new ArrayList<AbstractAnimationSprite>();
    //tile map related
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    private ContactListener contactListener;

    public static TextureAtlas heroAtlas;
    public static  TextureAtlas bulletsAtlas;
    public static  TextureAtlas enemyAtlas;
    public static TextureAtlas explosionAtlas;
    //box2d related
    public World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator b2wc;

    private Hero hero;


    public PlayScreen(Puzzle game) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("tiledMap/Fly.tmx");



        this.heroAtlas = new TextureAtlas("SpriteSheet/HeroSheet.txt");
        this.bulletsAtlas = new TextureAtlas("SpriteSheet/bullets.txt");
        this.enemyAtlas = new TextureAtlas("SpriteSheet/SpaceShips.txt");
        this.explosionAtlas = new TextureAtlas("SpriteSheet/explosions.txt");

        this.renderer = new OrthogonalTiledMapRenderer(map, 1.0f / this.game.PPM);

        this.game = game;

        this.gameCam = new OrthographicCamera();
        this.gamePort = new StretchViewport(Utils.scaleWithPPM(this.game.V_WIDTH), Utils.scaleWithPPM(this.game.V_HEIGHT), gameCam);

        gameCam.position.set(Utils.scaleWithPPM(this.game.V_WIDTH) / 2, Utils.scaleWithPPM(this.game.V_HEIGHT) / 2 , 0);

        world = new World(new Vector2(0, 0), true);


        b2dr = new Box2DDebugRenderer();
        this.b2wc = new B2WorldCreator(this);


        b2wc.addStaicBodiesToWorld(world, map);


        hero = new Hero(world,this,null);


        this.contactListener = new PuzzleContactListener();
        world.setContactListener(this.contactListener);





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

        for (AbstractBullet b : bulletList) {
            b.draw(this.game.getBatch());
        }

        for (Enemy e: enemyList) {
            e.draw(this.game.getBatch());
        }

        for (AbstractAnimationSprite a: this.animationList) {
            a.draw(this.game.getBatch());
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

        gameCam.position.y = hero.body.getPosition().y;
//        gameCam.position.x = hero.body.getPosition().x;
        gameCam.update();


        hero.update(dt);

        for (AbstractBullet bullet : bulletList) {
            if (bullet.body.getPosition().y - gameCam.position.y > this.game.V_HEIGHT / 1.5f / Puzzle.PPM) {
                bullet.setState(Constants.GameObjectState.REMOVABLE);
            }
            bullet.update(dt);
        }

        for (Enemy e: enemyList) {
            e.update(dt);
        }

        for (AbstractAnimationSprite a: this.animationList) {
            a.update(dt);
        }

        removeBullets(bulletList);
        removeEnemies(enemyList);
        removeAnimationSprites(this.animationList);
        renderer.setView(gameCam);
    }



    private void removeEnemies(List<Enemy> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getState() == Constants.GameObjectState.REMOVABLE) {
                objects.remove(i);
                i --;
            }
        }
    }

    private void removeBullets(List<AbstractBullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getState() == Constants.GameObjectState.REMOVABLE) {
                bullets.remove(i);
                i --;
            }
        }
    }

    private void removeAnimationSprites(List<AbstractAnimationSprite> animationList) {
        for (int i = 0; i < animationList.size(); i++) {
            if (animationList.get(i).getState() == Constants.GameObjectState.REMOVABLE) {
                animationList.remove(i);
                i --;
            }
        }
    }

    public TextureAtlas getHeroAtlas()
    {
        return this.heroAtlas;
    }

    public TextureAtlas getBulletsAtlas() {
        return this.bulletsAtlas;
    }

    public TextureAtlas getEnemyAtlas() {
        return this.enemyAtlas;
    }

    public void addBullet(AbstractBullet bullet) {
        this.bulletList.add(bullet);
    }
    public void addEnemy(Enemy enemy) {
        this.enemyList.add(enemy);
    }
    public void addAnimationSprite(AbstractAnimationSprite as) {
        this.animationList.add(as);
    }
}
