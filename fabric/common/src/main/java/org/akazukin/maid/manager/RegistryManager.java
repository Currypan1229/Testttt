package org.akazukin.maid.manager;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class RegistryManager {
    public static final RegistryKey<EntityType<?>> LITTLE_MAID = RegistryKey.of(RegistryKeys.ENTITY_TYPE, IdentifierManager.getId("little_maid"));
}
