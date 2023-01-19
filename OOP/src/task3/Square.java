package task3;

class Square extends Shape {

    private final double sideLength;

    Square(Point origin, double sideLength) {
        super(origin);
        this.sideLength = sideLength;
    }

    @Override
    double calculateArea() {
        System.out.println("Вычислена площадь квадрата");
        return sideLength * sideLength;
    }
}
