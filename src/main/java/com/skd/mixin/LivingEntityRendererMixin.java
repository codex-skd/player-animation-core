package com.skd.playeranimationcore.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.util.RenderUtil;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntityRenderer.class})
public class LivingEntityRendererMixin<S extends LivingEntityRenderState> {
   @Inject(
      method = {"submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;scale(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;)V"
      )}
   )
   private void doTranslations(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo ci) {
      if (state instanceof IAvatarAnimationState avatarRenderState) {
         AvatarAnimManager animationPlayer = avatarRenderState.playerAnimLib$getAnimManager();
         if (animationPlayer != null && animationPlayer.isActive()) {
            avatarRenderState.playerAnimLib$getAnimManager()
               .handleAnimations(animationPlayer.getTickDelta(), false, avatarRenderState.playerAnimLib$isFirstPersonPass());
            poseStack.scale(-1.0F, -1.0F, 1.0F);
            PlayerAnimBone body = animationPlayer.get3DTransform("body");
            poseStack.translate((double)(-body.position.x / 16.0F), (double)(body.position.y / 16.0F) + 0.75, (double)(body.position.z / 16.0F));
            body.rotation.x *= -1.0F;
            body.rotation.y *= -1.0F;
            RenderUtil.rotateMatrixAroundBone(poseStack, body);
            poseStack.scale(body.scale.x, body.scale.y, body.scale.z);
            poseStack.translate(0.0, -0.75, 0.0);
            poseStack.scale(-1.0F, -1.0F, 1.0F);
         }
      }
   }
}
