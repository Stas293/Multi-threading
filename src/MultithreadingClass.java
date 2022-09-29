import java.util.*;

public class MultithreadingClass {
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        Random random = new Random();
        int N = random.nextInt(10, 21);
        System.out.println("N = " + N);
        User user = new User();
        Car car = new Car();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            if (i < N / 2) {
                threads.add(new Thread(new FirstThread(user, car)));
            } else {
                threads.add(new Thread(new SecondThread(user, car)));
            }
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("The program stopped");
        System.out.println("User: " + user);
        System.out.println("Car: " + car);
        System.out.println("Total spent: " + (stopTime - startTime) + " ms");
    }
}
