package com.amg.ar.model.mode;


import com.amg.ar.R;


public class GameModeFactory {

    public static final int GAME_TYPE_TUTORIAL = 0x00000000;
    public static final int GAME_TYPE_REMAINING_TIME = 0x00000001;
    public static final int GAME_TYPE_LIMITED_TARGETS = 0x00000002;
    public static final int GAME_TYPE_SURVIVAL = 0x00000003;
    public static final int GAME_TYPE_DEATH_TO_THE_KING = 0x00000004;
    public static final int GAME_TYPE_OVERALL_RANKING = 0x00000005;
    public static final int GAME_TYPE_TWENTY_IN_A_ROW = 0x00000006;
    public static final int GAME_TYPE_MEMORIZE = 0x00000007;

    public static final int GAME_RANK_DESERTER = 0x00000000;
    public static final int GAME_RANK_SOLDIER = 0x00000001;
    public static final int GAME_RANK_CORPORAL = 0x00000002;
    public static final int GAME_RANK_SERGEANT = 0x00000003;
    public static final int GAME_RANK_ADMIRAL = 0x00000004;


    public static GameMode createRemainingTimeGame(int level) {
        final GameMode gameMode;
        switch (level) {
            case 1:
                gameMode = new GameModeSprint();
                gameMode.setImage(R.drawable.ic_icon_time_based_game_30_s);
                break;

            default:
                gameMode = new GameMode();
                gameMode.setImage(R.drawable.ghost);
        }
        gameMode.setType(GAME_TYPE_REMAINING_TIME);
        gameMode.setLevel(level);
        gameMode.setBonusAvailable(true);
        return gameMode;
    }

}
