package org.akazukin.maid.mob.model;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import org.akazukin.maid.manager.IdentifierManager;
import org.akazukin.maid.mob.LittleMaidEntity;

public class LittleMaidRenderer extends MobEntityRenderer<LittleMaidEntity, LittleMaidRenderState, LittleMaidModel> {
    public LittleMaidRenderer(final EntityRendererFactory.Context context) {
        super(context, new LittleMaidModel(context.getPart(EntityModelLayers.WITCH)), 0.3F);
        this.addFeature(new MaidArmorLayer(context, this));
    }

    @Override
    public Identifier getTexture(final LittleMaidRenderState state) {
        return IdentifierManager.getId("textures/entity/maid/" + state.modelName + "/body/" + state.bodyModelName + ".png");
    }

    @Override
    public LittleMaidRenderState createRenderState() {
        return null;
    }
}
