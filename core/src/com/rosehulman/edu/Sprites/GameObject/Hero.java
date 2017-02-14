package com.rosehulman.edu.Sprites.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Animation.ObjectExplosion;
import com.rosehulman.edu.Sprites.Bullet.Bullet;
import com.rosehulman.edu.Sprites.Weapon.Abstract.Weapon;
import com.rosehulman.edu.Sprites.Weapon.HeroWeapons.HeroCommonWeapon;
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 1/14/17.
 */

public class Hero extends GameObject {
    private enum State {LEFT, UP, RIGHT, DOWN}
    private State currentState;

    //animation related
    private Animation animation_left;
    private Animation animation_right;
    private Animation animation_up;
    private Animation animation_down;
    private float stateTimer;


    public Hero(World world, PlayScreen playScreen, Rectangle bounds) {
        super(world, playScreen, bounds);
        this.setRegion(playScreen.getHeroAtlas().getRegions().first());
        this.currentState = State.DOWN;
        this.stateTimer = 0;
        this.collisionDamage = 99999;
        this.health = 500;
        this.maxHealth = 500;
        this.setState(Constants.GameObjectState.ACTIVE);
        setBounds(this.body.getPosition().x, this.body.getPosition().y, Utils.scaleWithPPM(96), Utils.scaleWithPPM(96));
//        configureAnimation();
    }




    @Override
    public void onHit(Bullet bullet) {
        this.health -= bullet.getDamage();
    }


    @Override
    public void onHit(GameObject object)
    {
        this.health -= object.getCollisionDamage();
    }

    @Override
    protected Body createPhysicsBody(Rectangle bounds) {
        //TODO use bounds;

        BodyDef bdef = new BodyDef();
        bdef.position.set(50 / Puzzle.PPM, 50 / Puzzle.PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;
        Body body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        fdef.filter.categoryBits = Constants.HERO_BIT;
        fdef.filter.maskBits = Constants.ENEMY_BIT | Constants.ENEMY_BULLET_BIT | Constants.OBSTACLE_BIT;


        CircleShape shape = new CircleShape();
        shape.setRadius(25 / Puzzle.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

        body.setLinearVelocity(0, 1f);

        return body;
    }

    @Override
    protected Weapon configureWeapon() {
        return new HeroCommonWeapon(this.body.getPosition(), new Vector2(0, 1), world , this, playScreen);
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

//    public void configureAnimation() {
//        int heroHeight = 95;
//        int heroWidth = 65;
//        Array<TextureRegion> frames = new Array<TextureRegion>();
//        for (int i = 0; i < 4; i++) {
//            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 5, heroWidth, heroHeight ));
//        }
//        this.animation_down = new Animation(0.1f, frames);
//        frames.clear();
//
//        for (int i = 0; i < 4; i++) {
//            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 100, heroWidth, heroHeight ));
//        }
//        this.animation_up = new Animation(0.1f, frames);
//        frames.clear();
//        for (int i = 0; i < 4; i++) {
//            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 195, heroWidth, heroHeight ));
//        }
//        this.animation_left = new Animation(0.1f, frames);
//        frames.clear();
//        for (int i = 0; i < 4; i++) {
//            frames.add(new TextureRegion(getTexture(), 5 + i * 62, 290, heroWidth, heroHeight ));
//        }
//        this.animation_right = new Animation(0.1f, frames);
//    }




    @Override
    public void update(float dt) {

        if (this.objectState != Constants.GameObjectState.ACTIVE) {
            return;
        }
        setPosition(this.body.getPosition().x - getWidth() / 2.0f, this.body.getPosition().y - getHeight() / 2.0f);
        this.healthBar.update(dt);
        if (this.weapon != null) {
            this.weapon.update(dt);
        }

        this.stateTimer += dt;
        if (this.health <= 0) {
            this.setState(Constants.GameObjectState.DEAD);
        }
    }



    @Override
    public void onSetToInactiveState() {}

    @Override
    public void onSetToActiveState() {}

    @Override
    public void onSetToDeadState() {
        ObjectExplosion be = new ObjectExplosion(1f, this.getBoundingRectangle());
        this.playScreen.addAnimationSprite(be);
        this.playScreen.onHeroDie();
        this.healthBar = null;
        this.setState(Constants.GameObjectState.REMOVABLE);
    }

    @Override
    public void onSetToRemovableState() {
        this.body.setActive(false);
        this.body.setLinearVelocity(0, 0);
        this.setPosition(-1, -1);
    }

    @Override
    public void onSetToCleaningPhysicsBodyState() {
    }

}