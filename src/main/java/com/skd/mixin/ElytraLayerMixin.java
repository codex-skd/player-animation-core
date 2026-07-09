package com.skd.playeranimationcore.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.util.RenderUtil;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({WingsLayer.class})
public class ElytraLayerMixin {
   @Shadow
   private RenderLayerParent<?, ?> renderer;

   @Inject(
      method = {"submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/layers/EquipmentLayerRenderer;renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V"
      )}
   )
   private void inject(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, HumanoidRenderState humanoidRenderState, float f, float g, CallbackInfo ci) {
      if (humanoidRenderState instanceof IAvatarAnimationState animationState) {
         AvatarAnimManager emote = animationState.playerAnimLib$getAnimManager();
         if (emote != null && emote.isActive() && this.renderer instanceof AvatarRenderer<?> playerRenderer) {
            ((PlayerModel)playerRenderer.getModel()).body.translateAndRotate(poseStack);
            poseStack.translate(0.0, 0.0, 0.125);
            PlayerAnimBone bone = emote.get3DTransform("elytra");
            bone.applyOtherBone(emote.get3DTransform("cape"));
            bone.position.y *= -1.0F;
            RenderUtil.translateMatrixToBone(poseStack, bone);
            poseStack.translate(0.0, 0.0, -0.125);
         }
      }
   }
}
