package ru.ifmo.ctddev.tkachenko.task7;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 22.05.13
 * Time: 3:23
 */


/**
 * This class generates {@link Task} and runs it with {@link TaskRunner}
 */
public class Client {
    /**
     * Max value for testing
     */
    private final static int MAX_VALUE = (int) 1e7;
    /**
     * Object of class that evaluates result
     */
    private TaskRunner taskRunner;
    /**
     * Random helper for {@link ru.ifmo.ctddev.tkachenko.task7.Client#evaluate()}
     */
    private Random random = new Random();
    /**
     * Client name for convenient displaying
     */
    private String clientName;

    /**
     * Constructor from client's name and {@link TaskRunner} object
     *
     * @param name       client's name
     * @param taskRunner runner for tasks
     */
    public Client(String name, TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
        this.clientName = name;
    }

    /**
     * Method that starts generating tasks
     *
     * @see {@link ru.ifmo.ctddev.tkachenko.task7.Client#generateTask()}
     */
    public void evaluate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Task<Integer, Integer> task = generateTask();
                    int input = random.nextInt() % MAX_VALUE;
                    System.out.println(clientName + " " + taskRunner.run(task, input));
                }
            }
        }).start();
    }

    /**
     * Random task generator
     *
     * @return requested task
     */
    private Task<Integer, Integer> generateTask() {
        return new Task<Integer, Integer>() {
            @Override
            public Integer run(Integer value) {
                int sum = 0;
                for (int i  = 0; i < value; i++) {
                    sum += i;
                }
                return sum;
            }
        };
    }
}
