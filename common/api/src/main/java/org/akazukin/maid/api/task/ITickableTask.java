package org.akazukin.maid.api.task;

public interface ITickableTask extends ITask {
    /**
     * Call this method to initialize the task
     */
    @Override
    void init();

    /**
     * Process the task every ticks
     */
    @Override
    void run();
}
