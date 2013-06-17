package ru.ifmo.ctddev.tkachenko.task2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Matrix {

    private int height = 0, width = 0;
    private double[][] data;

    public Matrix(int height, int width) {
        this(new double[Math.max(height, 0)][Math.max(width, 0)]);
    }

    public Matrix(Matrix matrix) {
        this((matrix != null) ? matrix.getCopyOfData() : null);
    }


    public Matrix(double[][] data) {
        if (data == null) {
            throw new NullPointerException("Cannot construct from null data");
        }
        this.data = getCopyOfData(data);
        this.height = data.length;
        if (height == 0) {
            throw new MatrixSizeException();
        }

        if ((data[0] == null) || (data[0].length == 0)) {
            throw new MatrixSizeException();
        }
        this.width = data[0].length;

        for (int i = 0; i < height; i++) {
            if ((data[i] == null) || (data[i].length != width)) {
                throw new MatrixSizeException();
            }
        }
    }

    public Matrix(BufferedReader bufferedReader) throws IOException {
        readDataFromReader(bufferedReader);
    }

    public Matrix(File file) throws IOException {
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("Argument in constructor from file isn't a correct file");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        try {
            readDataFromReader(bufferedReader);
        } finally {
            bufferedReader.close();
        }
    }

    private void readDataFromReader(BufferedReader in) throws IOException {
        String line = in.readLine();
        if (line == null) {
            throw new WrongMatrixFormatException("Null first line");
        }
        StringTokenizer tokenizer = new StringTokenizer(line);
        if (tokenizer.countTokens() != 2) {
            throw new WrongMatrixFormatException("Wrong first line in matrix file");
        }
        try {
            this.height = Integer.parseInt(tokenizer.nextToken());
            this.width = Integer.parseInt(tokenizer.nextToken());
        } catch (NumberFormatException e) {
            throw new WrongMatrixFormatException("Not numbers in first line");
        }

        if (height <= 0 || width <= 0) {
            throw new WrongMatrixFormatException("Matrix size have to be positive");
        }
        double[][] ans = new double[height][width];

        for (int i = 0; i < height; i++) {
            line = in.readLine();
            if (line == null) {
                throw new WrongMatrixFormatException("Not enough lines in matrix");
            }
            tokenizer = new StringTokenizer(line);
            if (tokenizer.countTokens() != width) {
                throw new WrongMatrixFormatException("Wrong number of numbers in " + (i + 1) + " line");
            }
            for (int j = 0; j < width; j++) {
                if (!tokenizer.hasMoreTokens()) {
                    throw new WrongMatrixFormatException("Not enough columns in matrix");
                }
                try {
                    ans[i][j] = Double.parseDouble(tokenizer.nextToken());
                } catch (NumberFormatException e) {
                    throw new WrongMatrixFormatException("Not enough numbers in " + (i + 1) + "line");
                }

            }
        }

        this.data = ans;

    }

    public void write(File file) throws IOException {
        if (file == null || !file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("Argument in write function isn't a correct file");
        }

        FileWriter out = new FileWriter(file);

        try {
            out.write(height + " " + width + "\n");
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    out.write(data[i][j] + " ");
                }
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            try {
                out.close();
            } catch (IOException ee) {  /* Ignoring */ }
            throw e;
        }

    }

    private double[][] getCopyOfData() {
        return getCopyOfData(this.data);
    }

    private double[][] getCopyOfData(double[][] whatCopy) {
        if (whatCopy == null) {
            return null;
        }

        double[][] newData = new double[whatCopy.length][];
        for (int i = 0; i < whatCopy.length; i++) {
            if (whatCopy[i] == null) {
                newData[i] = null;
            } else {
                newData[i] = new double[whatCopy[i].length];
                for (int j = 0; j < whatCopy[i].length; j++) {
                    newData[i][j] = whatCopy[i][j];
                }
            }
        }

        return newData;
    }

    public Matrix scale(double val) {
        Matrix ans = new Matrix(this);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ans.data[i][j] *= val;
            }
        }
        return ans;
    }

    public Matrix add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Null argument");
        }

        if ((getHeight() != matrix.getHeight()) || (getWidth() != matrix.getWidth())) {
            throw new MatrixArithmeticException("Wrong size of right argument in add operation");
        }
        Matrix ans = new Matrix(this);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ans.data[i][j] += matrix.data[i][j];
            }
        }
        return ans;
    }

    public Matrix transpose() {
        Matrix ans = new Matrix(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ans.data[j][i] = data[i][j];
            }
        }
        return ans;
    }

    public Matrix subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Null argument");
        }

        if ((getHeight() != matrix.getHeight()) || (getWidth() != matrix.getWidth())) {
            throw new MatrixArithmeticException("Wrong size of right argument in subtract operation");
        }
        Matrix ans = new Matrix(this);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ans.data[i][j] -= matrix.data[i][j];
            }
        }
        return ans;
    }

    public Matrix multiply(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Null argument");
        }

        if (getWidth() != matrix.getHeight()) {
            throw new MatrixArithmeticException("Wrong size of right argument in multiply operation");
        }

        int resultHeight = getHeight();
        int resultWidth = matrix.getWidth();
        Matrix ans = new Matrix(resultHeight, resultWidth);

        for (int i = 0; i < resultHeight; i++) {
            for (int j = 0; j < resultWidth; j++) {
                double currentResult = 0;
                for (int k = 0; k < getWidth(); k++) {
                    currentResult += data[i][k] * matrix.data[k][j];
                }
                ans.data[i][j] = currentResult;
            }
        }

        return ans;
    }

    public void set(int i, int j, double value) {
        if (i > getHeight() || (j > getWidth()) || (i <= 0) || (j <= 0)) {
            throw new MatrixOutOfBoundException();
        }
        data[i - 1][j - 1] = value;
    }

    public double get(int i, int j) {
        if ((i > getHeight()) || (j > getWidth()) || (i <= 0) || (j <= 0)) {
            throw new MatrixOutOfBoundException();
        }
        return data[i - 1][j - 1];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                res.append(data[i][j] + " ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
