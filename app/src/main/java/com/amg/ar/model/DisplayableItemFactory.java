package com.amg.ar.model;

import java.util.ArrayList;

public class DisplayableItemFactory {
    // Type
    public final static int TYPE_EASY_GHOST = 0x00000001;
    /*public final static int TYPE_BULLET_HOLE = 0x00000002;
    public final static int TYPE_BABY_GHOST = 0x00000003;
    public final static int TYPE_GHOST_WITH_HELMET = 0x00000004;
    public final static int TYPE_HIDDEN_GHOST = 0x00000005;
    public final static int TYPE_KING_GHOST = 0x00000006;
    public final static int TYPE_BLOND_GHOST = 0x00000007;*/

    //World Boundaries
    private static final int MAX_X_IN_DEGREE = 175;
    private static final int MIN_X_IN_DEGREE = -175;
    private static final int MAX_Y_IN_DEGREE = -45;
    private static final int MIN_Y_IN_DEGREE = -105;

    private static final int DEFAULT_X_MIN_IN_DEGREE = -170;
    private static final int DEFAULT_X_MAX_IN_DEGREE = 170;
    private static final int DEFAULT_Y_MIN_IN_DEGREE = -80;
    private static final int DEFAULT_Y_MAX_IN_DEGREE = -50;

    //Health
    public final static int HEALTH_EASY_GHOST = 1;
    public final static int HEALTH_BABY_GHOST = 1;
    public final static int HEALTH_GHOST_WITH_HELMET = 5;
    public final static int HEALTH_HIDDEN_GHOST = 1;
    public final static int HEALTH_KING_GHOST = 1;
    public final static int HEALTH_BLOND_GHOST = 2;

    //Base Point
    public final static int BASE_POINT_EAST_GHOST = 1;
    public final static int BASE_POINT_BABY_GHOST = 2;
    public final static int BASE_POINT_GHOST_WITH_HELMET = 10;
    public final static int BASE_POINT_HIDDEN_GHOST = 2;
    public final static int BASE_POINT_KING_GHOST = 1;
    public final static int BASE_POINT_BLOND_GHOST = 2;

    //Exp Point
    public final static int EXP_POINT_EASY_GHOST = 2;
    public final static int EXP_POINT_BABY_GHOST = 4;
    public final static int EXP_POINT_GHOST_WITH_HELMET = 10;
    public final static int EXP_POINT_HIDDEN_GHOST = 5;
    public final static int EXP_POINT_KING_GHOST = 100;
    public final static int EXP_POINT_BLOND_GHOST = 4;


    public static TargetableItem createGhostWithRandomCoordinates(int ghostType) {
        return createGhostWithRandomCoordinates(ghostType, DEFAULT_X_MIN_IN_DEGREE,
                DEFAULT_X_MAX_IN_DEGREE, DEFAULT_Y_MIN_IN_DEGREE, DEFAULT_Y_MAX_IN_DEGREE);
    }

    public static TargetableItem createGhostWithRandomCoordinates(int ghostType, int xMin, int xMax, int yMin, int yMax) {
        TargetableItem targetableItem = createEasyGhost();
        targetableItem.setRandomCoordinates(
                Math.max(MIN_X_IN_DEGREE, xMin),
                Math.min(MAX_X_IN_DEGREE, xMax),
                Math.max(MIN_Y_IN_DEGREE, yMin),
                Math.min(MAX_Y_IN_DEGREE, yMax));
        return targetableItem;
    }

    public static TargetableItem createEasyGhost() {
        final ArrayList<Integer> drops = new ArrayList<Integer>();
        final TargetableItem easyGhost = createTargetableItem(TYPE_EASY_GHOST,
                HEALTH_EASY_GHOST,
                BASE_POINT_EAST_GHOST,
                EXP_POINT_EASY_GHOST);

        easyGhost.setDrop(drops);
        return easyGhost;
    }

    private static TargetableItem createTargetableItem(int type, int health, int basePoint, int expPoint) {
        TargetableItem targetableItem = new TargetableItem();
        targetableItem.setType(type);
        targetableItem.setHealth(health);
        targetableItem.setBasePoint(basePoint);
        targetableItem.setExpPoint(expPoint);
        return targetableItem;
    }

}
