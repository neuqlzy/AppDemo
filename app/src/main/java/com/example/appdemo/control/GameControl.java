package com.example.appdemo.control;

/**
 * Created by 李泽阳 on 2017/11/8.
 */

public interface GameControl {
    void initNewGame();
    int[] getItemIds();
    int[] eliminate();
    int[] eliminate(int position1, int position2);
}
