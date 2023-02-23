package com.example.assesment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendPokemon extends AppCompatActivity {

    String[] permissions = {"android.permission.SEND_SMS"};
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

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions, 80);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 80){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                EditText text = findViewById(R.id.editText);
                String message = text.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }else{
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                String msg = pref.getString("assistant", "") + ": Ow no you got denied!";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }
}