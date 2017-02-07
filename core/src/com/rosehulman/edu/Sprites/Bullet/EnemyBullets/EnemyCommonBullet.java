package com.rosehulman.edu.Sprites.Bullet.EnemyBullets;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Animation.BulletExplosion;
import com.rosehulman.edu.Sprites.Bullet.AbstractBullet;
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 1/31/17.
 */

public class EnemyCommonBullet extends AbstractBullet {


    private EnemyCommonBullet(Vector2 position, Vector2 direction, Animation animation, PlayScreen sc) {
        super(position, direction, animation, sc);
        setBounds(this.body.getPosition().x, this.body.getPosition().y, Utils.scaleWithPPM(32), Utils.scaleWithPPM(96));
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
    }


    public static AbstractBullet getInstance(Vector2 position, Vector2 direction, Animation animation, PlayScreen sc) {
        return new EnemyCommonBullet(position, direction, animation, sc);
    }


    @Override
    public void update(float dt) {
        super.update(dt);

    }

    @Override
    protected void updateDirection() {
//        Hero hero = sc.getHero();
//        Vector2 heroPos = hero.body.getPosition();
//
//        Vector2 myPos = this.body.getPosition();
//
//        Vector2 direction = new Vector2(heroPos.x - myPos.x, heroPos.y - myPos.y);
//        direction.nor();
//        this.direction = new Vector2(direction.x * 5, direction.y * 5);
//        this.body.setLinearVelocity(this.direction);
    }

    @Override
    protected float configureDamage() {
        return 10;
    }


    @Override
    public Body createPhysicsBody() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(this.position);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        //TODO
        fdef.filter.categoryBits = Constants.ENEMY_BULLET_BIT;
        fdef.filter.maskBits = Constants.HERO_BIT;
        fdef.isSensor = true;
        //TODO
        CircleShape shape = new CircleShape();
        shape.setRadius(12 / Puzzle.PPM);


        fdef.shape = shape;
        body.createFixture(fdef);


        body.setLinearVelocity(direction.x, direction.y);

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
        BulletExplosion be = new BulletExplosion(0.2f, this.getBoundingRectangle());
        this.sc.addAnimationSprite(be);
        this.setState(Constants.GameObjectState.CLEANING_PHYSICS_BODY);
    }

    @Override
    public void onSetToRemovableState() {

    }

    @Override
    public void onSetToCleaningPhysicsBodyState() {
    }


}
