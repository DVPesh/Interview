package task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test implements Runnable {

    private static ThreadSafeCounter counter;
    private static final long THREADS_QUANTITY = 502;
    private static final long NUMBER_OF_COUNTER_CHANGES = 5001;

    public static void main(String[] args) throws InterruptedException {
        counter = new ThreadSafeCounter();
        ExecutorService service = Executors.newCachedThreadPool();
        for (long i = 0; i < THREADS_QUANTITY; i++) {
            service.execute(new Test());
        }
        service.shutdown();
        int timeElapsed = 0;
        while (!service.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("Прошло %s секунд (counter = %s), ожидайте результат.%n",
                    timeElapsed += 5, counter.getCounter());
        }
        System.out.println("Результат готов: counter = " + counter.getCounter());
        if (THREADS_QUANTITY * NUMBER_OF_COUNTER_CHANGES == counter.getCounter()) {
            System.out.println("Тест успешно пройден");
        } else {
            System.out.println("Тест не пройден");
        }
    }

    @Override
    public void run() {
        for (long i = 0; i < NUMBER_OF_COUNTER_CHANGES; i++) {
            counter.getCounter();
            counter.incrementCounter();
            counter.getCounter();
        }
    }
}
