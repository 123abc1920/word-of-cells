package com.example.planetgame;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Random;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    public List<Cell> cellList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private MaterialCardView player;
        private ImageView redMonster, greenMonster, fluidMonster;

        ViewHolder(View view) {
            super(view);

            text = view.findViewById(R.id.number);
            player = view.findViewById(R.id.player);
            redMonster = view.findViewById(R.id.redmonster);
            greenMonster = view.findViewById(R.id.greenmonster);
            fluidMonster = view.findViewById(R.id.fluidmonster);
        }
    }

    public CellAdapter(Context context, List<Cell> cells) {
        this.cellList = cells;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cell cell = cellList.get(position);

        cell.player = holder.player;
        cell.background = holder.text;
        cell.redMonster = holder.redMonster;
        cell.greenMonster = holder.greenMonster;
        cell.fluidMonster = holder.fluidMonster;

        cell.player.setVisibility(View.GONE);
        cell.redMonster.setVisibility(View.GONE);
        cell.fluidMonster.setVisibility(View.GONE);
        cell.greenMonster.setVisibility(View.GONE);
        cell.background.setBackgroundColor(Color.TRANSPARENT);
        cell.background.setText("");

        if (cell.isBridge) {
            cell.background.setBackgroundColor(Color.YELLOW);
        }

        if (cell.equals(MainActivity.game.player.cell)) {
            cell.background.setBackgroundColor(Color.CYAN);
        }
        if (!cell.isDestroy) {
            if (cell.equals(MainActivity.game.greenMonster.cell)) {
                cell.greenMonster.setVisibility(View.VISIBLE);
            }
            if (cell.equals(MainActivity.game.redMonster.cell)) {
                cell.redMonster.setVisibility(View.VISIBLE);
            }
            if (cell.equals(MainActivity.game.fluidMonster.cell)) {
                cell.fluidMonster.setVisibility(View.VISIBLE);

                if (MainActivity.game.fluidMonster.type == Type.TREE) {
                    cell.fluidMonster.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                }
                if (MainActivity.game.fluidMonster.type == Type.ROCK) {
                    cell.fluidMonster.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.grey)));
                }
                if (MainActivity.game.fluidMonster.type == Type.WATER) {
                    cell.fluidMonster.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue)));
                }
            }
        }

        if (MainActivity.game.player.cell.equals(cell)) {
            cell.player.setVisibility(View.VISIBLE);
            cell.setEmpty();
        }

        if (!cell.isDestroy) {
            if (cell.type == Type.WATER) {
                holder.text.setBackgroundColor(Color.BLUE);
            } else if (cell.type == Type.TREE) {
                holder.text.setBackgroundColor(Color.GREEN);
            } else if (cell.type == Type.ROCK) {
                holder.text.setBackgroundColor(Color.GRAY);
            }
            holder.text.setText(cell.num + "");
        }

        if (cell.isEmpty) {
            holder.text.setBackgroundColor(Color.CYAN);
        }

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.game.createBridge) {
                    if (cell.isDestroy) {
                        if (cell.id == MainActivity.game.player.cell.id - 1 || cell.id == MainActivity.game.player.cell.id + 1 || cell.id == MainActivity.game.player.cell.id - Game.ONE_ROW || cell.id == MainActivity.game.player.cell.id + Game.ONE_ROW) {
                            cell.isDestroy = false;
                            cell.isBridge = true;
                            cell.background.setBackgroundColor(Color.YELLOW);
                            MainActivity.game.score.tree += 10;
                            MainActivity.game.score.rock += 10;
                            MainActivity.game.score.water += 10;
                            MainActivity.game.createBridge = false;
                            MainActivity.setBtn.setBackgroundTintList(null);
                            MainActivity.setBtn.setBackgroundColor(Color.rgb(103, 80, 164));
                            MainActivity.game.newStep(null);
                        }
                    }
                } else {
                    if (MainActivity.game.player.availableCells(MainActivity.game.player.a).contains(cell)) {
                        MainActivity.game.newStep(cell);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cellList.size();
    }
}