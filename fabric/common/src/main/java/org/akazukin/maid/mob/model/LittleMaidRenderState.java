package org.akazukin.maid.mob.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class LittleMaidRenderState extends BipedEntityRenderState {
    String modelName;
    String armorModelName;
    String bodyModelName;

    public LittleMaidRenderState() {
        this.modelName = "default";
        this.armorModelName = null;
        this.bodyModelName = "mob_littlemaid_00";
    }
}
