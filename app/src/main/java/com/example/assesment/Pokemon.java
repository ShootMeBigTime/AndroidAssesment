package com.example.assesment;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {

    int id;
    String imageUrl;
    Bitmap image;
    String name;
    String type;
    String discription;

    public Pokemon() {
        this.id = 0;
        this.imageUrl = "";
        this.image = null;
        this.name = "";
        this.type = "";
        this.discription = "";
    }

    public Pokemon(int id, String name, String type, String discription) {
        this.name = name;
        this.id = id;
        this.imageUrl = "";
        this.image = null;
        this.type = type;
        this.discription = discription;
    }

    public Pokemon(int id, Bitmap image, String name, String type, String discription) {
        this.id = id;
        this.imageUrl = "";
        this.image = image;
        this.name = name;
        this.type = type;
        this.discription = discription;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public static Pokemon parseJSON(JSONObject jsonObject) {
        Pokemon pokemon = new Pokemon();

        try {
            pokemon.id = jsonObject.getInt("id");
            pokemon.name = jsonObject.getString("name");
            pokemon.imageUrl = jsonObject.getJSONObject("sprites").getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemon;
    }


}