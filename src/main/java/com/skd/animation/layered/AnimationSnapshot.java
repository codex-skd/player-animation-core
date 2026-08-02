package com.skd.playeranimationcore.animation.layered;

import com.skd.playeranimationcore.bones.PlayerAnimBone;
import com.skd.playeranimationcore.bones.ToggleablePlayerAnimBone;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public record AnimationSnapshot(Map<String, ToggleablePlayerAnimBone> snapshots) implements IAnimation {
   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      if (this.snapshots.containsKey(bone.getName())) {
         bone.copyOtherBoneIfNotDisabled(this.snapshots.get(bone.getName()));
      }
   }

   @NotNull
   @Override
   public String toString() {
      return "AnimationSnapshot{snapshots=" + this.snapshots + "}";
   }
}
