package org.example.task1_2;

import java.util.Arrays;
import java.util.Scanner;

public class FindMinMaxSumAverageArray {
    public static void getMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        Thread maxThread = new Thread(()->{
            int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);
            System.out.println("Maximum value: " + max);
        });

        Thread minThread = new Thread(() -> {
            int min = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);
            System.out.println("Minimum value: " + min);
        });

        Thread sumThread = new Thread(() -> {
            int sum = Arrays.stream(arr).sum();
            System.out.println("Sum of array: " + sum);
        });

        Thread averageThread = new Thread(() -> {
            double average = Arrays.stream(arr).average().orElse(0.0);
            System.out.println("Average of array: " + average);
        });

        minThread.start();
        maxThread.start();
        sumThread.start();
        averageThread.start();

        try {
            minThread.join();
            maxThread.join();
            sumThread.join();
            averageThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
