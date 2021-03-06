package com.rosehulman.edu.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.rosehulman.edu.Sprites.Bullet.Bullet;
import com.rosehulman.edu.Sprites.GameObject.GameObject;

/**
 * Created by mot on 1/30/17.
 */

public class PuzzleContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        Object dataA  = fixA.getUserData();
        Object dataB = fixB.getUserData();

        //If A is a bullet
        if (dataA != null && Bullet.class.isAssignableFrom(dataA.getClass())) {
            //If B is a gameObject
            if (dataB != null && GameObject.class.isAssignableFrom(dataB.getClass())) {
                Bullet bullet = (Bullet) dataA;
                GameObject object = (GameObject) dataB;
                bullet.onHit(object);
                object.onHit(bullet);
            } else {

            }
        }

        //If B is bullet
        else if (dataB != null && Bullet.class.isAssignableFrom(dataB.getClass())) {
            //If A is gameObject
            if (dataA != null && GameObject.class.isAssignableFrom(dataA.getClass())) {
                Bullet bullet = (Bullet) dataB;
                GameObject object = (GameObject) dataA;
                bullet.onHit(object);
                object.onHit(bullet);
            } else {
            }
        }

        else if (dataA != null && GameObject.class.isAssignableFrom(dataA.getClass())) {
            if (dataB != null && GameObject.class.isAssignableFrom(dataB.getClass())) {
                GameObject objectA = (GameObject) dataA;
                GameObject objectB = (GameObject) dataB;
                objectA.onHit(objectB);
                objectB.onHit(objectA);
            } else {

            }
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
