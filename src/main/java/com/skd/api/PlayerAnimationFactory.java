package com.skd.playeranimationcore.api;

import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface PlayerAnimationFactory {
   PlayerAnimationFactory.FactoryHolder ANIMATION_DATA_FACTORY = new PlayerAnimationFactory.FactoryHolder();

   @Nullable
   IAnimation invoke(@NotNull Avatar var1);

   public static class FactoryHolder {
      private static final List<Function<Avatar, PlayerAnimationFactory.FactoryHolder.DataHolder>> factories = new ArrayList<>();

      private FactoryHolder() {
      }

      public void registerFactory(@Nullable Identifier id, int priority, @NotNull PlayerAnimationFactory factory) {
         factories.add(
            player -> Optional.ofNullable(factory.invoke(player))
                  .map(animation -> new PlayerAnimationFactory.FactoryHolder.DataHolder(id, priority, animation))
                  .orElse(null)
         );
      }

      @Internal
      public void prepareAnimations(Avatar player, AvatarAnimManager playerStack, Map<Identifier, IAnimation> animationMap) {
         for (Function<Avatar, PlayerAnimationFactory.FactoryHolder.DataHolder> factory : factories) {
            PlayerAnimationFactory.FactoryHolder.DataHolder dataHolder = factory.apply(player);
            if (dataHolder != null) {
               playerStack.addAnimLayer(dataHolder.priority(), dataHolder.animation());
               if (dataHolder.id() != null) {
                  animationMap.put(dataHolder.id(), dataHolder.animation());
               }
            }
         }
      }

      @Internal
      private static record DataHolder(@Nullable Identifier id, int priority, @NotNull IAnimation animation) {
      }
   }
}
