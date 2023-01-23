package task2;

public class Test {

    public static void main(String[] args) {
        Lorry lorry = new Lorry();
        lorry.start();
        lorry.move();
        lorry.stop();

        LightWeightCar car = new LightWeightCar();
        car.open();
        car.start();
        car.move();
    }
}
