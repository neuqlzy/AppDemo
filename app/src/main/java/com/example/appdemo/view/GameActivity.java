package com.example.appdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

import java.util.Calendar;
import java.util.Date;

public class GameActivity extends AppCompatActivity {
    protected GridView game_grid;
    protected TextView txt_score;
    protected TextView txt_exit;
    protected TextView txt_game_time;
    protected int selected = -1;
    protected MyTimer timer;
    protected MyTimer timer_fill;
    protected MyTimer timer_eli;
    protected MyTimer timer_check;
    protected boolean waiting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txt_score = (TextView)findViewById(R.id.txt_score);
        txt_game_time = (TextView)findViewById(R.id.txt_game_time);
        txt_exit = (TextView) findViewById(R.id.txt_exit);
        txt_exit.setOnClickListener(this::exit_click);
        game_grid = (GridView) findViewById(R.id.game_grid);
        game_grid.setAdapter(new GridAdapter(this, ControlCenter.getGameControl().getItemIds()));
        game_grid.setOnItemClickListener(this::item_click);
        timer = new MyTimer();
        timer.schedule(this::gameTimer_task, 1000, 1000);
    }

    private void gameTimer_task() {
        ControlCenter.getGameControl().incGameTime();
        txt_game_time.post(GameActivity.this::setGameTime);
        if(ControlCenter.getGameControl().getGameTime() <= 0) {
            timer.cancel();
            txt_score.post(GameActivity.this::score_board);
        }
    }

    private void setGameTime() {
        txt_game_time.setText("时间：" + ControlCenter.getGameControl().getGameTime());
    }

    private void score_board() {
        ControlCenter.getActivityControl().turnActivity(GameActivity.this, ScoreActivity.class);
    }

    private void exit_click(View view) {
        ControlCenter.getActivityControl().turnActivity(GameActivity.this, MainActivity.class);
    }

    protected void item_click(AdapterView<?> adapterView, View view, int i, long l) {
        if(waiting) return;
        GridAdapter adapter = (GridAdapter) game_grid.getAdapter();
        if(selected == -1) {
            selected = i;
            adapter.setSeclection(i);
        } else if(selected == i) {
            selected = -1;
            adapter.setSeclection(i);
        } else {
            adapter.setSeclection(selected);
            int k = ControlCenter.getGameControl().eliminate(selected, i);
            if(k > 0) {
                adapter.setItemId(ControlCenter.getGameControl().getItemIds());
            }
            selected = -1;
            timer_fill = new MyTimer();
            timer_fill.schedule(this::fill_task, 200);
            waiting = true;
        }
        adapter.notifyDataSetChanged();
    }

    private void refresh() {
        GridAdapter adapter = (GridAdapter) game_grid.getAdapter();
        adapter.setItemId(ControlCenter.getGameControl().getItemIds());
        adapter.notifyDataSetChanged();
        txt_score.setText("得分：" + ControlCenter.getGameControl().getScore());
        setGameTime();
    }

    private void fill_task() {
        timer_fill.cancel();
        ControlCenter.getGameControl().fillEmptyBlock();
        game_grid.post(GameActivity.this::refresh);
        timer_eli = new MyTimer();
        timer_eli.schedule(this::eliminate_task, 200);
    }

    private void eliminate_task() {
        timer_eli.cancel();
        int k = ControlCenter.getGameControl().eliminate();
        if(k > 0) {
            game_grid.post(GameActivity.this::refresh);
            timer_fill = new MyTimer();
            timer_fill.schedule(this::fill_task, 200);
        } else {
            timer_check = new MyTimer();
            timer_check.schedule(this::uneliminatableCheck_task, 200);
        }
    }

    private void uneliminatableCheck_task() {
        timer_check.cancel();
        if(ControlCenter.getGameControl().uneliminatableCheck()) {
            game_grid.post(this::uneliminatable_toast);
            game_grid.post(GameActivity.this::refresh);
        }
        waiting = false;
    }

    protected void uneliminatable_toast() {
        Toast.makeText(GameActivity.this, "不可消除！", Toast.LENGTH_LONG).show();
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
            timer.cancel();
            ControlCenter.getGameControl().stop();
            ControlCenter.getActivityControl().turnActivity(GameActivity.this, MainActivity.class);
        } else {
            Toast.makeText(getApplicationContext(), "再按一次结束游戏", Toast.LENGTH_SHORT).show();
        }
        backpress = now;
    }
}
