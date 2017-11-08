package com.example.appdemo.model;

import java.security.SecureRandom;

/**
 * Created by 李泽阳 on 2017/11/8.
 */

public class RandomServiceImpl implements RandomService {
    private SecureRandom csprng;
    @Override
    public int[][] initIcons(int columnCount) {
        int[][] icons = new int[columnCount][columnCount];
        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                icons[i][j] = GameIconList.getIcons()[getCsprng().nextInt(GameIconList.getIconsCount())];
            }
        }
        return icons;
    }

    @Override
    public int getRandomIconId() {
        return getCsprng().nextInt(GameIconList.getIconsCount());
    }

    protected SecureRandom getCsprng() {
        if(csprng == null)
            csprng = new SecureRandom();
        return csprng;
    }
}
