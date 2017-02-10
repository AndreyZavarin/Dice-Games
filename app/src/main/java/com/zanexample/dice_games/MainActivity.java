package com.zanexample.dice_games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.zanexample.dice_games.R.anim.rotate;

public class MainActivity extends AppCompatActivity {

    //ImageView iv_cpu, iv_player;
    //TextView tv_cpu, tv_player;
    ImageView iv_ir_1, iv_ir_2, iv_ir_3, iv_ir_4, iv_ir_5;
    Button doThrow;
    Button writeScore;
    //int cpuPoints = 0, playerPoints = 0;
    //Random rndm;
    TextView tvPlayerCount;
    TextView tvCurrentScore;
    // String cube_1, cube_2, cube_3, cube_4, cube_5;
    int scoreOfThrow = 0;
    int tmpForSearchCombs = 0;
    // String[] valueOnCubes = {cube_1, cube_2, cube_3, cube_4, cube_5};
    //int[] valueOnCubes = new int[5];
    //  String valueOnCubes = new String("");
    //?????????????????????????????
    //int[] counter = new int[7];

    int defaultNumberOfCubes = 5;
    int numberOfCubes = defaultNumberOfCubes;
    int[] valueOnCubes = new int[numberOfCubes];
    //Разобраться, почему выбивает эксепшн, при другом объявлении
    int[] counter = {0, 0, 0, 0, 0, 0, 0};
    int scoreOfCurrentThrow;
    int[] resultOfAnazlyzeCubs;
    int playerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_ir_1 = (ImageView) findViewById(R.id.ir_01);
        iv_ir_2 = (ImageView) findViewById(R.id.ir_02);
        iv_ir_3 = (ImageView) findViewById(R.id.ir_03);
        iv_ir_4 = (ImageView) findViewById(R.id.ir_04);
        iv_ir_5 = (ImageView) findViewById(R.id.ir_05);

        doThrow = (Button) findViewById(R.id.btnDoThrow);
        writeScore = (Button) findViewById(R.id.btnWriteScore);

        //rndm = new Random();
        tvPlayerCount = (TextView) findViewById(R.id.player_count);
        tvCurrentScore = (TextView) findViewById(R.id.currentScore);

        final ImageView[] arrayOfIVCubes = {iv_ir_1, iv_ir_2, iv_ir_3, iv_ir_4, iv_ir_5};
/*
        for(int i=0; i < valueOnCubes.length; i++){
            valueOnCubes[i] = rndm.nextInt(6) + 1;
        }
*/

        doThrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //          int throwCubes = rndm.nextInt(6) + 1;
                //setup values of cubes by random
                //for (int i = 0; i < valueOnCubes.length; i++) {
                // valueOnCubes += String.valueOf(rndm.nextInt(6) + 1);
                //   valueOnCubes[i] = rndm.nextInt(6) + 1;
                // }

                valueOnCubes = throwCubes(numberOfCubes);

                //search of combs (to separate method)
                //В массиве counter хранится количество повторений значений кубиков
                for (int i = 0; i < valueOnCubes.length; i++) {
                    counter[valueOnCubes[i]]++;
                }

                resultOfAnazlyzeCubs = combinations(valueOnCubes, counter);

                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                for (int i = 0; i < numberOfCubes; i++) {
                    arrayOfIVCubes[i].startAnimation(rotate);
                }

                setImage(valueOnCubes, arrayOfIVCubes);

                numberOfCubes = resultOfAnazlyzeCubs[1];
                scoreOfCurrentThrow = resultOfAnazlyzeCubs[0];

            /*    System.out.println("value\tcount");
                for(int i=0; i<counter.length; i++){
                    System.out.println(i + "\t" + counter[i]);
                }
				*/

                //Если кубики кончились, а ход не закончен, бросать заново все кубики
                if (numberOfCubes == 0) {
                    numberOfCubes = defaultNumberOfCubes;
                }
                //Обнуление счёта, если ничего не выпало
                if (scoreOfCurrentThrow == 0) {
                    scoreOfCurrentThrow = 0;
                    numberOfCubes = defaultNumberOfCubes;
                }
                scoreOfCurrentThrow = scoreOfCurrentThrow + resultOfAnazlyzeCubs[0];
                tvCurrentScore.setText(String.valueOf(scoreOfCurrentThrow));


                System.out.println("Score: " + resultOfAnazlyzeCubs[0]);
                System.out.println("Count: " + resultOfAnazlyzeCubs[1]);

                //zeroing array counter
                for (int i = 0; i < counter.length; i++) {
                    counter[i] = 0;
                }

