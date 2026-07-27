package com.skd.playeranimationcore.neoforge.event;

import com.skd.playeranimationcore.animation.AvatarAnimManager;
import net.minecraft.world.entity.Avatar;
import net.neoforged.bus.api.Event;

public class PlayerAnimationRegisterEvent extends Event {
   private final Avatar avatar;
   private final AvatarAnimManager manager;

   public PlayerAnimationRegisterEvent(Avatar avatar, AvatarAnimManager manager) {
      this.avatar = avatar;
      this.manager = manager;
   }

   public Avatar getAvatar() {
      return this.avatar;
   }

   public AvatarAnimManager getAnimManager() {
      return this.manager;
   }
}
