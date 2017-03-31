package com.amg.ar.ui.gameviews;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.amg.ar.mechanics.engine.GameEngineTime;

/**
 * 在这里实现绘制游戏剩余时间
 */
public class GameViewTime extends GameViewStandard {

    protected GameEngineTime mGameEngine;

    public GameViewTime(Context c, GameEngineTime gameEngine) {
        super(c, gameEngine);
        mGameEngine = gameEngine;
    }

    @Override
    public void onDrawing(Canvas c) {
        super.onDrawing(c);
        drawTimer(c);
    }

    /**
     * draw time, in red if time < 10 sec else in green
     *
     * @param canvas canvas from View.onDraw method
     */
    protected void drawTimer(Canvas canvas) {
        //右上角的游戏剩余时间
        /*final long millis = mGameEngine.getCurrentTime();
        final int seconds = (int) (millis / 1000);
        final String remainingTime = String.valueOf(seconds);
        final int radius = Math.max(mTimerBitmap.getWidth(), mTimerBitmap.getHeight()) + (int) mPadding;
        resetPainter();

        //draw transparent overlay
        useTransparentBlackPainter();
        canvas.drawOval(new RectF(-radius, -radius, radius, radius), mPaint);

        //draw icon
        useWhitePainter();
        canvas.drawBitmap(mTimerBitmap, 0, 0, mPaint);

        //draw time
        mPaint.getTextBounds(remainingTime, 0, remainingTime.length(), mBounds);
        canvas.drawText(remainingTime
                , mPadding + radius
                , radius
                , mPaint);*/

    }

}
