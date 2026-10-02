package oop.assignment_problems;

abstract class Shape {
    private static int counter = 0;
    private final String shapeId;

    public Shape() {
        counter++;
        this.shapeId = "SHP-" + (1000 + counter);
    }

    public String getShapeId() {
        return shapeId;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public abstract void scale(double xFactor, double yFactor);
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        this.radius *= xFactor;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        super();
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        this.side *= xFactor;
    }
}

public class BasicDrawingCanvas {
    public static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        printArea(c);

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());
    }
}