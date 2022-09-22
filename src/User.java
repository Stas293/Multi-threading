import java.util.concurrent.locks.ReentrantLock;

public class User {
    private double balance;
    ReentrantLock lock = new ReentrantLock();

    public User() {
        balance = 0;
    }

    public User(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        lock.lock();
        try {
            this.balance = balance;
        } finally {
            lock.unlock();
        }
    }

    public double addToBalance(double add) {
        lock.lock();
        try {
            return balance += add;
        } finally {
            lock.unlock();
        }
    }

    public double subtractFromBalance(double subtract) {
        lock.lock();
        try {
            return balance -= subtract;
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "Balance: " + balance;
    }
}
