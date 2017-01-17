package com.rosehulman.edu.Utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Created by mot on 1/14/17.
 */

public class SpriteUtils {

    public static Vector2 getPosFromBounds(Rectangle bounds) {
        Vector2 result = new Vector2();
        float xPos = bounds.getX() + bounds.getWidth() / 2;
        float yPos = bounds.getY() + bounds.getHeight() / 2;
        float xScaled = Utils.scaleWithPPM(xPos);
        float yScaled = Utils.scaleWithPPM(yPos);
        result.set(xScaled, yScaled);
        return result;
    }

    public static Vector2 getBoxFromBounds(Rectangle bounds) {
        Vector2 result = new Vector2();
        float width = bounds.getWidth() / 2;
        float height = bounds.getHeight() / 2;
        float wScaled = Utils.scaleWithPPM(width);
        float hScaled = Utils.scaleWithPPM(height);
        result.set(wScaled, hScaled);
        return result;
    }
}
