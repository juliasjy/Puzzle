package com.rosehulman.edu.Sprites.Animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rosehulman.edu.Interface.ObjectStateOwner;
import com.rosehulman.edu.Sounds.MySoundEffect;
import com.rosehulman.edu.Utils.Constants;

/**
 * Created by mot on 2/4/17.
 */

public abstract class AbstractAnimationSprite extends Sprite implements ObjectStateOwner {
    protected Animation animation;
    protected Constants.GameObjectState state;
    protected float stateTimer;
    protected float duration;
    protected Rectangle bounds;
    protected MySoundEffect sound;

    public AbstractAnimationSprite(float duration, Rectangle bounds) {
        this.duration = duration;
        this.bounds = bounds;
        setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        this.stateTimer = 0;
        this.animation = configureAnimation();
//        this.sound = new MySoundEffect("sounds/bossfire.wav");
//        this.sound.playSound();
    }

    protected abstract Animation configureAnimation();

    @Override
    public void setState(Constants.GameObjectState state) {
        this.state = state;
        switch (this.state) {
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
        return this.state;
    }


    protected TextureRegion getNextFrame() {
        return (TextureRegion) this.animation.getKeyFrame(stateTimer);
    }

    @Override
    public void update(float dt) {
        this.stateTimer += dt;
        this.duration -= dt;
        this.setRegion(getNextFrame());
        if (this.duration <= 0) {
            this.setState(Constants.GameObjectState.REMOVABLE);
//            this.sound.dispose();
        }
    }
}
