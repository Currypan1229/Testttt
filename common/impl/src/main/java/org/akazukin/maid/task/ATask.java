package org.akazukin.maid.task;

import org.akazukin.maid.api.task.ITask;

public abstract class ATask implements ITask {
    long ticks;

    @Override
    public void init() {
        this.internalInit();
    }

    @Override
    public final void run() {
        this.ticks++;

        this.internalRun();
    }

    protected abstract void internalRun();

    protected abstract void internalInit();
}
