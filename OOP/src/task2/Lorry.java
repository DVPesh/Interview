package task2;

class Lorry extends Car implements Stopable {

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }
}
