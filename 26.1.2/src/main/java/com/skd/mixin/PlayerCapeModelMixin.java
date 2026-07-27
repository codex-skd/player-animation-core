package com.skd.playeranimationcore.mixin;

import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.accessors.IBoneUpdater;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.util.RenderUtil;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerCapeModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {PlayerCapeModel.class},
   priority = 2001
)
public class PlayerCapeModelMixin implements IBoneUpdater {
   @Shadow
   @Final
   private ModelPart cape;

   @Inject(
      method = {"setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V"},
      at = {@At("TAIL")}
   )
   private void setupAnim(AvatarRenderState avatarRenderState, CallbackInfo ci) {
      AvatarAnimManager emote = ((IAvatarAnimationState) avatarRenderState).playerAnimLib$getAnimManager();
      if (emote != null && emote.isActive()) {
         PlayerAnimBone bone = RenderUtil.copyVanillaPart(this.cape, new PlayerAnimBone("cape"));
         bone.rotation.x -= (float) Math.PI;
         bone.rotation.z -= (float) Math.PI;
         bone.rotation.x *= -1.0F;
         bone.rotation.y *= -1.0F;
         emote.get3DTransform(bone);
         bone.rotation.x *= -1.0F;
         bone.rotation.y *= -1.0F;
         bone.rotation.x += (float) Math.PI;
         bone.rotation.z += (float) Math.PI;
         this.pal$updatePart(emote, this.cape, bone);
      } else {
         this.pal$resetAll(emote);
      }
   }

   @Override
   public void pal$updatePart(AvatarAnimManager emote, ModelPart part, PlayerAnimBone bone) {
      RenderUtil.translatePartToCape(part, bone, part.getInitialPose());
   }

   @Override
   public void pal$resetAll(@Nullable AvatarAnimManager emote) {
   }
}
