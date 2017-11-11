package com.example.appdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

import java.util.Calendar;

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

    private Calendar backpress = Calendar.getInstance();

    {
        backpress.add(Calendar.SECOND, -5);
    }
    @Override
    public void onBackPressed() {
        Calendar now = Calendar.getInstance();
        backpress.add(Calendar.SECOND, 3);
        if(now.compareTo(backpress) <= 0) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } else {
            Toast.makeText(getApplicationContext(), "再按一次退出游戏", Toast.LENGTH_SHORT).show();
        }
        backpress = now;
    }
}
