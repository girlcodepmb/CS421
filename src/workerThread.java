public class WorkerThread extends Thread {

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

    public void run() {

        int size = B[0].length;

        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < B.length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}