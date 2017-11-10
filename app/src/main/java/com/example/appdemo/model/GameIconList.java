package com.example.appdemo.model;

import com.example.appdemo.R;

/**
 * Created by 李泽阳 on 2017/11/8.
 */

public class GameIconList {
    private final static int[] icons = {
            R.mipmap.mushroom,
            R.mipmap.green_mushroom,
            R.mipmap.chain_chomp,
            R.mipmap.fire_flower,
            R.mipmap.goomba,
            R.mipmap.luma,
            R.mipmap.question_block,
            R.mipmap.question_coin,
            R.mipmap.super_paper_mario,
            R.mipmap.yoshi_egg
    };

    private final static int icon_empty = R.mipmap.wrong;
    public static int getIconEmpty() {
        return icon_empty;
    }
    public static int getIconsCount() {
        return icons.length;
    }
    public static int[] getIcons() {
        return icons;
    }
}
