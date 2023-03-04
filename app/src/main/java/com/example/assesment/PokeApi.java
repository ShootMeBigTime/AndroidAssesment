package com.example.assesment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PokeApi
{
    private static final int pokemonLimit = 40;
    private static final String urlPlaceholder = "?";
    private static final String allPokemonURL = "https://pokeapi.co/api/v2/pokemon?";
    private static final String pokemonURL = "https://pokeapi.co/api/v2/pokemon/?";
//    private static final String allPokemonURL = "https://time.jsontest.com";

    private ArrayList<Pokemon> allPokemon;
    private Context context;
    private PokemonAdapter adapter;
    private SharedPreferences sharedPreferences;

    public void SetAllPokemon(Context context, PokemonAdapter adapter)
    {
        this.adapter = adapter;
        this.context = context;

        this.allPokemon = new ArrayList<>();
        FetchAllPokemon(pokemonLimit);
    }

    public void SetPokemon(Context context, PokemonAdapter adapter, int id)
    {
        this.adapter = adapter;
        this.context = context;

        this.allPokemon = new ArrayList<>();
        FetchPokemonByUrl(buildPokemonURL(id));
    }

    private static String buildAllPokemonURL(int limit)
    {
        return allPokemonURL.replace(urlPlaceholder, "?limit=" + limit);
    }

    private static String buildPokemonURL(int id)
    {
        return pokemonURL.replace(urlPlaceholder, String.valueOf(id));
    }

    private void FetchAllPokemon(int limit)
    {
        try {
            String url = buildAllPokemonURL(limit);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            FetchPokemonByUrl(jsonArray.getJSONObject(i).getString("url"));
                        }
                        //TODO: cache data
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("DataFetch", "Invalid URL");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(jsonObjectRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Pokemon FetchPokemonByUrl(String url)
    {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Pokemon pokemon = null;

                    pokemon = Pokemon.parseJSON(response);
//                    pokemon.setImage(FetchPokemonImage(pokemon.imageUrl));

                    adapter.addToPokemonList(pokemon);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("DataFetch", "Invalid URL");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(jsonObjectRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private Bitmap FetchPokemonImage(String stringUrl)
    {
        Bitmap bitmap = null;

        try {
            URL url = new URL(stringUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            Log.d("Invalid URL", e.toString());
            return null;
        }
    }
}
