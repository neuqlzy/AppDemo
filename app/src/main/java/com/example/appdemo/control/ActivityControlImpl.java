package com.example.appdemo.control;

import android.app.Activity;
import android.content.Intent;

import com.example.appdemo.view.LoginActivity;
import com.example.appdemo.view.MainActivity;

public class ActivityControlImpl implements ActivityControl {

    public void turnActivity(Activity currentActivity, Class<?> targetActivity) {
        Intent intent = new Intent();
        intent.setClass(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
    }
}
