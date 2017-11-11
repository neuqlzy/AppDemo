package com.example.appdemo.control;

public interface GameControl {
    void initNewGame();

    int[] getItemIds();

    int eliminate();

    int eliminate(int position1, int position2);

    int fillEmptyBlock();

    int getScore();

    boolean uneliminatableCheck();

    int getGameTime();

    void incGameTime();
}
