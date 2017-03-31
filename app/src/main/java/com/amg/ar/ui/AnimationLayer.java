package com.amg.ar.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amg.ar.R;
import com.amg.ar.utils.LogUtils;

/**
 *幽灵鬼死亡显示的视图层
 */
public class AnimationLayer extends RelativeLayout {
    private final Context mContext;

    public AnimationLayer(Context context) {
        super(context);
        mContext = context;
    }

    //画死亡的幽灵怪物
    public void drawDyingGhost(Drawable drawable, int score, int textSize, int posX, int posY) {
        //death animation死亡动画
        ImageView gdView = new ImageView(mContext);
        gdView.setImageDrawable(drawable);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = posY;
        layoutParams.leftMargin = posX;

        Animation dyingAnimation = AnimationUtils.loadAnimation(mContext, R.anim.dying_ghost);
        AnimationListenerWithDeleter dyingGhostAnimationListener = new AnimationListenerWithDeleter(gdView);
        dyingAnimation.setAnimationListener(dyingGhostAnimationListener);


        addView(gdView, layoutParams);
        gdView.startAnimation(dyingAnimation);

        //score animation
        /*if (score != 0) {
            TextView splashingScore = new TextView(mContext);
            splashingScore.setTextSize(textSize);
            splashingScore.setMaxLines(1);
            if (score > 0) {
                splashingScore.setText("+" + score);
                splashingScore.setTextColor(getResources().getColor(R.color.holo_green));
            } else {
                splashingScore.setText(score);
                splashingScore.setTextColor(getResources().getColor(R.color.holo_red));
            }

            Animation splashingAnimation = AnimationUtils.loadAnimation(mContext, R.anim.splashing_score);
            AnimationListenerWithDeleter splashingAnimationListener = new AnimationListenerWithDeleter(splashingScore);
            splashingAnimation.setAnimationListener(splashingAnimationListener);

            //将这个view从父view中移除，然后再加入父view的顶端,保证在最前面
            this.bringToFront();
            addView(splashingScore, layoutParams);
            splashingScore.startAnimation(splashingAnimation);
        }*/
    }


    public void showTextView(TextView textView) {
        final Context context = getContext();
        if (context != null) {
            final Animation oldAnimation = textView.getAnimation();
            if (oldAnimation != null){
                oldAnimation.cancel();
            }
            final Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            textView.startAnimation(fadeIn);
        }
    }

    public void changeTextView(TextView textView, int nextStringId) {
        final Context context = getContext();
        if (context != null) {
            changeTextView(textView, context.getString(nextStringId));
        }
    }

    public void changeTextView(TextView textView, String nextString) {
        final Context context = getContext();
        if (context != null) {
            final Animation oldAnimation = textView.getAnimation();
            if (oldAnimation != null) oldAnimation.cancel();
            final Animation fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
            fadeOut.setAnimationListener(new FadeOutTextViewListener(textView, nextString));
            textView.startAnimation(fadeOut);
        }
    }

    public void hideTextView(TextView textView) {
        final Context context = getContext();
        if (context != null) {
            final Animation fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
            fadeOut.setAnimationListener(new FadeOutTextViewListener(textView));
            textView.startAnimation(fadeOut);
        }
    }


    /**
     * Delete the view once the animation ends
     * 动画结束的时候删除这个幽灵鬼（控件）
     */
    private class AnimationListenerWithDeleter implements Animation.AnimationListener {

        private View gdView;

        public AnimationListenerWithDeleter(View viewToDelete) {
            gdView = viewToDelete;
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            final ViewGroup container = (ViewGroup) gdView.getParent();
            container.post(new Runnable() {
                @Override
                public void run() {
                    container.removeView(gdView);
                    gdView = null;
                }
            });
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }


    private class FadeOutTextViewListener implements Animation.AnimationListener {
        private String mNextString;
        private final TextView mTextView;

        public FadeOutTextViewListener(TextView textView) {
            mTextView = textView;
        }


        public FadeOutTextViewListener(TextView textView, final String nextString) {
            mNextString = nextString;
            mTextView = textView;
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            post(new Runnable() {
                @Override
                public void run() {
                    final Context context = getContext();
                    if (mNextString != null && context != null) {
                        mTextView.setText(mNextString);
                        final Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
                        if (fadeIn != null) {
                            mTextView.startAnimation(fadeIn);
                        }
                    } else {
                        mTextView.setVisibility(GONE);
                    }
                }
            });
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }




}
