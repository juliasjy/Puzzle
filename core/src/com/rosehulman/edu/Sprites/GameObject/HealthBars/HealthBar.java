package com.rosehulman.edu.Sprites.GameObject.HealthBars;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.rosehulman.edu.Interface.Temporal;
import com.rosehulman.edu.Scenes.PlayScreen;
import com.rosehulman.edu.Sprites.GameObject.GameObject;
import com.rosehulman.edu.Utils.Utils;

/**
 * Created by mot on 2/2/17.
 */

public class HealthBar extends Sprite implements Temporal {
    private float objectHeight;
    private float objectWidth;
    private Vector2 position;

    private GameObject owner;
    private Sprite fgSprite;
    private Sprite bgSprite;

    private static float buffer = Utils.scaleWithPPM(10);
    private static TextureRegion fg = new TextureRegion(new Texture("green.jpg"));
    private static TextureRegion bg = new TextureRegion(new Texture("red.png"));

    public HealthBar(GameObject owner) {
        this.owner = owner;

        this.fgSprite = new Sprite(fg);
        this.bgSprite = new Sprite(bg);
        update(0);
    }

    @Override
    public void draw(Batch batch) {

        this.bgSprite.draw(batch);
        this.fgSprite.draw(batch);
    }


    @Override
    public void update(float dt) {
        float heightFix = owner.getHeight() / 2 + buffer;
        float width = owner.getWidth();
        float scale = owner.getHealth() / owner.getMaxHealth();
//        System.out.println(owner.getMaxHealth());
//        System.out.println("scale is " + scale);
        fgSprite.setBounds(owner.body.getPosition().x - owner.getWidth() / 2.0f, owner.body.getPosition().y + heightFix, width * scale, 0.15f);
        bgSprite.setBounds(owner.body.getPosition().x - owner.getWidth() / 2.0f, owner.body.getPosition().y + heightFix, width , 0.15f);
    }
}
