package task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCounter {

    private volatile long counter;
    private final Lock lock;

    public ThreadSafeCounter() {
        lock = new ReentrantLock(true);
    }

    public long getCounter() {
        return counter;
    }

    public void incrementCounter() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public long incrementAndGetCounter() {
        lock.lock();
        try {
            return counter++;
        } finally {
            lock.unlock();
        }
    }
}
