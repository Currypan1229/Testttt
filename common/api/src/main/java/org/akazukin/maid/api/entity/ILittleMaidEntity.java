package org.akazukin.maid.api.entity;

import org.akazukin.maid.api.task.ITask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface ILittleMaidEntity {
    boolean isLoaded();

    @Nullable
    ITask[] getRunningTasks();

    void addOwner(UUID owner);

    void removeOwner(UUID owner);

    boolean isHired();

    @NotNull
    UUID[] getOwners();

    boolean isOwner(UUID player);
}
