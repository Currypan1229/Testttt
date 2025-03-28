package org.akazukin.maid.mob;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NavigationController {
    EntityNavigation walkNavigation;
    EntityNavigation swimNavigation;

    public NavigationController(final MobEntity entity, final World world) {
        this.walkNavigation = new MobNavigation(entity, world);
        this.swimNavigation = new AmphibiousSwimNavigation(entity, world);
    }
}
