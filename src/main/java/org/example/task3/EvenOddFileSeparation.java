package org.example.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EvenOddFileSeparation {
    public static void getMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file path: ");
        String filePath = scanner.next();

        ArrayList<Integer> array = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while((line = bufferedReader.readLine()) != null){
                String[] arr = line.split(" ");
                for (var elem : arr) {
                    array.add(Integer.parseInt(elem.trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread evenThread = new Thread(() -> {
           try(BufferedWriter evenWriter = new BufferedWriter(new FileWriter("even_numbers.txt"))){
               int evenCount = 0;
               for (var elem : array) {
                   if(elem % 2 == 0){
                       evenWriter.write(elem + " ");
                       evenCount++;
                   }
               }
               System.out.println("Count of even numbers of array: " + evenCount);
               System.out.println("Even numbers are wrote.");
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        });

        Thread addThread = new Thread(() -> {
            try(BufferedWriter addWriter = new BufferedWriter(new FileWriter("add_numbers.txt"))){
                int addCount = 0;
                for (var elem : array) {
                    if(elem % 2 != 0){
                        addWriter.write(elem + " ");
                        addCount++;
                    }
                }
                System.out.println("Count of add numbers of array: " + addCount);
                System.out.println("Add numbers are wrote.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        evenThread.start();
        addThread.start();

        try {
            evenThread.join();
            addThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
