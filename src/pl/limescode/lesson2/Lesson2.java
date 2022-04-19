package pl.limescode.lesson2;

import pl.limescode.lesson2.exceptions.MyArrayDataException;
import pl.limescode.lesson2.exceptions.MyArraySizeException;
import pl.limescode.lesson2.utils.Utils;

public class Lesson2 {

    private static final int LENGTH_X = 4;
    private static final int LENGTH_Y = 4;

    public static void main(String[] args) {
        String[][] array = Utils.generateMatrix(4, 4);
        array[3][0] = "aaa";
        try {
            int totalSum = calculate(array);
            System.out.println("Total sum: " + totalSum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    public static int calculate(String[][] array) {
        int totalSum = 0;
        boolean isValid = validateMatrix(array);
        if (isValid) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    try {
                        int value = Integer.parseInt(array[i][j]);
                        totalSum += value;
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException("Wrong data format in cell[" + i + "][" + j + "]");
                    }
                }
            }
        }
        return totalSum;
    }

    public static boolean validateMatrix(String[][] array) {
        if (array == null) {
            return false;
        }
        if (array.length != LENGTH_X) {
            throw new MyArraySizeException();
        }
        for (String[] arr : array) {
            if (arr.length != LENGTH_Y)
                throw new MyArraySizeException();
        }
        return true;
    }

}
