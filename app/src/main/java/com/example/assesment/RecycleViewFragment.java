package com.example.assesment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

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

        PokeApi pokeApi = new PokeApi();
        PokemonAdapter pokemonAdapter = new PokemonAdapter();
        pokemonAdapter.setPokemonList(new ArrayList<>());

        pokeApi.SetAllPokemon(getContext(), pokemonAdapter);

//        pokemonAdapter.setPoke(pokemonList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(pokemonAdapter);

        return view;
    }
}