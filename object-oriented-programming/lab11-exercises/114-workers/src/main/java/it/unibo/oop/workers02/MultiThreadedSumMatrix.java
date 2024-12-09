package it.unibo.oop.workers02;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MultiThreadedSumMatrix implements SumMatrix {

    private final int n;

    /**
     * Construct a multithreaded matrix sum.
     * 
     * @param n
     *          no. threads to be adopted to perform the operation
     */
    public MultiThreadedSumMatrix(final int n) {
        super();
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        this.n = n;
    }

    @Override
    public double sum(final double[][] matrix) {
        final int size = matrix.length / this.n + matrix.length % this.n;

        return IntStream.range(0, this.n)
                .parallel()
                .mapToDouble(i -> {
                    double result = 0;
                    final int start = i * size;
                    final int end = Math.min(start + size, matrix.length);

                    for (int row = start; row < end; row++) {
                        result += Arrays.stream(matrix[row]).sum();
                    }
                    return result;
                }).sum();
    }
}
