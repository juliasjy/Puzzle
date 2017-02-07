package com.rosehulman.edu.Sprites.Weapon.EnemyWeapons;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Bullet.AbstractBullet;
import com.rosehulman.edu.Sprites.Bullet.EnemyBullets.EnemyCommonBullet;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Sprites.GameObject.Hero;
import com.rosehulman.edu.Sprites.Weapon.Abstract.SingleWeapon;

/**
 * Created by mot on 2/1/17.
 */

public class EnemyWeaponLinear extends SingleWeapon {

    public EnemyWeaponLinear(Vector2 position, Vector2 direction, World world, TextureAtlas textureAtlas, GameObject owner, PlayScreen sc) {
        super(position, direction, world, textureAtlas, owner, sc);
    }

    @Override
    public float setFireInterval() {
        return 1f;
    }

    @Override
    public Animation configureBulletAnimation() {
        this.setRegion(this.bulletAtlas.getRegions().first());
        int bulletHeight = 225;
        int bulletWidth = 125;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 8; i++) {
            frames.add(new TextureRegion(getTexture(), 0, 0, bulletWidth, bulletHeight));

        }
        return new Animation(0.1f, frames);
    }

    @Override
    public void updateDirection() {
        Hero hero = sc.getHero();
        Vector2 heroPos = hero.body.getPosition();
        Vector2 myPos = this.owner.body.getPosition();

        Vector2 direction = new Vector2(heroPos.x - myPos.x, heroPos.y - myPos.y);
        direction.nor();
        this.direction = new Vector2(direction.x * this.bulletSpeed, direction.y * this.bulletSpeed);
    }


    @Override
    public void fire() {
        Vector2 pos = new Vector2(this.position.x , this.position.y );
        AbstractBullet bullet = EnemyCommonBullet.getInstance(pos, direction, bulletAnimation, sc);
        sc.addBullet(bullet);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