                //  valueOnCubes = "";
            }
        });
        writeScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerScore = playerScore + scoreOfCurrentThrow;
                scoreOfCurrentThrow = 0;
                tvPlayerCount.setText(String.valueOf(playerScore));
            }
        });
        }

//java.lang.NegativeArraySizeException: -2 при быстром нажатии на кнопку бросить
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

    public void setImage(int[] value, ImageView[] imageView) {
        for (int i = 0; i < value.length; i++) {
            switch (value[i]) {
                case 1:
                    imageView[i].setImageResource(R.drawable.ir_01);
                    break;
                case 2:
                    imageView[i].setImageResource(R.drawable.ir_02);
                    break;
                case 3:
                    imageView[i].setImageResource(R.drawable.ir_03);
                    break;
                case 4:
                    imageView[i].setImageResource(R.drawable.ir_04);
                    break;
                case 5:
                    imageView[i].setImageResource(R.drawable.ir_05);
                    break;
                case 6:
                    imageView[i].setImageResource(R.drawable.ir_06);
                    break;
            }
        }
    }

}

//                Map<String, Integer> combinaions = new ArrayMap<>();
//                AssetManager assetManager = getAssets();
//                try {
//                    InputStream inputStream = assetManager.open("Combinations.txt");
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                    String line;
//                    while ((line = reader.readLine()) != null){
//                        String[] tmp = line.split(" ");
//
//                        combinaions.put(tmp[0], Integer.valueOf(tmp[1]));
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if(combinaions.containsKey(valueOnCubes)){
//                    System.out.println(combinaions.get(valueOnCubes));
//                }


/*
                //search of combs (to separate method)
                //В массиве counter хранится количество повторений значений кубиков
                for(int i=0; i<valueOnCubes.length; i++){
                    counter[valueOnCubes[i]]++;
                }
                System.out.println("value\tcount");
                for(int i=0; i<counter.length; i++){
                    System.out.println(i + "\t" + counter[i]);
                }
              //zeroing array
                for(int i=0; i<counter.length; i++){
                   counter[i]=0;}
*/

/*
        iv_cpu = (ImageView) findViewById(R.id.iv_cpu);
        iv_player = (ImageView) findViewById(R.id.iv_player);

        tv_cpu = (TextView) findViewById(R.id.tv_cpu);
        tv_player = (TextView) findViewById(R.id.tv_player);

        rndm = new Random();

        iv_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cpuThrow = rndm.nextInt(6) + 1;
                int playerThrow = rndm.nextInt(6) + 1;


                setImage(cpuThrow, iv_cpu);
                setImage(playerThrow, iv_player);
                //setImageCPU(cpuThrow);
              //  setImagePlayer(playerThrow);

                if(cpuThrow > playerThrow){
                    cpuPoints++;
                }

                if(playerThrow > cpuThrow){
                    playerPoints++;
                }

                if(playerThrow == cpuThrow) {
                    Toast.makeText(MainActivity.this, "Draw!", Toast.LENGTH_SHORT).show();
                }

                tv_cpu.setText("CPU: " + cpuPoints);
                tv_player.setText("YOU: " + playerPoints);

                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                iv_cpu.startAnimation(rotate);
                iv_player.startAnimation(rotate);

            }
        });*/



/*
    public void setImage(int num, ImageView imageView ) {
        switch (num){
            case 1:
                imageView.setImageResource(R.drawable.ir_01);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ir_02);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ir_03);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ir_04);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ir_05);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ir_06);
                break;
        }
    }
*//*
    public void setImageCPU(int num){
        switch (num){
            case 1:
                iv_cpu.setImageResource(R.drawable.ir_01);
                break;
            case 2:
                iv_cpu.setImageResource(R.drawable.ir_02);
                break;
            case 3:
                iv_cpu.setImageResource(R.drawable.ir_03);
                break;
            case 4:
                iv_cpu.setImageResource(R.drawable.ir_04);
                break;
            case 5:
                iv_cpu.setImageResource(R.drawable.ir_05);
                break;
            case 6:
                iv_cpu.setImageResource(R.drawable.ir_06);
                break;
        }
    }


    public void setImagePlayer(int num){
        switch (num){
            case 1:
                iv_player.setImageResource(R.drawable.ir_01);
                break;
            case 2:
                iv_player.setImageResource(R.drawable.ir_02);
                break;
            case 3:
                iv_player.setImageResource(R.drawable.ir_03);
                break;
            case 4:
                iv_player.setImageResource(R.drawable.ir_04);
                break;
            case 5:
                iv_player.setImageResource(R.drawable.ir_05);
                break;
            case 6:
                iv_player.setImageResource(R.drawable.ir_06);
                break;
        }
    }
*/





