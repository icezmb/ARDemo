package com.amg.ar.mechanics.engine;


import android.content.Context;

import com.amg.ar.mechanics.behaviors.GameBehaviorFactory;
import com.amg.ar.mechanics.behaviors.GameBehaviorTime;
import com.amg.ar.mechanics.informations.GameInformationTime;
import com.amg.ar.mechanics.routine.Routine;
import com.amg.ar.mechanics.routine.RoutineTicker;
import com.amg.ar.model.mode.GameMode;
import com.amg.ar.model.mode.GameModeFactory;
import com.amg.ar.model.weapon.WeaponFactory;
import com.amg.ar.ui.gameviews.GameViewTime;
import com.amg.ar.ui.gameviews.GameViewTimeDecreasing;

public class GameEngineFactory {

    private static final long DEFAULT_SPAWNING_TIME = 1000;
    private static final long TWENTY_IN_A_ROW_SPAWNING_TIME = 800;
    private static final long DEFAULT_TICKING_TIME = 1000;
    private static final long DEFAULT_STARTING_TIME = 300000*1000;

    public static GameEngine create(final Context context,
                                    GameEngine.IGameEngine iGameEngine,
                                    GameMode gameMode) {

        GameEngine gameEngine = null;
        switch (gameMode.getType()) {
            case GameModeFactory.GAME_TYPE_REMAINING_TIME:
                //创建游戏引擎
                gameEngine = createSprintOrMarathon(context, gameMode, iGameEngine);
                break;
        }
        return gameEngine;
    }

    private static GameEngine createSprintOrMarathon(final Context context,
                                                     final GameMode gameMode,
                                                     final GameEngine.IGameEngine iGameEngine) {
        //Game Information
        final GameInformationTime gameInformation = new GameInformationTime(gameMode,
                //武器
                WeaponFactory.createBasicWeapon(),
                //游戏时间
                DEFAULT_STARTING_TIME * gameMode.getLevel());

        return createSprintOrMarathon(context, iGameEngine, gameInformation);
    }

    private static GameEngine createSprintOrMarathon(final Context context,
                                                     final GameEngine.IGameEngine iGameEngine,
                                                     GameInformationTime gameInformation) {
        //Game Behavior
        GameBehaviorTime gameBehavior = GameBehaviorFactory.createSprint();
        gameBehavior.setGameInformation(gameInformation);

        //Game Engine & Game Behavior
        final GameEngineTime gameEngine = new GameEngineTime(context, iGameEngine, gameBehavior) {
            @Override
            public void onRun(int routineType, Object obj) {
                switch (routineType) {
                    case Routine.TYPE_RELOADER:
                        //刷新事件
                        mGameBehavior.reload();
                        break;

                    case Routine.TYPE_SPAWNER:
                        //生产幽灵鬼事件
                        final float[] cameraAngle = mGameView.getCameraAngleInDegree();//获取相机角度

                        mGameBehavior.spawn((int) cameraAngle[0], (int) cameraAngle[1]);
                        break;

                    case Routine.TYPE_TICKER:
                        mGameBehavior.tick((Long) obj);
                        break;

                }
            }
        };
        gameEngine.addRoutine(new Routine(Routine.TYPE_RELOADER, gameInformation.getWeapon().getReloadingTime()));
        gameEngine.addRoutine(new Routine(Routine.TYPE_SPAWNER, DEFAULT_SPAWNING_TIME));
        gameEngine.addRoutine(new RoutineTicker(DEFAULT_TICKING_TIME));
        gameBehavior.setInterface(gameEngine);

        //Game View
        final GameViewTime gameView = new GameViewTimeDecreasing(context, gameEngine);
        gameEngine.setGameView(gameView);

        return gameEngine;
    }
}
