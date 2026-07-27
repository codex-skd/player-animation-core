package com.skd.playeranimationcore.api;

import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import com.skd.playeranimationcore.event.Event;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PlayerAnimationAccess {
   public static final Event<PlayerAnimationAccess.AnimationRegister> REGISTER_ANIMATION_EVENT = new Event<>(listeners -> (player, animationStack) -> {
         for (PlayerAnimationAccess.AnimationRegister listener : listeners) {
            listener.registerAnimation(player, animationStack);
         }
      });

   public static AvatarAnimManager getPlayerAnimManager(Avatar avatar) throws IllegalArgumentException {
      if (avatar instanceof IAnimatedAvatar animated) {
         return animated.playerAnimLib$getAnimManager();
      } else {
         throw new IllegalArgumentException(avatar + " is not a player or library mixins failed");
      }
   }

   @Nullable
   public static IAnimation getPlayerAnimationLayer(@NotNull Avatar avatar, @NotNull Identifier id) {
      if (avatar instanceof IAnimatedAvatar animated) {
         return animated.playerAnimLib$getAnimation(id);
      } else {
         throw new IllegalArgumentException(avatar + " is not a player or library mixins failed");
      }
   }

   @FunctionalInterface
   public interface AnimationRegister {
      void registerAnimation(@NotNull Avatar var1, @NotNull AvatarAnimManager var2);
   }
}
