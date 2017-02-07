package com.rosehulman.edu.Sprites.Weapon.HeroWeapons;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Bullet.AbstractBullet;
import com.rosehulman.edu.Sprites.Bullet.HeroBullets.SampleBullet;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Sprites.Weapon.Abstract.SingleWeapon;

/**
 * Created by mot on 2/1/17.
 */

public class HeroCommonWeapon extends SingleWeapon {

    public HeroCommonWeapon(Vector2 position, Vector2 direction, World world, TextureAtlas textureAtlas, GameObject owner, PlayScreen sc) {
        super(position, direction, world, textureAtlas, owner, sc);
        this.direction = new Vector2(0, 20);
    }

    @Override
    public float setFireInterval()
    {
        return 0.06f;
    }

    @Override
    public Animation configureBulletAnimation() {
        this.setRegion(this.bulletAtlas.getRegions().first());
        int bulletHeight = 150;
        int bulletWidth = 80;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(getTexture(), 389, 0, bulletWidth, bulletHeight));
//        frames.add(new TextureRegion(getTexture(), 535, 294, 70, 140));
        return new Animation(0.1f, frames);
    }

    @Override
    public void updateDirection() {
    }


    @Override
    public void fire() {
        Vector2 pos = new Vector2(this.position.x , this.position.y + 0.5f);
        Vector2 posLeft = new Vector2(pos.x - 0.4f, pos.y - 0.3f);
        Vector2 posRight = new Vector2(pos.x + 0.4f, pos.y - 0.3f);
        AbstractBullet bullet = SampleBullet.getInstance(pos, direction, bulletAnimation, sc);
        AbstractBullet bulletLeft = SampleBullet.getInstance(posLeft, direction, bulletAnimation, sc);
        AbstractBullet bulletRight = SampleBullet.getInstance(posRight, direction, bulletAnimation, sc);
        sc.addBullet(bullet);
        sc.addBullet(bulletLeft);
        sc.addBullet(bulletRight);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
