package com.example.appdemo.control;

/**
 * Created by 李泽阳 on 2017/11/2.
 */

public class ControlCenter {
    private static ActivityControl activityControl;

    static {
        activityControl = new ActivityControlImpl();
    }

    public static ActivityControl getActivityControl() {
        return activityControl;
    }

    public static void setActivityControl(ActivityControl activityControl) {
        ControlCenter.activityControl = activityControl;
    }
}
