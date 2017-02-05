package com.rosehulman.edu.Sprites.Weapon.Abstract;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rosehulman.edu.Sprites.GameObject.Hero;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mot on 1/31/17.
 */

public abstract class ComposedWeapon extends Sprite implements Weapon {
    protected List<Weapon> weapons;
    protected TextureAtlas weaponAtlas;


    public ComposedWeapon(Hero hero, TextureAtlas weaponAtlas) {
        this.weapons = new ArrayList<Weapon>();
        this.weaponAtlas = weaponAtlas;
    }

    @Override
    public void fire() {
        for (Weapon w : weapons) {
            w.fire();
        }
    }


}
