import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;

import mpi.MPI;

public class MpiTask {
    public static final int N = 7_000_000;
    private static final Logger logger = Logger.getLogger(MpiTask.class.getName());

    private static void calculatePi() {
        int currentRank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size() - 1;
        double[] x = new double[(int) (N * currentRank * 1.0 / size)];
        double[] y = new double[(int) (N * currentRank * 1.0 / size)];
        MPI.COMM_WORLD.Barrier();
        sendReceiveData(currentRank, size, x, y);
        MPI.COMM_WORLD.Barrier();
        ArrayList<double[]> allRecords = new ArrayList<>();
        workAndSend(currentRank, size, x, y, allRecords);
        MPI.COMM_WORLD.Barrier();
        printResults(currentRank, allRecords);
    }

    private static void sendReceiveData(int currentRank, int size, double[] x, double[] y) {
        for (int j = 0; j < size; j++) {
            MPI.COMM_WORLD.Barrier();
            if (currentRank == 0) {
                sendData(j, size);
            } else if (currentRank == j + 1) {
                fillData(currentRank, size, x, y);
            }
            MPI.COMM_WORLD.Barrier();
        }
    }

    private static void fillData(int currentRank, int size, double[] x, double[] y) {
        MPI.COMM_WORLD.Recv(x, 0, (int) (N * currentRank * 1.0 / size), MPI.DOUBLE, 0, 100);
        MPI.COMM_WORLD.Recv(y, 0, (int) (N * currentRank * 1.0 / size), MPI.DOUBLE, 0, 100);
    }

    private static void workAndSend(int currentRank, int size, double[] x, double[] y, ArrayList<double[]> allRecords) {
        for (int j = 0; j < size; j++) {
            if (currentRank == j + 1) {
                long startTime;
                long endTime;
                double pi;
                startTime = System.nanoTime();
                pi = calculatePi(x, y);
                endTime = System.nanoTime();
                sendCalculations(currentRank, startTime, endTime, pi);
            } else if (currentRank == 0) {
                double[] data = new double[3];
                MPI.COMM_WORLD.Recv(data, 0, 3, MPI.DOUBLE, j + 1, 120);
                allRecords.add(data);
            }
            MPI.COMM_WORLD.Barrier();
        }
    }

    private static void printResults(int currentRank, ArrayList<double[]> allRecords) {
        if (currentRank == 0) {
            for (double[] singleRecord : allRecords)
                logger.log(java.util.logging.Level.INFO,
                        "Rank: {2}, Time: {0}, Pi: {1}", new Object[]{singleRecord[0], singleRecord[1], singleRecord[2]});
        }
    }

    private static void sendCalculations(int currentRank, long startTime, long endTime, double pi) {
        double[] sendData = new double[3];
        sendData[0] = 1.0 * (endTime - startTime) / Math.pow(10, 6);
        sendData[1] = pi;
        sendData[2] = currentRank;
        MPI.COMM_WORLD.Send(sendData, 0, 3, MPI.DOUBLE, 0, 120);
    }

    private static double calculatePi(double[] x, double[] y) {
        double pi;
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] * x[i] + y[i] * y[i] <= 1) {
                count++;
            }
        }
        pi = 4 * (double) count / x.length;
        return pi;
    }

    private static void sendData(int j, int size) {
        double[] x = DoubleStream.iterate(0, i -> i + 1).limit(N).map(i -> Math.random()).toArray();
        double[] y = DoubleStream.iterate(0, i -> i + 1).limit(N).map(i -> Math.random()).toArray();
        MPI.COMM_WORLD.Send(x, 0, (int) (N * (j + 1) * 1.0 / size), MPI.DOUBLE, j + 1, 100);
        MPI.COMM_WORLD.Send(y, 0, (int) (N * (j + 1) * 1.0 / size), MPI.DOUBLE, j + 1, 100);
    }

    public static void main(String[] args) {
        MPI.Init(args);
        calculatePi();
        MPI.Finalize();
    }
}
