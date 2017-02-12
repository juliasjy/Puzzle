package com.rosehulman.edu.Sprites.GameObject.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.Score;

/**
 * Created by mot on 1/31/17.
 */

public abstract class Enemy extends GameObject {
    protected Animation animation;
    protected float stateTimer;
    protected float xScale = 1.2f;
    protected float yScale = 1.2f;



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


    @Override
    public void update(float dt)
    {
        super.update(dt);
        this.tintTimer -= dt;
        if (this.tintTimer < 0) {
            this.setColor(1f,1f,1f,1.0f);
        }
    }

    protected void deathCheck() {
        if (this.health <= 0) {
            this.setState(Constants.GameObjectState.DEAD);
        }
    }


}
