package com.example.appdemo.control;

/**
 * Created by 李泽阳 on 2017/11/2.
 */

public class ControlCenter {
    private static ActivityControl activityControl;
    private static GameControl gameControl;

    static {
        activityControl = new ActivityControlImpl();
        gameControl = new GameControlImpl();
    }

    public static ActivityControl getActivityControl() {
        return activityControl;
    }

    public static GameControl getGameControl() {
        return gameControl;
    }
}
