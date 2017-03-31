package com.amg.ar.mechanics.behaviors;

import java.util.List;

import com.amg.ar.mechanics.informations.GameInformation;
import com.amg.ar.mechanics.informations.GameInformationStandard;
import com.amg.ar.model.DisplayableItem;
import com.amg.ar.model.DisplayableItemFactory;
import com.amg.ar.model.TargetableItem;
import com.amg.ar.utils.LogUtils;


public abstract class GameBehaviorStandard implements GameBehavior {

    public final static int FIRE_RESULT_NO_AMMO = 0x00000001;
    public final static int FIRE_RESULT_MISS = 0x00000002;
    public final static int FIRE_RESULT_KILL = 0x00000003;
    public final static int FIRE_RESULT_HIT = 0x00000004;

    private GameInformationStandard mGameInformation;
    protected IGameBehavior mIGameBehavior;

    public abstract void spawn(int xRange, int yRange);

    @Override
    public void setInterface(IGameBehavior iGameBehavior) {
        mIGameBehavior = iGameBehavior;
    }

    @Override
    public GameInformation getGameInformation() {
        return mGameInformation;
    }

    @Override
    public void setGameInformation(GameInformation gameInformation) {
        mGameInformation = (GameInformationStandard) gameInformation;
    }

    @Override
    public void setCurrentPosition(float posX, float posY, float posZ) {
        mGameInformation.setCurrentPosition(posX, posY, posZ);
    }

    @Override
    public float[] getCurrentPosition() {
        return mGameInformation.getCurrentPosition();
    }

    @Override
    public List<DisplayableItem> getItemsForDisplay() {
        return mGameInformation.getItemsForDisplay();
    }

    @Override
    public TargetableItem getCurrentTarget() {
        return mGameInformation.getCurrentTarget();
    }

    @Override
    public void setCurrentTarget(TargetableItem t) {
        mGameInformation.setCurrentTarget(t);
    }

    @Override
    public void removeTarget() {
        mGameInformation.removeTarget();
    }

    @Override
    public int getLastScoreAdded() {
        return mGameInformation.getLastScoreAdded();
    }

    @Override
    public void onClick(int x,int y) {
        fire(x,y);
    }

    public int getCurrentAmmunition() {
        return mGameInformation.getCurrentAmmunition();
    }

    public int getCurrentCombo() {
        return mGameInformation.getCurrentCombo();
    }

    public int getCurrentScore() {
        return mGameInformation.getCurrentScore();
    }


    /**
     * 点击开火事件
     * @return
     */
    protected int fire(int x,int y) {
        int fireResult = FIRE_RESULT_NO_AMMO;
        final int dmg = mGameInformation.getWeapon().fire();

        //final TargetableItem currentTarget = mGameInformation.getCurrentTarget();
        final TargetableItem currentTarget = mGameInformation.getTargetableItem(x,y);
        if (dmg != 0) {
            mGameInformation.bulletFired();
            if (currentTarget == null) {
                LogUtils.e("-----------没打中------------");
                //miss
                fireResult = FIRE_RESULT_MISS;
                //显示弹孔
                //missTarget();
            } else {
                LogUtils.e("-----------打中幽灵鬼------------");
                //hit
                fireResult = FIRE_RESULT_HIT;
                currentTarget.hit(dmg); //击中，计算生命值和伤害值
                if (!currentTarget.isAlive()) {
                    //kill没有生命值了
                    fireResult = FIRE_RESULT_KILL;
                    killTarget(currentTarget);
                }
            }
        }
        return fireResult;
    }

    /**
     * 杀死目标
     * @param currentTarget
     */
    protected void killTarget(TargetableItem currentTarget) {
        //LogUtils.e("-------------------------------------------------------");
        //设置当前幽灵鬼才能执行后续操作
        mGameInformation.setCurrentTarget(currentTarget);

        mGameInformation.targetKilled();
        //增加弹药
        mGameInformation.stackCombo();
        //增加分数
        mGameInformation.increaseScore(10 * currentTarget.getBasePoint() + 10 * mGameInformation.getCurrentCombo());
        //增加经验值exp
        mGameInformation.earnExp(currentTarget.getExpPoint());
        //
        mIGameBehavior.onTargetKilled(currentTarget);
    }

    public void reload() {
        mGameInformation.getWeapon().reload();
    }


    /**
     * 新增幽灵鬼
     * 由事件工厂调过来
     */
    public void spawnGhost(int ghostType, int xRange, int yRange) {
        final float[] pos = mGameInformation.getCurrentPosition();
        TargetableItem targetableItem=DisplayableItemFactory.createGhostWithRandomCoordinates(
                ghostType,
                (int) pos[0] - xRange,
                (int) pos[0] + xRange,
                (int) pos[1] - yRange,
                (int) pos[1] + yRange
        );
        mGameInformation.addTargetableItem(targetableItem);
    }


}
