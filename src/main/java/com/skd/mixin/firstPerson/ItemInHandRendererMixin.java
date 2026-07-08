package com.skd.playeranimationcore.mixin.firstPerson;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
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
         && animated.playerAnimLib$getAnimManager().isActive()
         && animated.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.THIRD_PERSON_MODEL) {
         ci.cancel();
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
         && player.playerAnimLib$getAnimManager().isActive()
         && entity == Minecraft.getInstance().getCameraEntity()
         && !Minecraft.getInstance().gameRenderer.getMainCamera().isDetached()
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
