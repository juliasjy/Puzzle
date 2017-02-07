package com.rosehulman.edu.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mot on 1/14/17.
 */

public class PlayScreen implements Screen, InputProcessor {

    //game related
    private Puzzle game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private List<AbstractBullet> bulletList;
    private List<Enemy> enemyList;
    private List<AbstractAnimationSprite> animationList;
    //tile map related
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private int counter = 0;
    private float bufferY;
    private float bufferX;
    private float step = 1/60f;
    private float lastX = 0;
    private float lastY = 0;
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

    private float time = 0;
    private boolean isPaused;

    public PlayScreen(Puzzle game) {
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("tiledMap/Fly.tmx");
        this.isPaused = false;
        this.time = 0;



        bulletList = Collections.synchronizedList(new ArrayList<AbstractBullet>());
        enemyList = Collections.synchronizedList(new ArrayList<Enemy>());
        animationList = Collections.synchronizedList(new ArrayList<AbstractAnimationSprite>());

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
        hero = new Hero(world,this,null);


        b2wc.addStaicBodiesToWorld(world, map);




        this.contactListener = new PuzzleContactListener();
        world.setContactListener(this.contactListener);

        bufferX = this.gamePort.getWorldWidth() / 3;
        bufferY = this.gamePort.getWorldHeight() / 5;

        Gdx.input.setInputProcessor(this);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
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
//
        for (Enemy e: enemyList) {
            e.draw(this.game.getBatch());
        }
//
        for (AbstractAnimationSprite a: this.animationList) {
            a.draw(this.game.getBatch());
        }

        this.game.getBatch().end();
    }

    @Override
    public void resize(int width, int height)
    {
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


    public void handleInput(float dt)
    {
//        this.hero.handleInput(dt);
    }


    public void update(float dt) {
        if (this.isPaused) {
            return;
        }

        this.time += dt;
        this.handleInput(dt);

        world.step(dt, 6, 2);

        gameCam.position.y += dt;

        //important
        this.lastY += dt;
        gameCam.update();


        hero.update(dt);

        for (AbstractBullet bullet : bulletList) {
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
        removeAnimationSprites(animationList);


        renderer.setView(gameCam);
    }



    private void removeEnemies(List<Enemy> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getState() == Constants.GameObjectState.REMOVABLE) {
                objects.remove(i);
                System.out.println("Object removed " + counter);
                counter++;
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





    public Hero getHero() {
        return this.hero;
    }

    public void onHeroDie() {
//        game.setScreen(new MenuScreen(game, null));
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 vec = this.gamePort.unproject(new Vector2(screenX, screenY));
        this.lastX = vec.x;
        this.lastY = vec.y;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 vec = this.gamePort.unproject(new Vector2(screenX, screenY));
        float tx = vec.x - lastX;
        float ty = vec.y - lastY;
        Vector2 pos = this.hero.body.getPosition();
        pos = new Vector2(pos.x, pos.y);
        pos.set(pos.x + tx, pos.y +ty);
        pos.set(Math.max(0, Math.min(pos.x, this.gamePort.getWorldWidth())),
                Math.max(bottomY(),
                        Math.min(pos.y, topY())));

        this.hero.body.setTransform(pos.x ,
                                    pos.y ,
                                    0);

        this.lastX = vec.x;
        this.lastY = vec.y;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

//        this.hero.body.setLinearVelocity(screenX, screenY);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    public float topY() {
        return this.gameCam.position.y + this.gamePort.getWorldHeight() / 2;
    }

    public float bottomY() {
        return this.gameCam.position.y - this.gamePort.getWorldHeight() / 2;
    }



    public boolean isOutOfGameArea(Vector2 position) {
        return isOutOfXBoundaries(position) ||
                isOutOfYBoundaries(position);
    }

    public boolean isOutOfXBoundaries(Vector2 position) {
        return isBeyondLeftBoundary(position) || isBeyondRightBoundary(position);
    }

    public boolean isOutOfYBoundaries(Vector2 position) {
        return isBeyondTopBoundary(position) || isBeyondBottomBoundary(position);
    }

    public boolean isBeyondBottomBoundary(Vector2 position) {
        return position.y < -bufferY + bottomY();
    }

    public boolean isBeyondTopBoundary(Vector2 position) {
        return position.y > bufferY + topY();
    }

    public boolean isBeyondLeftBoundary(Vector2 position) {
        return position.x < -bufferX;
    }

    public boolean isBeyondRightBoundary(Vector2 position) {
        return position.x  > this.gamePort.getWorldWidth() + bufferX;
    }


}
