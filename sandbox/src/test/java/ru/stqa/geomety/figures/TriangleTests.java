package ru.stqa.geomety.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        var s = new Triangle(5, 6, 4);
        double result = s.perimeter();
        Assertions.assertEquals(15, result);
    }

    @Test
    void canCalculateArea() {
        var s = new Triangle(5, 6, 4);
        double result = s.area();
        Assertions.assertEquals(9.921567416492215, result);
    }

    @Test
    void cannotCreateTriangleWithNegative () {
        try {
            new Triangle(-5, -6, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }

    @Test
    void violationOfTheTriangleInequality () {
        try {
            new Triangle(4, 25, 6);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }



}


