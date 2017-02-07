package com.rosehulman.edu.Utils;

/**
 * Created by mot on 1/30/17.
 */

public class Constants {

    public static final short HERO_BIT = 2;
    public static final short ENEMY_BIT = 4;
    public static final short HERO_BULLET_BIT = 8;
    public static final short ENEMY_BULLET_BIT = 16;
    public static final short DESTROYED_BIT = 1;
    public static final short OBSTACLE_BIT = 32;

    public enum GameObjectState {INACTIVE, ACTIVE, DEAD, CLEANING_PHYSICS_BODY, REMOVABLE}
}

