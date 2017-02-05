package com.rosehulman.edu.Sprites.GameObject.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.GameObject;

/**
 * Created by mot on 1/31/17.
 */

public abstract class Enemy extends GameObject {
    protected Animation animation;
    protected float stateTimer;

    public Enemy(World world, PlayScreen playScreen, Rectangle bounds) {
        super(world, playScreen, bounds);
        setRegion(playScreen.getEnemyAtlas().getRegions().first());
        this.stateTimer = 0;
        this.animation = configureAnimation();
    }

    public abstract Animation configureAnimation();


    protected TextureRegion getNextFrame(float dt) {
        return (TextureRegion) this.animation.getKeyFrame(stateTimer, true);
    }




}
