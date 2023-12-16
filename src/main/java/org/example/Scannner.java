package org.example;

import java.util.Scanner;

public class Scannner {
    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static String getStringInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
