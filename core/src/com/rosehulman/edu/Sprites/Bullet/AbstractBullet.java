package com.rosehulman.edu.Sprites.Bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Interface.ObjectStateOwner;
import com.rosehulman.edu.Scenes.PlayScreen;
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



    public World world;


    public AbstractBullet(Vector2 position, Vector2 direction, Animation animation, PlayScreen sc) {
        this.position = position;
        this.direction = direction;
        this.animation = animation;
        this.sc = sc;
        this.world = sc.world;
        this.body = createPhysicsBody();
        this.body.getFixtureList().first().setUserData(this);
        this.stateTimer = 0;
    }


    protected TextureRegion getNextFrame(float dt) {
        return (TextureRegion) this.animation.getKeyFrame(stateTimer, true);
    }

    protected abstract Body createPhysicsBody();


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


}
