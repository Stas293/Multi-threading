import java.util.concurrent.locks.ReentrantLock;

public class Car {
    private int totalSpent;
    ReentrantLock lock = new ReentrantLock();

    public Car() {
        totalSpent = 0;
    }

    public Car(int totalSpent) {
        this.totalSpent = totalSpent;
    }

    public int getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(int totalSpent) {
        lock.lock();
        try {
            this.totalSpent = totalSpent;
        } finally {
            lock.unlock();
        }
    }

    public int addToTotalSpent(int add) {
        lock.lock();
        try {
            return totalSpent += add;
        } finally {
            lock.unlock();
        }
    }

    public int subtractFromTotalSpent(int subtract) {
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
