import java.util.concurrent.atomic.AtomicInteger;

public class Car {
    private final AtomicInteger totalSpent;

    public Car() {
        totalSpent = new AtomicInteger(0);
    }

    public Car(int totalSpent) {
        this.totalSpent = new AtomicInteger(totalSpent);
    }

    public int getTotalSpent() {
        return totalSpent.get();
    }

    public void setTotalSpent(int totalSpent) {
        this.totalSpent.set(totalSpent);
    }

    public double addToTotalSpent(int add) {
        return totalSpent.addAndGet(add);
    }

    public double subtractFromTotalSpent(int subtract) {
        return totalSpent.addAndGet(-subtract);
    }

    public String toString() {
        return "Total spent: " + totalSpent;
    }
}
