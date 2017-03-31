package com.amg.ar.mechanics.engine;


import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.amg.ar.mechanics.behaviors.GameBehavior;
import com.amg.ar.mechanics.informations.GameInformation;
import com.amg.ar.mechanics.routine.Routine;
import com.amg.ar.model.DisplayableItem;
import com.amg.ar.model.TargetableItem;
import com.amg.ar.ui.AnimationLayer;
import com.amg.ar.ui.gameviews.GameView;
import com.amg.ar.utils.LogUtils;

public abstract class GameEngine implements GameBehavior.IGameBehavior,Routine.IRoutine {

    public static final int STATE_STOP = 0x00000001;
    public static final int STATE_RUNNING = 0x00000002;
    public static final int STATE_PAUSED = 0x00000003;

    protected int mCurrentState;

    final protected IGameEngine mInterface;
    final protected GameBehavior mGameBehavior;
    final protected ArrayList<Routine> mRoutines;

    //游戏视图
    private GameView mGameView;
    protected AnimationLayer mAnimationLayer;

    public GameEngine(final Context context, IGameEngine iGameEngine, GameBehavior gameBehavior) {

        mRoutines = new ArrayList<>();
        mCurrentState = STATE_STOP;
        mInterface = iGameEngine;
        mGameBehavior = gameBehavior;
        mAnimationLayer = new AnimationLayer(context);
    }


    /**
     * start the game, should be called only at the beginning once.
     */
    public void start() {
        startRoutines();
        mCurrentState = STATE_RUNNING;
    }

    /**
     * pause the game.
     */
    public void pause() {
        stopRoutines();
        mCurrentState = STATE_PAUSED;
    }

    /**
     * resume the game
     */
    public void resume() {
        startRoutines();
        mCurrentState = STATE_RUNNING;
    }

    /**
     * stop the game, should not be called manually
     */
    public void stop() {
        stopRoutines();
        mCurrentState = STATE_STOP;
        mInterface.onGameEngineStop();
    }

    protected void setGameView(GameView gameView) {
        mGameView = gameView;
        mGameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                mGameBehavior.onClick(x,y);
                return false;
            }
        });
        mGameView.setAnimationLayer(mAnimationLayer);
    }

    protected void startRoutines() {
        for (Routine routine : mRoutines) {
            routine.startRoutine();
        }
    }

    protected void stopRoutines() {
        for (Routine routine : mRoutines) {
            routine.stopRoutine();
        }
    }

    protected void addRoutine(Routine routine) {
        routine.setIRoutine(this);
        mRoutines.add(routine);
    }

    //传感器传来的三个值
    public void changePosition(float posX, float posY, float posZ) {
        mGameBehavior.setCurrentPosition(posX, posY, posZ);
        mGameView.invalidate();
    }

    //设置横向纵向的角度
    public void setCameraAngle(float horizontal, float vertical) {
        mGameView.setCameraAngleInDegree(horizontal, vertical);
    }

    public float[] getCurrentPosition() {
        return mGameBehavior.getCurrentPosition();
    }

    public List<DisplayableItem> getItemsForDisplay() {
        return mGameBehavior.getItemsForDisplay();
    }


    public TargetableItem getCurrentTarget() {
        return mGameBehavior.getCurrentTarget();
    }

    public void setCurrentTarget(TargetableItem t) {
        mGameBehavior.setCurrentTarget(t);
    }

    public void removeTarget() {
        mGameBehavior.removeTarget();
    }

    public int getLastScoreAdded() {
        return mGameBehavior.getLastScoreAdded();
    }

    public GameView getGameView() {
        return mGameView;
    }

    public AnimationLayer getAnimationLayer() {
        return mAnimationLayer;
    }

    @Override
    public void onSoundRequest(int soundType) {
    }

    public interface IGameEngine {
        void onGameEngineStop();
    }
}
