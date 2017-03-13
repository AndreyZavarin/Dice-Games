package com.zanexample.dice_games;

import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Andrey on 01.03.2017.
 */

public class DisplayThrowsInfo {

    static String[] messages = {"Сумма очков: ", "Очки сгорели!", " записывает очки", "Очки записаны", " бросает кости"};

    /**
     * Метод отображения комбинаций
     *
     * @param context - контекст класса
     * @param resultOfAnazlyzeCubs - массив количества очков одного броска и количество использованных кубиков
     * @param imageDrawable - массив ссылок на изображение
     * @param valueOnCubes - значение кубиков
     * @param currentCombination - текущая комбинация
     * @param currentScore - сумма очков текущего хода
     * @param combination_box - поле для вывода комбинации
     */
    public static void DisplayCombination(Context context, int[]resultOfAnazlyzeCubs, int[] imageDrawable, int[] valueOnCubes,
                                          /*int[] currentCombination,*/ /*int currentScore,*/ LinearLayout  combination_box ){

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(Gravity.BOTTOM);

        //Вывод комбинации в поле комбинаций
        for(int i = 0; i < resultOfAnazlyzeCubs.length; i++){

            ImageView image = new ImageView(context);
            image.setImageResource(imageDrawable[valueOnCubes[i]-1]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            image.setLayoutParams(layoutParams);
            image.setMaxHeight(15);
            image.setMaxWidth(15);
            linearLayout.addView(image);
        }

        /*System.out.println("Score: " + resultOfAnazlyzeCubs[0]);
        System.out.println("Count: " + resultOfAnazlyzeCubs[1]);*/

        TextView textView = new TextView(context);
        textView.setText(" - " + resultOfAnazlyzeCubs[0]);
        linearLayout.addView(textView);
        combination_box.addView(linearLayout);
    }

    /**
     * Метод вывода сообщение
     *
     * @param infoMessage - view для выводо сообщение
     * @param currentScoreTest - view для вывода счета
     * @param оptions - тип сообщения
     * @param currentScore - текущий счет
     */
    public static void DisplayMessage(TextView infoMessage, TextView currentScoreTest, int оptions, int currentScore){
        switch (оptions){
            //Cумма очков
            case 1:
                infoMessage.setText(messages[оptions-1]);
                currentScoreTest.setText(currentScore + "");
                break;
            //Очки скгорели
            case 2:
                infoMessage.setText(messages[оptions-1]);
                currentScoreTest.setText("");
                break;
            //Игрок записывает очки
            case 3:
                break;
            //Очки записаны
            case 4:
                break;
            //Бросаем кости =)
            case 5:
                break;

        }
    }
}
