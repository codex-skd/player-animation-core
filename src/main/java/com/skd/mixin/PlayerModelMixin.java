package com.skd.playeranimationcore.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.accessors.IBoneUpdater;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.util.RenderUtil;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import java.util.function.Function;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {PlayerModel.class},
   priority = 2001
)
public class PlayerModelMixin extends HumanoidModel<AvatarRenderState> implements IBoneUpdater {
   @Unique
   private final PlayerAnimBone pal$head = new PlayerAnimBone("head");
   @Unique
   private final PlayerAnimBone pal$torso = new PlayerAnimBone("torso");
   @Unique
   private final PlayerAnimBone pal$rightArm = new PlayerAnimBone("right_arm");
   @Unique
   private final PlayerAnimBone pal$leftArm = new PlayerAnimBone("left_arm");
   @Unique
   private final PlayerAnimBone pal$rightLeg = new PlayerAnimBone("right_leg");
   @Unique
   private final PlayerAnimBone pal$leftLeg = new PlayerAnimBone("left_leg");

   public PlayerModelMixin(ModelPart root, Function<Identifier, RenderType> renderType) {
      super(root, renderType);
   }

   @Inject(
      method = {"setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V"},
      at = {@At("HEAD")}
   )
   private void setDefaultBeforeRender(AvatarRenderState avatarRenderState, CallbackInfo ci) {
      this.head.visible = true;
   }

   @Inject(
      method = {"setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V"},
      at = {@At("RETURN")}
   )
   private void setupPlayerAnimation(AvatarRenderState avatarRenderState, CallbackInfo ci) {
      if (avatarRenderState instanceof IAvatarAnimationState animState && animState.playerAnimLib$getAnimManager() != null) {
         AvatarAnimManager emote = animState.playerAnimLib$getAnimManager();
         if (emote.isActive()) {
            this.pal$updatePart(emote, this.head, this.pal$head);
            this.pal$updatePart(emote, this.rightArm, this.pal$rightArm);
            this.pal$updatePart(emote, this.leftArm, this.pal$leftArm);
            this.pal$updatePart(emote, this.rightLeg, this.pal$rightLeg);
            this.pal$updatePart(emote, this.leftLeg, this.pal$leftLeg);
            this.pal$updatePart(emote, this.body, this.pal$torso);
         } else {
            this.pal$resetAll(emote);
         }

         if (animState.playerAnimLib$isFirstPersonPass()) {
            this.head.visible = false;
            this.body.visible = false;
            this.leftLeg.visible = false;
            this.rightLeg.visible = false;
            FirstPersonConfiguration config = emote.getFirstPersonConfiguration();
            this.rightArm.visible = config.isShowRightArm();
            this.leftArm.visible = config.isShowLeftArm();
         }
      } else {
         this.pal$resetAll(null);
      }
   }

   @Override
   public void pal$updatePart(AvatarAnimManager emote, ModelPart part, PlayerAnimBone bone) {
      RenderUtil.copyVanillaPart(part, bone);
      emote.updatePart(part, bone);
   }

   @WrapWithCondition(
      method = {"translateToHand(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/model/geom/ModelPart;translateAndRotate(Lcom/mojang/blaze3d/vertex/PoseStack;)V"
      )}
   )
   private boolean translateToHand(ModelPart modelPart, PoseStack poseStack, @Local(argsOnly = true) AvatarRenderState avatarRenderState) {
      if (avatarRenderState instanceof IAvatarAnimationState animState
         && animState.playerAnimLib$getAnimManager() != null
         && animState.playerAnimLib$getAnimManager().isActive()) {
         poseStack.translate(modelPart.x / 16.0F, modelPart.y / 16.0F, modelPart.z / 16.0F);
         if (modelPart.xRot != 0.0F || modelPart.yRot != 0.0F || modelPart.zRot != 0.0F) {
            RenderUtil.rotateZYX(poseStack.last(), modelPart.zRot, modelPart.yRot, modelPart.xRot);
         }

         poseStack.translate(0.0, (double)(modelPart.yScale - 1.0F) * 0.609375, (double)(modelPart.zScale - 1.0F) * 0.0625);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public void pal$resetAll(@Nullable AvatarAnimManager emote) {
   }
}
