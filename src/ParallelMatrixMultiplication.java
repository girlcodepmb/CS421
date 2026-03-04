import java.util.Scanner;
import java.util.Random;

class WorkerThread extends Thread {

    int[][] A, B, C;
    int startRow, endRow;

    public WorkerThread(int[][] A, int[][] B, int[][] C, int startRow, int endRow) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    public void run() {

        int size = B.length;

        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}

public class ParallelMatrixTest {

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter matrix size: ");
        int size = input.nextInt();

        System.out.print("Enter number of threads: ");
        int threads = input.nextInt();

        int[][] A = new int[size][size];
        int[][] B = new int[size][size];
        int[][] C = new int[size][size];

        Random rand = new Random();

        // Initialize matrices
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.nextInt(10);
                B[i][j] = rand.nextInt(10);
            }
        }

        WorkerThread[] workers = new WorkerThread[threads];

        int rowsPerThread = size / threads;

        long start = System.currentTimeMillis();

        for (int i = 0; i < threads; i++) {

            int startRow = i * rowsPerThread;
            int endRow = (i == threads - 1) ? size : startRow + rowsPerThread;

            workers[i] = new WorkerThread(A, B, C, startRow, endRow);
            workers[i].start();
        }

        for (int i = 0; i < threads; i++) {
            workers[i].join();
        }

        long end = System.currentTimeMillis();

        System.out.println("Parallel Execution Time: " + (end - start) + " ms");
    }
}