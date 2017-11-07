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

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by 李泽阳 on 2017/11/7.
 */

public class GameActivity extends AppCompatActivity {
    protected GridView game_grid;

    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private int[] icons = {
            R.mipmap.mushroom,
            R.mipmap.green_mushroom,
            R.mipmap.chain_chomp,
            R.mipmap.fire_flower,
            R.mipmap.goomba,
            R.mipmap.luma,
            R.mipmap.question_block,
            R.mipmap.question_coin,
            R.mipmap.super_paper_mario,
            R.mipmap.yoshi_egg
    };

    private int[] icon = new int[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game_grid = (GridView)findViewById(R.id.game_grid);
        initIcon();
        game_grid.setAdapter(new GridAdapter(this, icon));
        game_grid.setOnItemClickListener(this::item_click);
    }

    protected void item_click(AdapterView<?> adapterView, View view, int i, long l) {
        GridAdapter adapter = (GridAdapter)game_grid.getAdapter();
        adapter.setSeclection(i);
    }

    protected void initIcon() {
        SecureRandom csprng = new SecureRandom();
        for(int i = 0; i < icon.length; i++) {
            icon[i] = icons[csprng.nextInt(icons.length - 1)];
        }
    }
}
