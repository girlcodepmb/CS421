import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("Choose implementation:");
			System.out.println("1) Serial (manual input)");
			System.out.println("2) Simple Parallel (random matrices)");
			System.out.println("3) Manager Divide-and-Conquer Parallel (random matrices)");
			System.out.println("4) Exit");
			System.out.print("Selection: ");

			int sel = sc.nextInt();
			if (sel == 4) break;

			switch (sel) {
				case 1:
					SerialMatrixMultiplication.multiplyMatrices();
					break;
				case 2:
					ParallelMatrixMultiplication.main(new String[0]);
					break;
				case 3:
					FeatureParallelManagerThread.main(new String[0]);
					break;
				default:
					System.out.println("Invalid selection");
			}

			System.out.println();
		}

		sc.close();
	}
}
