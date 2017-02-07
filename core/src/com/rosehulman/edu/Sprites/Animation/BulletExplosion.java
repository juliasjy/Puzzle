package com.rosehulman.edu.Sprites.Animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Scenes.PlayScreen;

/**
 * Created by mot on 2/4/17.
 */

public class BulletExplosion extends AbstractAnimationSprite {



    public BulletExplosion(float duration, Rectangle bounds) {
        super(duration, bounds);
    }

    @Override
    protected Animation configureAnimation() {
        this.setRegion(PlayScreen.explosionAtlas.getRegions().first());
        int height = 64;
        int width = 64;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            for (int j =0; j < 4; j++) {
                frames.add(new TextureRegion(getTexture(), j * 64, i * 64, width, height));
            }
        }
        int numFrames = frames.size;
        float frameDuration = this.duration / numFrames;
        return new Animation(frameDuration, frames);
    }


    @Override
    public void onSetToInactiveState() {

    }

    @Override
    public void onSetToActiveState() {

    }

    @Override
    public void onSetToDeadState() {

    }

    @Override
    public void onSetToRemovableState() {

    }

    @Override
    public void onSetToCleaningPhysicsBodyState() {

    }
}
