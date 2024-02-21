package ru.stqa.geomety.figures;


public class Triangle {
    public final double side_1;
    public final double side_2;
    public final double side_3;

    public Triangle(double side_1, double side_2, double side_3) {
        this.side_1 = side_1;
        this.side_2 = side_2;
        this.side_3 = side_3;
    }

    public double perimeter() {
        return (this.side_1 + this.side_2 + this.side_3);
    }

    public double area() {
        var halfOfThePerimeter = perimeter()/2;
        return (Math.sqrt(halfOfThePerimeter * (halfOfThePerimeter - this.side_1) * (halfOfThePerimeter - this.side_2) * (halfOfThePerimeter - this.side_3)));
    }
}

/*        public void printTrianglePerimeter(double a, double b, double c) {
        var perimeter = String.format("Периметр треугольника со сторонами %f, %f и %f  = %f", this.side_1, this.side_2, this.side_3, perimeter());
        System.out.println(perimeter);

        var area = String.format("Площадь треугольника со сторонами %f, %f и %f  = %f", this.side_1, this.side_2, this.side_3, area());
        System.out.println(area);*/



    

       /* Старый вариант работы*/
/*    public static void printTrianglePerimeter(double a, double b, double c) {
        var perimeter = String.format("Периметр треугольника со сторонами %f, %f и %f  = %f", a, b, c, trianglePerimeter(a, b, c));
        System.out.println(perimeter);

        var area = String.format("Площадь треугольника со сторонами %f, %f и %f  = %f", a, b, c, triangleArea(a, b, c));
        System.out.println(area);

    }

    public static double trianglePerimeter(double a, double b, double c) {
        return ((a + b + c) / 2);
    }

    public static double triangleArea(double a, double b, double c) {
        double trianglePerimeter = ((a + b + c) / 2);
        return (Math.sqrt(trianglePerimeter * (trianglePerimeter - a) * (trianglePerimeter - b) * (trianglePerimeter - c)));
    }*/

