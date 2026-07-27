package com.skd.playeranimationcore.animation.layered.modifier;

import com.skd.playeranimationcore.PlayerAnimCore;
import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import com.skd.playeranimationcore.easing.EasingType;
import com.skd.playeranimationcore.enums.FadeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFadeModifier extends AbstractModifier {
   protected int length;
   protected int time;
   protected float tickDelta;
   @Nullable
   protected IAnimation transitionAnimation = null;

   public void setTransitionAnimation(@Nullable IAnimation transitionAnimation) {
      this.transitionAnimation = transitionAnimation;
   }

   protected AbstractFadeModifier(int length) {
      this.length = length;
   }

   @Override
   public boolean isActive() {
      return super.isActive() || this.transitionAnimation != null && this.transitionAnimation.isActive();
   }

   @Override
   public boolean canRemove() {
      return this.getFadeType() == FadeType.FADE_IN && this.calculateProgress(this.tickDelta, null) >= 1.0F;
   }

   @Override
   public void setupAnim(AnimationData state) {
      super.setupAnim(state);
      if (this.transitionAnimation != null) {
         this.transitionAnimation.setupAnim(state);
      }

      this.tickDelta = state.getPartialTick();
   }

   @Override
   public void tick(AnimationData state) {
      super.tick(state);
      if (this.transitionAnimation != null) {
         this.transitionAnimation.tick(state);
      }

      this.time++;
   }

   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      if (this.calculateProgress(this.tickDelta, bone.getName()) > 1.0F) {
         super.get3DTransform(bone);
      } else {
         PlayerAnimBone copy2 = new PlayerAnimBone(bone.getName());
         copy2.copyOtherBone(bone);
         super.get3DTransform(copy2);
         float a = this.getAlpha(copy2.getName(), this.calculateProgress(this.tickDelta, bone.getName()));
         if (this.getFadeType() == FadeType.FADE_IN && this.transitionAnimation != null && this.transitionAnimation.isActive()) {
            this.transitionAnimation.get3DTransform(bone);
         }

         bone.scale(1.0F - a).add(copy2.scale(a));
      }
   }

   protected float calculateProgress(float f, String boneName) {
      float actualTime = (float)this.time + f;
      if (this.getFadeType() == FadeType.FADE_IN) {
         return actualTime / (float)this.length;
      } else {
         float endTime = this.getEndTime(boneName);
         if (actualTime >= endTime) {
            return 0.0F;
         } else {
            return actualTime < endTime - (float)this.length ? 1.0F : (endTime - actualTime) / (float)this.length;
         }
      }
   }

   protected abstract float getAlpha(String var1, float var2);

   protected abstract FadeType getFadeType();

   protected float getEndTime(String boneName) {
      AnimationController var3 = this.getController();
      if (var3 instanceof AnimationController && var3.getCurrentAnimation() != null) {
         return var3.getCurrentAnimation().animation().length();
      } else {
         PlayerAnimCore.LOGGER
            .debug("The fade out modifier doesn't work on animations that aren't AnimationController instances! Please override the getEndTime method.");
         return 0.0F;
      }
   }

   public static AbstractFadeModifier standardFadeIn(int length, EasingType ease) {
      return standardFadeIn(length, ease, null);
   }

   public static AbstractFadeModifier standardFadeIn(int length, EasingType ease, @Nullable Float easingVariable) {
      return standardFade(length, ease, easingVariable, FadeType.FADE_IN);
   }

   public static AbstractFadeModifier functionalFadeIn(int length, AbstractFadeModifier.EasingFunction function) {
      return functionalFade(length, function, FadeType.FADE_IN);
   }

   public static AbstractFadeModifier standardFadeOut(int length, EasingType ease) {
      return standardFadeOut(length, ease, null);
   }

   public static AbstractFadeModifier standardFadeOut(int length, EasingType ease, @Nullable Float easingVariable) {
      return standardFade(length, ease, easingVariable, FadeType.FADE_OUT);
   }

   public static AbstractFadeModifier functionalFadeOut(int length, AbstractFadeModifier.EasingFunction function) {
      return functionalFade(length, function, FadeType.FADE_OUT);
   }

   public static AbstractFadeModifier standardFade(int length, final EasingType ease, @Nullable final Float easingVariable, final FadeType fadeType) {
      return new AbstractFadeModifier(length) {
         @Override
         protected float getAlpha(String boneName, float progress) {
            return (Float)ease.buildTransformer(easingVariable).apply(progress);
         }

         @Override
         protected FadeType getFadeType() {
            return fadeType;
         }
      };
   }

   public static AbstractFadeModifier functionalFade(int length, final AbstractFadeModifier.EasingFunction function, final FadeType fadeType) {
      return new AbstractFadeModifier(length) {
         @Override
         protected float getAlpha(String boneName, float progress) {
            return function.ease(boneName, progress);
         }

         @Override
         protected FadeType getFadeType() {
            return fadeType;
         }
      };
   }

   @Override
   public String toString() {
      return "AbstractFadeModifier{anim=" + this.anim + ", length=" + this.length + ", transitionAnimation=" + this.transitionAnimation + "}";
   }

   @FunctionalInterface
   public interface EasingFunction {
      float ease(String var1, float var2);
   }
}
