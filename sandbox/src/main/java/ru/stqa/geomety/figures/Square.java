package ru.stqa.geomety.figures;

public class Square {

    public double side;

    public Square(double side) {
        this.side = side;
    }


    public static void printSquareArea(Square s){
        java.lang.String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return 4 * this.side;
    }
}
