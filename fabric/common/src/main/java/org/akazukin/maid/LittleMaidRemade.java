package org.akazukin.maid;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.ModInitializer;

@Slf4j
public final class LittleMaidRemade implements ModInitializer {
    public static final String MOD_ID = "little_maid_remade";

    @Override
    public void onInitialize() {
        log.info("Little Maid Remade initializing...");

        log.info("Little Maid Remade initialized");
    }
}
