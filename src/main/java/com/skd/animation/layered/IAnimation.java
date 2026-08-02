package com.skd.playeranimationcore.animation.layered;

import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import org.jetbrains.annotations.NotNull;

public interface IAnimation {
   FirstPersonConfiguration DEFAULT_FIRST_PERSON_CONFIG = new FirstPersonConfiguration();

   default void tick(AnimationData state) {
   }

   default void setupAnim(AnimationData state) {
   }

   boolean isActive();

   void get3DTransform(@NotNull PlayerAnimBone var1);

   default PlayerAnimBone get3DTransform(@NotNull String name) {
      PlayerAnimBone bone = new PlayerAnimBone(name);
      this.get3DTransform(bone);
      return bone;
   }

   @NotNull
   default FirstPersonMode getFirstPersonMode() {
      return FirstPersonMode.NONE;
   }

   @NotNull
   default FirstPersonConfiguration getFirstPersonConfiguration() {
      return DEFAULT_FIRST_PERSON_CONFIG;
   }

   default boolean canRemove() {
      return false;
   }
}
