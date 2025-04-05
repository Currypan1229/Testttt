package org.akazukin.maid.mob.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.ArmPosing;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class LittleMaidModel extends EntityModel<LittleMaidRenderState> implements ModelWithArms, ModelWithHead {
    public static final String MODEL_NAME_HEAD = EntityModelPartNames.HEAD;
    public static final String MODEL_NAME_HAT = EntityModelPartNames.HAT;
    public static final String MODEL_NAME_BODY = EntityModelPartNames.BODY;
    public static final String MODEL_NAME_JACKET = EntityModelPartNames.JACKET;
    public static final String MODEL_NAME_SKIRT = "skirt";
    public static final String MODEL_NAME_APRON = "apron";
    public static final String MODEL_NAME_LEFT_ARM = EntityModelPartNames.LEFT_ARM;
    public static final String MODEL_NAME_LEFT_SLEEVE = "left_sleeve";
    public static final String MODEL_NAME_RIGHT_ARM = EntityModelPartNames.RIGHT_ARM;
    public static final String MODEL_NAME_RIGHT_SLEEVE = "right_sleeve";
    public static final String MODEL_NAME_LEFT_LEG = EntityModelPartNames.LEFT_LEG;
    public static final String MODEL_NAME_LEFT_PANTS = "left_pants";
    public static final String MODEL_NAME_RIGHT_LEG = EntityModelPartNames.RIGHT_LEG;
    public static final String MODEL_NAME_RIGHT_PANTS = "right_pants";
    public static final String MODEL_NAME_BONE_USING_DRIPLEAF = "using_dripleaf_bone";
    public static final String MODEL_NAME_BONE_CHANGING_COSTUME = "changing_costume_bone";

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart skirt;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart boneUsingDripleaf;
    private final ModelPart boneChangingCostume;

    private final BipedEntityModel.ArmPose leftArmPose;
    private final BipedEntityModel.ArmPose rightArmPose;

    private final boolean isUsingDripleaf;
    public float leaningPitch;

    protected LittleMaidModel(final ModelPart root) {
        super(root, LittleMaidModel::getTexturedModelData);

        this.root = root;
        this.head = this.root.getChild(MODEL_NAME_HEAD);
        this.body = this.root.getChild(MODEL_NAME_BODY);
        this.skirt = this.root.getChild(MODEL_NAME_SKIRT);
        this.leftArm = this.root.getChild(MODEL_NAME_LEFT_ARM);
        this.rightArm = this.root.getChild(MODEL_NAME_RIGHT_ARM);
        this.leftLeg = this.root.getChild(MODEL_NAME_LEFT_LEG);
        this.rightLeg = this.root.getChild(MODEL_NAME_RIGHT_LEG);
        this.boneUsingDripleaf = this.root.getChild(MODEL_NAME_BONE_USING_DRIPLEAF);
        this.boneChangingCostume = this.root.getChild(MODEL_NAME_BONE_CHANGING_COSTUME);

        this.root.setDefaultTransform(ModelTransform.NONE);
        this.head.setDefaultTransform(ModelTransform.origin(0.0F, 8.0F, 0.0F));
        this.body.setDefaultTransform(ModelTransform.origin(0.0F, 8.0F, 0.0F));
        this.skirt.setDefaultTransform(ModelTransform.origin(0.0F, 14.0F, 0.0F));
        this.leftArm.setDefaultTransform(ModelTransform.origin(4.0F, 9.5F, 0.5F));
        this.rightArm.setDefaultTransform(ModelTransform.origin(-4.0F, 9.5F, 0.5F));
        this.leftLeg.setDefaultTransform(ModelTransform.origin(1.5F, 17.0F, 0.0F));
        this.rightLeg.setDefaultTransform(ModelTransform.origin(-1.5F, 17.0F, 0.0F));
        this.boneUsingDripleaf.setDefaultTransform(ModelTransform.NONE);
        this.boneChangingCostume.setDefaultTransform(ModelTransform.NONE);

        this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
        this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;

        this.isUsingDripleaf = false;
    }

    public static TexturedModelData getTexturedModelData() {
        final ModelData modelData = new ModelData();
        final ModelPartData root = modelData.getRoot();

        root.addChild(MODEL_NAME_HEAD, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(MODEL_NAME_HEAD, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                        .uv(0, 32).cuboid(MODEL_NAME_HAT, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)),
                ModelTransform.origin(0.0F, 8.0F, 0.0F));
        root.addChild(MODEL_NAME_BODY, ModelPartBuilder.create()
                        .uv(0, 16).cuboid(MODEL_NAME_BODY, -3.0F, 0.0F, -2.0F, 6.0F, 9.0F, 4.0F, new Dilation(0.0F))
                        .uv(0, 48).cuboid(MODEL_NAME_JACKET, -3.0F, 0.0F, -2.0F, 6.0F, 9.0F, 4.0F, new Dilation(0.25F)),
                ModelTransform.origin(0.0F, 8.0F, 0.0F));
        root.addChild(MODEL_NAME_SKIRT, ModelPartBuilder.create()
                        .uv(32, 0).cuboid(MODEL_NAME_SKIRT, -4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                        .uv(32, 33).cuboid(MODEL_NAME_APRON, -4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)),
                ModelTransform.origin(0.0F, 14.0F, 0.0F));
        root.addChild(MODEL_NAME_LEFT_ARM, ModelPartBuilder.create()
                        .uv(28, 16).cuboid(MODEL_NAME_LEFT_ARM, -1.0F, -1.5F, -1.5F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
                        .uv(28, 50).cuboid(MODEL_NAME_LEFT_SLEEVE, -1.0F, -1.5F, -1.5F, 2.0F, 9.0F, 2.0F, new Dilation(0.25F)),
                ModelTransform.origin(4.0F, 9.5F, 0.5F));
        root.addChild(MODEL_NAME_RIGHT_ARM, ModelPartBuilder.create()
                        .uv(20, 16).cuboid(MODEL_NAME_RIGHT_ARM, -1.0F, -1.5F, -1.5F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
                        .uv(20, 50).cuboid(MODEL_NAME_RIGHT_SLEEVE, -1.0F, -1.5F, -1.5F, 2.0F, 9.0F, 2.0F, new Dilation(0.25F)),
                ModelTransform.origin(-4.0F, 9.5F, 0.5F));
        root.addChild(MODEL_NAME_LEFT_LEG, ModelPartBuilder.create()
                        .uv(50, 16).cuboid(MODEL_NAME_LEFT_LEG, -1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F))
                        .uv(50, 50).cuboid(MODEL_NAME_LEFT_PANTS, -1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.25F)),
                ModelTransform.origin(1.5F, 17.0F, 0.0F));
        root.addChild(MODEL_NAME_RIGHT_LEG, ModelPartBuilder.create()
                        .uv(36, 16).cuboid(MODEL_NAME_RIGHT_LEG, -1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F))
                        .uv(36, 50).cuboid(MODEL_NAME_RIGHT_PANTS, -1.5F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.25F)),
                ModelTransform.origin(-1.5F, 17.0F, 0.0F));

        root.addChild(MODEL_NAME_BONE_USING_DRIPLEAF, ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild(MODEL_NAME_BONE_CHANGING_COSTUME, ModelPartBuilder.create(), ModelTransform.NONE);

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setArmAngle(final Arm arm, final MatrixStack matrices) {
        this.root.applyTransform(matrices);
        this.getArm(arm).applyTransform(matrices);
    }

    private ModelPart getArm(@NotNull final Arm arm) {
        return arm == Arm.LEFT ? this.leftArm : this.rightArm;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    protected void animate(final AnimationState animationState, final Animation animation, final float age, final float speedMultiplier) {
        super.animate(animationState, animation, age, speedMultiplier);
    }

    private void setArmAngleByState(final LittleMaidRenderState state) {
        final boolean mainHandIsRight = state.mainArm == Arm.RIGHT;
        if (state.isUsingItem) {
            final boolean activeHandIsMain = state.activeHand == Hand.MAIN_HAND;
            if (mainHandIsRight == activeHandIsMain) {
                this.setRightArmAngleByState(state);
            } else {
                this.setLeftArmAngleByState(state);
            }
        } else {
            final boolean isTwoHanded = mainHandIsRight ? this.leftArmPose.isTwoHanded() : this.rightArmPose.isTwoHanded();
            if (mainHandIsRight != isTwoHanded) {
                this.setLeftArmAngleByState(state);
                this.setRightArmAngleByState(state);
            } else {
                this.setRightArmAngleByState(state);
                this.setLeftArmAngleByState(state);
            }
        }
    }

    private void setRightArmAngleByState(final LittleMaidRenderState state) {
        switch (this.rightArmPose) {
            case BipedEntityModel.ArmPose.EMPTY -> this.rightArm.yaw = 0.0F;
            case BipedEntityModel.ArmPose.BLOCK -> {
                this.rightArm.pitch = this.rightArm.pitch * 0.5F - 0.9424779F;
                this.rightArm.yaw = -0.5235988F;
            }
            case BipedEntityModel.ArmPose.ITEM -> {
                this.rightArm.pitch = this.rightArm.pitch * 0.5F - 0.31415927F;
                this.rightArm.yaw = 0.0F;
            }
            case BipedEntityModel.ArmPose.THROW_SPEAR -> {
                this.rightArm.pitch = this.rightArm.pitch * 0.5F - (float) Math.PI;
                if (state.isSwimming) {
                    this.rightArm.pitch += (float) Math.toRadians(-90.0D);
                }
                this.rightArm.yaw = 0.0F;
                this.rightArm.roll = (float) Math.toRadians(-20.0F);
            }
            case BipedEntityModel.ArmPose.BOW_AND_ARROW -> {
                this.rightArm.yaw = -0.1F + this.head.yaw;
                this.leftArm.yaw = 0.1F + this.head.yaw + 0.4F;
                this.rightArm.pitch = -1.5707964F + this.head.pitch;
                this.leftArm.pitch = -1.5707964F + this.head.pitch;
            }
            case BipedEntityModel.ArmPose.CROSSBOW_CHARGE ->
                    ArmPosing.charge(this.rightArm, this.leftArm, state.crossbowPullTime, state.itemUseTime, state.mainArm == Arm.RIGHT);
            case BipedEntityModel.ArmPose.CROSSBOW_HOLD -> ArmPosing.hold(this.rightArm, this.leftArm, this.head, true);
            case BipedEntityModel.ArmPose.SPYGLASS -> {
                this.rightArm.pitch = MathHelper.clamp(this.head.pitch - 1.9198622F - (state.isInSneakingPose ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.rightArm.yaw = this.head.yaw - 0.2617994F;
            }
            case BipedEntityModel.ArmPose.TOOT_HORN -> {
                this.rightArm.pitch = MathHelper.clamp(this.head.pitch, -1.2F, 1.2F) - 1.4835298F;
                this.rightArm.yaw = this.head.yaw - 0.5235988F;
            }
        }
    }

    private void setLeftArmAngleByState(final LittleMaidRenderState state) {
        switch (this.leftArmPose) {
            case BipedEntityModel.ArmPose.EMPTY -> this.leftArm.yaw = 0.0F;
            case BipedEntityModel.ArmPose.BLOCK -> {
                this.leftArm.pitch = this.leftArm.pitch * 0.5F - 0.9424779F;
                this.leftArm.yaw = 0.5235988F;
            }
            case BipedEntityModel.ArmPose.ITEM -> {
                this.leftArm.pitch = this.leftArm.pitch * 0.5F - 0.31415927F;
                this.leftArm.yaw = 0.0F;
            }
            case BipedEntityModel.ArmPose.THROW_SPEAR -> {
                this.leftArm.pitch = this.leftArm.pitch * 0.5F - (float) Math.PI;
                if (state.isSwimming) {
                    this.rightArm.pitch += (float) Math.toRadians(-90.0D);
                }
                this.leftArm.yaw = 0.0F;
                this.leftArm.roll = (float) Math.toRadians(20.0F);
            }
            case BipedEntityModel.ArmPose.BOW_AND_ARROW -> {
                this.rightArm.yaw = -0.1F + this.head.yaw - 0.4F;
                this.leftArm.yaw = 0.1F + this.head.yaw;
                this.rightArm.pitch = -1.5707964F + this.head.pitch;
                this.leftArm.pitch = -1.5707964F + this.head.pitch;
            }
            case BipedEntityModel.ArmPose.CROSSBOW_CHARGE ->
                    ArmPosing.charge(this.rightArm, this.leftArm, state.crossbowPullTime, state.itemUseTime, state.mainArm == Arm.RIGHT);
            case BipedEntityModel.ArmPose.CROSSBOW_HOLD ->
                    ArmPosing.hold(this.rightArm, this.leftArm, this.head, false);
            case BipedEntityModel.ArmPose.SPYGLASS -> {
                this.leftArm.pitch = MathHelper.clamp(this.head.pitch - 1.9198622F - (state.isInSneakingPose ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.leftArm.yaw = this.head.yaw + 0.2617994F;
            }
            case BipedEntityModel.ArmPose.TOOT_HORN -> {
                this.leftArm.pitch = MathHelper.clamp(this.head.pitch, -1.2F, 1.2F) - 1.4835298F;
                this.leftArm.yaw = this.head.yaw + 0.5235988F;
            }
        }
    }
}
