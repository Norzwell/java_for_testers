package ru.stqa.geomety.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        var s = new Triangle(5, 6, 5);
        double result = s.perimeter();
        Assertions.assertEquals(16, result);
    }

    @Test
    void canCalculateArea() {
        var s = new Triangle(5, 6, 5);
        double result = s.area();
        Assertions.assertEquals(12, result);
    }

    /*    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(8, Triangle.trianglePerimeter(5, 6, 5));
    }

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(12, Triangle.triangleArea(5, 6, 5));
    }*/
}
