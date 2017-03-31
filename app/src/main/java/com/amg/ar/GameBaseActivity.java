package com.amg.ar;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.ViewGroup;

import com.amg.ar.mechanics.engine.GameEngine;
import com.amg.ar.mechanics.engine.GameEngineFactory;
import com.amg.ar.model.mode.GameMode;
import com.amg.ar.model.mode.GameModeFactory;

public class GameBaseActivity extends ARBaseActivity implements GameEngine.IGameEngine {

    private GameEngine mGameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    protected void onPause() {
        super.onPause();

        if (mGameEngine != null) {
            mGameEngine.pause();
        }
    }

    @Override
    void onSmoothCoordinateChanged(float[] smoothCoordinate) {
        //传感器传来的三个值
        mGameEngine.changePosition(
                (float) Math.toDegrees(smoothCoordinate[0]),
                (float) Math.toDegrees(smoothCoordinate[2]),
                (float) Math.toDegrees(smoothCoordinate[1])
        );
    }

    @Override
    void onCameraReady(float horizontal, float vertical) {
        //if no gameBehavior choose the one corresponding to the right gameMode
        if (mGameEngine != null) {
            configureGameEngine(horizontal, vertical);
            mGameEngine.resume();

        } else {
            GameMode gameMode=GameModeFactory.createRemainingTimeGame(1);

            mGameEngine = GameEngineFactory.create(this, this, gameMode);
            configureGameEngine(horizontal, vertical);
            mGameEngine.start();
        }

    }

    private void configureGameEngine(float horizontal, float vertical) {
        //设置横向纵向的角度
        mGameEngine.setCameraAngle(horizontal, vertical);

        //第一层相机层在父类设置
        //第二层：幽灵鬼，瞄准镜等元素
        addContentView(mGameEngine.getGameView(),
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );

        //第三层：幽灵鬼死亡动画层
        addContentView(mGameEngine.getAnimationLayer(),
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );
    }

    @Override
    public void onGameEngineStop() {

    }

}
