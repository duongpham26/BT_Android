package com.duongpham26.bai5_22;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Fruit> fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        fruits = new ArrayList<>();

        fruits.add(new Fruit("Orange", "47 Calories", R.drawable.orange));
        fruits.add(new Fruit("Cherry", "50 Calories", R.drawable.cherry));
        fruits.add(new Fruit("Banana", "89 Calories", R.drawable.banana));
        fruits.add(new Fruit("Apple", "52 Calories", R.drawable.apple));
        fruits.add(new Fruit("Kiwi", "61 Calories", R.drawable.kiwi));
        fruits.add(new Fruit("Pear", "57 Calories", R.drawable.pear));
        fruits.add(new Fruit("Strawberry", "33 Calories", R.drawable.strawberry));
        fruits.add(new Fruit("Lemon", "29 Calories", R.drawable.lemon));
        fruits.add(new Fruit("Peach", "39 Calories", R.drawable.peach));
        fruits.add(new Fruit("Apricot", "48 Calories", R.drawable.apricot));
        fruits.add(new Fruit("Mango", "60 Calories", R.drawable.mango));

        FruitAdapter adapter = new FruitAdapter(this, fruits);
        listView.setAdapter(adapter);
    }
}
