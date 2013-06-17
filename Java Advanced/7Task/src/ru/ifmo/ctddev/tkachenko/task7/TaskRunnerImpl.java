package ru.ifmo.ctddev.tkachenko.task7;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

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
     * Default size for {@link TaskRunnerImpl#queueItems}
     */
    private final static int DEFAULT_QUEUE_SIZE = 100;

    /**
     * Blocking queue for tasks that are waiting to be evaluated
     */
    private BlockingQueue<QueueItem<?, ?>> queueItems = new LinkedBlockingQueue<QueueItem<?, ?>>(DEFAULT_QUEUE_SIZE);

    /**
     * Constructor from number of available threads
     *
     * @param numberOfThreads number of available threads
     */
    public TaskRunnerImpl(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Worker()).start();
        }
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
    public <X, Y> X run(Task<X, Y> task, Y value) {
        QueueItem<X, Y> queueItem = new QueueItem<X, Y>(task, value);
        try {
            queueItems.put(queueItem);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        synchronized (queueItem) {
            try {
                queueItem.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }

        return queueItem.getResult();
    }

    /**
     * Class that represents a queue item
     *
     * @param <X> result type
     * @param <Y> input argument type
     */
    private class QueueItem<X, Y> {
        /**
         * Task that needs to be evaluated
         */
        private Task<X, Y> task;
        /**
         * Task's result
         */
        private X result;
        /**
         * Input value
         */
        private Y value;

        /**
         * Constructor from task and input value
         *
         * @param task  input task
         * @param value input value
         */
        private QueueItem(Task<X, Y> task, Y value) {
            this.task = task;
            this.value = value;
        }

        /**
         * Method that runs task
         */
        public synchronized void run() {
            result = task.run(value);
            notifyAll();
        }

        /**
         * Get the result of task
         *
         * @return result value
         */
        private X getResult() {
            return result;
        }
    }

    /**
     * Class corresponding to a particular thread that calculates tasks
     */
    private class Worker implements Runnable {
        /**
         * Calculating tasks
         *
         * @see LinkedBlockingDeque
         */
        @Override
        public void run() {
            while (true) {
                QueueItem<?, ?> queueItem = null;
                try {
                    queueItem = queueItems.take();
                    queueItem.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                } finally {
                    synchronized (queueItem) {
                        queueItem.notifyAll();
                    }
                }

            }
        }
    }
}
