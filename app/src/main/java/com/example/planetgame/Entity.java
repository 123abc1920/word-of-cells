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
        int result = this.cell.id + a;
        if (result >= 0 && result < 25) {
            if (!MainActivity.adapter.cellList.get(result).isMonster) {
                if (!MainActivity.adapter.cellList.get(result).isDestroy) {
                    if (this.cell.col == 0 && MainActivity.adapter.cellList.get(result).col == 4) {
                        return null;
                    }
                    if (this.cell.col == 4 && MainActivity.adapter.cellList.get(result).col == 0) {
                        return null;
                    }
                    return MainActivity.adapter.cellList.get((result));
                }
            }
        }
        return null;
    }

}
