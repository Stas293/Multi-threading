public class MultithreadingTransfer {

    public static final int USER_QUANTITY = 5;
    public static final int CUSTOMERS = 100;
    public static final double ORIGINAL_FINANCES = 1000;
    public static final double MAX_TRANSFER = 1000;

    public static void main(String[] args) {
        UserFinances finances = new UserFinances(USER_QUANTITY, ORIGINAL_FINANCES);
        for (int i = 0; i < CUSTOMERS; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    int fromAccount = (int) (finances.getUsersLength() * Math.random());
                    int toAccount = (int) (finances.getUsersLength() * Math.random());
                    double cash = MAX_TRANSFER * Math.random();
                    finances.synchronizedFinance(fromAccount, toAccount, cash);
                }
            });
            t.start();
        }
    }
}
