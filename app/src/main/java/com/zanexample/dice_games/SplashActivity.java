package com.zanexample.dice_games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andrey on 24.02.2017.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashActivity.this, GameMenuActivity.class);
        startActivity(intent);
        finish();
    }
}
