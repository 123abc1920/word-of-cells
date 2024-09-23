package com.example.planetgame;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.util.Random;

public class FluidMonster extends Entity {
    private static final int[] a = {Game.ONE_ROW, 1, -Game.ONE_ROW, -1};
    public Type type;

    public FluidMonster() {
        Cell cell;

        Random r = new Random();
        int n = r.nextInt(4);
        if (n == 0) {
            this.type = Type.WATER;
        }
        if (n == 1) {
            this.type = Type.ROCK;
        }
        if (n == 2) {
            this.type = Type.TREE;
        }

        while (true) {
            cell = MainActivity.adapter.cellList.get(new Random().nextInt(25));
            if (!cell.isPlayer && !cell.isMonster) {
                this.cell = cell;
                this.cell.isMonster = true;
                return;
            }
        }
    }

    public void activity(Context context) {
        if (this.availableCells(a).size() > 0) {
            cell.fluidMonster.setVisibility(View.GONE);
            this.cell.isMonster = false;
            this.cell = this.availableCells(a).get(new Random().nextInt(this.availableCells(a).size()));
            this.cell.fluidMonster.setVisibility(View.VISIBLE);
            this.cell.isMonster = true;
            if (this.cell.type.equals(this.type)){
                this.cell.setEmpty();
            }
        }

        if (MainActivity.game.score.steps % 5 == 0) {
            Random random = new Random();
            int n = random.nextInt(3);
            if (n == 0) {
                cell.fluidMonster.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                this.type = Type.TREE;
            }
            if (n == 1) {
                cell.fluidMonster.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.grey)));
                this.type = Type.ROCK;
            }
            if (n == 2) {
                cell.fluidMonster.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue)));
                this.type = Type.WATER;
            }
        }
    }

}
