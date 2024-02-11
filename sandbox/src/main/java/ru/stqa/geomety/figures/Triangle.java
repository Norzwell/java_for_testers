package ru.stqa.geomety.figures;


public record Triangle (double a, double b, double c) {

    public static void printTrianglePerimeter(double a, double b, double c) {
        var perimeter = String.format("Периметр треугольника со сторонами %f, %f и %f  = %f", a, b, c, trianglePerimeter(a, b, c));
        System.out.println(perimeter);

        var area = String.format("Площадь треугольника со сторонами %f, %f и %f  = %f", a, b, c, triangleArea(a, b, c));
        System.out.println(area);

    }

    public static double trianglePerimeter(double a, double b, double c) {
        return ((a + b + c) / 2);
    }

/*    public static void printTriangleArea(double a, double b, double c) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f  = %f", a, b, c, triangleArea(a, b, c));
        System.out.println(text);
    }*/

    public static double triangleArea(double a, double b, double c) {
        double trianglePerimeter = ((a + b + c) / 2);
        return (Math.sqrt(trianglePerimeter * (trianglePerimeter - a) * (trianglePerimeter - b) * (trianglePerimeter - c)));
    }
}
