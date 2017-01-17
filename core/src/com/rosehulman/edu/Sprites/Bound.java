package com.rosehulman.edu.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by mot on 1/16/17.
 */

public class Bound extends InteractiveTiledObject {
    public Bound(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
