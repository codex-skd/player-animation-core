package com.skd.playeranimationcore.mixin.firstPerson;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemInHandLayer.class})
public class ItemInHandLayerMixin<S extends ArmedEntityRenderState> {
   @Inject(
      method = {"submitArmWithItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void dontRenderDisabledFirstPersonItem(
      S renderState,
      ItemStackRenderState itemStackRenderState,
      ItemStack itemStack,
      HumanoidArm arm,
      PoseStack poseStack,
      SubmitNodeCollector submitNodeCollector,
      int i,
      CallbackInfo ci
   ) {
      if (renderState instanceof IAvatarAnimationState state
         && state.playerAnimLib$isFirstPersonPass()
         && state.playerAnimLib$getAnimManager() != null
         && state.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.THIRD_PERSON_MODEL) {
         FirstPersonConfiguration config = state.playerAnimLib$getAnimManager().getFirstPersonConfiguration();
         if (!config.isShowLeftItem() && arm == HumanoidArm.LEFT || !config.isShowRightItem() && arm == HumanoidArm.RIGHT) {
            ci.cancel();
         }
      }
   }
}
