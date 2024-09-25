package com.example.planetgame;

import android.view.View;

import java.util.Random;

public class GreenMonster extends Entity {
    private static final int[] a = {Game.ONE_ROW - 1, -Game.ONE_ROW + 1, Game.ONE_ROW + 1, -Game.ONE_ROW - 1};
    public boolean isDestroy = false;

    public GreenMonster() {
        Cell cell;
        while (true) {
            cell = MainActivity.adapter.cellList.get(new Random().nextInt(Game.CELLS));
            if (!cell.isPlayer && !cell.isMonster) {
                this.cell = cell;
                this.cell.isMonster = true;
                return;
            }
        }
    }

    public void activity() {
        if (this.isDestroy) {
            return;
        }
        if (this.availableCells(a).size() > 0) {
            this.cell.greenMonster.setVisibility(View.GONE);
            this.cell.isMonster = false;
            boolean playerIsNearby = false;
            for (Cell c : this.availableCells(a)) {
                if (c.id == MainActivity.game.player.cell.id) {
                    this.cell = c;
                    playerIsNearby = true;
                    break;
                }
            }
            if (!playerIsNearby) {
                this.cell = this.availableCells(a).get(new Random().nextInt(this.availableCells(a).size()));
            }
            this.cell.greenMonster.setVisibility(View.VISIBLE);
            this.cell.isMonster = true;
            if (this.cell.isPlayer) {
                MainActivity.game.score.tree += 10;
                MainActivity.game.score.water += 10;
                MainActivity.game.score.rock += 10;
            }
        }
    }

}
