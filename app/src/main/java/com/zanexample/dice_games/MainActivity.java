package com.zanexample.dice_games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_cpu, iv_player;
    TextView tv_cpu, tv_player;

    int cpuPoints = 0, playerPoints = 0;
    Random rndm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                setImageCPU(cpuThrow);
                setImagePlayer(playerThrow);

                if(cpuThrow > playerThrow){
                    cpuPoints++;
                }

                if(playerThrow > cpuThrow){
                    playerPoints++;
                }

                tv_cpu.setText("CPU: " + cpuPoints);
                tv_player.setText("YOU: " + playerPoints);

                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                iv_cpu.startAnimation(rotate);
                iv_player.startAnimation(rotate);
            }
        });

    }

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





}
