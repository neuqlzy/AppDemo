package com.example.appdemo.control;

import android.app.Activity;

/**
 * Created by 李泽阳 on 2017/11/2.
 */

public interface ActivityControl {
    void turnActivity(Activity currentActivity, Class<?> targetActivity);
}
