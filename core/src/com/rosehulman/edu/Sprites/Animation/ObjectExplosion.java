package com.rosehulman.edu.Sprites.Animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sounds.MySoundEffect;

/**
 * Created by mot on 2/4/17.
 */

public class ObjectExplosion extends AbstractAnimationSprite {
    private float xScale = 1.5f;
    private float yScale = 1.5f;


    public ObjectExplosion(float duration, Rectangle bounds)
    {
        super(duration, bounds);
        float shiftX = (xScale - 1) * bounds.width / 2;
        float shiftY = (yScale - 1) * bounds.height / 2;

        setBounds(bounds.x - shiftX, bounds.y - shiftY, bounds.width * xScale, bounds.height * yScale);
        this.sound = new MySoundEffect("sounds/objectExplosion.ogg");
        this.sound.setVolume(0.1f);
        this.sound.playSound();
    }

    @Override
    protected Animation configureAnimation() {
        this.setRegion(PlayScreen.explosionAtlas.getRegions().first());
        int height = 64;
        int width = 64;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            for (int j =0; j < 4; j++) {
                frames.add(new TextureRegion(getTexture(), j * width, i * height, width, height));
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
