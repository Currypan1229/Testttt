package org.akazukin.maid.manager;

import net.minecraft.util.Identifier;
import org.akazukin.maid.LittleMaidRemade;

public class IdentifierManager {
    

    public static Identifier getId(final String path) {
        return Identifier.of(LittleMaidRemade.MOD_ID, path);
    }
}
