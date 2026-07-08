package com.skd.playeranimationcore.animation;

import com.skd.playeranimationcore.PlayerAnimCoreMod;
import com.skd.playeranimationcore.PlayerAnimCore;
import com.skd.playeranimationcore.animation.Animation;
import com.skd.playeranimationcore.loading.UniversalAnimLoader;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.server.packs.resources.PreparableReloadListener.PreparationBarrier;
import net.minecraft.server.packs.resources.PreparableReloadListener.SharedState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerAnimResources implements ResourceManagerReloadListener {
   public static final Identifier KEY = PlayerAnimCoreMod.id("animation");
   private static final Map<Identifier, Animation> ANIMATIONS = new Object2ObjectOpenHashMap();

   @Nullable
   public static Animation getAnimation(Identifier id) {
      return ANIMATIONS.get(id);
   }

   @NotNull
   public static Optional<Animation> getAnimationOptional(@NotNull Identifier identifier) {
      return Optional.ofNullable(getAnimation(identifier));
   }

   public static Map<Identifier, Animation> getAnimations() {
      return Collections.unmodifiableMap(ANIMATIONS);
   }

   @NotNull
   public static Map<String, Animation> getModAnimations(@NotNull String modid) {
      HashMap<String, Animation> map = new HashMap<>();

      for (Entry<Identifier, Animation> entry : ANIMATIONS.entrySet()) {
         if (entry.getKey().getNamespace().equals(modid)) {
            map.put(entry.getKey().getPath(), entry.getValue());
         }
      }

      return map;
   }

   public static boolean hasAnimation(Identifier id) {
      return ANIMATIONS.containsKey(id);
   }

   public void onResourceManagerReload(ResourceManager manager) {
      ANIMATIONS.clear();

      for (Entry<Identifier, Resource> resource : manager.listResources("player_animations", resourceLocation -> resourceLocation.getPath().endsWith(".json"))
         .entrySet()) {
         String namespace = resource.getKey().getNamespace();

         try (InputStream is = resource.getValue().open()) {
            for (Entry<String, Animation> entry : UniversalAnimLoader.loadAnimations(is).entrySet()) {
               ANIMATIONS.put(Identifier.fromNamespaceAndPath(namespace, entry.getKey()), entry.getValue());
            }
         } catch (Exception var10) {
            PlayerAnimCore.LOGGER.error("Player Animation Core failed to load animation {} because:", resource.getKey(), var10);
         }
      }
   }

   @NotNull
   public CompletableFuture<Void> reload(SharedState sharedState, Executor backgroundExecutor, PreparationBarrier barrier, Executor gameExecutor) {
      ResourceManager manager = sharedState.resourceManager();
      return CompletableFuture.runAsync(() -> this.onResourceManagerReload(manager), backgroundExecutor).thenCompose(barrier::wait);
   }
}
