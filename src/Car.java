import java.util.concurrent.locks.ReentrantLock;

public class Car {
    private double totalSpent;
    ReentrantLock lock = new ReentrantLock();

    public Car() {
        totalSpent = 0;
    }

    public Car(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        lock.lock();
        try {
            this.totalSpent = totalSpent;
        } finally {
            lock.unlock();
        }
    }

    public double addToTotalSpent(double add) {
        lock.lock();
        try {
            return totalSpent += add;
        } finally {
            lock.unlock();
        }
    }

    public double subtractFromTotalSpent(double subtract) {
        lock.lock();
        try {
            return totalSpent -= subtract;
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "Total spent: " + totalSpent;
    }
}
