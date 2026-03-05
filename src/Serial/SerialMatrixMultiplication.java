import java.util.Scanner;

public class SerialMatrixMultiplication {

    public static void multiplyMatrices() {

        Scanner sc = new Scanner(System.in);

        // ---- INPUT MATRIX A ----
        System.out.print("Enter rows of Matrix A: ");
        int rowsA = sc.nextInt();

        System.out.print("Enter columns of Matrix A: ");
        int colsA = sc.nextInt();

        int[][] A = new int[rowsA][colsA];

        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // ---- INPUT MATRIX B ----
        System.out.print("Enter rows of Matrix B: ");
        int rowsB = sc.nextInt();

        System.out.print("Enter columns of Matrix B: ");
        int colsB = sc.nextInt();

        int[][] B = new int[rowsB][colsB];

        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        // ---- CHECK MULTIPLICATION RULE ----
        if (colsA != rowsB) {
            System.out.println("Matrix multiplication not possible!");
            return;
        }

        // ---- RESULT MATRIX ----
        int[][] C = new int[rowsA][colsB];

        // ---- SERIAL MULTIPLICATION ----
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {

                int sum = 0;

                for (int k = 0; k < colsA; k++) {
                    sum += A[i][k] * B[k][j];
                }

                C[i][j] = sum;
            }
        }

        // ---- PRINT RESULT ----
        System.out.println("Result Matrix:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}