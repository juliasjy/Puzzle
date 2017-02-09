package com.rosehulman.edu.Sprites.GameObject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Interface.*;
import com.rosehulman.edu.Interface.ObjectStateOwner;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Bullet.Bullet;
import com.rosehulman.edu.Sprites.GameObject.HealthBars.HealthBar;
import com.rosehulman.edu.Sprites.Weapon.Abstract.Weapon;
import com.rosehulman.edu.Utils.Constants;

/**
 * Created by mot on 1/31/17.
 */

public abstract class GameObject extends Sprite implements  InputHandler, ObjectStateOwner {
    public World world;
    public Body body;

    protected Weapon weapon;
    protected HealthBar healthBar;
    protected float health;
    protected float maxHealth;
    protected float collisionDamage;
    protected PlayScreen playScreen;
    protected Constants.GameObjectState objectState;
    protected float tintTimer;

    public GameObject(World world, PlayScreen playScreen, Rectangle bounds) {
//        this.objectState = Constants.GameObjectState.ACTIVE;
        this.world = world;
        this.playScreen = playScreen;
        this.body = createPhysicsBody(bounds);
        this.body.getFixtureList().first().setUserData(this);
        this.health = 200;
        this.maxHealth = 200;
        this.collisionDamage = 20;
        this.tintTimer = 0;
        this.healthBar = new HealthBar(this);
        this.weapon = configureWeapon();
        this.setState(Constants.GameObjectState.INACTIVE);
    }

    public abstract void onHit(Bullet bullet);
    public abstract void onHit(GameObject object);


    //Should use shape2d to generalize this method
    protected abstract Body createPhysicsBody(Rectangle bounds);
    protected abstract Weapon configureWeapon();
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
    public float getCollisionDamage() {
        return this.collisionDamage;
    }

    @Override
    public void draw (Batch batch) {
        super.draw(batch);
        this.healthBar.draw(batch);
    }

    @Override
    public void update(float dt) {
        if (this.objectState == Constants.GameObjectState.REMOVABLE) {
            return;
        }
        if (this.objectState == Constants.GameObjectState.CLEANING_PHYSICS_BODY) {
            disposePhysicsBody();
            this.setState(Constants.GameObjectState.REMOVABLE);
            return;
        }
        if (playScreen.isBeyondBottomBoundary(this.body.getPosition()) || playScreen.isOutOfXBoundaries(this.body.getPosition())) {
            this.setState(Constants.GameObjectState.CLEANING_PHYSICS_BODY);
            return;
        }
        //set sprite relativePosition corresponding to location of physics body
        setPosition(this.body.getPosition().x - getWidth() / 2.0f, this.body.getPosition().y - getHeight() / 2.0f);
        if (this.objectState == Constants.GameObjectState.INACTIVE) {
            if (!playScreen.isBeyondTopBoundary(this.body.getPosition())) {
                this.setState(Constants.GameObjectState.ACTIVE);
            } else {
                return;
            }
        }

//        this.setColor(1,1,1,1);
        this.healthBar.update(dt);
        if (this.weapon != null) {
            this.weapon.update(dt);
        }
    }

    protected void disposePhysicsBody() {
        world.destroyBody(body);
        body.setUserData(null);
        body = null;
    }

    public float getMaxHealth() {
        return this.maxHealth;
    }
    public float getHealth() {return this.health;
    }
}
