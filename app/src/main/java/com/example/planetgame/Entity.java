package com.example.planetgame;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    public Cell cell;

    public List<Cell> availableCells(int[] a) {
        List<Cell> result = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            Cell c = adding(a[i]);
            if (c != null) {
                result.add(c);
            }
        }

        return result;
    }

    private Cell adding(int a) {
        int result = MainActivity.game.player.cell.id + a;
        if (result < 25 && result >= 0 && !MainActivity.adapter.cellList.get(result).isDestroy && !MainActivity.adapter.cellList.get(result).isEntity) {
            if (MainActivity.game.player.cell.col == 0 && MainActivity.adapter.cellList.get(result).col == 4) {
                return null;
            }
            if (MainActivity.game.player.cell.col == 4 && MainActivity.adapter.cellList.get(result).col == 0) {
                return null;
            }
            return MainActivity.adapter.cellList.get((result));
        }
        return null;
    }

}
