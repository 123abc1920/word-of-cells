package com.example.planetgame;

import java.util.Random;

public class Score {
    public int water = 0;
    public int tree = 0;
    public int rock = 0;
    public int steps = 0;

    public Score() {
        Random rand = new Random();

        this.water = rand.nextInt(81) + 20;
        this.tree = rand.nextInt(81) + 20;
        this.rock = rand.nextInt(81) + 20;
    }

    public void increaseScore(Type type, int num) {
        if (type == Type.ROCK) {
            this.rock -= num;
        } else if (type == Type.TREE) {
            this.tree -= num;
        } else if (type == Type.WATER) {
            this.water -= num;
        }
    }
}
