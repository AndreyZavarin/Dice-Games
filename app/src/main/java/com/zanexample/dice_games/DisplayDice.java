package com.zanexample.dice_games;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Andrey on 01.03.2017.
 */

public class DisplayDice{

    private static int[] imageDrawable = {R.drawable.ir_01, R.drawable.ir_02, R.drawable.ir_03, R.drawable.ir_04, R.drawable.ir_05, R.drawable.ir_06};
    private static ImageView[] arrayOfIVCubes = new ImageView[5];
    private static int[] imageId = {R.string.ir_01, R.string.ir_02, R.string.ir_03, R.string.ir_04, R.string.ir_05};

    /**
     * Метод создания костей
     *
     * @param context - контекст класса
     * @param numberOfCubes - значение кубиков
     * @return массив ImageView
     */
    public static ImageView[] createDice(Context context, int numberOfCubes){

       LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
        layoutParams.rightMargin += 5;
        layoutParams.topMargin += 200;

        Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
        for(int i = 0; i<numberOfCubes; i++){
            ImageView iv_ir = new ImageView(context);
            layoutParams.height = 120;
            layoutParams.weight = 120;
            iv_ir.setMaxHeight(120);
            iv_ir.setMaxWidth(120);
            iv_ir.setLayoutParams(layoutParams);
            iv_ir.setId(imageId[i]);
            iv_ir.startAnimation(rotate);
            arrayOfIVCubes[i] = iv_ir;
        }
        return arrayOfIVCubes;
    }

    /**
     * Метод отображения костей
     *
     * @param layout - поле для отображения кубиков
     * @param value - значения кубиков
     * @param imageView - массив ссылок на изображение
     */
    public static void displayDice(LinearLayout layout, int[] value, ImageView[] imageView){
        for (int i = 0; i < value.length; i++) {
            System.out.println(value[i]-1);
            imageView[i].setImageResource(imageDrawable[value[i]-1]);
            layout.addView(imageView[i]);
        }
    }
}
