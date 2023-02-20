package com.example.assesment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    //ImageView img;
    TextView name;
    TextView type;
    TextView discription;

    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);

    }
}
