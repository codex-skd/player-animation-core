package com.skd.playeranimationcore.mixin;

import com.skd.playeranimationcore.accessors.IAvatarAnimationState;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({AvatarRenderState.class})
public class AvatarRenderStateMixin implements IAvatarAnimationState {
   @Unique
   boolean playerAnimLib$isFirstPersonPass = false;
   @Unique
   AvatarAnimManager playerAnimLib$avatarAnimManager = null;

   @Override
   public boolean playerAnimLib$isFirstPersonPass() {
      return this.playerAnimLib$isFirstPersonPass;
   }

   @Override
   public void playerAnimLib$setFirstPersonPass(boolean value) {
      this.playerAnimLib$isFirstPersonPass = value;
   }

   @Override
   public void playerAnimLib$setAnimManager(AvatarAnimManager manager) {
      this.playerAnimLib$avatarAnimManager = manager;
   }

   @NotNull
   @Override
   public AvatarAnimManager playerAnimLib$getAnimManager() {
      return this.playerAnimLib$avatarAnimManager;
   }
}
