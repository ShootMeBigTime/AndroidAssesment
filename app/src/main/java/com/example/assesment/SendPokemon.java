package com.example.assesment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SendPokemon extends AppCompatActivity {

    String[] permissions = {"android.permission.SEND_SMS"};
    public static final int PICK_IMAGE = 1;
    public Uri selectedImage;


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
    public void openGallery(View v){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {

            ImageView img = findViewById(R.id.imageView);
            if(data != null)
            {
                Uri selectedImageUri = data.getData();
                img.setImageURI(selectedImageUri);
                selectedImage = selectedImageUri;
            }else{
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                String msg = pref.getString("assistant", "") + ": Ow no! something went wrong";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
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
                if(selectedImage != null){sendIntent.putExtra(Intent.EXTRA_STREAM, selectedImage);}
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