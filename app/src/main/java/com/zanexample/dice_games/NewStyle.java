package com.zanexample.dice_games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Andrey on 24.02.2017.
 */

/*
* 1 комбинация (в виде массива)
* сумма комбинации
*
*
* Тестовые данные:
*
* 1) комбинация - 1
*    Сумма очков - 10
*    Сообщение: Сумма очков - 10
*
* 2) Комбинация  - нет комбинации
*    Сообщение: очки сгорели
*
* */

public class NewStyle extends AppCompatActivity {
    LinearLayout infoWindow;
    LinearLayout combination_box;
    LinearLayout image_content;
    TextView infoMessage;
    TextView currentScoreTest;

    int[] counter = {0, 0, 0, 0, 0, 0, 0};
    int[] resultOfAnazlyzeCubs;
    int scoreOfCurrentThrow;


    Button doThrow;
    Button writeScore;


    TextView playerCount;

    int[] imageDrawable = {R.drawable.ir_01, R.drawable.ir_02, R.drawable.ir_03, R.drawable.ir_04, R.drawable.ir_05,  R.drawable.ir_05};

    //String[] messages = {"Сумма очков: ", "Очки сгорели!", " записывает очки", "Очки записаны", " бросает кости"};

    int defaultNumberOfCubes = 5;
    int numberOfCubes = defaultNumberOfCubes;
    int[] valueOnCubes = new int[numberOfCubes];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_style);

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


        //button = (Button) findViewById(R.id.btnDoThrow);


        doThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_content.removeAllViews();

                //Значение кубиков
                valueOnCubes = throwCubes(numberOfCubes);


                System.out.println(Arrays.toString(valueOnCubes));

                //В массиве counter хранится количество повторений значений кубиков
                for (int i = 0; i < valueOnCubes.length; i++) {
                    counter[valueOnCubes[i]]++;
                }

                resultOfAnazlyzeCubs = combinations(valueOnCubes, counter);

                //Создание кубиков
                ImageView[] imageView = DisplayDice.createDice(NewStyle.this, numberOfCubes);
                //Добавленеи кубиков на страницу
                DisplayDice.displayDice(image_content, valueOnCubes, imageView);

                System.out.println("Score: " + resultOfAnazlyzeCubs[0]);
                System.out.println("Count: " + resultOfAnazlyzeCubs[1]);


                scoreOfCurrentThrow += resultOfAnazlyzeCubs[0];

                if(resultOfAnazlyzeCubs[0] != 0 && numberOfCubes > 0){
                    //TODO разделить комбинации и счет (вынести из массива)
                    //Добавленеи комбинаций в блок комбинаций
                    DisplayThrowsInfo.DisplayMessage(infoMessage, currentScoreTest, 1, scoreOfCurrentThrow);
                    //Добавленеи сообщения
                    DisplayThrowsInfo.DisplayCombination(NewStyle.this, resultOfAnazlyzeCubs, imageDrawable, valueOnCubes,  combination_box);
                }
                else {
                    DisplayThrowsInfo.DisplayMessage(infoMessage, currentScoreTest, 2, 0);
                    combination_box.removeAllViews();
                }

                //колическо остався кубиков
                numberOfCubes = resultOfAnazlyzeCubs[1];
                //Количество очк
                scoreOfCurrentThrow = resultOfAnazlyzeCubs[0];


                //Если кубики кончились, а ход не закончен, бросать заново все кубики
                if (numberOfCubes == 0) {

                    numberOfCubes = defaultNumberOfCubes;
                    combination_box.removeAllViews();

                }
                //Обнуление счёта, если ничего не выпало
                if (scoreOfCurrentThrow == 0) {


                    scoreOfCurrentThrow = 0;
                    numberOfCubes = defaultNumberOfCubes;
                    combination_box.removeAllViews();
                }

                System.out.println("Score: " + resultOfAnazlyzeCubs[0]);
                System.out.println("Count: " + resultOfAnazlyzeCubs[1]);

                //scoreOfCurrentThrow = scoreOfCurrentThrow + resultOfAnazlyzeCubs[0];
                System.out.println("CountALL: " +  scoreOfCurrentThrow);

                //zeroing array counter
                for (int i = 0; i < counter.length; i++) {
                    counter[i] = 0;
                }
            }
        });
    }

    public int[] throwCubes(int numberOfCubes) {
        Random rndm = new Random();
        int[] valueOnThrowCubes = new int[numberOfCubes];
        //setup values of cubes by random
        for (int i = 0; i < numberOfCubes; i++) {
            valueOnThrowCubes[i] = rndm.nextInt(6) + 1;
        }
        return valueOnThrowCubes;
    }

    public int[] combinations(int[] valueOnCubes, int[] counter) {
        String stringCounter;
        String combination12345 = new String("12345");
        String combintaion23456 = new String("23456");
        int scoreOfThrow = 0;
        int tmpForCountUsedCubs = 0;
        int[] arrayReturns = {scoreOfThrow, tmpForCountUsedCubs};
        //Обработка цифры 1 и ее возможных комбинаций.
        switch (counter[1]) {
            case 1:
                scoreOfThrow = 10;
                break;
            case 2:
                scoreOfThrow = 20;
                break;
            case 3:
                scoreOfThrow = 100;
                break;
            case 4:
                scoreOfThrow = 200;
                break;
            case 5:
                scoreOfThrow = 1000;
                break;
        }
        //Обработка цифры 5 при одном или двух выпаданиях.
        switch (counter[5]) {
            case 1:
                scoreOfThrow = scoreOfThrow + 5;
                break;
            case 2:
                scoreOfThrow = scoreOfThrow + 10;
                break;
        }

        for (int i = 2; i < counter.length; i++) {
            if (counter[i] == 3) {
                scoreOfThrow = scoreOfThrow + i * 10;
                tmpForCountUsedCubs = 3;
            }
            if (counter[i] == 4) {
                scoreOfThrow = scoreOfThrow + i * 10 * 2;
                tmpForCountUsedCubs = 4;
            }
            if (counter[i] == 5) {
                scoreOfThrow = scoreOfThrow + i * 100;
                tmpForCountUsedCubs = 5;
            }
        }
        Arrays.sort(valueOnCubes);
        stringCounter = Arrays.toString(valueOnCubes);
        System.out.println("SortString " + stringCounter);
        if (stringCounter.equals(combination12345)) {
            scoreOfThrow = 125;
            tmpForCountUsedCubs = 5;
        }
        if (stringCounter.equals(combintaion23456)) {
            scoreOfThrow = 250;
            tmpForCountUsedCubs = 5;
        }
        tmpForCountUsedCubs = tmpForCountUsedCubs + counter[1] + counter[5];
        tmpForCountUsedCubs = numberOfCubes - tmpForCountUsedCubs;
        arrayReturns[0] = scoreOfThrow;
        arrayReturns[1] = tmpForCountUsedCubs;
        return arrayReturns;
    }


}
