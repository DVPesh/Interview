package task3;

class Triangle extends Shape {

    private final Point point2, point3;

    Triangle(Point origin, Point point2, Point point3) {
        super(origin);
        this.point2 = point2;
        this.point3 = point3;
    }

    @Override
    double calculateArea() {
        System.out.println("Вычислена площадь треугольника");
        return Math.abs((point2.x - origin.x) * (point3.y - origin.y) - (point3.x - origin.x) * (point2.y - origin.y)) / 2;
    }
}
