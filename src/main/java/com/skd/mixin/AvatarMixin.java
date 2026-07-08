package com.skd.playeranimationcore.mixin;

import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.api.PlayerAnimationAccess;
import com.skd.playeranimationcore.api.PlayerAnimationFactory;
import com.skd.playeranimationcore.util.ClientUtil;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Avatar.class})
public abstract class AvatarMixin extends LivingEntity implements IAnimatedAvatar {
   @Unique
   private final Map<Identifier, IAnimation> playerAnimLib$modAnimationData = new HashMap<>();
   @Unique
   private final AvatarAnimManager playerAnimLib$animationManager = this.playerAnimLib$createAnimationStack();

   protected AvatarMixin(EntityType<? extends LivingEntity> entityType, Level level) {
      super(entityType, level);
   }

   @Unique
   @SuppressWarnings("unchecked")
   private AvatarAnimManager playerAnimLib$createAnimationStack() {
      Avatar self = (Avatar)(Object)this;
      AvatarAnimManager manager = new AvatarAnimManager(self);
      PlayerAnimationFactory.ANIMATION_DATA_FACTORY.prepareAnimations(self, manager, this.playerAnimLib$modAnimationData);
      PlayerAnimationAccess.REGISTER_ANIMATION_EVENT.invoker().registerAnimation(self, manager);
      return manager;
   }

   @Override
   public AvatarAnimManager playerAnimLib$getAnimManager() {
      return this.playerAnimLib$animationManager;
   }

   @Override
   public IAnimation playerAnimLib$getAnimation(Identifier id) {
      return this.playerAnimLib$modAnimationData.containsKey(id) ? this.playerAnimLib$modAnimationData.get(id) : null;
   }

   @Intrinsic
   public void tick() {
      super.tick();
   }

   @Inject(
      method = {"tick", "method_5773"},
      at = {@At("TAIL")},
      remap = false
   )
   private void tick(CallbackInfo ci) {
      if (this.level().isClientSide()) {
         this.playerAnimLib$animationManager.handleAnimations(0.0F, true, ClientUtil.shouldBeFirstPersonPass());
      }
   }
}
