package com.rosehulman.edu.Sprites.GameObject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Interface.*;
import com.rosehulman.edu.Interface.ObjectStateOwner;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Bullet.Bullet;
import com.rosehulman.edu.Sprites.GameObject.HealthBars.HealthBar;
import com.rosehulman.edu.Utils.Constants;

/**
 * Created by mot on 1/31/17.
 */

public abstract class GameObject extends Sprite implements  InputHandler, ObjectStateOwner {
    public World world;
    public Body body;


    protected HealthBar healthBar;
    protected float health;
    protected float maxHealth;
    protected float collisionDamage;
    protected PlayScreen playScreen;
    protected Constants.GameObjectState objectState;


    public GameObject(World world, PlayScreen playScreen, Rectangle bounds) {
        this.world = world;
        this.playScreen = playScreen;
        this.body = createPhysicsBody(bounds);
        this.body.getFixtureList().first().setUserData(this);
        this.health = 500;
        this.maxHealth = 500;
        this.collisionDamage = 20;
        this.objectState = Constants.GameObjectState.ACTIVE;
        this.healthBar = new HealthBar(this);
    }



    public abstract void onHit(Bullet bullet);
    public abstract void onHit(GameObject object);


    //Should use shape2d to generalize this method
    protected abstract Body createPhysicsBody(Rectangle bounds);

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
        this.healthBar.update(dt);
        if (this.health <= 0 && this.objectState != Constants.GameObjectState.DEAD) {
            this.setState(Constants.GameObjectState.REMOVABLE);
        }

    }

    public float getMaxHealth() {
        return this.maxHealth;
    }

    public float getHealth() {
        return this.health;
    }
}
