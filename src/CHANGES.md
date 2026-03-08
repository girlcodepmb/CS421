CHANGES
=======

2026-03-08
- Added Parallel/FeatureParallelManagerThread.java: a divide-and-conquer manager-thread implementation that splits matrix rows recursively and computes subranges when below a threshold. Includes a demo `main` that prompts for matrix size and threshold and prints execution time.

Notes:
- To run: `javac Parallel/FeatureParallelManagerThread.java` then `java Parallel.FeatureParallelManagerThread` from the `src` directory.
