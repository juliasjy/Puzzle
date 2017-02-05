package com.rosehulman.edu.Sprites.Bullet;

import com.rosehulman.edu.Interface.Temporal;
import com.rosehulman.edu.Sprites.GameObject.GameObject;

/**
 * Created by mot on 1/31/17.
 */

public interface Bullet extends Temporal {
    void onHit(GameObject enemy);
    float getDamage();
}
