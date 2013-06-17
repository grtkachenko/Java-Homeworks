package ru.ifmo.ctddev.tkachenko.task6;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:01 PM
 */

/**
 * Main class
 */
public class Main {
    /**
     * @param args arguments that passed into the program
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new ArrayIndexOutOfBoundsException("Incorrect number of files in command line");
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        Tester tester = new Tester(n);
//        tester.testWithParticularNumberOfThreads(m);
        tester.testWithSeveralThreads();
    }
}
