public class User {
    private double balance;

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
        this.balance = balance;
    }

    public double addToBalance(double add) {
        return balance += add;
    }

    public double subtractFromBalance(double subtract) {
        return balance -= subtract;
    }
}
