package com.rosehulman.edu.Sprites.GameObject;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Utils.SpriteUtils;

/**
 * Created by mot on 1/14/17.
 */

public abstract class InteractiveTiledObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTiledObject(World world, TiledMap map, Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;
    }

    public void createBody() {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Vector2 pos = SpriteUtils.getPosFromBounds(bounds);
        bdef.position.set(pos);
        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);
        Vector2 box = SpriteUtils.getBoxFromBounds(bounds);
        shape.setAsBox(box.x, box.y);
        fdef.shape = shape;
        body.createFixture(fdef);
    }


}
