package com.rosehulman.edu.Sprites.Weapon;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rosehulman.edu.Puzzle;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 1/30/17.
 */

public class commonWeapon extends Sprite {
    public World world;
    public Body body;
    private Rectangle bounds;
    private Animation animation;
    private PlayScreen sc;
    private float stateTimer;

    public commonWeapon(World world, PlayScreen sc, Vector2 position) {
        super(sc.getBulletsAtlas().getTextures().first());
        this.world = world;
        this.sc = sc;
        this.bounds = new Rectangle();
        this.stateTimer = 0;
        define(position);
        setBounds(this.body.getPosition().x, this.body.getPosition().y, Utils.scaleWithPPM(32), Utils.scaleWithPPM(32));
        configureAnimation();
//        this.setRotation(90);
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.rotate(90);
    }


    public void define(Vector2 position) {
        BodyDef bdef = new BodyDef();

        bdef.position.set(position);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        fdef.filter.categoryBits = 0;
        fdef.filter.maskBits = -1;
        CircleShape shape = new CircleShape();
        shape.setRadius(12 / Puzzle.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

        body.setLinearVelocity(0, 10);
    }

//    public void createBody() {
//        BodyDef bdef = new BodyDef();
//        FixtureDef fdef = new FixtureDef();
//        PolygonShape shape = new PolygonShape();
//        Vector2 pos = SpriteUtils.getPosFromBounds(bounds);
//        bdef.relativePosition.set(pos);
//        bdef.type = BodyDef.BodyType.DynamicBody;
//
//        body = world.createBody(bdef);
//        Vector2 box = SpriteUtils.getBoxFromBounds(bounds);
//        shape.setAsBox(box.x, box.y);
//        fdef.shape = shape;
//        body.createFixture(fdef);
//
//        body.setLinearVelocity(0, 10);
//    }



    public void configureAnimation() {
        int bulletHeight = 55;
        int bulletWidth = 58;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 8; i++) {
            frames.add(new TextureRegion(getTexture(), 63 + i * 58, 30, bulletWidth, bulletHeight));
        }
        this.animation = new Animation(0.1f, frames);
        frames.clear();
    }


    public TextureRegion getNextFrame(float dt) {
        return (TextureRegion) this.animation.getKeyFrame(stateTimer, true);
    }

    public void update(float dt) {
        setPosition(this.body.getPosition().x - getWidth() / 2, this.body.getPosition().y - getHeight() / 2);
        this.stateTimer += dt * 2;
        this.setRegion(this.getNextFrame(dt));
//        setRotation(90);

    }

}
