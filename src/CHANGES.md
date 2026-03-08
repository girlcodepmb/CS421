CHANGES
=======

2026-03-08
- Added Parallel/FeatureParallelManagerThread.java: a divide-and-conquer manager-thread implementation that splits matrix rows recursively and computes subranges when below a threshold. Includes a demo `main` that prompts for matrix size and threshold and prints execution time.

Notes:
- To run: `javac Parallel/FeatureParallelManagerThread.java` then `java Parallel.FeatureParallelManagerThread` from the `src` directory.
 
2026-03-08
- Added `Main.java`: interactive selector to run `SerialMatrixMultiplication`, `ParallelMatrixMultiplication`, or `FeatureParallelManagerThread` from a single entry point.

To run from `src` directory:
```
javac Main.java Serial/SerialMatrixMultiplication.java Parallel/ParallelMatrixMultiplication.java Parallel/FeatureParallelManagerThread.java
java Main
```
