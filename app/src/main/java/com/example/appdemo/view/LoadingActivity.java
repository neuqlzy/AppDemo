package com.example.appdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 李泽阳 on 2017/10/31.
 */

public class LoadingActivity extends AppCompatActivity {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            ControlCenter.getActivityControl().turnActivity(LoadingActivity.this, GameActivity.class);
            LoadingActivity.this.finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        timer.schedule(task, 3000);
    }
}
