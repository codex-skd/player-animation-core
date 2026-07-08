package com.skd.playeranimationcore.mixin.firstPerson;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({HumanoidArmorLayer.class})
public abstract class HumanoidArmorLayerMixin<T extends HumanoidRenderState, A extends HumanoidModel<T>> {
   @Inject(
      method = {"submit"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/layers/EquipmentLayerRenderer;renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;II)V"
      )},
      cancellable = true
   )
   private void modifyArmorVisibility(
      PoseStack poseStack,
      SubmitNodeCollector submitNodeCollector,
      int packedLight,
      HumanoidRenderState renderState,
      float f1,
      float f2,
      CallbackInfo ci,
      @Local(ordinal = 0) HumanoidModel<?> humanoidModel
   ) {
      if (renderState instanceof IAvatarAnimationState state && state.playerAnimLib$isFirstPersonPass()) {
         humanoidModel.root().visible = false;
         AvatarAnimManager emote = state.playerAnimLib$getAnimManager();
         if (emote.getFirstPersonConfiguration().isShowArmor()) {
            humanoidModel.head.visible = true;
            humanoidModel.hat.visible = true;
            humanoidModel.body.visible = true;
            humanoidModel.rightArm.visible = emote.getFirstPersonConfiguration().isShowRightArm();
            humanoidModel.leftArm.visible = emote.getFirstPersonConfiguration().isShowLeftArm();
            humanoidModel.rightLeg.visible = true;
            humanoidModel.leftLeg.visible = true;
         } else {
            ci.cancel();
         }
      }
   }
}
