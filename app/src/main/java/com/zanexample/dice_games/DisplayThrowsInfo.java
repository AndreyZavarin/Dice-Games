package com.zanexample.dice_games;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Andrey on 01.03.2017.
 */

public class DisplayThrowsInfo {

    static String[] messages = {"Сумма очков: ", "Очки сгорели!", ": записывает очки", "Очки записаны", "Кости бросает: "};

    /**
     * Метод отображения комбинаций
     *
     * @param context - контекст класса
     * @param currentCombination - текущая комбинация
     * @param currentScore - сумма очков текущего хода
     * @param imageDrawable - массив ссылок на изображение
     * @param combination_box - поле для вывода комбинации
     */
    public static void DisplayCombination(Context context, int[] currentCombination, int currentScore,
                                              int[] imageDrawable, LinearLayout  combination_box ){

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(Gravity.BOTTOM);

        //Вывод комбинации в поле комбинаций
        for(int i = 0; i < currentCombination.length; i++){
            ImageView image = new ImageView(context);
            image.setImageResource(imageDrawable[currentCombination[i]-1]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            layoutParams.rightMargin += 5;
            image.setLayoutParams(layoutParams);
            image.setMaxHeight(15);
            image.setMaxWidth(15);
            linearLayout.addView(image);
        }

        TextView textView = new TextView(context);
        textView.setText(" - " + currentScore);
        linearLayout.addView(textView);
        combination_box.addView(linearLayout);
    }

    /**
     * Метод вывода сообщение
     *
     * @param infoMessage - view для выводо сообщение
     * @param currentScoreTest - view для вывода счета
     * @param messageType - тип сообщения
     * @param currentScore - текущий счет
     * @param player - имя игрока
     */
    public static void DisplayMessage(TextView infoMessage, TextView currentScoreTest, int messageType, int currentScore, String player){
        switch (messageType){
            //Cумма очков
            case 1:
                infoMessage.setText(messages[messageType-1]);
                currentScoreTest.setText(currentScore + "");
                break;
            //Очки скгорели
            case 2:
                infoMessage.setText(messages[messageType-1]);
                currentScoreTest.setText("");
                break;
            //Игрок записывает очки
            case 3:
                infoMessage.setText(player + messages[messageType-1]);
                currentScoreTest.setText("");
                break;
            //Очки записаны
            case 4:
                infoMessage.setText(messages[messageType-1]);
                currentScoreTest.setText("");
                break;
            //Бросает кости
            case 5:
                infoMessage.setText(messages[messageType-1] + player);
                currentScoreTest.setText("");
                break;
        }
    }
}
