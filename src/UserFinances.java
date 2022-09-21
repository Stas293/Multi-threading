import java.util.Arrays;

public class UserFinances {

    private final User[] users;

    public UserFinances(int quantity, double balance) {
        users = Arrays.stream(new User[quantity]).map(user -> new User(balance)).toArray(User[]::new);
    }

    public void synchronizedFinance(int fromUser, int toUser, double cash) {
        int first = Math.min(fromUser, toUser);
        int second = Math.max(fromUser, toUser);
        synchronized(users[first]) {
            synchronized(users[second]) {
                System.out.printf("%9s", Thread.currentThread().getName());
                users[fromUser].subtractFromBalance(cash);
                System.out.printf(" %10.2f fromUser %d toUser %d", cash, fromUser, toUser);
                users[toUser].addToBalance(cash);
                System.out.printf(" Total Balance: %10.2f%n", getCash());
            }
        }
    }

    public double getCash() {
        return Arrays.stream(users).mapToDouble(User::getBalance).sum();
    }

    public int getUsersLength() {
        return users.length;
    }
}
