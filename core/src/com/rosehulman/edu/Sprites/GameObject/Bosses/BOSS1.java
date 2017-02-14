package com.rosehulman.edu.Sprites.GameObject.Bosses;

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
import com.rosehulman.edu.Sprites.Animation.ObjectExplosion;
import com.rosehulman.edu.Sprites.Bullet.Bullet;
import com.rosehulman.edu.Sprites.GameObject.Enemies.Enemy;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Sprites.Weapon.Abstract.Weapon;
import com.rosehulman.edu.Sprites.Weapon.EnemyWeapons.ComposeWeapon.BossWeapon;
import com.rosehulman.edu.Utils.Constants;
import com.rosehulman.edu.Utils.SpriteUtils;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 2/1/17.
 */

public class BOSS1 extends Enemy {
    private float xScale = 1.2f;
    private float yScale = 1.2f;


    public BOSS1(World world, PlayScreen playScreen, Rectangle bounds) {
        super(world, playScreen, bounds);
        this.collisionDamage = 9999;
        this.health = 10000;
        this.maxHealth = 10000;
        this.setBounds(this.body.getPosition().x, this.body.getPosition().y,
                Utils.scaleWithPPM(bounds.getWidth()) * xScale,
                Utils.scaleWithPPM(bounds.getHeight()) * yScale);
    }

    @Override
    public Animation configureAnimation() {
        int shipHeight = 250;
        int shipWidth = 180;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 1; i++) {
            frames.add(new TextureRegion(getTexture(), 0, 500, shipWidth, shipHeight));
        }
        return new Animation(0.1f, frames);
    }

    @Override
    public void onHit(Bullet bullet)
    {
        if (objectState != Constants.GameObjectState.ACTIVE) {
            return;
        }
        this.setColor(1f,0f,0.0f,1f);
        this.tintTimer = 0.02f;
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
        return new BossWeapon(this.body.getPosition(), new Vector2(0, 1), world, this, playScreen);
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
        ObjectExplosion be = new ObjectExplosion(1f, this.getBoundingRectangle());
        this.playScreen.addAnimationSprite(be);
        this.playScreen.addScore(2000);
        this.setState(Constants.GameObjectState.CLEANING_PHYSICS_BODY);
        this.playScreen.onHeroDie();
    }

    @Override
    public void onSetToRemovableState() {
    }

    @Override
    public void onSetToCleaningPhysicsBodyState() {
//        disposePhysicsBody();
    }


}
