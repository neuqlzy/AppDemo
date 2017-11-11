package com.example.appdemo.control;

import com.example.appdemo.model.EliminateService;
import com.example.appdemo.model.EliminateServiceImpl;
import com.example.appdemo.model.RandomService;
import com.example.appdemo.model.RandomServiceImpl;

public class GameControlImpl implements GameControl {
    private RandomService randomService;
    private EliminateService eliminateService;
    private int[][] gridData;
    private int score;
    private int gameTime;
    private int columnCount;

    public GameControlImpl(int columnCount) {
        randomService = new RandomServiceImpl();
        eliminateService = new EliminateServiceImpl();
        this.columnCount = columnCount;
    }

    @Override
    public void initNewGame() {
        gridData = randomService.initIcons(columnCount);
        uneliminatableCheck();
        score = 0;
        gameTime = 60;
    }

    @Override
    public int[] getItemIds() {
        int[] data = new int[gridData.length * gridData.length];
        for(int i = 0; i < gridData.length; i++) {
            for (int j = 0; j < gridData[i].length; j++) {
                data[i + j * gridData.length] = gridData[i][j];
            }
        }
        return data;
    }

    @Override
    public int eliminate() {
        int k = eliminateService.eliminate(gridData);
        plusScore(k);
        return k;
    }

    @Override
    public int eliminate(int position1, int position2) {
        int count = 0;
        int x1, y1, x2, y2;
        x1 = position1 % columnCount;
        y1 = position1 / columnCount;
        x2 = position2 % columnCount;
        y2 = position2 / columnCount;
        if(isAdjacent(x1, y1, x2, y2)) {
            changeBlock(x1, y1, x2, y2);
            count = eliminateService.eliminate(gridData, x1, y1, x2, y2);
            if(count == 0) {
                changeBlock(x1, y1, x2, y2);
            }
        }
        plusScore(count);
        return count;
    }

    protected void changeBlock(int x1, int y1, int x2, int y2) {
        int temp = gridData[x1][y1];
        gridData[x1][y1] = gridData[x2][y2];
        gridData[x2][y2] = temp;
    }

    protected boolean isAdjacent(int x1, int y1, int x2, int y2) {
        if ((x1 == x2 && Math.abs(y1 - y2) == 1) || (Math.abs(x1 - x2) == 1 && y1 == y2)) {
            return true;
        }
        return false;
    }

    @Override
    public int fillEmptyBlock() {
        return randomService.fillEmptyBlock(gridData);
    }

    @Override
    public int getScore() {
        return score;
    }

    private void plusScore(int value) {
        if(gameTime > 0) {
            score += value;
            gameTime += value;
        }
    }

    @Override
    public boolean uneliminatableCheck() {
        if(eliminateService.uneliminatableCheck(gridData)) {
            gridData = randomService.initIcons(10);
            uneliminatableCheck();
            return true;
        }
        return false;
    }

    @Override
    public int getGameTime() {
        return this.gameTime;
    }

    @Override
    public void incGameTime() {
        this.gameTime--;
    }
}
