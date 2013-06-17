package ru.ifmo.ctddev.tkachenko.task6;

import de.erichseifert.gral.data.DataTable;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 14.05.13
 * Time: 1:13
 */

/**
 * Testing class
 */
public class Tester {
    /**
     * number of test
     */
    private final int NUMBER_OF_TESTS = 5;
    /**
     * min size of matrix for testing
     */
    private final int MIN_SIZE = 200;
    /**
     * the difference between the sizes of the matrices of two consecutive tests
     */
    private final int SIZE_DELTA = 100;
    /**
     * maximal number of threads for testing
     */
    private final int MIN_NUMBER_OF_THREADS = 1;
    /**
     * minimal number of threads for testing
     */
    private final int MAX_NUMBER_OF_THREADS = 16;
    /**
     * first matrix for testing
     */
    private SquareMatrix a;
    /**
     * second matrix for testing
     */
    private SquareMatrix b;
    /**
     * data that helps us to plot
     */
    private DataTable[] timeTables = new DataTable[NUMBER_OF_TESTS];

    /**
     * Constructor from matrix size. It generates random matrices with current size
     *
     * @param matrixSize matrix size
     * @see Tester#fillWithRandomNumbers(SquareMatrix, SquareMatrix)
     */
    public Tester(int matrixSize) {
        a = new SquareMatrix(matrixSize);
        b = new SquareMatrix(matrixSize);
        fillWithRandomNumbers(a, b);

    }

    /**
     * Run the matrixMultiplication of particular two matrices with particular number of threds
     *
     * @param numberOfThreads available number of threads
     * @see MatrixMultiplication#multiply(SquareMatrix, SquareMatrix, int)
     */
    public void testWithParticularNumberOfThreads(int numberOfThreads) {
        try {
            MatrixMultiplication.multiply(a, b, numberOfThreads);
        } catch (InterruptedException e) {
            System.out.println("Matrix hasn't been calculated");
        }
    }

    /**
     * Run the matrixMultiplication of particular two matrices with a few number
     * of threads(from {@link Tester#MIN_NUMBER_OF_THREADS} to {@link Tester#MAX_NUMBER_OF_THREADS}).
     * The result will be plotted.
     *
     * @see javax.swing.JFrame
     * @see SquareMatrix
     * @see DataTable
     */
    public void testWithSeveralThreads() {
        DataTable timeTable = getTimeTable(a, b);
        if (timeTable == null) {
            return;
        }
        TimePlot timePlot = new TimePlot();
        timePlot.addDataSource(timeTable);
        timePlot.setVisible(true);
    }

    /**
     * Run the matrixMultiplication of several pairs of matrices(with size from {@link Tester#MIN_SIZE} to
     * ({@link Tester#MIN_SIZE} + {@link Tester#SIZE_DELTA} * ({@link Tester#NUMBER_OF_TESTS} - 1)) with a few number
     * of threads(from {@link Tester#MIN_NUMBER_OF_THREADS} to {@link Tester#MAX_NUMBER_OF_THREADS}). The result will be plotted.
     *
     * @see javax.swing.JFrame
     * @see SquareMatrix
     * @see DataTable
     */
    public void testSeveralMatrixWithSeveralThreads() {
        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            int currentSize = MIN_SIZE + i * SIZE_DELTA;
            a = new SquareMatrix(currentSize);
            b = new SquareMatrix(currentSize);
            fillWithRandomNumbers(a, b);
            System.out.println("For size " + currentSize + " : ");
            timeTables[i] = getTimeTable(a, b);
        }

        TimePlot frame = new TimePlot();
        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            if (timeTables[i] != null) {
                frame.addDataSource(timeTables[i]);
            }
        }
        frame.setVisible(true);

    }

    /**
     * Method that returns the time table(for plotting) of execution time of two matrices
     * with several number of threads (from {@link Tester#MIN_NUMBER_OF_THREADS} to {@link Tester#MAX_NUMBER_OF_THREADS})
     *
     * @param a first matrix
     * @param b second matrix
     * @return time table of execution time
     * @see MatrixMultiplication#multiply(SquareMatrix, SquareMatrix, int)
     */
    private DataTable getTimeTable(SquareMatrix a, SquareMatrix b) {
        DataTable timeTable = new DataTable(Integer.class, Long.class);
        boolean isCalculated = true;
        for (int i = MIN_NUMBER_OF_THREADS; i <= MAX_NUMBER_OF_THREADS; i++) {
            try {
                timeTable.add(i, MatrixMultiplication.multiply(a, b, i));
            } catch (InterruptedException e) {
                System.out.println("Matrix hasn't been calculated");
                Thread.currentThread().interrupt();
                isCalculated = false;
            }
        }
        return isCalculated ? timeTable : null;
    }

    /**
     * Fill the matrices with random numbers
     *
     * @param a first matrix
     * @param b second matrix
     */
    private void fillWithRandomNumbers(SquareMatrix a, SquareMatrix b) {
        a.fillWithRandomNumbers();
        b.fillWithRandomNumbers();
    }


}
