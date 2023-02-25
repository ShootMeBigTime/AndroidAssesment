package com.example.assesment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recycle_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView);

        List<Pokemon> pokemonList = new ArrayList<>();

        // TODO: remove temp data
        // TODO: fill pokemonList with API data
        pokemonList.add(new Pokemon(1, "Charmander", "Fire", "Lizard goes bRRR"));
        pokemonList.add(new Pokemon(2, "Squirtle", "Water", "Turtle goes bRRR"));
        pokemonList.add(new Pokemon(3, "Balbesaur", "Grass", "Toad goes bRRR"));

        PokemonAdapter pokemonAdapter = new PokemonAdapter(pokemonList);
        recyclerView.setAdapter(pokemonAdapter);

        return view;
    }
}