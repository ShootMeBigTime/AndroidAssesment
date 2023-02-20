package com.example.assesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            if(preferences.getBoolean("Music", true)) {
                mediaPlayer.start();
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch (id){
            case R.id.pokedex:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.sendPokemon:
                intent = new Intent(this, sendPokemon.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}