package com.skd.playeranimationcore.util;

import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.ApiStatus.Internal;

public final class ClientUtil {
   public static LocalPlayer getClientPlayer() {
      return Minecraft.getInstance().player;
   }

   public static Level getLevel() {
      return Minecraft.getInstance().level;
   }

   @Internal
   public static boolean shouldBeFirstPersonPass() {
      return shouldBeFirstPersonPass(Minecraft.getInstance().gameRenderer.mainCamera());
   }

   @Internal
   public static boolean shouldBeFirstPersonPass(Camera camera) {
      return !camera.isDetached()
         && camera.entity() instanceof IAnimatedAvatar player
        && player.playerAnimLib$getAnimManager().getFirstPersonMode() == FirstPersonMode.THIRD_PERSON_MODEL
         && (!(camera.entity() instanceof LivingEntity) || !((LivingEntity)camera.entity()).isSleeping());
   }
}
