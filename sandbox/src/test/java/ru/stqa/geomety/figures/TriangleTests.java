package ru.stqa.geomety.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(8, Triangle.trianglePerimeter(5, 6, 5));
    }

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(12, Triangle.triangleArea(5, 6, 5));
    }
}
