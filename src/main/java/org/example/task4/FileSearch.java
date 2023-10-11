package org.example.task4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileSearch {
    public static void getMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file path: ");
        String filePath = scanner.next();

        System.out.print("Enter the word to search: ");
        String searchWord = scanner.next();

        Thread searchWords = new Thread(() -> {
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
                String line;
                int count = 0;

                while((line = bufferedReader.readLine()) != null){
                    String replaceLine = line.replaceAll("[^a-zA-Z0-9]+", " ");
                    String[] arrayWords = replaceLine.split(" ");
                    for (var word : arrayWords) {
                        if(word.equals(searchWord)){
                            count++;
                        }
                    }
                }

                System.out.println("Occurrences of '" + searchWord + "': " + count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        searchWords.start();

        try {
            searchWords.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
