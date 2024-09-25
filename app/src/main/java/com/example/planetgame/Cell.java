package com.example.planetgame;

import static com.example.planetgame.Game.startDialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.Random;

public class Cell {
    public int num, row, col, id;
    public Type type;
    public boolean isEmpty = false;
    public boolean isDestroy = false;
    public boolean isMonster = false;
    public boolean isPlayer = false;
    public boolean isBridge = false;
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
        this.background.setText("0");
        this.num = 0;
    }

    public void setDestroy() {
        this.isDestroy = true;
        this.isPlayer = false;
        this.isMonster = false;
        this.isBridge = false;
        this.num = 0;
        this.background.setBackgroundColor(Color.TRANSPARENT);
        this.background.setText("");

        if (this.equals(MainActivity.game.player.cell)) {
            startDialog(MainActivity.game.manager, "You lose(((");
        }
        if (this.equals(MainActivity.game.redMonster.cell)) {
            MainActivity.game.redMonster.isDestroy = true;
        }
        if (this.equals(MainActivity.game.greenMonster.cell)) {
            MainActivity.game.greenMonster.isDestroy = true;
        }
        if (this.equals(MainActivity.game.fluidMonster.cell)) {
            MainActivity.game.fluidMonster.isDestroy = true;
        }

        this.player.setVisibility(View.INVISIBLE);
        this.redMonster.setVisibility(View.INVISIBLE);
        this.greenMonster.setVisibility(View.INVISIBLE);
        this.fluidMonster.setVisibility(View.INVISIBLE);
    }
}
