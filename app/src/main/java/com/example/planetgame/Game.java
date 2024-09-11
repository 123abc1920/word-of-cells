package com.example.planetgame;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public FragmentManager manager;
    public Context context;
    private Random rand = new Random();
    protected Score score;
    protected Player player;
    protected RedMonster redMonster;
    protected GreenMonster greenMonster;
    protected FluidMonster fluidMonster;
    private List<Cell> cellList;
    public String text;
    public static final int ONE_ROW = 5;
    public static final int DESTROY_CELL = 3;

    public Game(FragmentManager manager, Context context) {
        cellList = new ArrayList<>();
        for (int i = 0, row = 0, col = 0; i < 25; i++) {
            cellList.add(new Cell(row, col, i, context));
            if ((i + 1) % ONE_ROW == 0) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }
        this.context = context;

        MainActivity.adapter = new CellAdapter(context, cellList);
        MainActivity.recyclerView.setAdapter(MainActivity.adapter);

        this.manager = manager;
        player = new Player(MainActivity.adapter.cellList.get(new Random().nextInt(25)));
        redMonster = new RedMonster();
        greenMonster = new GreenMonster();
        fluidMonster = new FluidMonster();
        score = new Score();

        MainActivity.tree.setText("Tree: " + score.tree);
        MainActivity.water.setText("Water: " + score.water);
        MainActivity.rock.setText("Rock: " + score.rock);
        MainActivity.steps.setText("Steps: " + score.steps);
    }

    public void newStep(Cell cell) {
        score.increaseScore(cell.type, cell.num);

        player.cell.player.setVisibility(View.GONE);
        player.cell.isEntity = false;
        player.cell = cell;
        cell.player.setVisibility(View.VISIBLE);

        for (int i = 0; i < Game.DESTROY_CELL; i++) {
            if (score.steps > 0) {
                MainActivity.adapter.cellList.get(MainActivity.toDestroy[i]).background.setVisibility(View.INVISIBLE);
            }
            int n = rand.nextInt(25);
            MainActivity.adapter.cellList.get(n).background.setBackgroundColor(Color.RED);
            MainActivity.adapter.cellList.get(n).isDestroy = true;
            MainActivity.toDestroy[i] = n;
        }

        score.steps++;
        MainActivity.steps.setText("Steps: " + score.steps);

        if (score.tree <= 0 && score.rock <= 0 && score.water <= 0) {
            text = "You win)))";
            startDialog(manager);
        }

        redMonster.activity();
        greenMonster.activity();
        fluidMonster.activity(this.context);

        for (Cell c : player.availableCells(player.a)) {
            if (c != null) {
                return;
            }
        }

        text = "You lose(((";
        startDialog(manager);
    }

    public void startDialog(FragmentManager manager) {
        EndGameDialog dialog = new EndGameDialog();
        dialog.show(manager, "dialog");
    }

}
