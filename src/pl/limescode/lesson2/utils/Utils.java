package pl.limescode.lesson2.utils;

import java.util.Random;

public class Utils {

    public static String[][] generateMatrix(int dimensionX, int dimensionY) {
        String[][] array = new String[dimensionX][dimensionY];
        int min = 0;
        int max = 10;
        Random random = new Random();
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                Integer randomValue = random.nextInt(max + min) + min;
                array[i][j] = randomValue.toString();
            }
        }
        printMatrix("Generated Matrix", array);
        return array;
    }

    public static void printMatrix(String message, String[][] matrix) {
        System.out.println(message + ": ");
        for (String[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
