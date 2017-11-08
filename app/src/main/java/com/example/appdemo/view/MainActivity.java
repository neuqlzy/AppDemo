package com.example.appdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.appdemo.R;
import com.example.appdemo.control.ActivityControlImpl;
import com.example.appdemo.control.ControlCenter;

public class MainActivity extends AppCompatActivity {
    private TextView txt_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlCenter.init(10);

        txt_play = (TextView)findViewById(R.id.txt_play);

        txt_play.setOnClickListener(this::txt_play_click);
    }

    private void txt_play_click(View v) {
        ControlCenter.getActivityControl().turnActivity(MainActivity.this, LoginActivity.class);
    }
}
