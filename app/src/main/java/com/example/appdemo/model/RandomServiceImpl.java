package com.example.appdemo.model;

import java.security.SecureRandom;

/**
 * Created by 李泽阳 on 2017/11/8.
 */

public class RandomServiceImpl implements RandomService {
    private SecureRandom csprng;
    @Override
    public int[][] initIcons(int columnCount) {
        int temp;
        boolean checkok = false;
        int[][] icons = new int[columnCount][columnCount];
        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                do {
                    temp = getRandomIconId();
                    checkok = true;
                    if (i > 1) {
                        if (temp == icons[i - 1][j] && temp == icons[i - 2][j]) {
                            checkok = false;
                        }
                    }
                    if (j > 1) {
                        if (temp == icons[i][j - 1] && temp == icons[i][j - 2]) {
                            checkok = false;
                        }
                    }
                } while (!checkok);
                icons[i][j] = temp;
            }
        }
        return icons;
    }

    protected SecureRandom getCsprng() {
        if(csprng == null)
            csprng = new SecureRandom();
        return csprng;
    }

    protected int getRandomIconId() {
        return GameIconList.getIcons()[getCsprng().nextInt(GameIconList.getIconsCount())];
    }
    @Override
    public int fillEmptyBlock(int[][] gridData) {
        int count = 0;
        for(int x = 0; x < gridData.length; x++) {
            for(int y = gridData[x].length - 1; y >= 0; y--) {
                if(gridData[x][y] == GameIconList.getIconEmpty()) {
                    count++;
                    for(int k = y - 1; gridData[x][y] == GameIconList.getIconEmpty(); k--){
                        if(k < 0) {
                            gridData[x][y] = getRandomIconId();
                        } else if(gridData[x][k] != GameIconList.getIconEmpty()) {
                            gridData[x][y] = gridData[x][k];
                            gridData[x][k] = GameIconList.getIconEmpty();
                        }
                    }
                }
            }
        }
        return count;
    }
}
