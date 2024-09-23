package com.example.planetgame;

public class Player extends Entity {

    public static final int[] a = {Game.ONE_ROW, 1, -Game.ONE_ROW, -1};

    public Player(Cell cell) {
        this.cell = cell;

        cell.isPlayer = true;
        cell.isEmpty = true;
        cell.num = 0;
    }

}
