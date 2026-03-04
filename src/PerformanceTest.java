public class PerformanceTest {

    public static void main(String[] args) throws Exception {

        int size = 500;
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];
        int[][] C = new int[size][size];

        int threads = Runtime.getRuntime().availableProcessors();

        long start = System.currentTimeMillis();

        ParallelMatrixMultiplication.multiply(A, B, C, threads);

        long end = System.currentTimeMillis();

        System.out.println("Parallel Time: " + (end - start) + " ms");
    }
}