import java.util.Random;

public class FirstThread implements Runnable{
    User user;
    Car car;

    public FirstThread(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    @Override
    public void run() {
        Random random = new Random();
        int K1 = random.nextInt(10000, 20000);
        for (int i = 0; i < K1; i++) {
            user.addToBalance(random.nextDouble(0, 10));
            car.addToTotalSpent(random.nextDouble(0, 100));
        }
    }
}
