package ru.ifmo.ctddev.tkachenko.task8;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:01 PM
 */

/**
 * Main class for task7
 */
public class Main {
    /**
     * Number of available {@link Client}
     */
    private final static int NUMBER_OF_CLIENTS = 10;
    /**
     * Number of available threads
     */
    private final static int NUMBER_OF_THREADS = 1;

    /**
     * @param args arguments that passed into the program
     */
    public static void main(String[] args) {
        TaskRunner taskRunner = new TaskRunnerImpl(NUMBER_OF_THREADS);

        Client[] clients = new Client[NUMBER_OF_CLIENTS];
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clients[i] = new Client("Client number " + i + " ", taskRunner);
            clients[i].evaluate();
        }

    }
}
