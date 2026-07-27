package com.skd.playeranimationcore.animation;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public final class RawAnimation {
   private final List<RawAnimation.Stage> animationList;

   private RawAnimation() {
      this(new ObjectArrayList());
   }

   private RawAnimation(List<RawAnimation.Stage> animationList) {
      this.animationList = animationList;
   }

   public static RawAnimation begin() {
      return new RawAnimation();
   }

   public RawAnimation thenPlay(Animation animation) {
      return this.then(animation, Animation.LoopType.DEFAULT);
   }

   public RawAnimation thenLoop(Animation animation) {
      return this.then(animation, Animation.LoopType.LOOP);
   }

   public RawAnimation thenWait(int ticks) {
      this.animationList.add(new RawAnimation.Stage(Animation.generateWaitAnimation((float)ticks), Animation.LoopType.PLAY_ONCE));
      return this;
   }

   public RawAnimation thenPlayAndHold(Animation animation) {
      return this.then(animation, Animation.LoopType.HOLD_ON_LAST_FRAME);
   }

   public RawAnimation thenPlayXTimes(Animation animation, int playCount) {
      for (int i = 0; i < playCount; i++) {
         this.then(animation, i == playCount - 1 ? Animation.LoopType.DEFAULT : Animation.LoopType.PLAY_ONCE);
      }

      return this;
   }

   public RawAnimation then(Animation animation, Animation.LoopType loopType) {
      this.animationList.add(new RawAnimation.Stage(animation, loopType));
      return this;
   }

   public List<RawAnimation.Stage> getAnimationStages() {
      return this.animationList;
   }

   public static RawAnimation copyOf(RawAnimation other) {
      RawAnimation newInstance = begin();
      newInstance.animationList.addAll(other.animationList);
      return newInstance;
   }

   @Override
   public String toString() {
      return "RawAnimation{" + this.animationList.stream().map(RawAnimation.Stage::toString).collect(Collectors.joining(" -> ")) + "}";
   }

   public int getStageCount() {
      return this.animationList.size();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else {
         return obj != null && this.getClass() == obj.getClass() ? this.hashCode() == obj.hashCode() : false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.animationList);
   }

   public static record Stage(@Nullable Animation animation, Animation.LoopType loopType) {
      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else {
            return obj != null && this.getClass() == obj.getClass() ? this.hashCode() == obj.hashCode() : false;
         }
      }

      @Override
      public String toString() {
         return this.animation == null ? "Invalid animation stage." : this.animation.toString();
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.animation, this.loopType);
      }
   }
}
