package pl.limescode.lesson5;

import java.util.Arrays;

public class Lesson5 {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long startTime = System.currentTimeMillis();
        calculate(arr);
        System.out.println("1st method time execution: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long startTime = System.currentTimeMillis();

        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];

        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        Thread thread1 = new Thread(() -> calculate(arr1));
        Thread thread2 = new Thread(() -> calculate(arr2));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

        System.out.println("2nd method time execution: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
