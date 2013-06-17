package ru.ifmo.ctddev.tkachenko.task2;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new ArrayIndexOutOfBoundsException("Incorrect number of files in command line");
        }
        String input = args[0], output = args[1];
        File inputFile = new File(input);
        File outputFile = new File(output);

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
        Matrix a, b, c;
        try {
            a = new Matrix(in);
            b = new Matrix(in);
            c = new Matrix(in);
            Matrix ans = a.multiply(a).add(b.multiply(c));
            ans.write(outputFile);
            in.close();
        } catch (IOException e) {
            try {
                in.close();
            } catch (IOException ee) {  /* Ignoring */ }
            throw e;
        }
    }
}
