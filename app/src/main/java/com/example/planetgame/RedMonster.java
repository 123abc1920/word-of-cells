package com.example.planetgame;

import android.view.View;

import java.util.Random;

public class RedMonster extends Entity {
    private static final int[] a = {Game.ONE_ROW, 1, -Game.ONE_ROW, -1, Game.ONE_ROW - 1, -Game.ONE_ROW + 1, Game.ONE_ROW + 1, -Game.ONE_ROW - 1};

    public RedMonster() {
        Cell cell;
        while (true) {
            cell = MainActivity.adapter.cellList.get(new Random().nextInt(25));
            if (!cell.isEntity) {
                this.cell = cell;
                this.cell.isEntity = true;
                return;
            }
        }
    }

    public void activity() {
        boolean canGo = false;
        for (Cell c : this.availableCells(a)) {
            if (c != null) {
                canGo = true;
                break;
            }
        }
        for (Cell c : this.availableCells(a)) {
            System.out.println(c.id);
        }
        if (canGo) {
            cell.redMonster.setVisibility(View.GONE);
            this.cell.isEntity = false;
            this.cell = this.availableCells(a).get(new Random().nextInt(this.availableCells(a).size()));
            this.cell.redMonster.setVisibility(View.VISIBLE);
            this.cell.isEntity = true;
        }
    }

}
