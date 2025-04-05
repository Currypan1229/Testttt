package org.akazukin.maid.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.akazukin.maid.api.entity.ILittleMaidEntity;
import org.akazukin.maid.api.task.ITask;
import org.akazukin.maid.manager.RegistryManager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LittleMaidEntity extends PathAwareEntity implements ILittleMaidEntity {
    public static final UUID[] EMPTY_UUID = new UUID[0];
    private static final EntityType<LittleMaidEntity> LITTLE_MAID;

    static {
        LITTLE_MAID = EntityType.Builder
                .create(LittleMaidEntity::new, SpawnGroup.CREATURE)
                .dimensions(0.6F, 1.5F)
                .eyeHeight(1.25F)
                .maxTrackingRange(8)
                .build(RegistryManager.LITTLE_MAID);
    }

    SimpleInventory inventory;
    Set<UUID> owners = new HashSet<>();
    ITask[] tasks;

    protected LittleMaidEntity(final EntityType<? extends PathAwareEntity> entityType, final World world) {
        super(entityType, world);

        for (final EquipmentSlot slot : EquipmentSlot.values()) {
            this.createEquipmentInventory(slot);
        }
        this.inventory = new SimpleInventory(9 * 2);
    }

    public static boolean canSpawn(final EntityType<LittleMaidEntity> ignoredType, final ServerWorldAccess world, final SpawnReason ignoredReason, final BlockPos pos, final Random ignoredRandom) {
        return (world.getBlockState(pos.down()).isIn(BlockTags.RABBITS_SPAWNABLE_ON)
                || world.getBlockState(pos.down()).isIn(BlockTags.WOLVES_SPAWNABLE_ON))

                && world.getBaseLightLevel(pos, 0) >= 10;
    }

    @Override
    protected EntityNavigation createNavigation(final World world) {
        final AmphibiousSwimNavigation navigation = new AmphibiousSwimNavigation(this, world);
        //navigation.getNodeMaker().setCanEnterOpenDoors(true);
        navigation.getNodeMaker().setCanOpenDoors(true);
        return navigation;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public ITask[] getRunningTasks() {
        return this.tasks;
    }

    @Override
    public void addOwner(final UUID owner) {
        this.owners.add(owner);
    }

    @Override
    public void removeOwner(final UUID owner) {
        this.owners.remove(owner);
    }

    @Override
    public boolean isHired() {
        return !this.owners.isEmpty();
    }

    @Override
    public UUID[] getOwners() {
        return this.owners.isEmpty() ? EMPTY_UUID : this.owners.toArray(EMPTY_UUID);
    }

    @Override
    public boolean isOwner(final UUID player) {
        return this.owners.contains(player);
    }

    @Override
    public void onDamaged(final DamageSource damageSource) {
        super.clearGoalsAndTasks();
        if (damageSource.getAttacker() != null) {
            if (!this.isHired()) {
                if (this.brain != null) {
                }
            }
        }
        super.onDamaged(damageSource);
    }
}
