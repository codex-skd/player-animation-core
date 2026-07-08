package com.skd.playeranimationcore.animation.layered;

import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnimationContainer<T extends IAnimation> implements IAnimation {
   @Nullable
   protected T anim;

   public AnimationContainer(@Nullable T anim) {
      this.anim = anim;
   }

   public AnimationContainer() {
      this.anim = null;
   }

   public void setAnim(@Nullable T newAnim) {
      this.anim = newAnim;
   }

   @Nullable
   public T getAnim() {
      return this.anim;
   }

   @Override
   public boolean isActive() {
      return this.anim != null && this.anim.isActive();
   }

   @Override
   public void tick(AnimationData state) {
      if (this.anim != null) {
         this.anim.tick(state);
      }
   }

   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      if (this.anim != null) {
         this.anim.get3DTransform(bone);
      }
   }

   @Override
   public void setupAnim(AnimationData state) {
      if (this.anim != null) {
         this.anim.setupAnim(state);
      }
   }

   @NotNull
   @Override
   public FirstPersonMode getFirstPersonMode() {
      return this.anim != null ? this.anim.getFirstPersonMode() : FirstPersonMode.NONE;
   }

   @NotNull
   @Override
   public FirstPersonConfiguration getFirstPersonConfiguration() {
      return this.anim != null ? this.anim.getFirstPersonConfiguration() : IAnimation.super.getFirstPersonConfiguration();
   }

   @Override
   public String toString() {
      return "AnimationContainer{anim=" + this.anim + "}";
   }
}
