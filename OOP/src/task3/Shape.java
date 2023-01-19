package task3;

abstract class Shape {

    protected final Point origin;

    Shape(Point origin) {
        this.origin = origin;
    }

    abstract double calculateArea();
}
