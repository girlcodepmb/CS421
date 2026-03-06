import java.util.Random;
import java.util.Scanner;

// Thread class that handles part of the matrix multiplication
class WorkerThread extends Thread {

  private int[][] A;
  private int[][] B;
  private int[][] C;
  private int startRow;
  private int endRow;

  public WorkerThread(int[][] A, int[][] B, int[][] C, int startRow, int endRow) {
    this.A = A;
    this.B = B;
    this.C = C;
    this.startRow = startRow;
    this.endRow = endRow;
  }

  @Override
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

//parallel logic
public class ParallelMatrixMultiplication {

  public static void main(String[] args) throws InterruptedException {

    Scanner input = new Scanner(System.in);

    System.out.print("Enter matrix size: ");
    int size = input.nextInt();

    System.out.print("Enter number of threads: ");
    int threads = input.nextInt();

    int[][] A = new int[size][size];
    int[][] B = new int[size][size];
    int[][] C = new int[size][size];

    Random rand = new Random();

    // Initialize matrices with random numbers
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        A[i][j] = rand.nextInt(10);
        B[i][j] = rand.nextInt(10);
      }
    }

    WorkerThread[] workers = new WorkerThread[threads];
    int rowsPerThread = size / threads;

    long startTime = System.currentTimeMillis();

    // Creation and startin of threads
    for (int i = 0; i < threads; i++) {

      int startRow = i * rowsPerThread;
      int endRow = (i == threads - 1) ? size : startRow + rowsPerThread;

      workers[i] = new WorkerThread(A, B, C, startRow, endRow);
      workers[i].start();
    }

    // latency (waiting) for all threads to finish
    for (int i = 0; i < threads; i++) {
      workers[i].join();
    }

    long endTime = System.currentTimeMillis();

    System.out.println("Parallel Execution Time: " + (endTime - startTime) + " ms");

    input.close();
  }
}