package com.zanexample.dice_games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //ImageView iv_cpu, iv_player;
    //TextView tv_cpu, tv_player;
    ImageView iv_ir_1, iv_ir_2, iv_ir_3, iv_ir_4, iv_ir_5;
    Button doThrow;
    //int cpuPoints = 0, playerPoints = 0;
    Random rndm;

    int cube_1, cube_2, cube_3, cube_4, cube_5;
    int scoreOfThrow=0;
    int tmpForSearchCombs = 0;
    int[] valueOnCubes = {cube_1,cube_2,cube_3,cube_4,cube_5};
    //?????????????????????????????
    int[] counter = {0,0,0,0,0,0,0};
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
        rndm = new Random();

        final ImageView[] arrayOfIVCubes = {iv_ir_1, iv_ir_2, iv_ir_3, iv_ir_4, iv_ir_5};
/*
        for(int i=0; i < valueOnCubes.length; i++){
            valueOnCubes[i] = rndm.nextInt(6) + 1;
        }
*/
        doThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      //          int throwCubes = rndm.nextInt(6) + 1;
                //setup values of cubes by random
                for(int i=0; i < valueOnCubes.length; i++){
                    valueOnCubes[i] = rndm.nextInt(6) + 1;
                }

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
                   counter[i]=0;
                }

                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                for(int i=0; i< arrayOfIVCubes.length; i++){
                    arrayOfIVCubes[i].startAnimation(rotate);
                }

                setImage(valueOnCubes, arrayOfIVCubes);

            }

        });



    }


    public void setImage(int[] value, ImageView[] imageView){
        for(int i=0; i < value.length; i++){
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





