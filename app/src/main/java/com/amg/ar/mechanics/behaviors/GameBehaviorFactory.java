package com.amg.ar.mechanics.behaviors;

import com.amg.ar.model.TargetableItem;

//事件工厂
public class GameBehaviorFactory {

    //同时存在幽灵鬼的最大数
    public static final int DEFAULT_MAX_TARGET = 1;

    public static GameBehaviorTimeDecreasing createSprint() {

        return new GameBehaviorTimeDecreasing() {
            @Override
            public void spawn(int xRange, int yRange) {//49;67
                if (mGameInformation.getCurrentTargetsNumber() < GameBehaviorFactory.DEFAULT_MAX_TARGET) {
                    //生产幽灵鬼
                    final int ghostType = TargetableItem.randomGhostTypeEasy();
                    spawnGhost(ghostType, xRange*3/5, yRange*3/5);
                }
            }
        };
    }

}
