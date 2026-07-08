package com.skd.playeranimationcore.mixin;

import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {AvatarRenderer.class},
   priority = 2000
)
public abstract class AvatarRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, AvatarRenderState, PlayerModel> {
   public AvatarRendererMixin(Context context, PlayerModel model, float shadowRadius) {
      super(context, model, shadowRadius);
   }

   @Inject(
      method = {"extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V"},
      at = {@At("HEAD")}
   )
   private void modifyRenderState(Avatar avatar, AvatarRenderState avatarRenderState, float f, CallbackInfo ci) {
      if (avatar instanceof IAnimatedAvatar animated) {
         AvatarAnimManager animation = animated.playerAnimLib$getAnimManager();
         animation.setTickDelta(f);
         ((IAvatarAnimationState) avatarRenderState).playerAnimLib$setAnimManager(animation);
      }
   }
}
