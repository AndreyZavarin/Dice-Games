package com.zanexample.dice_games;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Andrey on 24.02.2017.
 */

public class NewStyle extends AppCompatActivity {
    LinearLayout infoWindow;
    LinearLayout combination_box;
    TextView infoMessage;
    TextView countBox;
    Button button;

    int[] imageDrawable = {R.drawable.ir_01, R.drawable.ir_02, R.drawable.ir_03, R.drawable.ir_04, R.drawable.ir_05};

    String[] messages = {"Сумма очков: ", "Очки сгорели!", " записывает очки", "Очки записаны", " бросает кости"};
    int[] testCount = {10, 5, 100};

    int[][]  textValueCube = {{1} , //10очков
            {5}, //5очков
            {1,1,1}
    };

    int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_style);

        infoWindow = (LinearLayout) findViewById(R.id.info_window);
        combination_box = (LinearLayout) findViewById(R.id.combination_box);
        infoMessage = (TextView) findViewById(R.id.info_message);
        countBox = (TextView) findViewById(R.id.count_box);
        button = (Button) findViewById(R.id.btnDoThrow);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CountK" + clickCount);
                int tmpCount = testCount[clickCount];

                int[] tmpValueCube =  textValueCube[clickCount];
                System.out.println("----" + Arrays.toString(tmpValueCube));
                LinearLayout linearLayout = new LinearLayout(NewStyle.this);
                linearLayout.setGravity(Gravity.BOTTOM);
                for(int i = 0; i < tmpValueCube.length; i++){

                    ImageView imageView = new ImageView(NewStyle.this);
                    imageView.setImageResource(imageDrawable[tmpValueCube[i]-1]);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setMaxHeight(15);
                    imageView.setMaxWidth(15);
                    linearLayout.addView(imageView);
                }
                TextView textView = new TextView(NewStyle.this);
                textView.setText(" - " + testCount[clickCount]);
                linearLayout.addView(textView);
                combination_box.addView(linearLayout);
                clickCount++;
            }
        });
    }
}
