package ru.ifmo.ctddev.tkachenko.task6;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:01 PM
 */

/**
 * This is convenient way to handle the square matriсes
 */
public class SquareMatrix {
    /**
     * maximal available value in matrix
     */
    private static final int MAX_VALUE = (int) 1e2;
    /**
     * matrix size
     */
    private int size = 0;
    /**
     * elements of matrix
     */
    private int[][] data;
    /**
     * helper for generate elements of matrix
     *
     * @see ru.ifmo.ctddev.tkachenko.task6.SquareMatrix#fillWithRandomNumbers()
     */
    private Random random = new Random();

    /**
     * Constructor matrix size
     *
     * @param size matrix size
     * @throws MatrixSizeException
     */
    public SquareMatrix(int size) {
        if (size <= 0) {
            throw new MatrixSizeException("Wrong size of matrix");
        }
        this.size = size;
        data = new int[size][size];
    }

    /**
     * Method that fills matrix with random numbers
     *
     * @see Random
     */
    public void fillWithRandomNumbers() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = random.nextInt() % MAX_VALUE;
            }
        }

    }

    /**
     * Get the (i, j) element of the matrix
     *
     * @param i number of the row
     * @param j number of the column
     * @return requested element
     * @throws MatrixOutOfBoundException if the indexes is incorrect
     */
    public int get(int i, int j) {
        if (i >= size || i < 0 || j >= size || j < 0) {
            throw new MatrixOutOfBoundException("Wrong indexes in get method");
        }
        return data[i][j];
    }

    /**
     * Set the <tt>(i, j)</tt> element of the matrix
     *
     * @param i     number of the row
     * @param j     number of the column
     * @param value new value of the element
     * @throws MatrixOutOfBoundException if the indexes is incorrect
     */
    public void set(int i, int j, int value) {
        if (i >= size || i < 0 || j >= size || j < 0) {
            throw new MatrixOutOfBoundException("Wrong indexes in set method");
        }
        data[i][j] = value;
    }

    /**
     * Returns matrix size
     *
     * @return matrix size
     */
    public int size() {
        return size;
    }

    /**
     * Overriding toString method
     *
     * @return the string representation of the matrix
     * @see StringBuilder
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultString.append(data[i][j] + " ");
            }
            resultString.append("\n");
        }
        return resultString.toString();
    }
}


