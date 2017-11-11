package com.example.appdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    protected GridView game_grid;
    protected TextView txt_score;
    protected int selected = -1;
    protected MyTimer timer;
    protected MyTimer timer_eli;
    protected boolean waiting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txt_score = (TextView)findViewById(R.id.txt_score);
        game_grid = (GridView)findViewById(R.id.game_grid);
        game_grid.setAdapter(new GridAdapter(this, ControlCenter.getGameControl().getItemIds()));
        game_grid.setOnItemClickListener(this::item_click);
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
            timer = new MyTimer();
            timer.schedule(this::fill_task, 200);
            waiting = true;
        }
        adapter.notifyDataSetChanged();
    }

    private void refresh() {
        GridAdapter adapter = (GridAdapter) game_grid.getAdapter();
        adapter.setItemId(ControlCenter.getGameControl().getItemIds());
        adapter.notifyDataSetChanged();
        txt_score.setText(Integer.toString(ControlCenter.getGameControl().getScore()));
    }

    private void fill_task() {
        int k = ControlCenter.getGameControl().fillEmptyBlock();
        if(k > 0) {
            game_grid.post(GameActivity.this::refresh);
            timer_eli = new MyTimer();
            timer_eli.schedule(this::eliminate_task, 500);
        } else waiting = false;
    }

    private void eliminate_task() {
        int k = ControlCenter.getGameControl().eliminate();
        if(k > 0) {
            timer = new MyTimer();
            timer.schedule(this::fill_task, 200);
        } else waiting = false;
    }
}
