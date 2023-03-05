package com.example.assesment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    ImageView imageViewPokemon;
    TextView textViewName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        imageViewPokemon = view.findViewById(R.id.imageViewPokemon);
        textViewName = view.findViewById(R.id.textViewName);

        if (getArguments() != null)
        {
            int pokemonId = getArguments().getInt("id");

            // TODO: get pokemon data
            // TODO: calls on setPokemon
//            setPokemon(pokemonId);
        }

        return view;
    }

    public void setPokemon(Pokemon pokemon)
    {
        imageViewPokemon.setImageBitmap(pokemon.image);
//         TODO: set pokemon image
//         TODO: set pokemon name
//         TODO: set pokemon detail
    }
}