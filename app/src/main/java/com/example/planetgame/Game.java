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
    public static String text;
    public static final int ONE_ROW = 10;
    public static final int DESTROY_CELL = 3;
    public static final int CELLS = 100;

    public Game(FragmentManager manager, Context context) {
        cellList = new ArrayList<>();
        for (int i = 0, row = 0, col = 0; i < CELLS; i++) {
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
        player = new Player(MainActivity.adapter.cellList.get(new Random().nextInt(CELLS)));
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
        if (cell != null) {
            score.increaseScore(cell.type, cell.num);

            player.cell.player.setVisibility(View.GONE);
            player.cell.isPlayer = false;
            player.cell = cell;
            cell.player.setVisibility(View.VISIBLE);
            cell.isPlayer = true;
            cell.setEmpty();
        }

        score.steps++;
        MainActivity.steps.setText("Steps: " + score.steps);

        if (score.tree <= 0 && score.rock <= 0 && score.water <= 0) {
            text = "You win)))";
            startDialog(manager, "You win)))");
        }

        redMonster.activity();
        greenMonster.activity();
        fluidMonster.activity(this.context);

        for (int i = 0; i < Game.DESTROY_CELL; i++) {
            if (score.steps > 1) {
                MainActivity.adapter.cellList.get(MainActivity.toDestroy[i]).setDestroy();
            }
            int n = rand.nextInt(CELLS);
            MainActivity.adapter.cellList.get(n).background.setBackgroundColor(Color.RED);
            MainActivity.adapter.cellList.get(n).isDestroy = true;
            MainActivity.toDestroy[i] = n;
        }

        if (player.availableCells(Player.a).size() > 0) {
            return;
        }

        startDialog(manager, "You lose(((");
    }

    public static void startDialog(FragmentManager manager, String text1) {
        text = text1;
        EndGameDialog dialog = new EndGameDialog();
        dialog.show(manager, "dialog");
    }

}
