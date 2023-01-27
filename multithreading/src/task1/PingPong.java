package task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PingPong {

    private volatile boolean ping;
    private static Thread pingThread, pongThread;

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(pingPong::renderThePing);
        service.execute(pingPong::renderThePong);
        service.shutdown();
        if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
            pingThread.interrupt();
            pongThread.interrupt();
        }
    }

    private synchronized void renderThePing() {
        pingThread = Thread.currentThread();
        try {
            while (true) {
                while (!ping) {
                    this.wait();
                }
                System.out.println("Ping");
                ping = false;
                this.notify();
            }
        } catch (InterruptedException e) {
            System.out.println("The Ping was interrupted");
        }
    }

    private synchronized void renderThePong() {
        pongThread = Thread.currentThread();
        try {
            while (true) {
                while (ping) {
                    this.wait();
                }
                System.out.println("Pong");
                ping = true;
                this.notify();
            }
        } catch (InterruptedException e) {
            System.out.println("The Pong was interrupted");
        }
    }
}
