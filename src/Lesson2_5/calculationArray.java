package Lesson2_5;

import java.util.Arrays;

public class calculationArray {
    private static final int size = 10000000;
    private static final int half = size / 2;
    private float[] arr = new float[size];


    public void calc1() {

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long start = System.currentTimeMillis();
        fill(arr);
        System.out.println("Время выполнения одним потоком, мс :  " + (System.currentTimeMillis() - start));
    }


    public void calc2() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        float[] arr1 = new float[half];
        float[] arr2 = new float[half];
        long start2 = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, half); /** деление массивов*/
        System.arraycopy(arr, half, arr2, 0, half);

        Thread flow1 = new Thread(() -> {
            fill(arr1);
        });

        Thread flow2 = new Thread(() -> {
            fill(arr2);
        });

        flow1.start();
        flow2.start();

        try {
            flow1.join();
            flow2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, half);
        System.arraycopy(arr2, 0, arr, half, half);

        System.out.println("Время выполнения двумя потоками, мс :  " + (System.currentTimeMillis() - start2));

    }


    private void fill(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}


