package com.example.assesment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    List<Pokemon> pokemon;

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemon_item, parent, false);

        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = this.pokemon.get(position);

//        holder.image.setImageURI(Uri.parse(pokemon.imageUrl));
        holder.image.setImageBitmap(pokemon.image);
        holder.name.setText(pokemon.name);
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public String getItem(int id){
        return pokemon.get(id).name;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemon = pokemonList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addToPokemonList(Pokemon pokemon) {
        this.pokemon.add(pokemon);
        notifyDataSetChanged();
    }
}
