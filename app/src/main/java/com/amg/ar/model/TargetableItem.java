package com.amg.ar.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TargetableItem extends DisplayableItem {

    //health of the item
    protected int mHealth;
    protected int mBasePoint;
    protected int mExpPoint;
    protected ArrayList<Integer> mDrop;


    public TargetableItem() {
        super();
        mBasePoint = 1;
        mHealth = 1;
        mExpPoint = 0;
        mDrop = new ArrayList<>();
    }

    public TargetableItem(int x, int y, int type) {
        super(x, y, type);
        mBasePoint = 1;
        mHealth = 1;
        mExpPoint = 0;
        mDrop = new ArrayList<>();
    }

    protected TargetableItem(Parcel in) {
        super(in);
    }

    /**
     * Hit the item with damage
     *  生命值-伤害值
     * @param damage
     */
    public void hit(int damage) {
        mHealth = Math.max(0, mHealth - damage);
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        super.writeToParcel(out, i);
        out.writeInt(mHealth);
        out.writeInt(mBasePoint);
        out.writeInt(mExpPoint);
        out.writeList(mDrop);
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mHealth = in.readInt();
        mBasePoint = in.readInt();
        mExpPoint = in.readInt();
        mDrop = new ArrayList<Integer>();
        in.readList(mDrop, Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<TargetableItem> CREATOR = new Parcelable.Creator<TargetableItem>() {
        public TargetableItem createFromParcel(Parcel in) {
            return new TargetableItem(in);
        }

        public TargetableItem[] newArray(int size) {
            return new TargetableItem[size];
        }
    };





    /**
     * used to know if this targetable is alive
     *
     * @return true if alive
     */
    public boolean isAlive() {
        if (mHealth == 0){
            return false;
        }
        return true;
    }

    /**
     * Getters and Setters
     */
    public int getHealth() {
        return mHealth;
    }

    public void setHealth(int health) {
        mHealth = health;
    }

    public int getBasePoint() {
        return mBasePoint;
    }

    public void setBasePoint(int basePoint) {
        mBasePoint = basePoint;
    }

    public void setExpPoint(int expPoint) {
        mExpPoint = expPoint;
    }

    public int getExpPoint() {
        return mExpPoint;
    }

    public ArrayList<Integer> getDrop() {
        return mDrop;
    }

    public void setDrop(ArrayList<Integer> drop) {
        mDrop = drop;
    }


    /**
     * 随机产生幽灵鬼类型
     * @return
     */
    public static int randomGhostTypeEasy() {
        /*final int randomDraw = MathUtils.randomize(0, 100);
        if (randomDraw < 40) {
            //40%
            return DisplayableItemFactory.TYPE_EASY_GHOST;
        } else if (randomDraw < 80) {
            //40%
            return DisplayableItemFactory.TYPE_HIDDEN_GHOST;
        } else if (randomDraw < 99) {
            //19%
            return DisplayableItemFactory.TYPE_BLOND_GHOST;
        } else {
            //1%
            return DisplayableItemFactory.TYPE_KING_GHOST;
        }*/
        return DisplayableItemFactory.TYPE_EASY_GHOST;
    }


}
