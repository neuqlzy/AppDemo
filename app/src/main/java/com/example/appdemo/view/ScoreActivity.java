package com.example.appdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

public class ScoreActivity extends AppCompatActivity {
    private TextView txt_finally_score;
    private MyTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        txt_finally_score = (TextView) findViewById(R.id.txt_finally_score);
        txt_finally_score.setText("您的得分：" + ControlCenter.getGameControl().getScore());
        timer = new MyTimer();
        timer.schedule(this::turnMainActivity_task, 10000);
    }

    private void turnMainActivity_task() {
        timer.cancel();
        txt_finally_score.post(ScoreActivity.this::turnMainActivity);
    }

    private void turnMainActivity() {
        ControlCenter.getActivityControl().turnActivity(ScoreActivity.this, MainActivity.class);
    }
}
