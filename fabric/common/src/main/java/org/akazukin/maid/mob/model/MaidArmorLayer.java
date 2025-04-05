package org.akazukin.maid.mob.model;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

public class MaidArmorLayer extends FeatureRenderer<LittleMaidRenderState, LittleMaidModel> {
    public MaidArmorLayer(final FeatureRendererContext<LittleMaidRenderState, LittleMaidModel> context, final LittleMaidRenderer renderer) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LittleMaidRenderState state, float limbAngle, float limbDistance) {

    }
}
