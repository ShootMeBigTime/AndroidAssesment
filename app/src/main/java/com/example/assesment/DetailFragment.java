package com.example.assesment;

import android.graphics.Bitmap;
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
    TextView textViewDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        imageViewPokemon = view.findViewById(R.id.imageViewPokemon);
        textViewName = view.findViewById(R.id.textViewName);
        textViewDetail = view.findViewById(R.id.textViewDetail);

        if (getArguments() != null)
        {
            String pokemon = getArguments().getString("pokemon");

            setPokemon(pokemon);
        }

        return view;
    }

    public void setPokemon(String pokemon)
    {
        // TODO: set pokemon image
        imageViewPokemon.setImageBitmap(PokeApi.FetchImageByPokemonId(12));

        // TODO: set pokemon name
        // TODO: set pokemon detail
    }
}