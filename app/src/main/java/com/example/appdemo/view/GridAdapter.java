package com.example.appdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李泽阳 on 2017/11/7.
 */

public class GridAdapter extends BaseAdapter {

    private int itemLength;
    private int clickTemp = -1;//标识被选择的item
    private int[] clickedList;//这个数组用来存放item的点击状态
    private int[] itemId;
    private Context context;

    public GridAdapter(Context context, int[] itemId){
        this.context = context;
        this.itemId = itemId;
        itemLength = this.itemId.length;
        clickedList = new int[itemLength];
        for (int i = 0; i < itemLength; i++){
            clickedList[i] = 0;      //初始化item点击状态的数组
        }
    }

    public void setSeclection(int position) {
        clickTemp = position;
    }

    @Override
    public int getCount() {
        return itemLength;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ImageView imageview; // 声明ImageView的对象
        if (convertView == null) {
            imageview = new ImageView(context); // 实例化ImageView的对象
            imageview.setScaleType(ImageView.ScaleType.FIT_XY); // 设置缩放方式
            imageview.setPadding(0, 0, 0, 0); // 设置ImageView的内边距
        } else {
            imageview = (ImageView) convertView;
        }
        imageview.setImageResource(itemId[position]); // 为ImageView设置要显示的图片
        imageview.setImageAlpha(Integer.MAX_VALUE);
        if(clickTemp == position){    //根据点击的Item当前状态设置背景
            if (clickedList[position] == 0){
                imageview.setBackgroundColor(Color.BLUE);
                clickedList[position] = 1;
            }
            else {
                imageview.setBackgroundColor(Color.TRANSPARENT);
                clickedList[position] = 0;
                clickTemp = -1;
            }
        }
        return imageview;
    }
}
