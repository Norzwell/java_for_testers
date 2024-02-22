package ru.stqa.geomety.figures;

import java.awt.geom.Area;

public record Triangle (double side_1, double side_2, double side_3) {


    public Triangle {
        if (side_1 < 0 || side_2 < 0 || side_3 < 0) {
            throw new IllegalArgumentException("У стороны не может быть отрицательное значение");
        }
        if ((!(side_1 < (side_2 + side_3)) || !(side_2 < (side_1 + side_3)) || !(side_3 < (side_1 + side_2)))) {
            throw new IllegalArgumentException("Сумма двух сторон треугольника должна быть не меньше третьей стороны ");
        }
    }

    public double perimeter() {
        return (side_1 + side_2 + side_3);
    }

    public double area() {
        var halfOfThePerimeter = perimeter() / 2;
        return (Math.sqrt(halfOfThePerimeter * (halfOfThePerimeter - side_1) * (halfOfThePerimeter - side_2) * (halfOfThePerimeter - side_3)));
    }

    public static void printTrianglePerimeter(Triangle tr) {
        var text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", tr.side_1(), tr.side_2(), tr.side_3(), tr.perimeter());
        System.out.println(text);
    }
    public static void printTriangleArea(Triangle tr) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", tr.side_1(), tr.side_2(), tr.side_3(), tr.area());
        System.out.println(text);
    }
}

//    public static void printTriangleArea(double side_1, double side_2, double side_3){
//        java.lang.String text = String.format("Площадь треугольника со сторонами %f, %f, %f = %f", side_1, side_2, side_3, area());
//        System.out.println(text);
//    }

/* public class Triangle {
    public static double side_1;
    public static double side_2;
    public static double side_3;



    public Triangle(double side_1, double side_2, double side_3) {
        Triangle.side_1 = side_1;
        Triangle.side_2 = side_2;
        Triangle.side_3 = side_3;
    }


    public double perimeter() {
        return (side_1 + side_2 + side_3);
    }

    public double area() {
        var halfOfThePerimeter = perimeter()/2;
        return (Math.sqrt(halfOfThePerimeter * (halfOfThePerimeter - side_1) * (halfOfThePerimeter - side_2) * (halfOfThePerimeter - side_3)));
    }
}*/