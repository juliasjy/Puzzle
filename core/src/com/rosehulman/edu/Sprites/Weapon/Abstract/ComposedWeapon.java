package com.rosehulman.edu.Sprites.Weapon.Abstract;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Sprites.GameObject.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 1/31/17.
 */

public abstract class ComposedWeapon extends Sprite implements Weapon {
    protected List<Weapon> weapons;
    protected Vector2 relativePosition;
    protected Vector2 direction; //normal vector
    protected PlayScreen sc;
    protected GameObject owner;

    public World world;


    public ComposedWeapon(Vector2 relativePosition, Vector2 direction, World world, GameObject owner, PlayScreen sc) {
        this.weapons = new ArrayList<Weapon>();
        this.relativePosition = relativePosition;
        this.direction = direction;
        this.world = world;
        this.owner = owner;
        this.sc = sc;
    }

    @Override
    public void fire(float dt) {
        for (Weapon w : weapons) {
            w.fire(dt);
        }
    }

    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }


}
