package com.example.assesment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class PokeApi
{
    public class PokemonData
    {
        String Name;
        String Detail;
    }

    private static final String baseImageURL = "https://pokeapi.co/api/v2/pokemon/?";
    private static final String basePokemonURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/?.png";

    public static Bitmap FetchImageByPokemonId(int pokemonId)
    {
        String url = buildImageUrl(pokemonId);
        return FetchImage(url);
    }

    private static Bitmap FetchImage(String URL)
    {
        Bitmap bitmap = null;

        try
        {
            InputStream inputStream = new java.net.URL(URL).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return bitmap;
    }

    private static String buildPokemonUrl(String pokemonName)
    {
        return basePokemonURL.replace("?", pokemonName);
    }

    private static String buildImageUrl(int pokemonId)
    {
        return baseImageURL.replace("?", String.valueOf(pokemonId));
    }
}
