package com.example.music_app.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music_app.R;

public class AlbumActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
    }


    public static Intent createIntent(AppCompatActivity activity) {
        return new Intent(activity, AlbumActivity.class);
    }
}
