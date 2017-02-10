package com.rosehulman.edu.Sprites.Bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Interface.ObjectStateOwner;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sounds.MySoundEffect;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Utils.Constants;

/**
 * Created by mot on 1/31/17.
 */

public abstract class AbstractBullet extends Sprite implements Bullet, ObjectStateOwner {
    protected Constants.GameObjectState objectState;
    protected Vector2 position;
    protected Vector2 direction; //normal vector
    protected Animation animation;
    protected PlayScreen sc;
    public Body body;
    protected float stateTimer;
    protected float damage;

    public World world;

    public MySoundEffect sound;


    public AbstractBullet(Vector2 position, Vector2 direction, Animation animation, PlayScreen sc) {
        this.objectState = Constants.GameObjectState.ACTIVE;
        this.position = position;
        this.direction = direction;
        this.animation = animation;
        this.sc = sc;
        this.world = sc.world;
        this.body = createPhysicsBody();
        this.body.getFixtureList().first().setUserData(this);
        this.stateTimer = 0;
        this.damage = configureDamage();
        this.setRegion(this.getNextFrame(0));
        this.setAngle();

//        this.sound = new MySoundEffect("sounds/objectExplosion.ogg");
//        this.sound.setVolume(0.1f);


    }


    protected TextureRegion getNextFrame(float dt) {
        return (TextureRegion) this.animation.getKeyFrame(stateTimer, true);
    }

    protected abstract Body createPhysicsBody();
    protected abstract void updateDirection();
    protected abstract float configureDamage();

    @Override
    public void setState(Constants.GameObjectState state) {
        this.objectState = state;
        switch (this.objectState) {
            case ACTIVE:
                this.onSetToActiveState();
                break;
            case DEAD:
                this.onSetToDeadState();
                break;
            case INACTIVE:
                this.onSetToInactiveState();
                break;
            case REMOVABLE:
                this.onSetToRemovableState();
                break;
            case CLEANING_PHYSICS_BODY:
                this.onSetToCleaningPhysicsBodyState();
                break;
            default:
                break;
        }
    }

    @Override
    public Constants.GameObjectState getState() {
        return this.objectState;
    }


    protected void setAngle() {
        float x = this.direction.x;
        float y = this.direction.y;
        float radians = (float) Math.atan2(y, x);
        double degrees = Math.toDegrees(radians);

        this.setRotation((float)degrees - 90);
    }

    @Override
    public void update(float dt) {
        if (this.objectState == Constants.GameObjectState.REMOVABLE) {
            return;
        }
        if (this.objectState == Constants.GameObjectState.CLEANING_PHYSICS_BODY) {
            dispose();
            this.setState(Constants.GameObjectState.REMOVABLE);
            return;
        }
        if (sc.isOutOfGameArea(this.body.getPosition())) {
            this.setState(Constants.GameObjectState.CLEANING_PHYSICS_BODY);
            return;
        }
        this.setRegion(this.getNextFrame(dt));
        setPosition(this.body.getPosition().x - getWidth() / 2.0f, this.body.getPosition().y - getHeight() / 2.0f);
        this.stateTimer += dt * 2;

        if (this.objectState == Constants.GameObjectState.ACTIVE) {
            this.updateDirection();
            this.setAngle();
        }


    }

    protected void dispose() {
        world.destroyBody(body);
        body.setUserData(null);
        body = null;
//        sound.dispose();
    }


    @Override
    public void onHit(GameObject enemy)
    {
        if (this.objectState != Constants.GameObjectState.ACTIVE) {
            return;
        }
        this.setState(Constants.GameObjectState.DEAD);

//        System.out.println("play bullet sound");

    }

    @Override
    public float getDamage() {
        return this.damage;
    }

}
