package com.example.appdemo.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this::btn_login_click);
    }

    private void btn_login_click(View v) {
        new AlertDialog.Builder(LoginActivity.this).setTitle("系统提示")//设置对话框标题
            .setMessage("未成年人请在父母的监护下游戏！")//设置显示的内容
            .setPositiveButton("继续登录", this::dialog_btn_ok_click)
            .setNegativeButton("退出登录", this::dialog_btn_cancel_click)
            .show();
    }

    private void dialog_btn_ok_click(DialogInterface dialog, int which) {
        ControlCenter.getActivityControl().turnActivity(LoginActivity.this, LoadingActivity.class);
    }

    private void dialog_btn_cancel_click(DialogInterface dialog, int which) {
        ControlCenter.getActivityControl().turnActivity(LoginActivity.this, MainActivity.class);
    }
}
