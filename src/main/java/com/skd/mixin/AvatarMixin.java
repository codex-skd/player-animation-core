package com.skd.playeranimationcore.mixin;

import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.api.PlayerAnimationAccess;
import com.skd.playeranimationcore.api.PlayerAnimationFactory;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

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
      if (!((Object)this instanceof Avatar self)) return null;
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
}
