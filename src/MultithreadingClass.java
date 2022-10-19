import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MultithreadingClass {
    private static final int NUMBERS = 100;
    public static void main(String[] args) {
        BlockingQueue<Integer> collatzIntegers = new LinkedBlockingQueue<>();
        for (int i = 1; i < NUMBERS; i++) {
            collatzIntegers.add(i);
        }
        System.out.println("Collatz integers: " + collatzIntegers);
        long startTime = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < NUMBERS; i++) {
                executorService.submit(new Collatz(collatzIntegers));
            }
        } finally {
            System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " ms");
        }
    }
}
