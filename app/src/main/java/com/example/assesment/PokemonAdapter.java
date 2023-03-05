package com.example.assesment;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    interface OnClickListener {
        void onItemSelected(String pokemon);
    }

    OnClickListener listener;
    List<Pokemon> pokemon;

    public PokemonAdapter(RecycleViewFragment fragment) {
        listener = (MainActivity)fragment.getActivity();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemon_item, parent, false);

        view.setOnClickListener(view1 -> {
            TextView textView = view1.findViewById(R.id.textViewName);
            if (textView != null)
            {
                String pokemonName = textView.getText().toString();
                listener.onItemSelected(pokemonName);
            }
        });

        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = this.pokemon.get(position);

        holder.image.setImageBitmap(pokemon.image);
        holder.name.setText(pokemon.name);
        holder.id.setText(String.valueOf(pokemon.id));
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
