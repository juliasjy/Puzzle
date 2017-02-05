package com.rosehulman.edu.Sprites.Weapon;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.Bullet.AbstractBullet;
import com.rosehulman.edu.Sprites.Bullet.HeroBullets.SampleBullet;
import com.rosehulman.edu.Sprites.GameObject.Hero;
import com.rosehulman.edu.Sprites.Weapon.Abstract.SingleWeapon;

/**
 * Created by mot on 2/1/17.
 */

public class SampleWeapon extends SingleWeapon {

    public SampleWeapon(Vector2 position, Vector2 direction, World world, TextureAtlas textureAtlas, Hero hero, PlayScreen sc) {
        super(position, direction, world, textureAtlas, hero, sc);
    }

    @Override
    public float setFireInterval() {
        return 0.05f;
    }

    @Override
    public Animation configureBulletAnimation() {
        this.setRegion(this.bulletAtlas.getRegions().first());
        int bulletHeight = 62;
        int bulletWidth = 66;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 8; i++) {
//            new TextureRegion(getTexture(), 62 + i * 60, 25, bulletWidth, bulletHeight).
            frames.add(new TextureRegion(getTexture(), 62 + i * 60, 25, bulletWidth, bulletHeight));

        }
        return new Animation(0.1f, frames);
    }


    @Override
    public void fire() {
        Vector2 pos = new Vector2(this.position.x , this.position.y );
        Vector2 posLeft = new Vector2(pos.x - 0.2f, pos.y - 0.2f);
        Vector2 posRight = new Vector2(pos.x + 0.2f, pos.y - 0.2f);
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
