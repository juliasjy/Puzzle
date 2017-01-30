package com.rosehulman.edu.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Sprites.Bound;
import com.rosehulman.edu.Sprites.Brick;

/**
 * Created by mot on 1/14/17.
 */

public class B2WorldCreator {

    public B2WorldCreator() {
    }

    public void addStaicBodiesToWorld(World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //bound
//        for (MapObject object:  map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rec = ((RectangleMapObject) object).getRectangle();
//            Bound bd = new Bound(world, map, rec);
//            bd.createBody();
//        }
//
//        //bricks
//        for (MapObject object:  map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rec = ((RectangleMapObject) object).getRectangle();
//            Brick br = new Brick(world, map, rec);
//            br.createBody();
//        }
        //destination

    }
}
