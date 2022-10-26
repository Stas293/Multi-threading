import java.util.concurrent.BlockingQueue;

public class Collatz implements Runnable {
    BlockingQueue<Integer> collatzIntegers;
    public Collatz(BlockingQueue<Integer> collatzIntegers) {
        this.collatzIntegers = collatzIntegers;
    }

    @Override
    public void run() {
        int n = collatzIntegers.poll();
        System.out.println("Thread " + Thread.currentThread().getName() + " is working on " + n);
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
    }
}
