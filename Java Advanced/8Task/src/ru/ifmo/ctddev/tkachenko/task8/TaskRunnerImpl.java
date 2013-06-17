package ru.ifmo.ctddev.tkachenko.task8;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 22.05.13
 * Time: 3:23
 */

/**
 * Implementation of {@link TaskRunner} interface
 */
public class TaskRunnerImpl implements TaskRunner {
    /**
     * Helper for execution of tasks
     */
    private ExecutorService executor;

    /**
     * Constructor from number of available threads
     *
     * @param numberOfThreads number of available threads
     */
    public TaskRunnerImpl(int numberOfThreads) {
        executor = Executors.newFixedThreadPool(numberOfThreads);
    }

    /**
     * Method that will run the task with particular input value.
     * It uses {@link LinkedBlockingDeque} for evaluating
     *
     * @param task  task that needs to be evaluated
     * @param value input value
     * @param <X>   result type
     * @param <Y>   input type
     * @return result of evaluating
     */
    @Override
    public <X, Y> X run(final Task<X, Y> task, final Y value) {
        X result = null;
        try {
            result = executor.submit(new Callable<X>() {
                @Override
                public X call() throws Exception {
                    return task.run(value);
                }
            }).get();
        } catch (InterruptedException e) {
            System.out.println("The current thread was interrupted while waiting");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("The computation threw an exception");
            e.printStackTrace();
        }

        return result;
    }

}
