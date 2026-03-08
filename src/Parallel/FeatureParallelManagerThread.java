import java.util.Random;
import java.util.Scanner;

// Divide-and-conquer manager thread that splits row ranges
public class FeatureParallelManagerThread {

  static class ManagerThread extends Thread {
    private final int[][] A;
    private final int[][] B;
    private final int[][] C;
    private final int startRow;
    private final int endRow; // exclusive
    private final int threshold;

    public ManagerThread(int[][] A, int[][] B, int[][] C, int startRow, int endRow, int threshold) {
      this.A = A;
      this.B = B;
      this.C = C;
      this.startRow = startRow;
      this.endRow = endRow;
      this.threshold = threshold;
    }

    @Override
    public void run() {
      try {
        if (endRow - startRow <= threshold) {
          multiplyRange();
        } else {
          int mid = (startRow + endRow) / 2;
          ManagerThread left = new ManagerThread(A, B, C, startRow, mid, threshold);
          ManagerThread right = new ManagerThread(A, B, C, mid, endRow, threshold);

          left.start();
          right.start();

          left.join();
          right.join();
        }
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }

    private void multiplyRange() {
      int n = B.length;
      for (int i = startRow; i < endRow; i++) {
        for (int j = 0; j < n; j++) {
          int sum = 0;
          for (int k = 0; k < n; k++) {
            sum += A[i][k] * B[k][j];
          }
          C[i][j] = sum;
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter matrix size: ");
    int size = input.nextInt();

    System.out.print("Enter threshold (min rows per task, e.g. 16): ");
    int threshold = input.nextInt();

    int[][] A = new int[size][size];
    int[][] B = new int[size][size];
    int[][] C = new int[size][size];

    Random rand = new Random();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        A[i][j] = rand.nextInt(10);
        B[i][j] = rand.nextInt(10);
      }
    }

    long startTime = System.currentTimeMillis();

    ManagerThread root = new ManagerThread(A, B, C, 0, size, Math.max(1, threshold));
    root.start();
    root.join();

    long endTime = System.currentTimeMillis();
    System.out.println("Divide-and-Conquer Parallel Execution Time: " + (endTime - startTime) + " ms");

    input.close();
  }
}
