package com.example.appdemo.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

/**
 * Created by 李泽阳 on 2017/11/7.
 */

public class GameActivity extends AppCompatActivity {
    protected GridView game_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game_grid = (GridView)findViewById(R.id.game_grid);
        game_grid.setAdapter(new GridAdapter(this, ControlCenter.getGameControl().getItemIds()));
        game_grid.setOnItemClickListener(this::item_click);
    }

    protected void item_click(AdapterView<?> adapterView, View view, int i, long l) {
        GridAdapter adapter = (GridAdapter)game_grid.getAdapter();
        adapter.setSeclection(i);
        adapter.notifyDataSetChanged();
    }
}
