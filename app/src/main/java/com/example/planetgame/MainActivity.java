package com.example.planetgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static Game game;
    public static TextView tree, water, rock, steps;
    public static CellAdapter adapter;
    public static RecyclerView recyclerView;
    public static int[] toDestroy = {-1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tree = findViewById(R.id.tree);
        water = findViewById(R.id.water);
        rock = findViewById(R.id.rock);
        steps = findViewById(R.id.steps);
        recyclerView = findViewById(R.id.recycleView_main);

        Button startBtn = findViewById(R.id.startBtn);
        Context context = this;
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game = new Game(getSupportFragmentManager(), context);
            }
        });

        Button skipBtn=findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.newStep(null);
            }
        });

        game = new Game(getSupportFragmentManager(), this);
    }
}