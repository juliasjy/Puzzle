package com.rosehulman.edu.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Weapon.commonWeapon;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 1/14/17.
 */

public class Hero extends Sprite {
    public World world;
    public Body body;

    private enum State {LEFT, UP, RIGHT, DOWN}
    private State currentState;
    private int count = 2;

    //animation related
    private Animation animation_left;
    private Animation animation_right;
    private Animation animation_up;
    private Animation animation_down;
    private float stateTimer;
    private PlayScreen sc;






    public Hero(World world, PlayScreen sc) {
        super(sc.getHeroAtlas().getRegions().first());
        this.world = world;
        this.sc = sc;

        this.currentState = State.DOWN;
        this.stateTimer = 0;

        define();
        setBounds(this.body.getPosition().x, this.body.getPosition().y, Utils.scaleWithPPM(32), Utils.scaleWithPPM(32));

        configureAnimation();


    }


    public void define() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Puzzle.PPM, 32 / Puzzle.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(12 / Puzzle.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }


    private TextureRegion getNextFrame(float dt) {
        TextureRegion region = null;
        switch (currentState) {
            case LEFT:
                region = (TextureRegion) this.animation_left.getKeyFrame(stateTimer, true);
                break;
            case RIGHT:
                region = (TextureRegion) this.animation_right.getKeyFrame(stateTimer, true);
                break;
            case UP:
                region = (TextureRegion) this.animation_up.getKeyFrame(stateTimer, true);
                break;
            case DOWN:
                region = (TextureRegion) this.animation_down.getKeyFrame(stateTimer, true);
                break;
            default:
                region = (TextureRegion) this.animation_down.getKeyFrame(stateTimer, true);
                break;
        }
        return region;
    }

    public void setNextState(State state) {
        this.currentState = state;
    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && this.body.getLinearVelocity().y <= 1 ) {
            this.body.setLinearVelocity(0, 5);
            this.setNextState(State.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && body.getLinearVelocity().x <= 1) {
            this.body.setLinearVelocity(5, 0);
            this.setNextState(State.RIGHT);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && body.getLinearVelocity().x >= -1) {
            this.body.setLinearVelocity(-5, 0);
            this.setNextState(State.LEFT);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && body.getLinearVelocity().y >= -1) {
            this.body.setLinearVelocity(0, -5);
            this.setNextState(State.DOWN);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.UP)  && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
        !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&  !Gdx.input.isKeyPressed(Input.Keys.DOWN) ) {
            this.body.setLinearVelocity(0, 0);
        }
    }

    public void configureAnimation() {
        int heroHeight = 95;
        int heroWidth = 65;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 5, heroWidth, heroHeight ));
        }
        this.animation_down = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 100, heroWidth, heroHeight ));
        }
        this.animation_up = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 195, heroWidth, heroHeight ));
        }
        this.animation_left = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 290, heroWidth, heroHeight ));
        }
        this.animation_right = new Animation(0.1f, frames);
    }

    public void update(float dt) {

        setPosition(this.body.getPosition().x - getWidth() / 2, this.body.getPosition().y - getHeight() / 2);
        this.stateTimer += dt;
        this.setRegion(this.getNextFrame(dt));

        count ++;
        if (count > 20) {
            count = 0;
            commonWeapon weapon = new commonWeapon(this.world, this.sc, this.body.getPosition());
//            weapon.createBody();
            this.sc.addBullet(weapon);
        }
    }

}