package com.skd.playeranimationcore.mixin;

import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.util.ClientUtil;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class})
public class LivingEntityTickMixin {
   @Inject(
      method = {"tick"},
      at = {@At("TAIL")}
   )
   private void onTick(CallbackInfo ci) {
      if ((Object)this instanceof Avatar avatar && avatar.level().isClientSide()) {
         ((IAnimatedAvatar)avatar).playerAnimLib$getAnimManager().handleAnimations(0.0F, true, ClientUtil.shouldBeFirstPersonPass());
      }
   }
}
