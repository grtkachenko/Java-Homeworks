package ru.ifmo.ctddev.tkachenko.task7;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 22.05.13
 * Time: 3:20
 */

/**
 * Interface of task. We can run the the task with
 * particular input value and get a result of evaluating.
 *
 * @param <X> result type
 * @param <Y> argument type
 */
public interface Task<X, Y> {
    /**
     * Method that calculates the task.
     *
     * @param value input argument
     * @return evaluated result
     */
    X run(Y value);
}
