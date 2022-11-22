package org.example.repository;

import java.util.Scanner;

public class Scan {

    private static String returnString;
    private static Integer returnInt;

    public static String getSomeString(){
        returnString = thisScanner().nextLine();
        return returnString;
    }
    public static Integer getSomeInt(){
        returnInt = thisScanner().nextInt();
        return returnInt;
    }
//    public static boolean hasNext(){
//        Scanner scanner = new Scanner(System.in);
//        return scanner.hasNext();
//    }

    private static Scanner thisScanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
}