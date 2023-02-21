package com.example.assesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SendPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_pokemon);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
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
                intent = new Intent(this, SendPokemon.class);
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
    public void sendMessage(View v){
        EditText text = findViewById(R.id.editText);
        String message = text.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");

        startActivity(sendIntent);
    }
}