package com.skd.playeranimationcore.animation;

import com.skd.playeranimationcore.animation.Animation;
import com.skd.playeranimationcore.animation.RawAnimation;
import net.minecraft.resources.Identifier;

public class PlayerRawAnimationBuilder {
   private final RawAnimation rawAnimation = RawAnimation.begin();

   public static PlayerRawAnimationBuilder begin() {
      return new PlayerRawAnimationBuilder();
   }

   public PlayerRawAnimationBuilder thenPlay(Identifier animation) {
      return this.then(animation, Animation.LoopType.DEFAULT);
   }

   public PlayerRawAnimationBuilder thenLoop(Identifier animation) {
      return this.then(animation, Animation.LoopType.LOOP);
   }

   public PlayerRawAnimationBuilder thenWait(int ticks) {
      this.rawAnimation.thenWait(ticks);
      return this;
   }

   public PlayerRawAnimationBuilder thenPlayAndHold(Identifier animation) {
      return this.then(animation, Animation.LoopType.HOLD_ON_LAST_FRAME);
   }

   public PlayerRawAnimationBuilder thenPlayXTimes(Identifier animation, int playCount) {
      for (int i = 0; i < playCount; i++) {
         this.then(animation, i == playCount - 1 ? Animation.LoopType.DEFAULT : Animation.LoopType.PLAY_ONCE);
      }

      return this;
   }

   public PlayerRawAnimationBuilder then(Identifier animation, Animation.LoopType loopType) {
      Animation instance = PlayerAnimResources.getAnimation(animation);
      if (instance == null) {
         throw new IllegalArgumentException("Could not find animation with name: " + animation);
      } else {
         this.rawAnimation.then(instance, loopType);
         return this;
      }
   }

   public RawAnimation build() {
      return this.rawAnimation;
   }
}
