package com.zanexample.dice_games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Andrey on 23.02.2017.
 */

public class GameMenuActivity extends AppCompatActivity {
    private Button buttonOneOnOneGame,
                   buttonHelpGame,
                   buttonExitGame;
    //public final String NUMBER_PLAYERS = "com.zanexample.dice_games.NUMBER_PLAYERS";
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        buttonOneOnOneGame = (Button) findViewById(R.id.one_on_one_game);
        buttonHelpGame = (Button) findViewById(R.id.help_game);
        buttonExitGame = (Button) findViewById(R.id.exit_game);

        buttonOneOnOneGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GameMenuActivity.this, MainActivity.class);
                //intent.putExtra(NUMBER_PLAYERS, 2);
                startActivity(intent);
                //TODO добавить плавный переход
                //overridePendingTransition();
            }
        });

        buttonHelpGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GameMenuActivity.this, HelpGameActivity.class);
                startActivity(intent);

            }
        });

        buttonExitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
