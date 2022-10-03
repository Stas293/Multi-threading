import java.util.Random;

public class SecondThread implements Runnable{
    User user;
    Car car;

    public SecondThread(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    @Override
    public void run() {
        Random random = new Random();
        int K2 = random.nextInt(10000, 20000);
        for (int i = 0; i < K2; i++) {
            car.addToTotalSpent(random.nextInt(0, 10));
            user.addToBalance(random.nextInt(0, 100));
        }
    }
}
