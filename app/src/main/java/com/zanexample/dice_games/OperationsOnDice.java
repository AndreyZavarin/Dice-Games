package com.zanexample.dice_games;


import java.util.Arrays;
import java.util.Random;

/**
 * Created by Andrey on 01.03.2017.
 */

public class OperationsOnDice {

    public int[] throwCubes(int numberOfCubes) {
        Random rndm = new Random();
        int[] valueOnThrowCubes = new int[numberOfCubes];
        //setup values of cubes by random
        for (int i = 0; i < numberOfCubes; i++) {
            valueOnThrowCubes[i] = rndm.nextInt(6) + 1;
        }

        return valueOnThrowCubes;
    }

    public int[] getCountOfValue(int[] valueOnCubes) {
        int[] counter = {0, 0, 0, 0, 0, 0, 0};
        //В массиве counter хранится количество повторений значений кубиков
        for (int i = 0; i < valueOnCubes.length; i++) {
            counter[valueOnCubes[i]]++;
        }

        return counter;
    }

    public int[] currentCombinationToIntArray(int combination) {
        String temp = Integer.toString(combination);
        int[] currentCombination = new int[temp.length()];

        for (int i = 0; i < temp.length(); i++){
            currentCombination[i] = temp.charAt(i) - '0';
        }

        return currentCombination;
    }

    public int[] combinations(int[] valueOnCubes, int[] countOfValue, int numberOfCubes) {
        String stringCounter;
        String combination12345 = new String("12345");
        String combintaion23456 = new String("23456");
        int scoreOfThrow = 0;
        int tmpForCountUsedCubs = 0;
        int currentCombination = 0;
        String currentCombinationString = "";
        int[] arrayReturns = {scoreOfThrow, tmpForCountUsedCubs, currentCombination};

        //Обработка цифры 1 и ее возможных комбинаций.
        switch (countOfValue[1]) {
            case 1:
                scoreOfThrow = 10;
                currentCombinationString = "1";
                break;
            case 2:
                scoreOfThrow = 20;
                currentCombinationString = "11";
                break;
            case 3:
                scoreOfThrow = 100;
                currentCombinationString = "111";
                break;
            case 4:
                scoreOfThrow = 200;
                currentCombinationString = "1111";
                break;
            case 5:
                scoreOfThrow = 1000;
                currentCombinationString = "11111";
                break;
        }
        //Обработка цифры 5 при одном или двух выпаданиях.
        switch (countOfValue[5]) {
            case 1:
                scoreOfThrow = scoreOfThrow + 5;
                currentCombinationString = currentCombinationString + "5" ;
                tmpForCountUsedCubs = 1;
                break;
            case 2:
                scoreOfThrow = scoreOfThrow + 10;
                currentCombinationString = currentCombinationString + "55";
                tmpForCountUsedCubs = 2;
                break;
        }

        for (int i = 2; i < countOfValue.length; i++) {
            if (countOfValue[i] == 3) {
                scoreOfThrow = scoreOfThrow + i * 10;
                tmpForCountUsedCubs += 3;
                currentCombinationString = currentCombinationString +
                        String.valueOf(i)+
                        String.valueOf(i)+
                        String.valueOf(i);
            }
            if (countOfValue[i] == 4) {
                scoreOfThrow = scoreOfThrow + i * 10 * 2;
                tmpForCountUsedCubs += 4;
                currentCombinationString = currentCombinationString +
                        String.valueOf(i)+
                        String.valueOf(i)+
                        String.valueOf(i)+
                        String.valueOf(i);

            }
            if (countOfValue[i] == 5) {
                scoreOfThrow = scoreOfThrow + i * 100;
                tmpForCountUsedCubs += 5;
                currentCombinationString = currentCombinationString +
                        String.valueOf(i)+
                        String.valueOf(i)+
                        String.valueOf(i)+
                        String.valueOf(i)+
                        String.valueOf(i);
            }
        }
        Arrays.sort(valueOnCubes);
        stringCounter = Arrays.toString(valueOnCubes);
        System.out.println("SortString " + stringCounter);
        if (stringCounter.equals(combination12345)) {
            scoreOfThrow = 125;
            tmpForCountUsedCubs = 5;
            currentCombinationString ="12345" ;
        }
        if (stringCounter.equals(combintaion23456)) {
            scoreOfThrow = 250;
            tmpForCountUsedCubs = 5;
            currentCombinationString = "23456";
        }
        tmpForCountUsedCubs = tmpForCountUsedCubs + countOfValue[1];
        tmpForCountUsedCubs = numberOfCubes - tmpForCountUsedCubs;

        if(currentCombinationString.length() == 0) {
            currentCombinationString = "0";
        }

        arrayReturns[0] = scoreOfThrow;
        arrayReturns[1] = tmpForCountUsedCubs;
        arrayReturns[2] = Integer.parseInt(currentCombinationString);
        return arrayReturns;
    }

}
