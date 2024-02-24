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

    @Test
    void testEquality(){
        var triangle1 = new Triangle(4, 5, 6);
        var triangle2 = new Triangle(4, 5, 6);
        Assertions.assertEquals(triangle1, triangle2);
    }

    @Test
    void testEquality2() {
        var tr1 = new Triangle(4, 5, 6);
        var tr2 = new Triangle(4, 6, 5);
        Assertions.assertEquals(tr1, tr2);
    }
    @Test
    void testEquality3() {
        var tr1 = new Triangle(4, 5, 6);
        var tr2 = new Triangle(5, 6, 4);
        Assertions.assertEquals(tr1, tr2);
    }
    @Test
    void testEquality4() {
        var tr1 = new Triangle(4, 5, 6);
        var tr2 = new Triangle(5, 4, 6);
        Assertions.assertEquals(tr1, tr2);
    }
    @Test
    void testEquality5() {
        var tr1 = new Triangle(4, 5, 6);
        var tr2 = new Triangle(6, 4, 5);
        Assertions.assertEquals(tr1, tr2);
    }
    @Test
    void testEquality6() {
        var tr1 = new Triangle(4, 5, 6);
        var tr2 = new Triangle(6, 5, 4);
        Assertions.assertEquals(tr1, tr2);
    }

}


