package com.example.appdemo.control;

public class ControlCenter {
    private static ActivityControl activityControl;
    private static GameControl gameControl;

    public static void init(int columnCount) {
        activityControl = new ActivityControlImpl();
        gameControl = new GameControlImpl(columnCount);
    }

    public static ActivityControl getActivityControl() {
        return activityControl;
    }

    public static GameControl getGameControl() {
        return gameControl;
    }
}
