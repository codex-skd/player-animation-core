package com.skd.playeranimationcore.mixin.firstPerson;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import com.skd.playeranimationcore.util.ClientUtil;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LevelRenderer.class})
public class LevelRendererMixin {
   @ModifyExpressionValue(
      method = {"extractVisibleEntities"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/Camera;isDetached()Z"
      )}
   )
    private boolean fakeThirdPersonMode(boolean original, @Local(argsOnly = true) Camera camera, @Share("firstPerson") LocalBooleanRef isFirstPerson) {
       if (ClientUtil.shouldBeFirstPersonPass(camera)
          && camera.entity() instanceof IAnimatedAvatar player
          && player.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.THIRD_PERSON_MODEL) {
          isFirstPerson.set(true);
          return true;
       } else {
          return original;
       }
    }

   @Inject(
      method = {"extractVisibleEntities"},
      at = {@At(
         value = "INVOKE",
         target = "Ljava/util/List;add(Ljava/lang/Object;)Z"
      )}
   )
   private void setRenderStateToFirstPerson(
      Camera camera,
      Frustum frustum,
      DeltaTracker deltaTracker,
      LevelRenderState renderState,
      CallbackInfo ci,
      @Local Entity entity,
      @Local EntityRenderState entityRenderState,
      @Share("firstPerson") LocalBooleanRef isFirstPerson
   ) {
      if (entity == camera.entity() && isFirstPerson.get()) {
         ((IAvatarAnimationState)entityRenderState).playerAnimLib$setFirstPersonPass(true);
         entityRenderState.shadowPieces.clear();
         entityRenderState.shadowRadius = 0.0F;
      }
   }
}
