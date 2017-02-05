package com.rosehulman.edu.Sprites.Bullet.HeroBullets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Animation.BulletExplosion;
import com.rosehulman.edu.Sprites.Bullet.AbstractBullet;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 1/31/17.
 */

public class SampleBullet extends AbstractBullet {
    private float damage;



    private SampleBullet(Vector2 position, Vector2 direction, Animation animation, PlayScreen sc) {
        super(position, direction, animation, sc);
        setBounds(this.body.getPosition().x, this.body.getPosition().y, Utils.scaleWithPPM(48), Utils.scaleWithPPM(48));
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
//        this.rotate(90);
        this.damage = 10;
    }


    public static AbstractBullet getInstance(Vector2 position, Vector2 direction, Animation animation, PlayScreen sc) {
        return new SampleBullet(position, direction, animation, sc);
    }


    @Override
    public void update(float dt) {
        this.setRegion(this.getNextFrame(dt));
        setPosition(this.body.getPosition().x - getWidth() / 2.0f, this.body.getPosition().y - getHeight() / 2.0f);
        this.stateTimer += dt * 2;

        if (this.objectState == Constants.GameObjectState.REMOVABLE) {
            world.destroyBody(body);
            body.setUserData(null);
            body = null;
        }
    }



    @Override
    public Body createPhysicsBody() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(this.position);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        //TODO
        fdef.filter.categoryBits = Constants.HERO_BULLET_BIT;
        fdef.filter.maskBits = Constants.ENEMY_BIT;
        fdef.isSensor = true;
        //TODO
        CircleShape shape = new CircleShape();
        shape.setRadius(12 / Puzzle.PPM);


        fdef.shape = shape;
        body.createFixture(fdef);

        //TODO
        body.setLinearVelocity(0, 15);

        return body;
    }

    @Override
    public void onSetToInactiveState() {

    }

    @Override
    public void onSetToActiveState() {

    }

    @Override
    public void onSetToDeadState() {

    }

    @Override
    public void onSetToRemovableState() {
        BulletExplosion be = new BulletExplosion(0.2f, this.getBoundingRectangle());
        this.sc.addAnimationSprite(be);
    }


    @Override
    public void onHit(GameObject enemy) {
        this.setState(Constants.GameObjectState.REMOVABLE);
    }

    @Override
    public float getDamage() {
        return this.damage;
    }

}
