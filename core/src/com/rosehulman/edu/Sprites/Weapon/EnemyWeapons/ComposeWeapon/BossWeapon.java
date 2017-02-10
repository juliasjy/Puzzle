package com.rosehulman.edu.Sprites.Weapon.EnemyWeapons.ComposeWeapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Sprites.Weapon.Abstract.ComposedWeapon;
import com.rosehulman.edu.Sprites.Weapon.Abstract.Weapon;
import com.rosehulman.edu.Sprites.Weapon.EnemyWeapons.SingleWeapon.EnemyWeaponLinear;
import com.rosehulman.edu.Sprites.Weapon.EnemyWeapons.SingleWeapon.Swipe;

/**
 * Created by mot on 2/9/17.
 */

public class BossWeapon extends ComposedWeapon {

    public BossWeapon(Vector2 relativePosition, Vector2 direction, World world, GameObject owner, PlayScreen sc) {
        super(relativePosition, direction, world, owner, sc);
        initialize();
    }

    private void initialize() {
        for (float i = -0.5f; i <= 0.5f; i += 0.5f) {
            Weapon w = new Trident(new Vector2(i, 0), new Vector2(0, 1), world, this.owner, this.sc);
            this.addWeapon(w);
        }
        Weapon w = new Swipe(new Vector2(0, 0), new Vector2(0, 1), world, this.owner, this.sc);
        this.addWeapon(w);
    }


    @Override
    public void update(float dt) {
        this.fire(dt);
    }
}
