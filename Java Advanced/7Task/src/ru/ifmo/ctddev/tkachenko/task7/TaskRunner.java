package ru.ifmo.ctddev.tkachenko.task7;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 22.05.13
 * Time: 3:20
 */

/**
 * Interface of runner that will run the tasks
 */
public interface TaskRunner {
    /**
     * Method that will run the task with particular input value
     *
     * @param task  task that needs to be evaluated
     * @param value input value
     * @param <X>   result type
     * @param <Y>   input type
     * @return result of evaluating
     */
    <X, Y> X run(Task<X, Y> task, Y value);
}
