package com.example.assesment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    List<Pokemon> Pokemon;

    PokemonAdapter(List<Pokemon> pokemons){

        this.Pokemon = pokemons;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemon_item, parent, false);

        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        Pokemon pokemon = Pokemon.get(position);

        holder.img.setImageResource(pokemon.id);
        holder.text.setText(pokemon.name);
    }

    @Override
    public int getItemCount() {
        return Pokemon.size();
    }

    public String getItem(int id){
        return Pokemon.get(id).name;
    }
}
