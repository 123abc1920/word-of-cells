package com.example.planetgame;

import static com.example.planetgame.Game.startDialog;

import android.view.View;

import java.util.Random;

public class RedMonster extends Entity {
    private static final int[] a = {Game.ONE_ROW, 1, -Game.ONE_ROW, -1, Game.ONE_ROW - 1, -Game.ONE_ROW + 1, Game.ONE_ROW + 1, -Game.ONE_ROW - 1};

    public RedMonster() {
        Cell cell;
        while (true) {
            cell = MainActivity.adapter.cellList.get(new Random().nextInt(25));
            if (!cell.isPlayer && !cell.isMonster) {
                this.cell = cell;
                this.cell.isMonster = true;
                return;
            }
        }
    }

    public void activity() {
        if (this.availableCells(a).size() > 0) {
            cell.redMonster.setVisibility(View.GONE);
            this.cell.isMonster = false;
            this.cell = this.availableCells(a).get(new Random().nextInt(this.availableCells(a).size()));
            for (Cell c : this.availableCells(a)) {
                if (c.isPlayer) {
                    this.cell = c;
                }
            }
            this.cell.redMonster.setVisibility(View.VISIBLE);
            this.cell.isMonster = true;
            if (this.cell.isPlayer) {
                startDialog(MainActivity.game.manager, "You lose(((");
            }
        }
    }

}
