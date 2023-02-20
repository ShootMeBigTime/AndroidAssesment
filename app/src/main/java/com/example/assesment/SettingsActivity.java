package com.example.assesment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import java.util.zip.Inflater;

public class SettingsActivity extends AppCompatActivity {

    static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingFragment()).commit();
    }
    public static class SettingFragment extends PreferenceFragment{


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings_main);
            updateSummary(getPreferenceScreen());
        }

        private void updateSummary(Preference p) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if(preferences.getBoolean("Music", true)){
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.music);
                mediaPlayer.start();
            }else{
                onPause();
            }

        }
    }
}