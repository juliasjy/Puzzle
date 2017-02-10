package com.rosehulman.edu.Sprites.Weapon.EnemyWeapons.ComposeWeapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Sprites.Weapon.Abstract.ComposedWeapon;
import com.rosehulman.edu.Sprites.Weapon.Abstract.Weapon;
import com.rosehulman.edu.Sprites.Weapon.EnemyWeapons.SingleWeapon.EnemyWeaponLinear;

/**
 * Created by mot on 2/9/17.
 */

public class Trident extends ComposedWeapon {

    public Trident(Vector2 relativePosition, Vector2 direction, World world, GameObject owner, PlayScreen sc) {
        super(relativePosition, direction, world, owner, sc);
        initialize();
    }

    private void initialize() {
        for (int i = -2; i <= 2; i += 2) {
            Weapon w = new EnemyWeaponLinear(relativePosition, new Vector2(i, -8f), world, this.owner, this.sc);
            this.addWeapon(w);
        }
    }


    @Override
    public void update(float dt) {
        this.fire(dt);
    }
}
