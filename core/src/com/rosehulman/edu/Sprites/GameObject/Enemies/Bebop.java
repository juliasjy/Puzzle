package com.rosehulman.edu.Sprites.GameObject.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Animation.BulletExplosion;
import com.rosehulman.edu.Sprites.Bullet.Bullet;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Sprites.Weapon.Abstract.Weapon;
import com.rosehulman.edu.Sprites.Weapon.EnemyWeapons.EnemyWeaponLinear;
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.SpriteUtils;
import com.rosehulman.edu.Utils.Utils;
import com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT;

/**
 * Created by mot on 2/1/17.
 */

public class Bebop extends Enemy {


    public Bebop(World world, PlayScreen playScreen, Rectangle bounds) {
        super(world, playScreen, bounds);
        this.collisionDamage = 10;
        this.health = 400;
        this.setBounds(this.body.getPosition().x, this.body.getPosition().y, Utils.scaleWithPPM(68),  Utils.scaleWithPPM(68));

    }

    @Override
    public Animation configureAnimation() {
        int shipHeight = 120;
        int shipWidth = 124;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 1; i++) {
            frames.add(new TextureRegion(getTexture(), 0, 0, shipWidth, shipHeight));
        }
        return new Animation(0.1f, frames);
    }

    @Override
    public void onHit(Bullet bullet)
    {
        if (objectState != Constants.GameObjectState.ACTIVE) {
            return;
        }
        this.health -= bullet.getDamage();
        deathCheck();
    }

    @Override
    public void onHit(GameObject hero)
    {
        if (objectState != Constants.GameObjectState.ACTIVE) {
            return;
        }
        this.health -= hero.getCollisionDamage();
        deathCheck();
    }





    @Override
    protected Body createPhysicsBody(Rectangle bounds) {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Vector2 pos = SpriteUtils.getPosFromBounds(bounds);

        bdef.position.set(pos);
        bdef.type = BodyDef.BodyType.DynamicBody;

        fdef.filter.categoryBits = Constants.ENEMY_BIT;
        fdef.filter.maskBits = Constants.HERO_BIT | Constants.HERO_BULLET_BIT;


        body = world.createBody(bdef);
        Vector2 box = SpriteUtils.getBoxFromBounds(bounds);
        shape.setAsBox(box.x, box.y);
        fdef.shape = shape;



        body.createFixture(fdef);
        return body;
    }

    @Override
    protected Weapon configureWeapon() {
//        return null;
        return new EnemyWeaponLinear(this.body.getPosition(), new Vector2(0, 1), world, playScreen.getBulletsAtlas(), this, playScreen);
    }


    @Override
    public void handleInput(float dt) {

    }

    @Override
    public void update(float dt) {
        super.update(dt);
        this.setRegion(this.getNextFrame(dt));
        this.stateTimer += dt * 2;
    }

    @Override
    public void onSetToInactiveState() {
        this.body.setActive(false);
        System.out.println("Enemy set to inactive state");
    }

    @Override
    public void onSetToActiveState() {
        System.out.println("Enemy set to active state");
        this.body.setActive(true);
    }


    @Override
    public void onSetToDeadState()
    {
        BulletExplosion be = new BulletExplosion(1f, this.getBoundingRectangle());
        this.playScreen.addAnimationSprite(be);
        this.setState(Constants.GameObjectState.CLEANING_PHYSICS_BODY);
    }

    @Override
    public void onSetToRemovableState() {
    }

    @Override
    public void onSetToCleaningPhysicsBodyState() {
//        disposePhysicsBody();
    }


}
