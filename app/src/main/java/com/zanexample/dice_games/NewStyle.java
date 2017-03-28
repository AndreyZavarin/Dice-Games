package com.zanexample.dice_games;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Andrey on 24.02.2017.
 */

public class NewStyle extends AppCompatActivity {
    LinearLayout infoWindow; //блок сообщений
    LinearLayout combination_box; //блок комбинаций
    LinearLayout image_content;
    TextView infoMessage;
    TextView currentScoreTest;


    int[] resultOfAnazlyzeCubs;
    int scoreOfCurrentThrow;

    Button doThrow;
    TextView playerCount;

    int[] imageDrawable = {R.drawable.ir_01, R.drawable.ir_02, R.drawable.ir_03, R.drawable.ir_04, R.drawable.ir_05,  R.drawable.ir_06};

    int defaultNumberOfCubes = 5;
    int numberOfCubes = defaultNumberOfCubes;
    int[] valueOnCubes = new int[numberOfCubes];

    int playerScore = 0;
    TextView tvPlayerCount;
    Button writeScore;

    int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_style);

      //  writeScore = (Button) findViewById(R.id.btnWriteScore);

        doThrow = (Button) findViewById(R.id.btnDoThrowTest);
        writeScore = (Button) findViewById(R.id.btnWriteScoreTest);
        //Счет игрока
        playerCount = (TextView) findViewById(R.id.player_count_test);
        infoMessage = (TextView) findViewById(R.id.info_message);
        combination_box = (LinearLayout) findViewById(R.id.combination_box);
        //Текущий счет
        currentScoreTest = (TextView) findViewById(R.id.current_score_test);

        infoWindow = (LinearLayout) findViewById(R.id.info_window);
        image_content = (LinearLayout) findViewById(R.id.image_content);

        doThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoWindow.setVisibility(View.VISIBLE);
                combination_box.setVisibility(View.VISIBLE);

                image_content.removeAllViews();
                //crutch
                if(numberOfCubes == defaultNumberOfCubes) {
                    combination_box.removeAllViews();
                }

                OperationsOnDice playerOne = new OperationsOnDice();

                //Значение кубиков
                valueOnCubes = playerOne.throwCubes(numberOfCubes);
                resultOfAnazlyzeCubs = playerOne.combinations(valueOnCubes, playerOne.getCountOfValue(valueOnCubes), numberOfCubes);
                //Создание кубиков
                ImageView[] imageView = DisplayDice.createDice(NewStyle.this, numberOfCubes);
                //Добавленеи кубиков на страницу
                DisplayDice.displayDice(image_content, valueOnCubes, imageView);

                scoreOfCurrentThrow += resultOfAnazlyzeCubs[0];

                int[] currentCombination = playerOne.currentCombinationToIntArray(resultOfAnazlyzeCubs[2]) ;//Это текущая комбинация

                int currentScore = resultOfAnazlyzeCubs[0]; // Это значение текущей комбинации

                totalScore += currentScore; // Это общий счет (текущий счет)

                //Если очки не сгорели
                if(currentCombination[0] != 0 && currentScore > 0){
                    //Вывод сообщения в блок сообщений (сумма очков)
                    DisplayThrowsInfo.DisplayMessage(infoMessage, currentScoreTest, 1, totalScore, "");
                    //Добавленеи комбинаций в блок комбинаций
                    DisplayThrowsInfo.DisplayCombination(NewStyle.this, currentCombination, currentScore, imageDrawable, combination_box);
                }
                else {
                    //Вывод сообщения в блок сообщений(очки сгорели)
                    DisplayThrowsInfo.DisplayMessage(infoMessage, currentScoreTest, 2, 0, "");
                    totalScore = 0;
                    numberOfCubes = defaultNumberOfCubes;
                    combination_box.removeAllViews();
                }

                numberOfCubes = resultOfAnazlyzeCubs[1];

                //Если кубики кончились, а ход не закончен, бросать заново все кубики
                if (numberOfCubes == 0) {
                    numberOfCubes = defaultNumberOfCubes;
             //       combination_box.removeAllViews();
                }

                //Обнуление счёта, если ничего не выпало
                if (resultOfAnazlyzeCubs[0] == 0) {
                    scoreOfCurrentThrow = 0;
                    numberOfCubes = defaultNumberOfCubes;
                    combination_box.removeAllViews();
                }

            }
        });
        writeScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                playerScore = playerScore + totalScore;
                if(totalScore != 0) {
                    DisplayThrowsInfo.DisplayMessage(infoMessage, currentScoreTest, 4, 0, "");
                }
                playerCount.setText(String.valueOf(playerScore));
                totalScore = 0;
                combination_box.removeAllViews();
                numberOfCubes = defaultNumberOfCubes;

            }
        });
    }


}
