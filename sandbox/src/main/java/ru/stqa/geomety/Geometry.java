package ru.stqa.geomety;

import ru.stqa.geomety.figures.Rectangle;
import ru.stqa.geomety.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7));
        Square.printSquareArea(new Square(5));
        Square.printSquareArea(new Square(3));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(100, 58.4);
    }

}
