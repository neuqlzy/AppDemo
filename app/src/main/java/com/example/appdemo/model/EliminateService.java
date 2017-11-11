package com.example.appdemo.model;

public interface EliminateService {
    int eliminate(int[][] gridData, int x1, int y1, int x2, int y2);

    int eliminate(int[][] gridData);

    boolean uneliminatableCheck(int[][] gridData);
}
