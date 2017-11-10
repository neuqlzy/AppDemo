package com.example.appdemo.control;

import android.app.Activity;

public interface ActivityControl {
    void turnActivity(Activity currentActivity, Class<?> targetActivity);
}
