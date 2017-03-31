package com.amg.ar.mechanics.informations;


import android.os.Parcel;
import android.os.Parcelable;

import com.amg.ar.model.mode.GameMode;
import com.amg.ar.model.weapon.Weapon;

public class GameInformationTime extends GameInformationStandard {

    //游戏剩余时间
    protected long mCurrentTime;
    protected long mStartingTimeInMillis;
    protected long mEndingTimeInMillis;

    public GameInformationTime(GameMode gameMode, Weapon weapon, long currentTime) {
        super(gameMode, weapon);
        mCurrentTime = currentTime;
        mStartingTimeInMillis = 0;
        mEndingTimeInMillis = System.currentTimeMillis();
    }

    public GameInformationTime(Parcel in) {
        super(in);
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mCurrentTime = in.readLong();
        mStartingTimeInMillis = in.readLong();
        mEndingTimeInMillis = in.readLong();

    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        super.writeToParcel(out, i);
        out.writeLong(mCurrentTime);
        out.writeLong(mStartingTimeInMillis);
        out.writeLong(mEndingTimeInMillis);
    }

    public static final Parcelable.Creator<GameInformationTime> CREATOR = new Parcelable.Creator<GameInformationTime>() {
        public GameInformationTime createFromParcel(Parcel in) {
            return new GameInformationTime(in);
        }

        public GameInformationTime[] newArray(int size) {
            return new GameInformationTime[size];
        }
    };

    public long getCurrentTime() {
        return mCurrentTime;
    }

    public void setCurrentTime(long currentTime) {
        mCurrentTime = currentTime;
    }

    public void setStartingTime() {
        mStartingTimeInMillis = System.currentTimeMillis();
    }

    public void setEndingTime() {
        mEndingTimeInMillis = System.currentTimeMillis();
    }

    public long getPlayingTime() {
        return mEndingTimeInMillis - mStartingTimeInMillis;
    }

}
