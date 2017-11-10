package com.example.appdemo.view;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer extends Timer {

    public TimerTask schedule(final Runnable r, long delay) {
        final TimerTask task = new TimerTask() { public void run() { r.run(); }};
        this.schedule(task, delay);
        return task;
    }
}
