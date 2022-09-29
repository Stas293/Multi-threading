import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final AtomicInteger balance;

    public User() {
        balance = new AtomicInteger(0);
    }

    public User(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    public int getBalance() {
        return this.balance.get();
    }

    public void setBalance(int balance) {
        this.balance.set(balance);
    }

    public double addToBalance(int add) {
        return this.balance.addAndGet(add);
    }

    public double subtractFromBalance(int subtract) {
        return this.balance.addAndGet(-subtract);
    }

    public String toString() {
        return "Balance: " + balance;
    }
}
