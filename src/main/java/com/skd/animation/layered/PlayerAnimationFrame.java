package com.skd.playeranimationcore.animation.layered;

import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import java.util.HashMap;
import java.util.Map.Entry;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerAnimationFrame implements IAnimation {
   protected boolean isActive = false;
   protected PlayerAnimationFrame.PlayerBone head = new PlayerAnimationFrame.PlayerBone();
   protected PlayerAnimationFrame.PlayerBone body = new PlayerAnimationFrame.PlayerBone();
   protected PlayerAnimationFrame.PlayerBone rightArm = new PlayerAnimationFrame.PlayerBone();
   protected PlayerAnimationFrame.PlayerBone leftArm = new PlayerAnimationFrame.PlayerBone();
   protected PlayerAnimationFrame.PlayerBone rightLeg = new PlayerAnimationFrame.PlayerBone();
   protected PlayerAnimationFrame.PlayerBone leftLeg = new PlayerAnimationFrame.PlayerBone();
   protected PlayerAnimationFrame.PlayerBone rightItem = new PlayerAnimationFrame.PlayerBone();
   protected PlayerAnimationFrame.PlayerBone leftItem = new PlayerAnimationFrame.PlayerBone();
   HashMap<String, PlayerAnimationFrame.PlayerBone> parts = new HashMap<>();

   public PlayerAnimationFrame() {
      this.parts.put("head", this.head);
      this.parts.put("body", this.body);
      this.parts.put("right_arm", this.rightArm);
      this.parts.put("left_arm", this.leftArm);
      this.parts.put("right_leg", this.rightLeg);
      this.parts.put("left_leg", this.leftLeg);
      this.parts.put("right_item", this.rightItem);
      this.parts.put("left_item", this.leftItem);
   }

   @Override
   public void tick(AnimationData state) {
      IAnimation.super.tick(state);
   }

   @Override
   public boolean isActive() {
      return this.isActive;
   }

   public void resetPose() {
      for (Entry<String, PlayerAnimationFrame.PlayerBone> entry : this.parts.entrySet()) {
         entry.getValue().setToInitialPose();
      }
   }

   public void enableAll() {
      for (Entry<String, PlayerAnimationFrame.PlayerBone> entry : this.parts.entrySet()) {
         entry.getValue().enableAll();
      }
   }

   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      PlayerAnimationFrame.PlayerBone part = this.parts.get(bone.getName());
      if (part != null) {
         part.applyToBone(bone);
      }
   }

   @Override
   public String toString() {
      return "PlayerAnimationFrame{isActive=" + this.isActive + ", parts=" + this.parts + "}";
   }

   public static class PlayerBone {
      public Float offsetPosX = null;
      public Float offsetPosY = null;
      public Float offsetPosZ = null;
      public Float rotX = null;
      public Float rotY = null;
      public Float rotZ = null;
      public Float scaleX = null;
      public Float scaleY = null;
      public Float scaleZ = null;

      public void setToInitialPose() {
         this.rotX = null;
         this.rotY = null;
         this.rotZ = null;
         this.offsetPosX = null;
         this.offsetPosY = null;
         this.offsetPosZ = null;
         this.scaleX = null;
         this.scaleY = null;
         this.scaleZ = null;
      }

      public void enableAll() {
         this.rotX = 0.0F;
         this.rotY = 0.0F;
         this.rotZ = 0.0F;
         this.offsetPosX = 0.0F;
         this.offsetPosY = 0.0F;
         this.offsetPosZ = 0.0F;
         this.scaleX = 1.0F;
         this.scaleY = 1.0F;
         this.scaleZ = 1.0F;
      }

      public PlayerAnimBone applyToBone(PlayerAnimBone bone) {
         if (this.offsetPosX != null) {
            bone.position.x = this.offsetPosX;
         }

         if (this.offsetPosY != null) {
            bone.position.y = this.offsetPosY;
         }

         if (this.offsetPosZ != null) {
            bone.position.z = this.offsetPosZ;
         }

         if (this.rotX != null) {
            bone.rotation.x = this.rotX;
         }

         if (this.rotY != null) {
            bone.rotation.y = this.rotY;
         }

         if (this.rotZ != null) {
            bone.rotation.z = this.rotZ;
         }

         if (this.scaleX != null) {
            bone.scale.x = this.scaleX;
         }

         if (this.scaleY != null) {
            bone.scale.y = this.scaleY;
         }

         if (this.scaleZ != null) {
            bone.scale.z = this.scaleZ;
         }

         return bone;
      }
   }
}
