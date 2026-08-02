package com.skd.playeranimationcore.mixin.firstPerson;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemInHandRenderer.class})
public class ItemInHandRendererMixin {
   @Inject(
      method = {"renderHandsWithItems"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void disableDefaultItemIfNeeded(
      float f, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, LocalPlayer localPlayer, int i, CallbackInfo ci
   ) {
      if (localPlayer instanceof IAnimatedAvatar animated
         && animated.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.THIRD_PERSON_MODEL) {
         ci.cancel();
      }
   }

   @Unique
   private final PlayerAnimBone pal$rightItem = new PlayerAnimBone("right_item");
   @Unique
   private final PlayerAnimBone pal$leftItem = new PlayerAnimBone("left_item");
   @Unique
   private final PlayerAnimBone pal$rightArm = new PlayerAnimBone("right_arm");
   @Unique
   private final PlayerAnimBone pal$leftArm = new PlayerAnimBone("left_arm");

   @Inject(
      method = {"renderItem"},
      at = {@At("HEAD")}
   )
   private void applyBoneTransforms(
      LivingEntity entity, ItemStack itemStack, ItemDisplayContext transformType,
      PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, CallbackInfo ci
   ) {
      if (entity == Minecraft.getInstance().getCameraEntity()
         && entity instanceof IAnimatedAvatar animated
         && !Minecraft.getInstance().gameRenderer.mainCamera().isDetached()
         && animated.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.HANDS_ONLY
         && animated.playerAnimLib$getAnimManager().isActive()) {
         AvatarAnimManager anim = animated.playerAnimLib$getAnimManager();
         PlayerAnimBone bone = transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
            ? this.pal$leftItem : this.pal$rightItem;
         bone.setToInitialPose();
         anim.get3DTransform(bone);
         poseStack.translate(bone.position.x / 16.0F, -bone.position.y / 16.0F, bone.position.z / 16.0F);
         if (bone.rotation.z != 0.0F) {
            poseStack.mulPose(Axis.ZP.rotation(-bone.rotation.y));
         }
         if (bone.rotation.y != 0.0F) {
            poseStack.mulPose(Axis.YP.rotation(-bone.rotation.z));
         }
         if (bone.rotation.x != 0.0F) {
            poseStack.mulPose(Axis.XP.rotation(-bone.rotation.x));
         }
         poseStack.scale(bone.scale.x, bone.scale.y, bone.scale.z);
      }
   }

   @Inject(
      method = {"renderItem"},
      at = {@At("HEAD")}
   )
   private void applyArmBoneTransforms(
      LivingEntity entity, ItemStack itemStack, ItemDisplayContext transformType,
      PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, CallbackInfo ci
   ) {
      if (entity == Minecraft.getInstance().getCameraEntity()
         && entity instanceof IAnimatedAvatar animated
         && !Minecraft.getInstance().gameRenderer.mainCamera().isDetached()
         && animated.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.HANDS_ONLY_ARM
         && animated.playerAnimLib$getAnimManager().isActive()) {
          AvatarAnimManager anim = animated.playerAnimLib$getAnimManager();
          var config = anim.getFirstPersonConfiguration();
          float scale = config.getArmRotationScale();
          float armLength = config.getArmLength();
          float pitchFactor = config.getPitchFactor();
          PlayerAnimBone bone = transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
             ? this.pal$leftArm : this.pal$rightArm;
          bone.setToInitialPose();
          anim.get3DTransform(bone);
          float handX = (float)(Math.sin(bone.rotation.y) * armLength) / 16.0F;
          float handY = (float)(Math.sin(bone.rotation.x) * armLength * pitchFactor) / 16.0F;
          poseStack.translate(handX, -handY, 0.0F);
          if (bone.rotation.z != 0.0F) {
            poseStack.mulPose(Axis.ZP.rotation(-bone.rotation.y * scale));
         }
         if (bone.rotation.y != 0.0F) {
            poseStack.mulPose(Axis.YP.rotation(-bone.rotation.z * scale));
         }
         if (bone.rotation.x != 0.0F) {
            poseStack.mulPose(Axis.XP.rotation(-bone.rotation.x * scale));
         }
      }
   }

   @Inject(
      method = {"renderItem"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState;submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V"
      )},
      cancellable = true
   )
   private void cancelItemRender(
      LivingEntity entity,
      ItemStack itemStack,
      ItemDisplayContext transformType,
      PoseStack poseStack,
      SubmitNodeCollector submitNodeCollector,
      int i,
      CallbackInfo ci
   ) {
      if (entity instanceof IAnimatedAvatar player
         && entity == Minecraft.getInstance().getCameraEntity()
         && !Minecraft.getInstance().gameRenderer.mainCamera().isDetached()
         && player.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.THIRD_PERSON_MODEL) {
         FirstPersonConfiguration config = player.playerAnimLib$getAnimManager().getFirstPersonConfiguration();
         if (transformType != ItemDisplayContext.FIRST_PERSON_RIGHT_HAND && transformType != ItemDisplayContext.THIRD_PERSON_RIGHT_HAND) {
            if (!config.isShowLeftItem()) {
               ci.cancel();
            }
         } else if (!config.isShowRightItem()) {
            ci.cancel();
         }
      }
   }
}
