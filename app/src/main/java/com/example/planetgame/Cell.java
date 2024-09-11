package com.example.planetgame;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.Random;

public class Cell {
    public int num, row, col, id;
    public Type type;
    public boolean isEmpty = false;
    public boolean isDestroy = false;
    public boolean isEntity = false;
    public TextView background;
    public MaterialCardView player;
    public ImageView redMonster, greenMonster, fluidMonster;

    public Cell(int row, int col, int id, Context context) {
        Random rand = new Random();
        int type = rand.nextInt(3) + 1;
        if (type == 1) {
            this.type = Type.ROCK;
        } else if (type == 2) {
            this.type = Type.TREE;
        } else if (type == 3) {
            this.type = Type.WATER;
        }
        this.num = rand.nextInt(21) + 1;

        this.row = row;
        this.col = col;

        this.id = id;
    }

    public void setEmpty() {
        isEmpty = true;
        this.background.setBackgroundColor(Color.CYAN);
        this.num = 0;
    }
}
