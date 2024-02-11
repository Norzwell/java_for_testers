package ru.stqa.geomety.figures;

public class Square {
    public static void printSquareArea(double side){
        java.lang.String text = String.format("Площадь квадрата со стороной %f = %f", side, squareArea(side));
        System.out.println(text);
    }

    private static double squareArea(double a) {
        return a * a;
    }
}