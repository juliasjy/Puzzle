package com.rosehulman.edu.Sprites.Weapon.Abstract;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.GameObject;

/**
 * Created by mot on 1/30/17.
 */

public abstract class SingleWeapon extends Sprite implements Weapon {
    protected Vector2 relativePosition;
    protected Vector2 direction; //normal vector
    protected Animation bulletAnimation;
    protected TextureAtlas bulletAtlas;
    protected PlayScreen sc;
    protected float fireCounter = 0;
    protected float fireInterval;
    protected float bulletSpeed = 10;

    protected GameObject owner;

    public World world;


//    protected float stateTimer;



    public SingleWeapon(Vector2 relativePosition, Vector2 direction, World world, GameObject owner, PlayScreen sc) {
        this.sc = sc;
        this.relativePosition = relativePosition;
        this.direction = direction;
        this.owner = owner;
        this.world = world;
        this.bulletAtlas = PlayScreen.bulletsAtlas;
        this.bulletAnimation = configureBulletAnimation();
        this.fireInterval = setFireInterval();
        this.updateDirection();
    }



    public abstract float setFireInterval();
    public abstract Animation configureBulletAnimation();
    public abstract void updateDirection();

    @Override
    public void update(float dt) {
        this.fire(dt);
    }

    @Override
    public void fire(float dt) {
        this.updateDirection();
    }

}
