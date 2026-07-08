package com.skd.playeranimationcore.animation.layered.modifier;

import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import com.skd.playeranimationcore.math.Vec3f;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

public class AdjustmentModifier extends AbstractModifier {
   public boolean fadeIn = true;
   public boolean fadeOut = true;
   public boolean enabled = true;
   private AnimationData data;
   protected BiFunction<String, AnimationData, Optional<AdjustmentModifier.PartModifier>> source;
   protected int instructedFadeout = 0;
   private int remainingFadeout = 0;

   public AdjustmentModifier(Function<String, Optional<AdjustmentModifier.PartModifier>> source) {
      this((name, data) -> source.apply(name));
   }

   public AdjustmentModifier(BiFunction<String, AnimationData, Optional<AdjustmentModifier.PartModifier>> source) {
      this.source = source;
   }

   @Override
   public void tick(AnimationData state) {
      super.tick(state);
      this.data = state;
      if (this.remainingFadeout > 0) {
         this.remainingFadeout--;
         if (this.remainingFadeout <= 0) {
            this.instructedFadeout = 0;
         }
      }
   }

   @Override
   public void setupAnim(AnimationData state) {
      super.setupAnim(state);
      this.data = state;
   }

   public void fadeOut(int fadeOut) {
      this.instructedFadeout = fadeOut;
      this.remainingFadeout = fadeOut + 1;
   }

   protected float getFadeOut(float delta) {
      float fadeOut = 1.0F;
      if (this.remainingFadeout > 0 && this.instructedFadeout > 0) {
         float current = Math.max((float)this.remainingFadeout - delta, 0.0F);
         fadeOut = current / (float)this.instructedFadeout;
         return Math.min(fadeOut, 1.0F);
      } else {
         if (this.fadeOut) {
            AnimationController stopTick = this.getController();
            if (stopTick instanceof AnimationController && stopTick.getCurrentAnimation() != null) {
               float stopTickx = stopTick.getCurrentAnimation().animation().length();
               float endTick = stopTick.getCurrentAnimation().animation().data().<Float>get("endTick").orElse(stopTickx);
               float position = -1.0F * (stopTick.getAnimationTicks() - stopTickx);
               float length = stopTickx - endTick;
               if (length > 0.0F) {
                  fadeOut = position / length;
                  fadeOut = Math.min(fadeOut, 1.0F);
               }
            }
         }

         return fadeOut;
      }
   }

   protected float getFadeIn() {
      float fadeIn = 1.0F;
      if (this.fadeIn) {
         AnimationController beginTick = this.getController();
         if (beginTick instanceof AnimationController && beginTick.getCurrentAnimation() != null) {
            float beginTickx = beginTick.getCurrentAnimation().animation().data().<Float>get("beginTick").orElse(0.0F);
            fadeIn = beginTickx > 0.0F ? beginTick.getAnimationTicks() / beginTickx : 1.0F;
            fadeIn = Math.min(fadeIn, 1.0F);
         }
      }

      return fadeIn;
   }

   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      if (!this.enabled) {
         super.get3DTransform(bone);
      } else {
         Optional<AdjustmentModifier.PartModifier> partModifier = this.source.apply(bone.getName(), this.data);
         float fade = this.getFadeIn() * this.getFadeOut(this.data.getPartialTick());
         if (partModifier.isPresent()) {
            super.get3DTransform(bone);
            this.transformBone(bone, partModifier.get(), fade);
         } else {
            super.get3DTransform(bone);
         }
      }
   }

   protected void transformBone(PlayerAnimBone bone, AdjustmentModifier.PartModifier partModifier, float fade) {
      Vec3f pos = partModifier.offset().mul(fade);
      Vec3f rot = partModifier.rotation().mul(fade);
      Vec3f scale = partModifier.scale().mul(fade);
      bone.position.add(pos.x(), pos.y(), pos.z());
      bone.rotation.add(rot.x(), rot.y(), rot.z());
      bone.scale.add(scale.x(), scale.y(), scale.z());
   }

   @Override
   public String toString() {
      return "AdjustmentModifier{anim=" + this.anim + ", enabled=" + this.enabled + "}";
   }

   public static record PartModifier(Vec3f rotation, Vec3f scale, Vec3f offset) {
      public PartModifier(Vec3f rotation, Vec3f offset) {
         this(rotation, Vec3f.ZERO, offset);
      }
   }
}
