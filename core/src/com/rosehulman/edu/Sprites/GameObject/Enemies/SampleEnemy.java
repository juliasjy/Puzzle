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
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.SpriteUtils;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 2/1/17.
 */

public class SampleEnemy extends Enemy {



    public SampleEnemy(World world, PlayScreen playScreen, Rectangle bounds) {
        super(world, playScreen, bounds);
        this.collisionDamage = 10;
        this.health = 400;
        this.setBounds(this.body.getPosition().x, this.body.getPosition().y, Utils.scaleWithPPM(96),  Utils.scaleWithPPM(96));

    }

    @Override
    public Animation configureAnimation() {
        int shipHeight = 350;
        int shipWidth = 225;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 1; i++) {
            frames.add(new TextureRegion(getTexture(), 370 , 494, shipWidth, shipHeight));
        }
        return new Animation(0.1f, frames);
    }

    @Override
    public void onHit(Bullet bullet) {
        this.health -= bullet.getDamage();
    }

    @Override
    public void onHit(GameObject hero) {
        this.health -= hero.getCollisionDamage();
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


        body.setLinearVelocity(0, -1);
        return body;
    }




    @Override
    public void handleInput(float dt) {

    }

    @Override
    public void update(float dt) {
        super.update(dt);
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
    public void onSetToInactiveState() {

    }

    @Override
    public void onSetToActiveState() {

    }

    @Override
    public void onSetToDeadState() {
        System.out.println("Enemy died");
    }

    @Override
    public void onSetToRemovableState() {
        BulletExplosion be = new BulletExplosion(1f, this.getBoundingRectangle());
        this.playScreen.addAnimationSprite(be);
    }
}
