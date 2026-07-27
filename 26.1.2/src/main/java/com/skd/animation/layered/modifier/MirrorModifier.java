package com.skd.playeranimationcore.animation.layered.modifier;

import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class MirrorModifier extends AbstractModifier {
   public static final Map<String, String> mirrorMap;
   public boolean enabled = true;

   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      if (!this.enabled) {
         super.get3DTransform(bone);
      } else {
         String modelName = bone.getName();
         if (mirrorMap.containsKey(modelName)) {
            modelName = mirrorMap.get(modelName);
         }

         this.transformBone(bone);
         PlayerAnimBone newBone = new PlayerAnimBone(modelName);
         newBone.copyOtherBone(bone);
         super.get3DTransform(newBone);
         this.transformBone(newBone);
         bone.copyOtherBone(newBone);
      }
   }

   @NotNull
   @Override
   public FirstPersonConfiguration getFirstPersonConfiguration() {
      FirstPersonConfiguration configuration = super.getFirstPersonConfiguration();
      return !this.enabled
         ? configuration
         : new FirstPersonConfiguration()
            .setShowLeftArm(configuration.isShowRightArm())
            .setShowRightArm(configuration.isShowLeftArm())
            .setShowLeftItem(configuration.isShowRightItem())
            .setShowRightItem(configuration.isShowLeftItem());
   }

   protected void transformBone(PlayerAnimBone bone) {
      bone.position.x *= -1.0F;
      bone.rotation.y *= -1.0F;
      bone.rotation.z *= -1.0F;
   }

   @Override
   public String toString() {
      return "MirrorModifier{anim=" + this.anim + ", enabled=" + this.enabled + "}";
   }

   static {
      HashMap<String, String> partMap = new HashMap<>();
      partMap.put("left_arm", "right_arm");
      partMap.put("left_leg", "right_leg");
      partMap.put("left_item", "right_item");
      partMap.put("right_arm", "left_arm");
      partMap.put("right_leg", "left_leg");
      partMap.put("right_item", "left_item");
      mirrorMap = Collections.unmodifiableMap(partMap);
   }
}
