package task3;

class Test {

    public static void main(String[] args) {
        Shape circle = new Circle(new Point(0.0, 0.0), 5.0);
        Shape square = new Square(new Point(0.0, 0.0), 5.0);
        Shape triangle = new Triangle(new Point(0.0, 0.0), new Point(5.0, 5.0), new Point(6.0, 1.1));
        Shape[] shapes = new Shape[]{circle, square, triangle};
        for (Shape shape : shapes) {
            System.out.println(shape.calculateArea());
        }
    }
}
