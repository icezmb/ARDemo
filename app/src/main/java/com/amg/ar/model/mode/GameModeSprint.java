package com.amg.ar.model.mode;


import android.os.Parcel;
import android.os.Parcelable;

import com.amg.ar.mechanics.informations.GameInformation;
import com.amg.ar.mechanics.informations.GameInformationStandard;

public class GameModeSprint extends GameMode {

    private static final int RANK_LIMIT_DESERTER = 0;
    private static final int RANK_LIMIT_SOLDIER = 500;
    private static final int RANK_LIMIT_CORPORAL = 900;
    private static final int RANK_LIMIT_SERGEANT = 1300;
    private static final int RANK_LIMIT_ADMIRAL = 1650;

    public GameModeSprint() {
        super();
    }

    protected GameModeSprint(Parcel in) {
        super(in);
    }


    @Override
    public int getRank(GameInformation gameInformation) {//获取排名
        final int score = ((GameInformationStandard) gameInformation).getScoreInformation().getScore();
        if (score >= RANK_LIMIT_ADMIRAL) {
            return GameModeFactory.GAME_RANK_ADMIRAL;
        } else if (score >= RANK_LIMIT_SERGEANT) {
            return GameModeFactory.GAME_RANK_SERGEANT;
        } else if (score >= RANK_LIMIT_CORPORAL) {
            return GameModeFactory.GAME_RANK_CORPORAL;
        } else if (score >= RANK_LIMIT_SOLDIER) {
            return GameModeFactory.GAME_RANK_SOLDIER;
        } else {
            return GameModeFactory.GAME_RANK_DESERTER;
        }
    }

    public static final Parcelable.Creator<GameModeSprint> CREATOR = new Parcelable.Creator<GameModeSprint>() {

        public GameModeSprint createFromParcel(Parcel in) {
            return new GameModeSprint(in);
        }

        public GameModeSprint[] newArray(int size) {
            return new GameModeSprint[size];
        }
    };

}

