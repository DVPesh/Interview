package task3;

class Circle extends Shape {

    private final double radius;

    Circle(Point origin, double radius) {
        super(origin);
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        System.out.println("Вычислена площадь круга");
        return Math.PI * radius * radius;
    }
}
