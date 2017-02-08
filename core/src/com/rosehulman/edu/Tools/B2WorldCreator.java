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
import com.rosehulman.edu.Interface.InputHandler;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.Bosses.BOSS1;
import com.rosehulman.edu.Sprites.GameObject.Enemies.Bebop;
import com.rosehulman.edu.Sprites.GameObject.Enemies.Enemy;
import com.rosehulman.edu.Sprites.GameObject.Enemies.SampleEnemy;

/**
 * Created by mot on 1/14/17.
 */

public class B2WorldCreator {
    private PlayScreen sc;


    public B2WorldCreator(PlayScreen sc) {
        this.sc = sc;
    }

    public void addStaicBodiesToWorld(World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        Enemy enemy = null;
        for (MapObject object:  map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();
            Integer type = (Integer) object.getProperties().get("type");
            System.out.println(type);
            switch (type) {
                case 0:
                    enemy = new Bebop(world, sc, rec);

                    break;
                case 1:
                    enemy = new SampleEnemy(world, sc, rec);

                    break;
                case 2:
                    enemy = new BOSS1(world, sc, rec);
                    break;

            }
            if (enemy != null) {
                sc.addEnemy(enemy);
                enemy = null;
            }
        }
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
