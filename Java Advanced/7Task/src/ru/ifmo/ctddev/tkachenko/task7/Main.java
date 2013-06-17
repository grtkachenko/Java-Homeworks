package ru.ifmo.ctddev.tkachenko.task7;

import java.util.Random;

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
     * Number of available {@link TaskRunner}
     */
    private final static int NUMBER_OF_RUNNERS = 1;
    /**
     * Number of available threads
     */
    private final static int NUMBER_OF_THREADS = 1;

    /**
     * @param args arguments that passed into the program
     */
    public static void main(String[] args) {
        Random random = new Random();
        TaskRunner[] taskRunners = new TaskRunnerImpl[NUMBER_OF_RUNNERS];
        for (int i = 0; i < NUMBER_OF_RUNNERS; i++) {
            taskRunners[i] = new TaskRunnerImpl(NUMBER_OF_THREADS);
        }

        Client[] clients = new Client[NUMBER_OF_CLIENTS];
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            int index = random.nextInt(NUMBER_OF_RUNNERS);
            clients[i] = new Client("Client number " + i + " ", taskRunners[index]);
            clients[i].evaluate();
        }

    }
}
