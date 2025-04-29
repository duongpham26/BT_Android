package com.duongpham26.bai5_22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    Context context;
    List<Fruit> fruits;

    public FruitAdapter(Context context, List<Fruit> fruits) {
        this.context = context;
        this.fruits = fruits;
    }

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int i) {
        return fruits.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView name = convertView.findViewById(R.id.fruitName);
        TextView calories = convertView.findViewById(R.id.calories);

        Fruit fruit = fruits.get(i);
        name.setText(fruit.name);
        calories.setText(fruit.calories);
        imageView.setImageResource(fruit.image);

        return convertView;
    }
}

