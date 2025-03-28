package org.akazukin.maid.api.task;

import java.io.Closeable;

public interface ITask extends Closeable {
    /**
     * Call this method to initialize the task
     */
    void init();

    /**
     * Process the task every ticks
     *
     * @return true if the task is runnable
     */
    boolean isRunnable();

    /**
     * Process the task
     */
    void run();

    /**
     * Check be able to close the task.
     * the case when return true, the task has completed, or cancelled.
     */
    void isClosable();
}
