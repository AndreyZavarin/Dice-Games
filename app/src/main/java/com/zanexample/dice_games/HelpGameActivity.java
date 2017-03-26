package com.zanexample.dice_games;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andrey on 23.02.2017.
 */

public class HelpGameActivity extends AppCompatActivity {
    private TextView help_content;
    private Button exit_help_game;
    private Intent intent;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_help_game);

        help_content = (TextView) findViewById(R.id.help_content);
        exit_help_game = (Button) findViewById(R.id.exit_help_game);
        help_content.setText(Html.fromHtml(readFile("rules")));

        exit_help_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(HelpGameActivity.this, GameMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Метод получения данных из файла
     *
     * @param name имя файла
     * @return текс файла
     */
    public String readFile(String name) {
        String data = "";
        String line = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getAssets().open(name)));
            while ((line = reader.readLine()) != null) {
                data += line;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }








}
