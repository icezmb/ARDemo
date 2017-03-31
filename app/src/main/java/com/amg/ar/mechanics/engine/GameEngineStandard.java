package com.amg.ar.mechanics.engine;


import android.content.Context;

import com.amg.ar.mechanics.behaviors.GameBehaviorStandard;
import com.amg.ar.model.TargetableItem;
import com.amg.ar.ui.gameviews.GameView;
import com.amg.ar.ui.gameviews.GameViewStandard;

public abstract class GameEngineStandard extends GameEngine {

    protected GameBehaviorStandard mGameBehavior;
    protected GameViewStandard mGameView;

    public GameEngineStandard(Context context, IGameEngine iGameEngine, GameBehaviorStandard gameBehavior) {
        super(context, iGameEngine, gameBehavior);
        mGameBehavior = gameBehavior;
    }

    public int getCurrentAmmunition() {
        return mGameBehavior.getCurrentAmmunition();
    }

    public int getCurrentCombo() {
        return mGameBehavior.getCurrentCombo();
    }

    public int getCurrentScore() {
        return mGameBehavior.getCurrentScore();
    }

    protected void setGameView(GameView gameView) {
        super.setGameView(gameView);
        mGameView = (GameViewStandard) gameView;
    }

    /**
     * 命中目标，处理目标动画
     * @param target
     */
    public void onTargetKilled(TargetableItem target) {
        mGameView.animateDyingGhost(target);
    }

}
